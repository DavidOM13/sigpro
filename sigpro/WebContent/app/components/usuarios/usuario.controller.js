var app = angular.module('usuarioController', [ 'ngTouch', 'ui.grid.edit','indeterminate' ,'treeControl']);

app.controller(
 'usuarioController',
 [
  '$scope',
  '$http',
  '$interval',
  '$q',
  'i18nService',
  'Utilidades',
  '$routeParams',
  'uiGridConstants',
  '$mdDialog',
  '$window',
  '$location',
  '$route',
  '$q',
  '$uibModal',
  'dialogoConfirmacion', 
  function($scope, $http, $interval, $q,i18nService,$utilidades,$routeParams,uiGridConstants,$mdDialog, $window, $location, $route,$q,$uibModal, $dialogoConfirmacion) {
	var mi=this;
	$window.document.title =$utilidades.sistema_nombre+' - Usuario';
	mi.colaboradorSeleccionado =false;
	i18nService.setCurrentLang('es');
	mi.mostrarcargando=true;
	mi.cargandoPermisos=false;
	mi.entityselected = null;
	mi.esNuevo = false;
	mi.unidadEjecutoraUsuario="";
	mi.cooperanteUsuario="";
	mi.rolUsuario="";
	mi.cargarArbol=false;
	mi.cargandoPermisos=false;
	mi.paginaActual = 1;
	mi.numeroMaximoPaginas = $utilidades.numeroMaximoPaginas;
	mi.elementosPorPagina = $utilidades.elementosPorPagina;
	mi.permisoSelected={id:"",nombre:"", descripcion:""};
	mi.usuariosSelected={usuario:"", email:"",password:"", usuarioCreo:"", fechaCreacion:"", usuarioActualizo:"", fechaActualizacion:"", colaborador:""};
	mi.claves={password1:"", password2:""};
	mi.nuevosPermisos=[];
	mi.nuevosPrestamos=[];
	mi.prestamosEliminados=[];
	mi.prestamosNuevos=[];
	mi.prestamosAsignados=[];
	mi.permisosEliminados=[];
	var usuarioMail ="";
	mi.permisosAsignados=[];
	mi.verAreaPermisos=false;
	mi.colaborador={};
	mi.mensajeActualizado={mensaje:"buscar colaborador"};
	mi.cambioPassword= false;
	mi.mostrarCambioPassword = false;
	var passwordLocal="";
	mi.tieneColaborador=false;
	mi.edicionPermisos=false;
	mi.filtros=[];
	var check_mess=false;
	mi.tipoUsuario={id:"",nombre:"",grupo:""};
	mi.nombreUnidadEjecutora="";
	mi.nombreCooperante="";
	mi.rolUsuario=0;
	
	mi.treedata=[];
	mi.expanded=[];
	mi.nodo_seleccionado;
	mi.tree_options={
			injectClasses:{
				liSelected: 'tree-node-noselected',
				labelSelected: 'tree-node-noselected',
			}
		};			
	
	mi.editarElemento = function (event) {
        var filaId = angular.element(event.toElement).scope().rowRenderIndex;
        mi.gridApi.selection.selectRow(mi.gridOptions.data[filaId]);
        mi.usuariosSelected=mi.gridOptions.data[filaId];
        mi.editarUsuario();
    };
	
	mi.gridOptions = {
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		paginationPageSizes : [ 25, 50, 75 ],
		paginationPageSize : 25,
		enableFiltering: true,
		data : [],
		useExternalFiltering: true,
		useExternalSorting: true,
		rowTemplate: '<div ng-dblclick="grid.appScope.usuarioc.editarElemento($event)" ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.uid" ui-grid-one-bind-id-grid="rowRenderIndex + \'-\' + col.uid + \'-cell\'" class="ui-grid-cell ng-scope ui-grid-disable-selection grid-align-right" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }" role="gridcell" ui-grid-cell="" ></div>',
		columnDefs : [ {
			name : 'Usuario',
			cellClass : 'grid-align-left',
			field : 'usuario',
			filterHeaderTemplate: '<div class="ui-grid-filter-container"><input type="text" style="width: 90%;" ng-model="grid.appScope.usuarioc.filtros[\'usuario\']" ng-keypress="grid.appScope.usuarioc.filtrar($event)" style="width:175px;"></input></div>'
		}, {
			name : 'Correo',
			cellClass : 'grid-align-left',
			field : 'email',
			filterHeaderTemplate: '<div class="ui-grid-filter-container"><input type="text" style="width: 90%;" ng-model="grid.appScope.usuarioc.filtros[\'email\']" ng-keypress="grid.appScope.usuarioc.filtrar($event)" style="width:175px;"></input></div>'
		}, {
			name: 'Usuario creo',
			cellClass : 'grid-align-left',
			field: 'usuarioCreo',
			filterHeaderTemplate: '<div class="ui-grid-filter-container"><input type="text" style="width: 90%;" ng-model="grid.appScope.usuarioc.filtros[\'usuario_creo\']" ng-keypress="grid.appScope.usuarioc.filtrar($event)" style="width:175px;"></input></div>'
		}, {
			name : 'Fecha creación',
			cellClass : 'grid-align-left',
			field : 'fechaCreacion',
			filterHeaderTemplate: '<div class="ui-grid-filter-container"><input type="text" style="width: 90%;" ng-model="grid.appScope.usuarioc.filtros[\'fecha_creacion\']" ng-keypress="grid.appScope.usuarioc.filtrar($event)" style="width:175px;"></input></div>'
		},{
			name: 'Usuario actualizo',
			cellClass : 'grid-align-left',
			field: 'usuarioActualizo',
			enableFiltering: false
		},{
			name: 'Fecha actualizacion',
			cellClass : 'grid-align-left',
			field: 'fechaActualizacion',
			enableFiltering: false
		}
		],

	};
	mi.cargarTabla=function(pagina){
		$http.post('/SUsuario',
				{ accion : 'getUsuarios',  pagina: pagina, numeroUsuarios: mi.elementosPorPagina,filtro_usuario: mi.filtros['usuario'],filtro_email: mi.filtros['email'],
			filtro_usuario_creo: mi.filtros['usuario_creo'], filtro_fecha_creacion: mi.filtros['fecha_creacion'], t: (new Date()).getTime() }).success(function(data) {
				mi.gridOptions.data =  data.usuarios;
				mi.mostrarcargando=false;
				
		});
	};

	mi.gridOptions.multiSelect = false;
	mi.gridOptions.modifierKeysToMultiSelect = false;
	mi.gridOptions.noUnselect = true;
	mi.gridOptions.onRegisterApi = function(gridApi) {
		mi.gridApi = gridApi;
		gridApi.selection.on
		.rowSelectionChanged(
			$scope,
			function(row) {
			var msg = 'row selected '
			+ row;
			mi.usuariosSelected = row.entity;
			usuarioMail= mi.usuariosSelected.email;
		});
		if($routeParams.reiniciar_vista=='rv'){
			mi.guardarEstado();
	    }
	    else{
	    	  $http.post('/SEstadoTabla', { action: 'getEstado', grid:'usuarios', t: (new Date()).getTime()}).then(function(response){
		      if(response.data.success && response.data.estado!='')
		    	  mi.gridApi.saveState.restore( $scope, response.data.estado);
		    	  mi.gridApi.colMovable.on.columnPositionChanged($scope, mi.guardarEstado);
			      mi.gridApi.colResizable.on.columnSizeChanged($scope, mi.guardarEstado);
			      mi.gridApi.core.on.columnVisibilityChanged($scope, mi.guardarEstado);
			      mi.gridApi.core.on.sortChanged($scope, mi.guardarEstado);
			  });
	    }
	};


	mi.redireccionSinPermisos=function(){
		$window.location.href = '/main.jsp#!/forbidden';		
	}

	mi.cancelar = function() {
		mi.colaboradorSeleccionado=false;
		mi.verAreaPermisos=false;
//		mi.nombreUnidadEjecutora="";
//		mi.nombreCooperante="";
//		mi.tipoUsuario={id:"",nombre:"",grupo:""};
		mi.isCollapsed = false;
		mi.cambioPassword= false;
		mi.mostrarCambioPassword = false;
		mi.tieneColaborador=false;
		mi.edicionPermisos=false;
		mi.cargandoPermisos=false; 
//		mi.prestamosAsignados=[];
//		mi.unidadEjecutoraUsuario="";
//		mi.cooperanteUsuario="";
//		mi.rolUsuario="";
//		mi.prestamosNuevos=[];
//		mi.prestamosEliminados=[];
//		mi.usuariosSelected={usuario:"", email:"",password:"", usuarioCreo:"", fechaCreacion:"", usuarioActualizo:"", fechaActualizacion:"", colaborador:""};
	}

	mi.verPermisos=function(){
		mi.verAreaPermisos=!mi.verAreaPermisos;
	}
	mi.nuevoUsuario=function(){
		mi.treedata=[];
		mi.expanded=[];
		mi.gridApi.selection.clearSelectedRows();
		mi.cargarArbol=false;
		mi.claves.password1="";
		mi.claves.password2="";
		mi.permisosAsignados=[];
		mi.usuariosSelected={usuario:"", email:"",password:"", usuarioCreo:"", fechaCreacion:"", usuarioActualizo:"", fechaActualizacion:"", colaborador:""};
		mi.isCollapsed = true;
		mi.entityselected = null;
		mi.esNuevo = true;
		$utilidades.setFocus(document.getElementById("mail"));
		$http.post('/SProyecto',
				{ accion: 'controlArbolTodosProyectos', usuario: '', t: (new Date()).getTime() }).success(
			function(response) {
				mi.treedata=response.proyectos;
				if(mi.treedata.id==0){
					mi.expanded.push(mi.treedata.children[0]);
					mi.setParentNode(mi.treedata);
					mi.cargarArbol=true;
				}
			});
	};
	function getIdsPrestamos(prestamos){
		var ids=[]
		for(var i =0; i<prestamos.length;i++){
			ids.push(prestamos[i].id);
		}
		return ids;
	};
	
	mi.guardarUsuario=function(){
		var asignaciones =mi.getChecksArbol(mi.treedata);
		var estructuras_permisos = mi.getChecksArbol(mi.treedata);
		if(mi.esNuevo){
			if(mi.claves.password1!=="" && mi.claves.password2!=="" && mi.usuariosSelected.usuario!=="" && mi.usuariosSelected.email!=="" && mi.nombreUnidadEjecutora!=="SIN UNIDAD EJECUTORA"){
				if(validarEmail(mi.usuariosSelected.email)){
					if(mi.claves.password1===mi.claves.password2){
						mi.usuariosSelected.password= mi.claves.password1;						
						var str = mi.getChecksArbol(mi.treedata);
						console.log(str);
						$http.post('/SUsuario',
								{
									accion: 'guardarUsuario',
									usuario:mi.usuariosSelected.usuario,
									email:mi.usuariosSelected.email,
									password:mi.usuariosSelected.password,
									permisos:JSON.stringify(mi.nuevosPermisos),
									estructuraAsignada: estructuras_permisos,
									rol: mi.tipoUsuario.id,
									esnuevo:true,
									t: (new Date()).getTime()

								}
								).success(
									function(data) {
										if(data.success){
											mi.paginaActual=1;
											$utilidades.mensaje('success','Usuario creado exitosamente');
											check_mess=true;
											mi.cargarTabla(mi.paginaActual);
											mi.nuevosPermisos=[];
											mi.esNuevo=false;
											mi.tipoUsuarioRol=mi.tipoUsuario.nombre;
										}
							});
					}else{
						$utilidades.mensaje('danger','Las contraseñas no coinciden');
					}
				}else{
					$utilidades.mensaje('danger','Ingrese un correo electrónico válido');
				}

			}else{
				$utilidades.mensaje('danger','Existen campos obligatorios vacíos');
			}
		}else{
			if(mi.usuariosSelected.email!==""){
				if(validarEmail(mi.usuariosSelected.email)){
					if(usuarioMail===mi.usuariosSelected.email && passwordLocal=== mi.usuariosSelected.password && mi.prestamosNuevos.length==0 && mi.prestamosEliminados.length==0){
						var str = mi.getChecksArbol(mi.treedata);
							$http.post('/SUsuario',
									{
										accion: 'actualizarPermisos',
										usuario:mi.usuariosSelected.usuario,
										permisosNuevos:JSON.stringify(mi.nuevosPermisos),
										permisosEliminados:JSON.stringify(mi.permisosEliminados),
										estructuraAsignada: estructuras_permisos,
										t: (new Date()).getTime()
									}).success(
										function(data) {
											if(data.success){
												mi.cargarTabla(mi.paginaActual);
												mi.nuevosPermisos=[];
												mi.permisosEliminados=[];
												if(mi.usuariosSelected.password!==passwordLocal){
													$http.post('/SUsuario', {accion: 'cambiarPassword' , usuario: mi.usuariosSelected.usuario,	password:mi.usuariosSelected.password, t: (new Date()).getTime()}).success(
															function(response) {
																if(response.success){
																	 $utilidades.mensaje('success', 'Cambio de contraseña exitoso');
																}else{
																	$utilidades.mensaje('danger', 'No se pudo cambiar la contraseña.');
																}
													});
												}
												$utilidades.mensaje('success', 'Actualización de permisos exitosa');
											}
								});
						

					}else{
						$http.post('/SUsuario',
								{
									accion: 'guardarUsuario',
									usuario:mi.usuariosSelected.usuario,
									email:mi.usuariosSelected.email,
									estructuraAsignada:asignaciones ,
									prestamosEliminados: JSON.stringify(mi.prestamosEliminados),
									estructuraAsignada: estructuras_permisos,
									t: new Date().getTime(),
									esnuevo:false
								}).success(
									function(data) {
										if(data.success){
											if(mi.nuevosPermisos.length==0 && mi.permisosEliminados.length==0){
												mi.cargarTabla(mi.paginaActual);
												if(mi.usuariosSelected.password!==passwordLocal){
													$http.post('/SUsuario', {accion: 'cambiarPassword' , usuario: mi.usuariosSelected.usuario,	password:mi.usuariosSelected.password, t: (new Date()).getTime()}).success(
															function(response) {
																if(response.success){
																	 $utilidades.mensaje('success', 'Actualizacion de datos exitosa');
																}else{
																	$utilidades.mensaje('danger', 'No se pudo cambiar la contraseña');
																}
													});
												}
												if(!check_mess){
													$utilidades.mensaje('success', 'Usuario creado exitosamente');
												}
												
											}else{
												$http.post('/SUsuario',
														{
															accion: 'actualizarPermisos',
															usuario:mi.usuariosSelected.usuario,
															permisosNuevos:JSON.stringify(mi.nuevosPermisos),
															permisosEliminados:JSON.stringify(mi.permisosEliminados),
															estructuraAsignada: estructuras_permisos,
															t: (new Date()).getTime()
														}).success(
															function(data) {
																if(data.success){
																	mi.nuevosPermisos=[];
																	mi.permisosEliminados=[];
																	if(mi.usuariosSelected.password!==passwordLocal){
																		$http.post('/SUsuario', {accion: 'cambiarPassword' , usuario: mi.usuariosSelected.usuario,	password:mi.usuariosSelected.password, t: (new Date()).getTime()}).success(
																				function(response) {
																					if(response.success){
																						mi.paginaActual=1;
																						$utilidades.mensaje('success','Información actualizada exitosamente.');
																						mi.isCollapsed = false;
																						mi.cargarTabla(mi.paginaActual);
																					}else{
																						$utilidades.mensaje('danger', 'No se pudo cambiar la contraseña');
																					}
																		});
																	}else{
																		$utilidades.mensaje('success','Información actualizada exitosamente');
																	}

																}
													});

											}
										}else {
											$utilidades.mensaje('danger','No se lograron realizar los cambios');
										}

							});

					}
				}else{
					$utilidades.mensaje('warning','El correo electrónico no es válido');
				}

			}else{
				$utilidades.mensaje('warning','Los campos no deben de quedar vacios');
			}
		}
    if(mi.colaboradorSeleccionado && mi.tipoUsuario.id==4 &&validarEmail(mi.usuariosSelected.email) ){
      mi.asignarColaborador();
    }
  };


	mi.eliminarUsuario=function(ev){
		if(mi.usuariosSelected.usuario!==""){
			$dialogoConfirmacion.abrirDialogoConfirmacion($scope
					, "Confirmación de Borrado"
					, '¿Desea borrar al usuario "'+mi.usuariosSelected.usuario+'"?'
					, "Borrar"
					, "Cancelar")
			.result.then(function(data) {
				if(data){
					$http.post('/SUsuario', {
						accion: 'eliminarUsuario',
						usuario: mi.usuariosSelected.usuario,
						t: (new Date()).getTime()
					}).success(function(response){
						if(response.success){
							$utilidades.mensaje('success','Usuario elimiado con éxito');
							mi.cargarTabla(mi.paginaActual);
							mi.usuariosSelected={usuario:"", email:"",password:"", usuarioCreo:"", fechaCreacion:"", usuarioActualizo:"", fechaActualizacion:"", colaborador:""};
						}
						else
							$utilidades.mensaje('danger','Error al eliminar el usuario');
					});
				}
			}, function(){
				
			});
		}else{
		    $utilidades.mensaje('warning','Seleccione un usuario');
		}
	};
	
	function validarEmail(email) {
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(email);
	}
	mi.mostrarPermisos=function(){
		mi.edicionPermisos=true;
	};
	
	mi.editarUsuario=function(){
		mi.tipoUsuarioRol="";
		if(mi.usuariosSelected.usuario!==""){
			
			mi.treedata=[];
			mi.expanded=[];
			
			mi.cargarArbol=false;
			mi.isCollapsed = true;
			mi.esNuevo=false;
			passwordLocal=mi.usuariosSelected.password;
			if(mi.usuariosSelected.colaborador!=null){
				mi.tieneColaborador=true;
			}
			mi.tipoUsuarioRol=mi.getTipoRol(mi.usuariosSelected.rol)
			mi.cargandoPermisos= true;
			mi.permisosAsignados=[];
			mi.prestamosAsignados=[];
			mi.prestamosEliminados=[];
			mi.prestamosNuevos=[];
			$utilidades.setFocus(document.getElementById("usuario"));
			$http.post('/SUsuario', {
	    		accion:'obtenerPermisos',
	    		usuario: mi.usuariosSelected.usuario,
	    		t: new Date().getTime()
	    	}).then(function(response) {
	    	    mi.permisosAsignados =response.data.permisos;
	    	    mi.prestamosAsignados = response.data.proyectos;
	    	    mi.rolUsuario=response.data.rol;
	    	    mi.unidadEjecutoraUsuario=response.data.unidadEjecutora;
	    	    mi.cooperanteUsuario=response.data.cooperante;
	    	   mi.cargandoPermisos=false;
	    	});
			$http.post('/SProyecto',
					{ accion: 'controlArbolTodosProyectos', usuario: mi.usuariosSelected.usuario, t: (new Date()).getTime() }).success(
				function(response) {
					mi.treedata=response.proyectos;
					if(mi.treedata.id==0){
						mi.expanded.push(mi.treedata.children[0]);
						mi.setParentNode(mi.treedata);
						mi.cargarArbol=true;
					}
				});
			
		}else{
			$utilidades.mensaje('warning','Seleccione un usuario');
		}

	};


	mi.reiniciarVista=function(){
		if($location.path()=='/usuarios/rv')
			$route.reload();
		else
			$location.path('/usuarios/rv');
	};

	mi.cambiarPagina=function(){
		mi.cargarTabla(mi.paginaActual);
	};

	mi.guardarEstado=function(){
		var estado = mi.gridApi.saveState.save();
		var tabla_data = { action: 'guardaEstado', grid:'usuarios', estado: JSON.stringify(estado), t: (new Date()).getTime() };
		$http.post('/SEstadoTabla', tabla_data).then(function(response){

		});
	}
	
	mi.buscarPrestamosNuevos=function(){
		var modalInstance = $uibModal.open({
		    animation : 'true',
		    ariaLabelledBy : 'modal-title',
		    ariaDescribedBy : 'modal-body',
		    templateUrl : 'buscarPermiso.jsp',
		    controller : 'modalBuscarPrestamos',
		    controllerAs : 'modalBuscar',
		    backdrop : 'static',
		    size : 'md',
		    resolve : {
		    	infoUsuario: function(){
		    		var parametros={ usuario:mi.usuariosSelected.usuario, rol:mi.rolUsuario,unidadEjecutora:mi.unidadEjecutoraUsuario,cooperante:mi.cooperanteUsuario};
		    		return  parametros;
		    	}
		    }

		});

		modalInstance.result.then(function(data) {
			try{
				mi.prestamosAsignados.push({id:data.id, nombre:data.nombre});
				mi.prestamosNuevos.push(data.id);
				
			}catch(err){
				$utilidades.mensaje('warning', 'El préstamo ya se encuentra agregado');
			}
		}, function() {
		});
	};

	mi.buscarPermiso = function(tipo) {
		if(tipo===1 && mi.esNuevo){
			mi.usuariosSelected.colaborador="";
			mi.nombreUnidadEjecutora="";
			mi.prestamosAsignados=[];
		}
		var modalInstance = $uibModal.open({
		    animation : 'true',
		    ariaLabelledBy : 'modal-title',
		    ariaDescribedBy : 'modal-body',
		    templateUrl : 'buscarPermiso.jsp',
		    controller : 'modalBuscarPermiso',
		    controllerAs : 'modalBuscar',
		    backdrop : 'static',
		    size : 'md',
		    resolve : {
		    	infoPermisos: function(){
		    		var parametros={nuevo:mi.esNuevo, usuario:mi.usuariosSelected.usuario, tipo:tipo};
		    		return  parametros;
		    	}
		    }

		});

		modalInstance.result.then(function(resultadoSeleccion) {
			
			if(resultadoSeleccion.tipo===1){
					mi.cargandoPermisos=true;
					mi.tipoUsuario.id=resultadoSeleccion.rol.id;
					mi.tipoUsuario.nombre=resultadoSeleccion.rol.nombre;
					if(resultadoSeleccion.rol.id==2 ||resultadoSeleccion.rol.id==3 ||resultadoSeleccion.rol.id==1){
						if(mi.esNuevo){
							mi.prestamosAsignados=[];
						}
						cargarPrestamosPorElemento(2,0);
					}
					if(resultadoSeleccion.rol.id!==6 && resultadoSeleccion.rol.id!==4){
						mi.tipoUsuario.grupo=resultadoSeleccion.id;
					}
					$http.post('/SRol',{accion:'getPermisosPorRol',id:resultadoSeleccion.rol.id, t: (new Date()).getTime()}).success(
							function(response){
								mi.permisosAsignados=response.permisos;
								mi.nuevosPermisos=response.ids;
								mi.cargandoPermisos=false;
							}
					);
			
			}else if(resultadoSeleccion.tipo===3){
				if(mi.esNuevo){
					mi.prestamosAsignados=[];
				}
				cargarPrestamosPorElemento(6,resultadoSeleccion.cooperante.id);
				mi.tipoUsuario.grupo=resultadoSeleccion.cooperante.id;
				mi.nombreCooperante=resultadoSeleccion.cooperante.nombre;
			}
			else{
				if(mi.permisosAsignados.length>0){
					for(var i=0;i<resultadoSeleccion.length;i++){
						mi.permisosAsignados.push(resultadoSeleccion[i]);
					}
				}else{
					mi.permisosAsignados=resultadoSeleccion;
				}
				
				for(var i=0;i<resultadoSeleccion.length;i++){
					mi.nuevosPermisos.push(resultadoSeleccion[i].id);
				}
			}
			
		}, function() {
		});
	}

	mi.eliminarPermiso= function(permiso){
		var indice = mi.permisosAsignados.indexOf(permiso);
		if (indice !== -1) {
	       mi.permisosAsignados.splice(indice, 1);
	       mi.nuevosPermisos.splice(indice,1);
	    }
		if(!mi.esNuevo){
			mi.permisosEliminados.push(permiso.idPermiso);
		}
	};

	mi.buscarColaborador=function(){
		var modalInstance = $uibModal.open({
		    animation : 'true',
		    ariaLabelledBy : 'modal-title',
		    ariaDescribedBy : 'modal-body',
		    templateUrl : 'buscarColaborador.jsp',
		    controller : 'modalBuscarColaborador',
		    controllerAs : 'modalBuscar',
		    backdrop : 'static',
		    size : 'md',
		    resolve : {
		    	infoUsuario: function(){
		    		var parametros={ usuario:mi.usuariosSelected.usuario};
		    		return  parametros;
		    	}
		    }

		});

		modalInstance.result.then(function(data) {
			if(mi.tipoUsuario.id==4 || mi.tipoUsuario.id==5 ){				
				cargarPrestamosPorElemento(mi.tipoUsuario.id, data.unidadEjecutora);
			}
			mi.usuariosSelected.colaborador=data.primerNombre+ " "+data.primerApellido;
			mi.nombreUnidadEjecutora=data.nombreUnidadEjecutora;
			mi.colaboradorSeleccionado=true;
			mi.colaborador=data;
			mi.tipoUsuario.grupo=data.unidadEjecutora;
			mi.mensajeActualizado.mensaje=data.primerApellido+ ", "+data.primerNombre;
		}, function() {
		});
	};
	function cargarPrestamosPorElemento(tipo, id){
		if(mi.esNuevo){
			var datos = {
					accion : 'getPrestamosPorElemento',
					id : id,
					tipo:tipo,
					t: (new Date()).getTime()
				};
			$http.post('/SUsuario', datos).then(
					function(response) {
						if (response.data.success) {
							if(mi.esNuevo){
								mi.prestamosAsignados= response.data.prestamos;
							}
						} else {
							$utilidades.mensaje('danger',
									'No se pudieron actualizar los datos');
						}
					});
		}
		
	};
	mi.eliminarPrestamo=function(index,row){
		if(mi.esNuevo){
			if(index > -1){
				mi.prestamosAsignados.splice(index,1);
			}
		}else{
			mi.prestamosEliminados=[row.id];
			mi.prestamosAsignados.splice(index,1);
		}
	};
	mi.getTipoRol=function(input){
		var ret="";
		switch(input) {
		    case 1:
		        ret= "Superadministador";
		        break;
		    case 2:
		    	 ret= "Administrador";
		        break;
		    case 3:
		    	 ret= "DCP";
		        break;
		    case 4:
		    	ret= "Unidad Ejecutora";
		        break;
		    case 5:
		    	ret= "Planificador";
		        break;
		    case 6:
		    	ret= "Cooperante";
		        break;
		}
		return ret;
	}
	mi.asignarColaborador= function(){
		if(mi.colaboradorSeleccionado){
			var datos = {
					accion : 'actualizar',
					id : mi.colaborador.id,
					primerNombre :  mi.colaborador.primerNombre,
					segundoNombre :  mi.colaborador.segundoNombre,
					primerApellido :  mi.colaborador.primerApellido,
					segundoApellido :  mi.colaborador.segundoApellido,
					cui :  mi.colaborador.cui,
					unidadEjecutora :  mi.colaborador.unidadEjecutora,
					usuario : mi.usuariosSelected.usuario,
					seleccion:mi.tipoUsuario.grupo,
					t: (new Date()).getTime()
				};

				$http.post('/SColaborador', datos).then(
						function(response) {
							if (response.data.success) {
								$utilidades.mensaje('success',
										'Colaborador asignado con exito.');
							} else {
								$utilidades.mensaje('danger',
										'No se pudieron actualizar los datos');
							}
						});
		}else{
			$utilidades.mensaje('danger',
			'Seleccione un colaborador.');
		}

	}
	
	mi.filtrar = function(evt){
		if(evt.keyCode==13){
			$http.post('/SUsuario', { accion: 'getTotalUsuarios',	filtro_usuario: mi.filtros['usuario'],filtro_email: mi.filtros['email'],
				filtro_usuario_creo: mi.filtros['usuario_creo'], filtro_fecha_creacion: mi.filtros['fecha_creacion'], t: (new Date()).getTime()  }).success(
					function(response) {
						mi.totalUsuarios  = response.totalUsuarios;
						mi.cargarTabla(mi.paginaActual);
						mi.gridApi.selection.clearSelectedRows();
						mi.usuariosSelected.usuario = "";
			});
		}
	};

	$http.post('/SUsuario', { accion: 'getTotalUsuarios', 	filtro_nombre: mi.filtros['nombre'],
		filtro_usuario_creo: mi.filtros['usuario_creo'], filtro_fecha_creacion: mi.filtros['fecha_creacion'],filtro_email:mi.filtros["email"], t: (new Date()).getTime()  }).success(
			function(response) {
				mi.totalUsuarios = response.totalUsuarios;
				mi.cargarTabla(mi.paginaActual);
	});
	
	
	mi.setParentNode=function(nodo){
		for(var i=0; i<nodo.children.length;i++){
			nodo.children[i].parent = nodo;
			mi.setParentNode(nodo.children[i]);
		}
	}
	
	
	
	mi.showSelected=function(nodo){
		//console.log(nodo);
	};
	
	mi.onChange = function(nodo){
		if(nodo.estado==null)
			nodo.estado = false;
		mi.checkDescendientes(nodo);
		mi.checkAncestros(nodo);
	}
	
	mi.checkDescendientes=function(nodo){
		for(var i=0; nodo.children!== null && i<nodo.children.length; i++){
			nodo.children[i].estado = nodo.estado;
			mi.checkDescendientes(nodo.children[i]);
		}
	}
	
	mi.checkAncestros=function(nodo){
		estado = true;
		for(var i = 0; nodo.parent!=null && nodo.parent.children!= null && i<nodo.parent.children.length; i++){
			estado = nodo.parent.children[i].estado!=true ? null : estado;
		}
		estado_2 = false;
		for(var i = 0; nodo.parent!=null && nodo.parent.children!= null && i<nodo.parent.children.length; i++){
			estado_2 = nodo.parent.children[i].estado!=false ? null : estado_2;
		}
		estado = (estado_2==null ? estado : estado_2);
		if(nodo.parent!=null){
			nodo.parent.estado = estado;
			mi.checkAncestros(nodo.parent);
		}
	}
	
	mi.getChecksArbol = function(nodo){
		var ret='';
		for(var i=0; nodo.children!=null && i<nodo.children.length; i++){
			if(nodo.children[i].estado==null || nodo.children[i].estado){
				ret = ret + '|' + nodo.children[i].objeto_tipo + ',' + nodo.children[i].id;
			}
			var temp = mi.getChecksArbol(nodo.children[i]);
			ret =  (temp.length>0 ? ret + '|' + temp : ret);
		}
		return ret.substring(1);
	}
	
	mi.seleccionarTodo=function(){
		mi.treedata.estado = true;
		mi.checkDescendientes(mi.treedata);
	}
	
	mi.deseleccionarTodo=function(){
		mi.treedata.estado = false;
		mi.checkDescendientes(mi.treedata);
	}
	
	
	
	
} ]);

