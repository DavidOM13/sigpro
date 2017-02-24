var app = angular.module('programaController', [ 'ngTouch' ]);

app.controller('programaController',['$scope','$http','$interval','i18nService','Utilidades','$routeParams','$window','$location','$route','uiGridConstants','$mdDialog','$uibModal','$q',
	function($scope, $http, $interval,i18nService,$utilidades,$routeParams,$window,$location,$route,uiGridConstants,$mdDialog,$uibModal,$q) {

	var mi = this;
	i18nService.setCurrentLang('es');
	
	$window.document.title = $utilidades.sistema_nombre+' - Programas';
	
	mi.programa = null;
	mi.esNuevo = false;
	mi.campos = {};
	mi.esColapsado = false;
	mi.mostrarcargando=true;
	mi.paginaActual = 1;
	mi.programatipoid = "";
	mi.programatiponombre="";
	mi.camposdinamicos = {};
	mi.formatofecha = 'dd/MM/yyyy';
	mi.numeroMaximoPaginas = $utilidades.numeroMaximoPaginas;
	mi.elementosPorPagina = $utilidades.elementosPorPagina;
	mi.totalProgramas = 0;

	mi.columnaOrdenada=null;
	mi.ordenDireccion = null;

	mi.filtros = [];
	mi.orden = null;

	mi.fechaOptions = {
			formatYear : 'yy',
			maxDate : new Date(2050, 12, 31),
			minDate : new Date(1990, 1, 1),
			startingDay : 1
	};

	mi.gridOpciones = {
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect: false,
		modifierKeysToMultiSelect: false,
		noUnselect: true,
		enableFiltering: true,
		enablePaginationControls: false,
	    paginationPageSize: $utilidades.elementosPorPagina,
	    useExternalFiltering: true,
	    useExternalSorting: true,
		columnDefs : [
			{ name: 'id', width: 60, displayName: 'ID', cellClass: 'grid-align-right', type: 'number', enableFiltering: false },
			{ name: 'nombre',  displayName: 'Nombre',cellClass: 'grid-align-left',
				filterHeaderTemplate: '<div class="ui-grid-filter-container"><input type="text" style="width: 90%;" ng-model="grid.appScope.programac.filtros[\'nombre\']" ng-keypress="grid.appScope.programac.filtrar($event)" ></input></div>'
			},
			{ name : 'programatipo',    displayName : 'Tipo programa' ,cellClass: 'grid-align-left', enableFiltering: false, enableSorting: false },
			{ name: 'usuarioCreo',  displayName: 'Usuario Creación',
				filterHeaderTemplate: '<div class="ui-grid-filter-container"><input type="text"style="width: 90%;" ng-model="grid.appScope.programac.filtros[\'usuario_creo\']"  ng-keypress="grid.appScope.programac.filtrar($event)" ></input></div>'
			},
		    { name: 'fechaCreacion',  displayName: 'Fecha Creación', cellClass: 'grid-align-right', type: 'date', cellFilter: 'date:\'dd/MM/yyyy\'',
				filterHeaderTemplate: '<div class="ui-grid-filter-container"><input type="text" style="width: 90%;" ng-model="grid.appScope.programac.filtros[\'fecha_creacion\']" ng-keypress="grid.appScope.programac.filtrar($event)"  ></input></div>'
		    }
		],
		onRegisterApi: function(gridApi) {
			mi.gridApi = gridApi;
			gridApi.selection.on.rowSelectionChanged($scope,function(row) {
				mi.programa = row.entity;
			});

			gridApi.core.on.sortChanged( $scope, function ( grid, sortColumns ) {
				if(sortColumns.length==1){
					grid.appScope.programac.columnaOrdenada=sortColumns[0].field;
					grid.appScope.programac.ordenDireccion = sortColumns[0].sort.direction;

					grid.appScope.programac.cargarTabla(grid.appScope.programac.paginaActual);
				}
				else if(sortColumns.length>1){
					sortColumns[0].unsort();
				}
				else{
					if(grid.appScope.programac.columnaOrdenada!=null){
						grid.appScope.programac.columnaOrdenada=null;
						grid.appScope.programac.ordenDireccion=null;
					}
				}
			} );

			if($routeParams.reiniciar_vista=='rv'){
				mi.guardarEstado();
				mi.obtenerTotalProgramas();
		    }
		    else{
		    	  $http.post('/SEstadoTabla', { action: 'getEstado', grid:'programa', t: (new Date()).getTime()}).then(function(response){
		    		  if(response.data.success && response.data.estado!='')
		    			  mi.gridApi.saveState.restore( $scope, response.data.estado);
			    	  mi.gridApi.colMovable.on.columnPositionChanged($scope, mi.guardarEstado);
				      mi.gridApi.colResizable.on.columnSizeChanged($scope, mi.guardarEstado);
				      mi.gridApi.core.on.columnVisibilityChanged($scope, mi.guardarEstado);
				      mi.gridApi.core.on.sortChanged($scope, mi.guardarEstado);
				      mi.obtenerTotalProgramas();
				  });
		    }
		}
	};


	mi.cargarTabla = function(pagina){
		mi.mostrarcargando=true;
		$http.post('/SPrograma', { accion: 'getProgramaPagina', pagina: pagina,
			numeroprograma:  $utilidades.elementosPorPagina, filtro_nombre: mi.filtros['nombre'],
			filtro_usuario_creo: mi.filtros['usuario_creo'], filtro_fecha_creacion: mi.filtros['fecha_creacion'],
			columna_ordenada: mi.columnaOrdenada, orden_direccion: mi.ordenDireccion, t:moment().unix()
			}).success(
				function(response) {
					mi.entidades = response.programas;
					mi.gridOpciones.data = mi.entidades;
					mi.mostrarcargando = false;
				});
	}


	mi.guardar = function(esvalido){
		for (campos in mi.camposdinamicos) {
			if (mi.camposdinamicos[campos].tipo === 'fecha') {
				mi.camposdinamicos[campos].valor_f = mi.camposdinamicos[campos].valor!=null ? moment(mi.camposdinamicos[campos].valor).format('DD/MM/YYYY') : "";
			}
		}
		if(mi.programa!=null && mi.programa.nombre!=null){
			var param_data = {
				accion : 'guardar',
				id: mi.programa.id,
				nombre: mi.programa.nombre,
				descripcion:mi.programa.descripcion,
				programatipoid: mi.programatipoid,
				esnuevo: mi.esNuevo,
				datadinamica : JSON.stringify(mi.camposdinamicos),
				t:moment().unix()
			};
			$http.post('/SPrograma',param_data).then(
				function(response) {
					if (response.data.success) {
						mi.programa.id = response.data.id;
						$utilidades.mensaje('success','Programa '+(mi.esNuevo ? 'creado' : 'guardado')+' con éxito');
						mi.obtenerTotalProgramas();
						mi.esNuevo = false;
					}else
						$utilidades.mensaje('danger','Error al '+(mi.esNuevo ? 'crearo' : 'guardar')+' el Programa');
			});

		}else
			$utilidades.mensaje('warning','Debe de llenar todos los campos obligatorios');
	 }

	mi.borrar = function(ev) {
		if(mi.programa !=null && mi.programa.id!=null){
			var confirm = $mdDialog.confirm()
	          .title('Confirmación de borrado')
	          .textContent('¿Desea borrar el programa "'+mi.programa.nombre+'"?')
	          .ariaLabel('Confirmación de borrado')
	          .targetEvent(ev)
	          .ok('Borrar')
	          .cancel('Cancelar');
			
			$mdDialog.show(confirm).then(function() {
				$http.post('/SPrograma', {
					accion: 'borrarPrograma',
					id: mi.programa.id,
					t:moment().unix()
				}).success(function(response){
					if(response.success){
						$utilidades.mensaje('success','Programa borrado con éxito');
						mi.programa = null;
						mi.obtenerTotalProgramas();
					}
					else
						$utilidades.mensaje('danger','Error al borrar el Programa');
				
				 }, function() {
					    
				    });
			
			
			});
		}
		else
			$utilidades.mensaje('warning','Debe seleccionar el Programa que desea borrar');
	};

	mi.nuevo = function (){
		mi.programatipoid = "";
		mi.programatiponombre="";
		mi.esColapsado = true;
		mi.programa = {};
		mi.esNuevo = true;
		mi.camposdinamicos = {};
		mi.gridApi.selection.clearSelectedRows();
	};

	mi.editar = function() {
		if(mi.programa!=null && mi.programa.id!=null){
			mi.programatipoid = mi.programa.programatipoid;
			mi.programatiponombre=mi.programa.programatipo;
			mi.esColapsado = true;
			mi.esNuevo = false;

			var parametros = {
					accion: 'getProgramaPropiedadPorTipo',
					idPrograma: mi.programa!=''? mi.programa.id:0,
				    idProgramaTipo: mi.programatipoid,
				    t:moment().unix()
			}
			$http.post('/SProgramaPropiedad', parametros).then(function(response){
				mi.camposdinamicos = response.data.programapropiedades
				for (campos in mi.camposdinamicos) {
					switch (mi.camposdinamicos[campos].tipo){
					case "fecha":
						mi.camposdinamicos[campos].valor = (mi.camposdinamicos[campos].valor!='') ? moment(mi.camposdinamicos[campos].valor,'DD/MM/YYYY').toDate() : null;
						break;
					case "entero":
						mi.camposdinamicos[campos].valor = (mi.camposdinamicos[campos].valor!='') ? Number(mi.camposdinamicos[campos].valor) : null;
						break;
					case "decimal":
						mi.camposdinamicos[campos].valor = (mi.camposdinamicos[campos].valor!='') ? Number(mi.camposdinamicos[campos].valor) : null;
						break;
					case "booleano":
						mi.camposdinamicos[campos].valor = mi.camposdinamicos[campos].valor == 'true' ? true : false;
						break;
					}
				}
			});

		}
		else
			$utilidades.mensaje('warning','Debe seleccionar el Programa que desea editar');
	}

	mi.irATabla = function() {
		mi.esColapsado=false;
		mi.esNuevo = false;
	}

	mi.guardarEstado=function(){
		var estado = mi.gridApi.saveState.save();
		var tabla_data = { action: 'guardaEstado', grid:'programa', estado: JSON.stringify(estado) };
		$http.post('/SEstadoTabla', tabla_data).then(function(response){

		});
	}

	mi.cambioPagina=function(){
		mi.cargarTabla(mi.paginaActual);
	}

	mi.reiniciarVista=function(){
		if($location.path()=='/programa/rv')
			$route.reload();
		else
			$location.path('/programa/rv');
	}

	mi.abrirPopupFecha = function(index) {
		mi.camposdinamicos[index].isOpen = true;
	};

	mi.filtrar = function(evt){
		if(evt.keyCode==13){
			mi.obtenerTotalProgramas();
		}
	};

	mi.obtenerTotalProgramas = function(){
		$http.post('/SPrograma', { accion: 'numeroProgramas',t:moment().unix(),
			filtro_nombre: mi.filtros['nombre'],
			filtro_usuario_creo: mi.filtros['usuario_creo'], filtro_fecha_creacion: mi.filtros['fecha_creacion']  } ).then(
				function(response) {
					mi.totalProgramas = response.data.totalprogramas;
					mi.paginaActual = 1;
					mi.cargarTabla(mi.paginaActual);
		});
	};

	mi.irAProyectos=function(programaid){
		if(mi.programa!=null){
			$location.path('/proyecto/'+ programaid );
		}
	};

	mi.llamarModalBusqueda = function(servlet, accionServlet, datosCarga,columnaId,columnaNombre) {
		var resultado = $q.defer();
		var modalInstance = $uibModal.open({
			animation : 'true',
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'buscarPorPrograma.jsp',
			controller : 'buscarPorPrograma',
			controllerAs : 'modalBuscar',
			backdrop : 'static',
			size : 'md',
			resolve : {
				$servlet : function() {
					return servlet;
				},
				$accionServlet : function() {
					return accionServlet;
				},
				$datosCarga : function() {
					return datosCarga;
				},
				$columnaId : function() {
					return columnaId;
				},
				$columnaNombre : function() {
					return columnaNombre;
				}
			}
		});

		modalInstance.result.then(function(itemSeleccionado) {
			resultado.resolve(itemSeleccionado);
		});
		return resultado.promise;
	};

	mi.buscarProgramaTipo = function() {
		var resultado = mi.llamarModalBusqueda('/SProgramaTipo', {
			accion : 'numeroProgramaTipos'
		}, function(pagina, elementosPorPagina) {
			return {
				accion : 'getProgramaTipoPagina',
				pagina : pagina,
				registros : elementosPorPagina
			};
		},'id','nombre');

		resultado.then(function(itemSeleccionado) {
			mi.programatipoid= itemSeleccionado.id;
			mi.programatiponombre = itemSeleccionado.nombre;

			var parametros = {
					accion: 'getProgramaPropiedadPorTipo',
					idPrograma: mi.programa!=''? mi.programa.id:0,
					idProgramaTipo: itemSeleccionado.id,
					t:moment().unix()
			}

			$http.post('/SProgramaPropiedad', parametros).then(function(response){
				mi.camposdinamicos = response.data.programapropiedades;
				for (campos in mi.camposdinamicos) {
					switch (mi.camposdinamicos[campos].tipo){
						case "fecha":
							mi.camposdinamicos[campos].valor = (mi.camposdinamicos[campos].valor!='') ? moment(mi.camposdinamicos[campos].valor,'DD/MM/YYYY').toDate() : null;
							break;
						case "entero":
							mi.camposdinamicos[campos].valor = (mi.camposdinamicos[campos].valor!='') ? Number(mi.camposdinamicos[campos].valor) : null;
							break;
						case "decimal":
							mi.camposdinamicos[campos].valor = (mi.camposdinamicos[campos].valor!='') ? Number(mi.camposdinamicos[campos].valor) : null;
							break;
						case "booleano":
							mi.camposdinamicos[campos].valor = mi.camposdinamicos[campos].valor == 'true' ? true : false;
							break;
					}
				}
			});
		});
	};

	
} ]);

