<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="org.apache.shiro.SecurityUtils" %>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<div ng-controller="riesgopropiedadController as riesgopropiedadc" class="maincontainer all_page" id="title">
		<h3>Propiedad Riesgo</h3><br/>
		<div class="row" align="center" ng-hide="riesgopropiedadc.mostraringreso">
			<div class="col-sm-12 operation_buttons" align="right">
				<div class="btn-group">
			       <shiro:hasPermission name="crearCooperante">
			       		<label class="btn btn-primary" ng-click="riesgopropiedadc.nuevo()">Nuevo</label>
			       </shiro:hasPermission> 
			       <shiro:hasPermission name="editarCooperante"><label class="btn btn-primary" ng-click="riesgopropiedadc.editar()">Editar</label></shiro:hasPermission>
			       <shiro:hasPermission name="eliminarCooperante">
			       		<label class="btn btn-primary" ng-click="riesgopropiedadc.borrar()">Borrar</label>
			       </shiro:hasPermission>
    			</div>				
    		</div>
    		<shiro:hasPermission name="verCooperante">
    		<div class="col-sm-12" align="center">
    			<div style="height: 35px;">
					<div style="text-align: right;"><div class="btn-group" role="group" aria-label="">
						<a class="btn btn-default" href ng-click="riesgopropiedadc.reiniciarVista()" role="button" uib-tooltip="Reiniciar la vista de la tabla" tooltip-placement="left">
							<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
						</a>
					</div>
					</div>
				</div>
				<br/>
				<div id="maingrid" ui-grid="riesgopropiedadc.gridOptions" ui-grid-save-state 
						ui-grid-move-columns ui-grid-resize-columns ui-grid-selection ui-grid-pinning ui-grid-pagination class="grid">
					<div class="grid_loading" ng-hide="!riesgopropiedadc.mostrarcargando">
				  	<div class="msg">
				      <span><i class="fa fa-spinner fa-spin fa-4x"></i>
						  <br /><br />
						  <b>Cargando, por favor espere...</b>
					  </span>
					</div>
				  </div>
				</div>
				<ul uib-pagination total-items="riesgopropiedadc.totalCooperantes" 
						ng-model="riesgopropiedadc.paginaActual" 
						max-size="riesgopropiedadc.numeroMaximoPaginas" 
						items-per-page="riesgopropiedadc.elementosPorPagina"
						first-text="Primero"
						last-text="Último"
						next-text="Siguiente"
						previous-text="Anterior"
						class="pagination-sm" boundary-links="true" force-ellipses="true"
						ng-change="riesgopropiedadc.cambioPagina()"
				></ul>
			</div>
    		</shiro:hasPermission>
    		
		</div>
		<div class="row" ng-show="riesgopropiedadc.mostraringreso">
			<h4 ng-hide="!riesgopropiedadc.esnuevo">Nueva Propiedad</h4>
			<h4 ng-hide="riesgopropiedadc.esnuevo">Edición de Propiedad</h4>
			<div class="col-sm-12 operation_buttons" align="right">
				<div class="btn-group">
			        <label class="btn btn-success" ng-click="riesgopropiedadc.guardar()">Guardar</label>
			        <label class="btn btn-primary" ng-click="riesgopropiedadc.irATabla()">Ir a Tabla</label>
    			</div>
    		</div>
			
			<div class="col-sm-12">
				<form>
						<div class="form-group">
							<label for="id">ID</label>
    						<label class="form-control" id="id">{{ riesgopropiedadc.riesgopropiedad.id }}</label>
						</div>
						<div class="form-group">
							<label for="nombre">* Nombre</label>
    						<input type="text" class="form-control" id="nombre" placeholder="Nombre" ng-model="riesgopropiedadc.riesgopropiedad.nombre">
						</div>
						<div class="form-group">
							<label for="nombre">* Tipo dato</label>
							<select class="form-control" ng-model="riesgopropiedadc.riesgopropiedad.datotipoid" >
								<option value="">Seleccione una opción</option>
								<option ng-repeat="opciones in riesgopropiedadc.tipodatos" value="{{opciones.id}}">{{opciones.nombre}}</option>
							</select>
						</div>	
						<div class="form-group">
							<label for="descripcion">Descripción</label>
    						<input type="text" class="form-control" id="descripcion" placeholder="Descripción" ng-model="riesgopropiedadc.riesgopropiedad.descripcion">
						</div>
						<div class="form-group">
							<label for="usuarioCreo">Usuario que creo</label>
    						<label class="form-control" id="usuarioCreo">{{ riesgopropiedadc.riesgopropiedad.usuarioCreo }}</label>
						</div>
						<div class="form-group">
							<label for="fechaCreacion">Fecha de creación</label>
    						<label class="form-control" id="fechaCreacion">{{ riesgopropiedadc.riesgopropiedad.fechaCreacion }}</label>
						</div>
						<div class="form-group">
							<label for="usuarioActualizo">Usuario que actualizo</label>
    						<label class="form-control" id="usuarioCreo">{{ riesgopropiedadc.riesgopropiedad.usuarioActualizo }}</label>
						</div>
						<div class="form-group">
							<label for="fechaActualizacion">Fecha de actualizacion</label>
    						<label class="form-control" id="usuarioCreo">{{ riesgopropiedadc.riesgopropiedad.fechaActualizacion }}</label>
						</div>
				</form>
			</div>
			<div align="center">Los campos marcados con * son obligatorios</div>
			<div class="col-sm-12 operation_buttons" align="right">
				<div class="col-sm-12 operation_buttons" align="right">
					<div class="btn-group">
				        <label class="btn btn-success" ng-click="riesgopropiedadc.guardar()">Guardar</label>
				        <label class="btn btn-primary" ng-click="riesgopropiedadc.irATabla()">Ir a Tabla</label>
	    			</div>
	    		</div>
    		</div>
		</div>
	</div>