app.controller('modalBuscarPermiso', [
	'$uibModalInstance', '$scope', '$http', '$interval', 'i18nService',
	'Utilidades', '$timeout', '$log','infoPermisos', modalBuscarPermiso
]);

function modalBuscarPermiso($uibModalInstance, $scope, $http, $interval, i18nService, $utilidades, $timeout, $log, infoPermisos) {

	var mi = this;
	if(infoPermisos.nuevo){
		mi.mostrarCargando = true;
		if(infoPermisos.tipo===1){
			$http.post('/SRol', {
	    		accion:'getRoles',
	    		t: (new Date()).getTime()
	    	}).then(function(response) {
	    	    if (response.data.success) {
	    	    	mi.data = response.data.roles;
	    	    	mi.opcionesGrid.data = mi.data;
	    			mi.mostrarCargando = false;
	    	    }
	    	});
			
		}else if(infoPermisos.tipo===3){
			var data_={
					accion : 'getCooperantesPagina', 
					t:moment().unix()
			};
			$http.post('/SCooperante', data_).then(function(response) {
	    	    if (response.data.success) {
	    	    	mi.data=response.data.cooperantes;
	    	    	mi.opcionesGrid.data = mi.data;
	    	    	mi.mostrarCargando = false;
	    	    }
	    	});
			
		}else{
			$http.post('/SPermiso', {
	    		accion:'getPermisos'
	    	}).then(function(response) {
	    	    if (response.data.success) {
	    	    	mi.data = response.data.permisos;
	    	    	mi.opcionesGrid.data = mi.data;
	    			mi.mostrarCargando = false;
	    	    }
	    	});
		}
    	
	}else{
		 $http.post('/SUsuario', {
		    	accion : 'getPermisosDisponibles',
		    	usuario:infoPermisos.usuario
		        }).success(function(response) {
		        	mi.data = response.permisos;
	    	    	mi.opcionesGrid.data = mi.data;
	    			mi.mostrarCargando = false;
		    });
	}
	mi.totalElementos = 0;
	mi.paginaActual = 1;
	mi.numeroMaximoPaginas = 5;
	mi.elementosPorPagina = 9;

	mi.mostrarCargando = false;
	mi.data = [];

	mi.itemSeleccionado = null;
	mi.seleccionado = false;
	var especificacion=[];
	if(infoPermisos.tipo===3){
		especificaciones=[
			{
				displayName : 'ID',
				name : 'id',
				cellClass : 'grid-align-right',
				enableFiltering: true,
				type : 'text', width : 150

			},
			{
				displayName : 'nombre',
				name : 'nombre',
				cellClass : 'grid-align-right',
				enableFiltering: true

			},
			{
				displayName : 'Siglas',
				name : 'siglas',
				cellClass : 'grid-align-left'
			}
		]
	}else{
		especificaciones=[
			{
				displayName : 'ID',
				name : 'id',
				cellClass : 'grid-align-left',
				enableFiltering: true,
				type : 'text', width : 150

			},
			{
				displayName : 'nombre',
				name : 'nombre',
				cellClass : 'grid-align-left',
				enableFiltering: true

			},
			{
				displayName : 'Descripción',
				name : 'descripcion',
				cellClass : 'grid-align-left'
			}
		];
	}
	mi.tipo_vista=infoPermisos.tipo;
	mi.selecciorTodo=function(){
		mi.gridApi.selection.selectAllRows();
	}
	mi.deseleccionarTodo=function(){
		 mi.gridApi.selection.clearSelectedRows();
	}
	mi.dataSeleccion=[];
    mi.opcionesGrid = {
		data : mi.data,
		enableFiltering: true,
		columnDefs : especificaciones,
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		enableSelectAll: infoPermisos.tipo==0? true :false,
		multiSelect : infoPermisos.tipo==0? true :false,
		modifierKeysToMultiSelect : false,
		noUnselect : false,
		enableFiltering : true,
		enablePaginationControls : false,
		paginationPageSize : 5,
		onRegisterApi : function(gridApi) {
		    mi.gridApi = gridApi;
		    gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
	    		 asignar(rows);
	    	});
		    mi.gridApi.selection.on.rowSelectionChanged($scope,
				    mi.seleccionarPermiso);
		    gridApi.selection.on.rowSelectionChanged($scope, function (row) {
                mi.dataSeleccion.push(row.entity);
            });
		}
    }

    mi.seleccionarPermiso = function(row) {
    	mi.itemSeleccionado = row.entity;
    	mi.seleccionado = row.isSelected;
    };

    function asignar(input){
    	for(var i=0;i<input.length;i++){
    		 mi.dataSeleccion.push(input[i].entity);
    	}
    	return true;
    };
     mi.ok = function() {
    	if (mi.seleccionado) {
    		if(infoPermisos.tipo===1){
    			 $uibModalInstance.close({tipo:1, rol:mi.itemSeleccionado});
    		}else if(infoPermisos.tipo===3){
    			 $uibModalInstance.close({tipo:3, cooperante:mi.itemSeleccionado});
    		}else if(infoPermisos.tipo===0){
    			 $uibModalInstance.close(mi.dataSeleccion);
    		}
    		else{
    			 $uibModalInstance.close(mi.itemSeleccionado);
    		}
    	   
    	}else if(mi.tipo_vista==0){
    		 $uibModalInstance.close(mi.dataSeleccion);
    	} else {
    	    $utilidades.mensaje('warning', 'Debe seleccionar un permiso');
    	}
     };

     mi.cancel = function() {
    	$uibModalInstance.dismiss('cancel');
     };
}