app.controller('buscarPorPrograma', [ '$uibModalInstance',
	'$scope', '$http', '$interval', 'i18nService', 'Utilidades',
	'$timeout', '$log', '$servlet', '$accionServlet', '$datosCarga',
	'$columnaId','$columnaNombre',buscarPorPrograma ]);

function buscarPorPrograma($uibModalInstance, $scope, $http, $interval,
	i18nService, $utilidades, $timeout, $log, $servlet,$accionServlet,$datosCarga,$columnaId,$columnaNombre) {

	var mi = this;

	mi.totalElementos = 0;
	mi.paginaActual = 1;
	mi.numeroMaximoPaginas = 5;
	mi.elementosPorPagina = 9;

	mi.mostrarCargando = false;
	mi.data = [];

	mi.itemSeleccionado = null;
	mi.seleccionado = false;

	$http.post($servlet, $accionServlet).success(function(response) {
		for ( var key in response) {
			mi.totalElementos = response[key];
		}
		mi.cargarData(1);
	});

	mi.opcionesGrid = {
		data : mi.data,
		columnDefs : [ {
			displayName : 'ID',
			name : $columnaId,
			cellClass : 'grid-align-right',
			type : 'number',
			width : 70
		}, {
			displayName : 'Nombre',
			name : $columnaNombre,
			cellClass : 'grid-align-left'
		} ],
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		modifierKeysToMultiSelect : false,
		noUnselect : false,
		enableFiltering : true,
		enablePaginationControls : false,
		paginationPageSize : 5,
		onRegisterApi : function(gridApi) {
			mi.gridApi = gridApi;
			mi.gridApi.selection.on.rowSelectionChanged($scope,
					mi.seleccionarTipoRiesgo);
		}
	}

	mi.seleccionarTipoRiesgo = function(row) {
		mi.itemSeleccionado = row.entity;
		mi.seleccionado = row.isSelected;
	};

	mi.cargarData = function(pagina) {
		mi.mostrarCargando = true;
		$http.post($servlet, $datosCarga(pagina, mi.elementosPorPagina)).then(
				function(response) {
					if (response.data.success) {

						for ( var key in response.data) {
							if (key != 'success')
								mi.data = response.data[key];
						}
						mi.opcionesGrid.data = mi.data;

						mi.mostrarCargando = false;
					}
				});
	};

	mi.cambioPagina = function() {
		mi.cargarData(mi.paginaActual);
	}

	mi.ok = function() {
		if (mi.seleccionado) {
			$uibModalInstance.close(mi.itemSeleccionado);
		} else {
			$utilidades.mensaje('warning', 'Debe seleccionar una fila');
		}
	};

	mi.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}