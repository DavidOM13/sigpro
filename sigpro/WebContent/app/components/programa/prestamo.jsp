<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div ng-controller="prestamoController as controller"
	ng-class=" controller.esTreeview ? 'maincontainer_treeview all_page' : 'maincontainer all_page'" id="title">
	<script type="text/ng-template" id="map.html">
        <div class="modal-header">
            <h3 class="modal-title">Mapa de Ubicación</h3>
        </div>
        <div class="modal-body" style="height: 400px;">
            			<ui-gmap-google-map id="mainmap" ng-if="refreshMap" center="map.center" zoom="map.zoom" options="map.options" events="map.events"  >
							<ui-gmap-marker idkey="1" coords="posicion"></ui-gmap-marker>
						</ui-gmap-google-map>
		</div>
        <div class="modal-footer">
			 <div class="btn-group">
				<label class="btn btn-success" ng-click="ok()"> &nbsp;&nbsp;&nbsp;&nbsp;Ok&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label class="btn btn-primary" ng-click="cancel()">Quitar</label>
			<div class="btn-group">
        </div>
    </script>
	<shiro:lacksPermission name="24010">
		<span ng-init="controller.redireccionSinPermisos()"></span>
	</shiro:lacksPermission>
	<shiro:hasPermission name="43010">
		<span ng-init="controller.cambioOrden()"></span>
	</shiro:hasPermission>
	<div class="panel panel-default" ng-if="!controller.esTreeview">
	  <div class="panel-heading"><h3>{{etiquetas.proyecto}}s</h3></div>
	</div>
	<div align="center" ng-hide="controller.esColapsado" ng-if="!controller.esTreeview">
		<div class="col-sm-12 operation_buttons" align="right">
			<div class="btn-group">
			<shiro:hasPermission name="24040">
				<label class="btn btn-primary" ng-click="controller.nuevo()" uib-tooltip="Nuevo">
				<span class="glyphicon glyphicon-plus"></span> Nuevo</label>
			</shiro:hasPermission>
			<shiro:hasPermission name="24020">
				<label class="btn btn-primary" ng-click="controller.editar()" uib-tooltip="Editar">
				<span class="glyphicon glyphicon-pencil"></span> Editar</label>
			</shiro:hasPermission>
			<shiro:hasPermission name="24030">
				<label class="btn btn-danger" ng-click="controller.borrar()" uib-tooltip="Borrar">
				<span class="glyphicon glyphicon-trash"></span> Borrar</label>
			</shiro:hasPermission>
			</div>
		</div>
		<div class="col-sm-12 operation_buttons" align="right">
			<div class="btn-group" role="group" aria-label="">
					<shiro:hasPermission name="24010">
						<a class="btn btn-default" href ng-click="controller.reiniciarVista()" role="button" uib-tooltip="Reiniciar la vista de la tabla" tooltip-placement="left"><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span></a>
					</shiro:hasPermission>
					</div>
		</div>
		<shiro:hasPermission name="24010">
		<div class="col-sm-12" align="center">
			<br/>
			<div id="grid1" ui-grid="controller.gridOpciones" ui-grid-save-state ui-grid-move-columns ui-grid-resize-columns ui-grid-selection ui-grid-pinning ui-grid-pagination
				ui-grid-pagination>
				<div class="grid_loading" ng-hide="!controller.mostrarcargando">
				  	<div class="msg">
				      <span><i class="fa fa-spinner fa-spin fa-4x"></i>
						  <br /><br />
						  <b>Cargando, por favor espere...</b>
					  </span>
					</div>
				  </div>
			</div>
			<br/>
			<div class="total-rows">Total de {{  controller.totalProyectos + (controller.totalProyectos == 1 ? "Prestamo" : " Prestamos" ) }}</div>
				<ul uib-pagination total-items="controller.totalProyectos"
						ng-model="controller.paginaActual"
						max-size="controller.numeroMaximoPaginas"
						items-per-page="controller.elementosPorPagina"
						first-text="Primero"
						last-text="Último"
						next-text="Siguiente"
						previous-text="Anterior"
						class="pagination-sm" boundary-links="true" force-ellipses="true"
						ng-change="controller.cambioPagina()"
				></ul>
		</div>

		</shiro:hasPermission>
	</div>
	<div class="row second-main-form" ng-show="controller.esColapsado || controller.esTreeview">
		<div class="page-header">
			<h2 ng-if="controller.esNuevo"><small>Nuevo {{etiquetas.proyecto}}</small></h2>
			<h2 ng-if="!controller.esNuevo"><small>Edición de {{etiquetas.proyecto}}</small></h2>
			</div>
		<div class="operation_buttons">
			<div class="btn-group" style="float: right;">
				<shiro:hasPermission name="24020">
					<label class="btn btn-success" ng-click="controller.mForm.$valid && controller.botones ? controller.guardar(form.$valid) : ''" ng-disabled="!controller.mForm.$valid || !controller.botones" uib-tooltip="Guardar" tooltip-placement="bottom">
					<span class="glyphicon glyphicon-floppy-saved"></span> Guardar</label>
				</shiro:hasPermission>
				<label ng-if="!controller.esTreeview" class="btn btn-primary" ng-click="controller.botones ? controller.irATabla() : ''" uib-tooltip="Ir a Tabla" tooltip-placement="bottom" ng-disabled="!controller.botones">
				<span class="glyphicon glyphicon-list-alt"></span> Ir a Tabla</label>
				<label ng-if="controller.esTreeview" class="btn btn-danger" ng-click=" controller.botones ? controller.t_borrar() : ''" ng-disabled="!(controller.proyecto.id>0) || !controller.botones" uib-tooltip="Borrar" tooltip-placement="bottom">
				<span class="glyphicon glyphicon-trash"></span> Borrar</label>
			</div>
		</div>
		<div class="col-sm-12" ng-if="controller.proyecto.projectCargado==1">
				<div class="componente_sigade">Estructura importada desde un archivo de Project</div>
			</div>
		<br>
		<div class="col-sm-12">
			<form name="controller.mForm" style="margin-top: 10px;">
			<uib-tabset active="controller.active">
				<shiro:hasPermission name="43010">
				<uib-tab ng-click="controller.getPorcentajes();" index="0" heading="Préstamo" >
					<br/>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<input type="text" class="inputText"   
										ng-model="controller.prestamo.codigoPresupuestario" ng-readonly="true" ng-required="true"
										ng-click="controller.buscarCodigoPresupuestario()"
										onblur="this.setAttribute('value', this.value);" ng-value="controller.prestamo.codigoPresupuestario" />			            	
										<span class="label-icon" ng-click="controller.buscarCodigoPresupuestario()">
											<i class="glyphicon glyphicon-search"></i>
										</span>
										<label class="floating-label">* Código presupuestario</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<input type="text" class="inputText"  ng-model="controller.prestamo.numeroPrestamo" ng-required="true" 
										ng-value="controller.prestamo.numeroPrestamo" onblur="this.setAttribute('value', this.value);">
										<label class="floating-label" >* Número de {{etiquetas.proyecto}}</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
									<input type="text" class="inputText"   ng-model="controller.prestamo.proyectoPrograma" ng-required="true"
									onblur="this.setAttribute('value', this.value);" ng-value="controller.prestamo.proyectoPrograma"  >
									<label class="floating-label">* Proyecto/Programa</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group" >
										<div id="cooperante" angucomplete-alt placeholder="" pause="100" selected-object="controller.cambioCooperante"
											  local-data="controller.cooperantes" search-fields="nombre" title-field="nombre" field-required="true" field-label="* Cooperante"
											  minlength="2" input-class="form-control form-control-small field-angucomplete" match-class="angucomplete-highlight"
											  initial-value="controller.prestamo.cooperantenombre" focus-out="controller.blurCooperante()"></div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">    						
										<input type="text" class="inputText" uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaDecreto" is-open="controller.fd_abierto"
											datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar" ng-required="true"
											ng-value="controller.prestamo.fechaDecreto" onblur="this.setAttribute('value', this.value);"
										/>
										<span class="label-icon" ng-click="controller.abrirPopupFecha(1007)">	
											<i class="glyphicon glyphicon-calendar"></i>
										</span>
										<label class="floating-label">* Fecha Decreto</label>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<input type="text" class="inputText" uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaSuscripcion" is-open="controller.fs_abierto"
											datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar" ng-required="true"
											ng-value="controller.prestamo.fechaSuscripcion" onblur="this.setAttribute('value', this.value);"
											/>
											<span class="label-icon" ng-click="controller.abrirPopupFecha(1008)">
												<i class="glyphicon glyphicon-calendar"></i>
										</span>
										<label class="floating-label">* Fecha de Suscripción</label>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<input type="text" class="inputText" uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaVigencia" is-open="controller.fv_abierto"
											datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar" ng-required="true"
											ng-value="controller.prestamo.fechaVigencia" onblur="this.setAttribute('value', this.value);"
											/>
											<span class="label-icon" ng-click="controller.abrirPopupFecha(1012)">
												<i class="glyphicon glyphicon-calendar"></i>
										</span>
										<label class="floating-label">* Fecha de vigencia</label>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" class="inputText"  
										ng-model="controller.prestamo.tipoMonedaNombre" ng-readonly="true" ng-required="true"
										ng-click="controller.buscarTipoMoneda()"
										onblur="this.setAttribute('value', this.value);" ng-value="controller.prestamo.tipoMonedaNombre"/>
										<span class="label-icon" ng-click="controller.buscarTipoMoneda()">
											<i class="glyphicon glyphicon-search"></i>
										</span>
										<label class="floating-label">* Tipo de Moneda</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group money-input">
										<input type="text" 
										 class="inputText input-money"  
										 ng-model="controller.prestamo.montoContratado" 
										 ng-required="true"
										 ng-value="controller.prestamo.montoContratado"
										 onblur="this.setAttribute('value', this.value);" 
										 ng-change="controller.setPorcentaje(1);" 
										 ui-number-mask="0"
										 >
										<label class="floating-label" >* Monto Contratado</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" 
										 class="inputText input-money"  
										 ng-model="controller.prestamo.montoContratadoUsd" 
										 ng-required="true"
										 ng-value="controller.prestamo.montoContratadoUsd" 
										 onblur="this.setAttribute('value', this.value);" 
										 ng-change="controller.setPorcentaje(2);"
										 ui-number-mask="0"
										 >
										 <label class="floating-label" >* Monto Contratado $</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" 
										class="inputText input-money" 
										ng-model="controller.prestamo.montoContratadoQtz" 
										ng-required="true"
										ng-value="controller.prestamo.montoContratadoQtz" 
										onblur="this.setAttribute('value', this.value);"
										ui-number-mask="0"
										>
										<label class="floating-label" >* Monto Contratado Q</label>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<input inputText="text" 
										class="inputText input-money" 
										ng-model="controller.prestamo.desembolsoAFechaUsd" 
										ng-required="true"
										ng-value="controller.prestamo.desembolsoAFechaUsd" 
										onblur="this.setAttribute('value', this.value);" 
										ng-change="controller.setPorcentaje(1);"
										ui-number-mask="0"
										>
										<label class="floating-label">* Desembolso a la Fecha $</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" class="inputText input-money" ng-model="controller.prestamo.desembolsoAFechaUsdP" ng-disabled="true"
										ng-value="controller.prestamo.desembolsoAFechaUsdP" onblur="this.setAttribute('value', this.value);" ui-number-mask="2"/>
										<label class="floating-label">Desembolso a la Fecha %</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" 
										class="inputText input-money" 
										ng-model="controller.prestamo.montoPorDesembolsarUsd" 
										ng-required="true"
										ng-value="controller.prestamo.montoPorDesembolsarUsd" 
										onblur="this.setAttribute('value', this.value);" 
										ng-change="controller.setPorcentaje(2);"
										ng-disabled="true"
										ui-number-mask="0"
										/>
										<label class="floating-label">* Monto por Desembolsar $</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" class="inputText input-money" ng-model="controller.prestamo.montoPorDesembolsarUsdP" ng-disabled="true"
										ng-value="controller.prestamo.montoPorDesembolsarUsdP" onblur="this.setAttribute('value', this.value);" ui-number-mask="0"/>
										<label class="floating-label">Monto por Desembolsar %</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<input type="number"
										 class="inputText "  
										 ng-model="controller.proyecto.ejecucionFisicaReal"
										 ng-value="controller.proyecto.ejecucionFisicaReal"
										 onblur="this.setAttribute('value', this.value);"
										 min="0" max="100">
										<label class="floating-label" >Ejecucion Física Real %</label>
									</div>
								</div>
							</div>
					
				</uib-tab>
				</shiro:hasPermission>
				<shiro:hasPermission name="43010">
				<uib-tab ng-click="controller.getPorcentajes();" index="controller.ordenTab+1" heading="Configuraciones Préstamo" >
					<br/>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" class="inputText" uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaElegibilidadUe" is-open="controller.fe_abierto"
											datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar" ng-required="true" ng-change="controller.setPorcentaje(5);"
											ng-value="controller.prestamo.fechaElegibilidadUe" onblur="this.setAttribute('value', this.value);" />
										<span class="label-icon" ng-click="controller.abrirPopupFecha(1009)">
												<i class="glyphicon glyphicon-calendar"></i>
										</span>
										<label class="floating-label">* Fecha de Elegibilidad</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group">
											<input type="text" class="inputText" uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaCierreOrigianlUe" is-open="controller.fco_abierto"
												datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar" ng-required="true" ng-change="controller.setPorcentaje(5);"
												ng-value="controller.prestamo.fechaCierreOrigianlUe" onblur="this.setAttribute('value', this.value);"
											/>
											<span class="label-icon" ng-click="controller.abrirPopupFecha(1010)">
													<i class="glyphicon glyphicon-calendar"></i>
											</span>
										<label class="floating-label">* Fecha de Cierre Original</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
											<input type="text" class="inputText"   uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaCierreActualUe" is-open="controller.fca_abierto"
												datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar" ng-required="true" ng-change="controller.setPorcentaje(5);"
												ng-value="controller.prestamo.fechaCierreActualUe" onblur="this.setAttribute('value', this.value);"/>
											<span class="label-icon" ng-click="controller.abrirPopupFecha(1011)"
											ng-readonly="true">
													<i class="glyphicon glyphicon-calendar"></i>
											</span>
										<label  class="floating-label">* Fecha de Cierre Actual</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" 
										class="inputText input-money" 
										ng-model="controller.prestamo.mesesProrrogaUe" 
										ng-required="true"
										ng-readonly="true"
										ng-value="controller.prestamo.mesesProrrogaUe"
										onblur="this.setAttribute('value', this.value);" 
										ui-number-mask="2">
										<label class="floating-label">* Meses de Prórroga</label>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" class="inputText input-money"   ng-model="controller.prestamo.plazoEjecucionUe" ng-disabled="true"
										ng-value="controller.prestamo.plazoEjecucionUe" onblur="this.setAttribute('value', this.value);" ui-number-mask="2"/>
										<label class="floating-label">Plazo de Ejecución %</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group">							
										<input type="text" 
										class="inputText input-money" 
										ng-model="controller.prestamo.montoAsignadoUe" 
										ng-required="true"
										ng-value="controller.prestamo.montoAsignadoUe"
										onblur="this.setAttribute('value', this.value);" 
										ui-number-mask="0"
										>
										<label class="floating-label">* Monto Asignado</label>
									</div>
								</div>
							</div>
							<div class="row">
								
								<div class="col-sm-6">
									<div class="form-group">							
										<input 
										type="text" 
										class="inputText input-money" 
										ng-model="controller.prestamo.montoAsignadoUeUsd" 
										ng-required="true" 
										ng-value="controller.prestamo.montoAsignadoUeUsd" 
										onblur="this.setAttribute('value', this.value);"
										ng-change="controller.setPorcentaje(3);"
										ui-number-mask="0"
										/>
										<label class="floating-label">* Monto Asignado $</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group">							
										<input 
										type="text" 
										class="inputText input-money" 
										ng-model="controller.prestamo.montoAsignadoUeQtz" 
										ng-required="true" 
										ng-value="controller.prestamo.montoAsignadoUeQtz" 
										onblur="this.setAttribute('value', this.value);" 
										ui-number-mask="2"
										>
										<label class="floating-label">* Monto Asignado Q</label>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" 
										class="inputText input-money" 
										ng-model="controller.prestamo.desembolsoAFechaUeUsd" 
										ng-required="true"
										ng-value="controller.prestamo.desembolsoAFechaUeUsd" 
										onblur="this.setAttribute('value', this.value);" 
										ng-change="controller.setPorcentaje(3);"
										ui-number-mask="0"
										>
										<label class="floating-label">* Desembolso a la Fecha $</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group">
										<input type="text" class="inputText input-money" ng-model="controller.prestamo.desembolsoAFechaUeUsdP" ng-disabled="true"
										ng-value="controller.prestamo.desembolsoAFechaUeUsdP" onblur="this.setAttribute('value', this.value);" ui-number-mask="2"/>
										<label class="floating-label">Desembolsos a la fecha %</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">							
										<input type="text" 
										class="inputText input-money" 
										ng-model="controller.prestamo.montoPorDesembolsarUeUsd" 
										ng-required="true" 
										ng-value="controller.prestamo.montoPorDesembolsarUeUsd" 
										onblur="this.setAttribute('value', this.value);" 
										ng-change="controller.setPorcentaje(4);"
										ng-disabled="true"
										ui-number-mask="0"
										/>
										<label class="floating-label">* Monto por desembolsar $</label>
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group">							
										<input type="text" class="inputText input-money" ng-model="controller.prestamo.montoPorDesembolsarUeUsdP" ng-disabled="true" 
										ng-value="controller.prestamo.montoPorDesembolsarUeUsdP" onblur="this.setAttribute('value', this.value);" ui-number-mask="2"/>
										<label class="floating-label">Monto por desembolsar %</label>
									</div>
								</div>
							</div>
				
				</uib-tab>
				<uib-tab index="controller.ordenTab+2" heading="Adicionales" ng-if="controller.mostrarPrestamo" >
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group">
							   	<input  type="text" class="inputText"   ng-model="controller.prestamo.numeroAutorizacion" 
							   	onblur="this.setAttribute('value', this.value);" ng-value="controller.prestamo.numeroAutorizacion">
							   	<label class="floating-label">Número Autorización</label>
							</div>
						</div>
					
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="text" class="inputText"  ng-model="controller.prestamo.destino" 
							   	ng-value="controller.prestamo.destino" onblur="this.setAttribute('value', this.value);">
							   	<label class="floating-label" >Destino</label>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="text" class="inputText"  ng-model="controller.prestamo.sectorEconomico" 
							   	ng-value="controller.prestamo.sectorEconomico" onblur="this.setAttribute('value', this.value);">
							   	<label class="floating-label" >Sector Económico</label>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
								<input type="text" class="inputText"  uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaFirma" is-open="controller.ff_abierto"
									datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar"
									ng-click="controller.abrirPopupFecha(1004)" ng-value="controller.prestamo.fechaCorte" onblur="this.setAttribute('value', this.value);"/>
								<span class="label-icon" ng-click="controller.abrirPopupFecha(1004)"
								ng-readonly="true">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								<label class="floating-label">Fecha de Firma</label>
								
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<input type="text" class="inputText"  uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaCorte" is-open="controller.fc_abierto"
													datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar"
													ng-click="controller.abrirPopupFecha(1003)" ng-value="controller.prestamo.fechaCorte" onblur="this.setAttribute('value', this.value);"/>
								<span class="label-icon" ng-click="controller.abrirPopupFecha(1003)"
								ng-readonly="true">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								<label class="floating-label">Fecha de Corte</label>
							</div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
				            	<input type="text" class="inputText"    
				            	ng-model="controller.prestamo.tipoAutorizacionNombre" ng-readonly="true" 
				            	ng-click="controller.buscarAutorizacionTipo()"
				            	onblur="this.setAttribute('value', this.value);" ng-value="controller.prestamo.tipoAutorizacionNombre"			            	/>
				            	<span class="label-icon" ng-click="controller.buscarAutorizacionTipo()"><i class="glyphicon glyphicon-search"></i></span>				          	
					          	<label class="floating-label">Tipo Autorización</label>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
							   	<input  type="number" class="inputText" ng-model="controller.prestamo.aniosPlazo" max="100" min="0" 
							   	ng-value="controller.prestamo.aniosPlazo" onblur="this.setAttribute('value', this.value);">
							   	<label class="floating-label">Años Plazo</label>
							</div>
						</div>
							
						<div class="col-sm-6">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.aniosGracia" max="100" min="0" 
							   		ng-value="controller.prestamo.aniosGracia" onblur="this.setAttribute('value', this.value);">
							   	<label  class="floating-label">Años de Gracia</label>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<input type="text" class="inputText"  uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaAutorizacion" is-open="controller.fa_abierto"
									datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar"
									ng-click="controller.abrirPopupFecha(1005)" ng-value="controller.prestamo.fechaAutorizacion" onblur="this.setAttribute('value', this.value);"/>
								<span class="label-icon" ng-click="controller.abrirPopupFecha(1005)"
								ng-readonly="true">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								<label class="floating-label">Fecha de Autorización</label>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<input type="text" class="inputText"  uib-datepicker-popup="{{controller.formatofecha}}" ng-model="controller.prestamo.fechaFinEjecucion" is-open="controller.ffe_abierto"
									datepicker-options="controller.fechaOptions" close-text="Cerrar" current-text="Hoy" clear-text="Borrar"
									ng-click="controller.abrirPopupFecha(1006)" ng-value="controller.prestamo.fechaFinEjecucion" onblur="this.setAttribute('value', this.value);"/>
								<span class="label-icon" ng-click="controller.abrirPopupFecha(1006)"
								ng-readonly="true">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								<label class="floating-label">Fecha Fin de Ejecución</label>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
				            	<input type="text" class="inputText" ng-model="controller.prestamo.tipoInteresNombre" ng-readonly="true"
				            	ng-click="controller.buscarInteresTipo()"
				            	onblur="this.setAttribute('value', this.value);" ng-value="controller.prestamo.tipoInteresNombre"/>
				            	<span class="label-icon" ng-click="controller.buscarInteresTipo()">
				            		<i class="glyphicon glyphicon-search"></i>
				            	</span>
					          	<label class="floating-label">Tipo de Interés</label>
							</div>
						</div>
						
						<div class="col-sm-6">
							<div class="form-group">
							   	<input type="number" class="inputText"  ng-model="controller.prestamo.porcentajeInteres"  
							   	onblur="this.setAttribute('value', this.value);" ng-value="controller.prestamo.porcentajeInteres"/>
							   	<label class="floating-label">Porcentaje de Interés</label>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
							   	<input  class="inputText" type="number"  ng-model="controller.prestamo.periodoEjecucion" max="100" min="0" 
							   	ng-value="controller.prestamo.periodoEjecucion" onblur="this.setAttribute('value', this.value);">
							   	<label class="floating-label">Período de Ejecución</label>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
							   	<input type="number" class="inputText"  ng-model="controller.prestamo.porcentajeComisionCompra"  
							   	onblur="this.setAttribute('value', this.value);" ng-value="controller.prestamo.porcentajeComisionCompra"/>
							   	<label class="floating-label">Porc. Comisión Compra</label>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.amortizado"  
							   	ng-value="controller.prestamo.amortizado" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">Amortizado</label>
							</div>
							
						</div>
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.porAmortizar" 
							   	ng-value="controller.prestamo.porAmortizar" onblur="this.setAttribute('value', this.value);" />
							   	<label class="floating-label">Por Amortizar</label>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText"  ng-model="controller.prestamo.principalAnio" 
							   	ng-value="controller.prestamo.principalAnio" onblur="this.setAttribute('value', this.value);" />
							   	<label class="floating-label">Principal del Año</label>
							</div>
						</div>
					</div>
					
					<div class="row">						
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.interesesAnio"  
							   	ng-value="controller.prestamo.interesesAnio" onblur="this.setAttribute('value', this.value);" />
							   	<label class="floating-label">Intereses del Año</label>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.comisionCompromisoAnio"  
							   	ng-value="controller.prestamo.comisionCompromisoAnio" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">Com. Compromiso del Año</label>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.otrosGastos"  
							   	ng-value="controller.prestamo.otrosGastos" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">Otros Gastos</label>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.principalAcumulado"  
							   	ng-value="controller.prestamo.principalAcumulado" onblur="this.setAttribute('value', this.value);"/>
							   	<label  class="floating-label">Principal Acumulado</label>
							</div>
						</div>
					</div>
					
					<div class="row"> 
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number"  class="inputText"  ng-model="controller.prestamo.interesesAcumulados" 
							   	ng-value="controller.prestamo.interesesAcumulados" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">Intereses Acumulados</label>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number"  class="inputText"  ng-model="controller.prestamo.comisionCompromisoAcumulado"  
							   	ng-value="controller.prestamo.comisionCompromisoAcumulado" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">Com. Compromiso Acumulado</label>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number"  class="inputText"  ng-model="controller.prestamo.otrosCargosAcumulados"  
							   	ng-value="controller.prestamo.otrosCargosAcumulados" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label" >Otros Cargos Acumulados</label>
							</div>
						</div>					
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
							   	<input type="number"  class="inputText"  ng-model="controller.prestamo.presupuestoAsignadoFuncionamiento" 
								ng-value="controller.prestamo.presupuestoAsignadoFuncionamiento" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">P. Asignado Funcionamiento</label>
							</div>
						</div>
					
						<div class="col-sm-6">
							<div class="form-group">
							   	<input type="number" class="inputText"  ng-model="controller.prestamo.presupuestoModificadoFun"  
							   	ng-value="controller.prestamo.presupuestoModificadoFun" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">P. Modificado Funcionamiento</label>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.presupuestoVigenteFun" 
							   	ng-value="controller.prestamo.presupuestoVigenteFun" onblur="this.setAttribute('value', this.value);"/>
							   	<label  class="floating-label">P. Vigente Funcionamiento</label>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number"   class="inputText" ng-model="controller.prestamo.presupuestoAsignadoInversion"  
							   	ng-value="controller.prestamo.presupuestoAsignadoInversion" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">P. Asignado Inversion</label>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText"  ng-model="controller.prestamo.presupuestoModificadoInv" 
							   	ng-value="controller.prestamo.presupuestoModificadoInv" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">P. Modificado Inversión</label>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText"  ng-model="controller.prestamo.presupuestoVigenteInv" 
							   	ng-value="controller.prestamo.presupuestoVigenteInv" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">P. Vigente Inversión</label>
							</div>
						</div>
					</div>		
								
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.presupuestoDevengadoFun" 
							   	ng-value="controller.prestamo.presupuestoDevengadoFun" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">P. Devengado Funcionamiento</label>
							</div>
						</div>
					
						<div class="col-sm-6">
							<div class="form-group">
							   	<input type="number"  class="inputText" ng-model="controller.prestamo.presupuestoDevengadoInv"  
							   	ng-value="controller.prestamo.presupuestoDevengadoInv" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">P. Devengado Inversión</label>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.presupuestoPagadoFun" 
							   	ng-value="controller.prestamo.presupuestoPagadoFun" onblur="this.setAttribute('value', this.value);"/>
							   	<label  class="floating-label">P. Pagado Funcionamiento </label>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText"  ng-model="controller.prestamo.presupuestoPagadoInv"  
							   		ng-value="controller.prestamo.presupuestoPagadoInv" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">P. Pagado Inversión </label>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.saldoCuentas"
							   	ng-value="controller.prestamo.saldoCuentas" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">Saldo de Cuentas </label>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.desembolsoReal"
							   	ng-value="controller.prestamo.desembolsoReal" onblur="this.setAttribute('value', this.value);"/>  
							   	<label class="floating-label">Desembolso Real GTQ</label>
							</div>
						</div>
					</div>
					
					<div class="row">	
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.desembolsoAFechaUe"
							   	ng-value="controller.prestamo.desembolsoAFechaUe" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">Desembolso a la Fecha</label>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group">
							   	<input type="number" class="inputText" ng-model="controller.prestamo.montoPorDesembolsarUe"
							   	ng-value="controller.prestamo.montoPorDesembolsarUe" onblur="this.setAttribute('value', this.value);"/>
							   	<label class="floating-label">Monto por Desembolsar</label>
							</div>
						</div>
					</div>
					
					<div class="form-group">
		            	<input type="text" class="inputText"  
		            	ng-click="controller.buscarEstadoEjecucion()"
		            	ng-model="controller.prestamo.ejecucionEstadoNombre" ng-readonly="true"
		            	onblur="this.setAttribute('value', this.value);" ng-value="controller.prestamo.ejecucionEstadoNombre" />
		            	<span class="label-icon" ng-click="controller.buscarEstadoEjecucion()"><i class="glyphicon glyphicon-search"></i></span>
			          	<label class="floating-label">Estado de Ejecución</label>
					</div>
					
					<div class="form-group">
						<input type="text" ng-model="controller.proyecto.descripcion"
							class="inputText" id="campo2" 
							ng-value="controller.proyecto.descripcion" onblur="this.setAttribute('value', this.value);">
						<label for="campo2" class="floating-label">Descripción</label>
					</div>
				</uib-tab>
				</shiro:hasPermission>
				<uib-tab  index="controller.ordenTab+3" heading="Unidades Ejecutoras" ng-if="controller.mostrarPrestamo">
					
				</uib-tab>
				<uib-tab  index="controller.ordenTab+4" heading="Componentes" ng-if="controller.mostrarPrestamo">
					
				</uib-tab>
				<uib-tab  index="controller.ordenTab+5" heading="Matriz" ng-if="controller.mostrarPrestamo">
					
				</uib-tab>
			</uib-tabset>
			</form>
		</div>
		<div class="col-sm-12 operation_buttons" align="right" style="margin-top: 15px;">
			<div align="center" class="label-form">Los campos marcados con * son obligatorios y las fechas deben tener formato de dd/mm/aaaa</div>
			<br/>
			<div class="btn-group" ng-disabled="!controller.botones">
				<shiro:hasPermission name="24020">
					<label class="btn btn-success" ng-click="controller.mForm.$valid && controller.botones ? controller.guardar(controller.mForm.$valid) : ''" ng-disabled="!controller.mForm.$valid || !controller.botones" uib-tooltip="Guardar" tooltip-placement="top">
					<span class="glyphicon glyphicon-floppy-saved"></span> Guardar</label>
				</shiro:hasPermission>
				<label ng-if="!controller.esTreeview" class="btn btn-primary" ng-click="controller.botones ? controller.irATabla() : ''" uib-tooltip="Ir a Tabla" tooltip-placement="top" ng-disabled="!controller.botones">
				<span class="glyphicon glyphicon-list-alt"></span> Ir a Tabla</label>
				<label ng-if="controller.esTreeview" class="btn btn-danger" ng-click=" controller.botones ? controller.t_borrar() : ''" ng-disabled="!(controller.proyecto.id>0) || !controller.botones" uib-tooltip="Borrar" tooltip-placement="top">
				<span class="glyphicon glyphicon-trash"></span> Borrar</label>
			</div>
		</div>
	</div>
</div>