app.controller('modalBuscarColaborador', [
	'$uibModalInstance', '$scope', '$http', '$interval', 'i18nService',
	'Utilidades', '$timeout', '$log','infoUsuario',modalBuscarColaborador
]);
function modalBuscarColaborador($uibModalInstance, $scope, $http, $interval, i18nService, $utilidades, $timeout, $log, infoUsuario) {
	var mi = this;
	mi.totalElementos = 0;
	mi.paginaActual = 1;
	mi.numeroMaximoPaginas = 5;
	mi.elementosPorPagina = 9;

	mi.mostrarCargando = false;
	mi.data = [];

	mi.itemSeleccionado = null;
	mi.seleccionado = false;



    mi.opcionesGrid = {
		data : mi.data,
		columnDefs : [
			{
				displayName : 'Primer nombre',
				name : 'primerNombre',
				cellClass : 'grid-align-right',
				type : 'text'

			},
			
			{
				displayName : 'Primer apellido',
				name : 'primerApellido',
				cellClass : 'grid-align-left'
			}
			,
			{
				displayName : 'CUI',
				name : 'cui',
				cellClass : 'grid-align-right',
				type : 'number'
			}
			, {
				displayName : 'Unidad Ejecutora',
				name : 'nombreUnidadEjecutora',
				cellClass : 'grid-align-left'
			}
		],
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
			    mi.seleccionarPermiso);
		}
    }
    var datos = {
			accion : 'getColaboradores',
			pagina :1,
			registros : 100,
			t: (new Date()).getTime()
		};

		mi.mostrarCargando = true;
		$http.post('/SUsuario', datos).then(function(response) {
			if (response.data.success) {

				mi.data = response.data.colaboradores;
				mi.opcionesGrid.data = mi.data;

				mi.mostrarCargando = false;
			}
		});
    mi.seleccionarPermiso = function(row) {
    	mi.itemSeleccionado = row.entity;
    	mi.seleccionado = row.isSelected;
    };


     mi.ok = function() {
    	if (mi.seleccionado) {
    	    $uibModalInstance.close(mi.itemSeleccionado);
    	} else {
    	    $utilidades.mensaje('warning', 'Debe seleccionar un colaborador');
    	}
     };


     mi.cancel = function() {
    	$uibModalInstance.dismiss('cancel');
     };
}




