<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="org.apache.shiro.SecurityUtils" %>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<div ng-controller="recursopropiedadController as recursopropiedadc" class="maincontainer all_page" id="title">
		<h3>Propiedad Recurso</h3><br/>
		<div class="row" align="center" ng-hide="recursopropiedadc.mostraringreso">
			<div class="col-sm-12 operation_buttons" align="right">
				<div class="btn-group">
			       <shiro:hasPermission name="crearCooperante">
			       		<label class="btn btn-primary" ng-click="recursopropiedadc.nueva()">Nueva</label>
			       </shiro:hasPermission>
			       <shiro:hasPermission name="editarCooperante"><label class="btn btn-primary" ng-click="recursopropiedadc.editar()">Editar</label></shiro:hasPermission>
			       <shiro:hasPermission name="eliminarCooperante">
			       		<label class="btn btn-primary" ng-click="recursopropiedadc.borrar()">Borrar</label>
			       </shiro:hasPermission>


    			</div>
    		</div>
    		<shiro:hasPermission name="verCooperante">
    		<div class="col-sm-12" align="center">
    			<div style="height: 35px;">
					<div style="text-align: right;"><div class="btn-group" role="group" aria-label="">
						<a class="btn btn-default" href ng-click="recursopropiedadc.reiniciarVista()" role="button" uib-tooltip="Reiniciar la vista de la tabla" tooltip-placement="left"><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span></a>
					</div>
					</div>
				</div>
				<br/>
				<div id="maingrid" ui-grid="recursopropiedadc.gridOptions" ui-grid-save-state
						ui-grid-move-columns ui-grid-resize-columns ui-grid-selection ui-grid-pinning ui-grid-pagination class="grid">
					<div class="grid_loading" ng-hide="!recursopropiedadc.mostrarcargando">
				  	<div class="msg">
				      <span><i class="fa fa-spinner fa-spin fa-4x"></i>
						  <br /><br />
						  <b>Cargando, por favor espere...</b>
					  </span>
					</div>
				  </div>
				</div>
				<ul uib-pagination total-items="recursopropiedadc.totalRecursoPropiedades"
						ng-model="recursopropiedadc.paginaActual"
						max-size="recursopropiedadc.numeroMaximoPaginas"
						items-per-page="recursopropiedadc.elementosPorPagina"
						first-text="Primero"
						last-text="Último"
						next-text="Siguiente"
						previous-text="Anterior"
						class="pagination-sm" boundary-links="true" force-ellipses="true"
						ng-change="recursopropiedadc.cambioPagina()"
				></ul>
			</div>
    		</shiro:hasPermission>

		</div>
		<div class="row" ng-show="recursopropiedadc.mostraringreso">
			<h4 ng-hide="!recursopropiedadc.esnuevo">Nueva Propiedad</h4>
			<h4 ng-hide="recursopropiedadc.esnuevo">Edición de Propiedad</h4>
			<div class="col-sm-12 operation_buttons" align="right">
				<div class="btn-group">
			        <label class="btn btn-success" ng-click="recursopropiedadc.guardar()">Guardar</label>
			        <label class="btn btn-primary" ng-click="recursopropiedadc.irATabla()">Ir a Tabla</label>
    			</div>
    		</div>

			<div class="col-sm-12">
				<form>
						<div class="form-group">
							<label for="id">ID</label>
    						<label class="form-control" id="id">{{ recursopropiedadc.recursopropiedad.id }}</label>
						</div>
						<div class="form-group">
							<label for="nombre">* Nombre</label>
    						<input type="text" class="form-control" id="nombre" placeholder="Nombre" ng-model="recursopropiedadc.recursopropiedad.nombre">
						</div>
						<div class="form-group">
							<label for="nombre">* Tipo dato</label>
							<select class="form-control" 
								ng-model="recursopropiedadc.datoTipoSeleccionado" 
								ng-options="opcion as opcion.nombre for opcion in recursopropiedadc.tipodatos"></select>
						</div>
						<div class="form-group">
							<label for="descripcion">Descripción</label>
    						<input type="text" class="form-control" id="descripcion" placeholder="Descripción" ng-model="recursopropiedadc.recursopropiedad.descripcion">
						</div>
						<div class="form-group">
							<label for="usuarioCreo">Usuario que creo</label>
    						<label class="form-control" id="usuarioCreo">{{ recursopropiedadc.recursopropiedad.usuarioCreo }}</label>
						</div>
						<div class="form-group">
							<label for="fechaCreacion">Fecha de creación</label>
    						<label class="form-control" id="fechaCreacion">{{ recursopropiedadc.recursopropiedad.fechaCreacion }}</label>
						</div>
						<div class="form-group">
							<label for="usuarioActualizo">Usuario que actualizo</label>
    						<label class="form-control" id="usuarioCreo">{{ recursopropiedadc.recursopropiedad.usuarioActualizo }}</label>
						</div>
						<div class="form-group">
							<label for="fechaActualizacion">Fecha de actualizacion</label>
    						<label class="form-control" id="usuarioCreo">{{ recursopropiedadc.recursopropiedad.fechaActualizacion }}</label>
						</div>
				</form>
			</div>
			<div align="center">Los campos marcados con * son obligatorios</div>
			<div class="col-sm-12 operation_buttons" align="right">
				<div class="col-sm-12 operation_buttons" align="right">
					<div class="btn-group">
				        <label class="btn btn-success" ng-click="recursopropiedadc.guardar()">Guardar</label>
				        <label class="btn btn-primary" ng-click="recursopropiedadc.irATabla()">Ir a Tabla</label>
	    			</div>
	    		</div>
    		</div>
		</div>
	</div>