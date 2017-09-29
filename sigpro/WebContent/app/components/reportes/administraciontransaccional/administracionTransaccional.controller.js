var app = angular.module('administracionTransaccionalController',['ngTouch','smart-table']);
app.controller('administracionTransaccionalController',['$scope', '$http', '$interval','Utilidades','i18nService','$window',
	function($scope, $http, $interval, $utilidades,i18nService,$window){
		var mi = this;
		
		mi.validar = function(noElemento){
			if(mi.fechaInicio != null && mi.fechaInicio.toString().length == 10 && 
					mi.fechaFin != null && mi.fechaFin.toString().length == 10)
			{
				if (moment(mi.fechaFin,'DD/MM/YYYY').toDate() < moment(mi.fechaInicio,'DD/MM/YYYY').toDate()){
						$utilidades.mensaje('warning','Fecha fin es mayor a fecha inicio');
				}else
					mi.generar();
			}
		}
		
		mi.abrirPopupFecha = function(index) {
			switch(index){
				case 1000: mi.fi_abierto = true; break;
				case 1001: mi.ff_abierto = true; break;
			}
		};
		
		mi.charOptions= {
			scales: {
				legend: {
					display: true,
					position: 'bottom'
				},
				yAxes: [
					{	
						id: 'y-axis-1',
						type: 'linear',
						display: true,
						position: 'left',
						ticks: {
							callback: function (value) {
								return numeral(value).format('0')
                        	}
	                   },
	                   scaleLabel: {
	                       display: true,
	                       labelString: 'Cantidad de transacciones'
	                   }
			        	
			        }
				],
				xAxes: [{
			    	  scaleLabel: {
	                       display: true,
	                       labelString: 'Estado'
	                     }
			      }
			      ]
			}
		};
		
		mi.generar = function(){
			mi.mostrarcargando = true;
			mi.mostrarTablas = false;
			$http.post('/SAdministracionTransaccional', {accion: 'getTransacciones', fechaInicio: moment(mi.fechaInicio).format('DD/MM/YYYY'), fechaFin: moment(mi.fechaFin).format('DD/MM/YYYY'), t: new Date().getTime()}).success(
					function(response){
						mi.rowDatos = response.usuarios;
						mi.displayedDatos = [].concat(mi.rowDatos);
						
						mi.totalCreados = 0;
						mi.totalActualizados = 0;
						mi.totalEliminados = 0;
						for(x in mi.rowDatos){
							mi.totalCreados += mi.rowDatos[x].creados;
							mi.totalActualizados += mi.rowDatos[x].actualizados;
							mi.totalEliminados += mi.rowDatos[x].eliminados;
						}
						
						mi.generarGrafica(response.creados[0].anios, response.actualizados[0].anios, response.eliminados[0].anios);
						
						mi.series = ['Datos'];
						mi.datos = [mi.totalCreados,mi.totalActualizados,mi.totalEliminados];
						mi.labels = [];
						mi.labels= ['Creados', 'Actualizados', 'Eliminados']
						mi.data = mi.datos;
						
						mi.mostrarcargando = false;
						mi.mostrarTablas = true;
					});
		}
		
		mi.totalesPorUsuario = function(row){
			mi.datos = [row.creados, row.actualizados, row.eliminados];
			mi.data = mi.datos;
		}
		
		mi.totalesGenerales = function(){
			mi.datos = [mi.totalCreados,mi.totalActualizados,mi.totalEliminados];
			mi.data = mi.datos;
		}
		
		mi.calcularTamanosPantalla = function(){
			mi.tamanoPantalla = Math.floor(document.getElementById("reporte").offsetWidth);
			mi.tamanoCelda = mi.tamanoPantalla / 5;
			mi.anchoPantalla = Math.floor(document.getElementById("reporte").offsetHeight);
		}
		
		angular.element($window).bind('resize', function(){ 
            mi.calcularTamanosPantalla();
            $scope.$digest();
        });
		
		mi.calcularTamanosPantalla();
		
		mi.exportarExcel = function(){
			$http.post('/SAdministracionTransaccional', { 
				 accion: 'exportarExcel', 
				 fechaInicio: mi.fechaInicio, 
				 fechaFin: mi.fechaFin,
				 t:moment().unix()
			  } ).then(
					  function successCallback(response) {
						  var anchor = angular.element('<a/>');
						  anchor.attr({
					         href: 'data:application/ms-excel;base64,' + response.data,
					         target: '_blank',
					         download: 'AdministracionTransaccional.xls'
						  })[0].click();
					  }.bind(this), function errorCallback(response){
				 	}
			  	);
			};
			
			mi.descargarExcelDetalle = function(row){
				$http.post('/SAdministracionTransaccional', {
					accion: 'exportarExcelDetalle',
					usuarioDetalle: row.usuario,
					fechaInicio: mi.fechaInicio, 
					fechaFin: mi.fechaFin,
					t:moment().unix()
				}).then(
						  function successCallback(response) {
							  var anchor = angular.element('<a/>');
							  anchor.attr({
						         href: 'data:application/ms-excel;base64,' + response.data,
						         target: '_blank',
						         download: 'AdministracionTransaccional_' + row.usuario +'_' + mi.fechaInicio + '_al_' + mi.fechaFin + '_.xls'
							  })[0].click();
						  }.bind(this), function errorCallback(response){
					 	}
				  	);
			}
			
		mi.exportarPdfDetalle=function(row){
			$http.post('/SAdministracionTransaccional', { 
				 accion: 'exportarPdfDetalle', 
				 fechaInicio: mi.fechaInicio, 
				 fechaFin: mi.fechaFin,
				 usuarioDetalle: row.usuario,
				 t:moment().unix()
			  } ).then(
					  function successCallback(response) {
						  var anchor = angular.element('<a/>');
						  anchor.attr({
					         href: 'data:application/pdf;base64,' + response.data,
					         target: '_blank',
					         download: 'AdministracionTransaccional_' + row.usuario +'_' + mi.fechaInicio + '_al_' + mi.fechaFin + '_.pdf'
						  })[0].click();
					  }.bind(this), function errorCallback(response){
				 	}
			  	);
		};
			
		mi.exportarPdf=function(){
			$http.post('/SAdministracionTransaccional', { 
				 accion: 'exportarPdf', 
				 fechaInicio: mi.fechaInicio, 
				 fechaFin: mi.fechaFin,
				 t:moment().unix()
			  } ).then(
					  function successCallback(response) {
						  var anchor = angular.element('<a/>');
						  anchor.attr({
					         href: 'data:application/pdf;base64,' + response.data,
					         target: '_blank',
					         download: 'AdministracionTransaccional.pdf'
						  })[0].click();
					  }.bind(this), function errorCallback(response){
				 	}
			  	);
		};
		
		mi.arregloMeses = ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sept", "Oct", "Nov", "Dic"]; 
		mi.radarColors = ['#8ecf4c', '#88b4df', '#ff3333'];
		
		mi.generarGrafica = function(arregloCreadas, arregloActualizadas, arregloEliminadas){
			mi.labelsMeses = [];
			mi.seriesLineales = [];
			mi.dataLineales = [];
			mi.seriesLineales = ['Creados', 'Actualizados', 'Eliminados'];
			
			mi.dataLinealesCreados = [];
			mi.dataLinealesActualizadas = [];
			mi.dataLinealesEliminadas = [];
			
			for(var i=0; i<arregloCreadas.length; i++){
				for(var j=0; j<=11; j++){
					mi.labelsMeses.push(mi.arregloMeses[j] + "-" + arregloCreadas[i].anio);
				}
			}
			
			for(var i=0;i<arregloCreadas.length; i++){
				for(var j=0; j<=11; j++){
					mi.dataLinealesCreados.push(arregloCreadas[i].mes[j]);
					mi.dataLinealesActualizadas.push(arregloActualizadas[i].mes[j]);
					mi.dataLinealesEliminadas.push(arregloEliminadas[i].mes[j]);
				}
			}
			
			mi.dataLineales.push(mi.dataLinealesCreados);
			mi.dataLineales.push(mi.dataLinealesActualizadas);
			mi.dataLineales.push(mi.dataLinealesEliminadas);
		}
		
		mi.charOptionsLineales= {
				legend: {
					display: true,
					position: 'bottom'
				},
				scales: {
					yAxes: [
						{	
							id: 'y-axis-1',
							type: 'linear',
							display: true,
							position: 'left',
							ticks: {
								callback: function (value) {
									return numeral(value).format('0')
	                        	}
		                   },
		                   scaleLabel: {
		                       display: true,
		                       labelString: 'Cantidad de transacciones'
		                   }
				        	
				        }
					],
					xAxes: [{
				    	  scaleLabel: {
		                       display: true,
		                       labelString: 'Meses'
		                     }
				      }
				      ]
				}
			};
		
}]);