app.controller('modalBuscarPrestamos', [
	'$uibModalInstance', '$scope', '$http', '$interval', 'i18nService',
	'Utilidades', '$timeout', '$log','infoUsuario',modalBuscarPrestamos
]);
function modalBuscarPrestamos($uibModalInstance, $scope, $http, $interval, i18nService, $utilidades, $timeout, $log, infoUsuario) {
	var mi = this;
	mi.totalElementos = 0;
	mi.paginaActual = 1;
	mi.numeroMaximoPaginas = 5;
	mi.elementosPorPagina = 9;

	mi.mostrarCargando = false;
	mi.data = [];

	mi.itemSeleccionado = null;
	mi.seleccionado = false;


    mi.opcionesGrid = {
		data : mi.data,
		columnDefs : [
			{
				displayName : 'ID',
				name : 'id',
				cellClass : 'grid-align-right',
				enableFiltering: true,
				type : 'text', width : 150

			},
			{
				displayName : 'nombre',
				name : 'nombre',
				cellClass : 'grid-align-right',
				enableFiltering: true

			}
		],
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
			    mi.seleccionarPermiso);
		}
    }
    var datos={
    		accion : 'getPrestamosPorElemento',
			tipo :infoUsuario.rol,
			id : 0
    }
    if (infoUsuario.rol==4 || infoUsuario.rol==5){
    	datos.id=infoUsuario.unidadEjecutora;
    }else if(infoUsuario.rol==6){
    	datos.id=infoUsuario.cooperante;
    }
    datos.t= (new Date()).getTime();
    mi.mostrarCargando = true;
	$http.post('/SUsuario', datos).then(function(response) {
		if (response.data.success) {

			mi.data = response.data.prestamos;
			mi.opcionesGrid.data = mi.data;

			mi.mostrarCargando = false;
		}
	});
    mi.seleccionarPermiso = function(row) {
    	mi.itemSeleccionado = row.entity;
    	mi.seleccionado = row.isSelected;
    };


     mi.ok = function() {
    	if (mi.seleccionado) {
    	    $uibModalInstance.close(mi.itemSeleccionado);
    	} else {
    	    $utilidades.mensaje('warning', 'Debe seleccionar un colaborador');
    	}
     };


     mi.cancel = function() {
    	$uibModalInstance.dismiss('cancel');
     };
}
