<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<style type="text/css">
	
		.botonTodo{
				border-bottom-color: #2e6da4;
	    		border-bottom-width: 3px;
		}
		.botonProyectos{
			border-bottom-color: #ff831f;
    		border-bottom-width: 3px;
		}
		
		.botonComponentes{
			border-bottom-color: #9ed728;
    		border-bottom-width: 3px;
		}
		
		.botonProductos{
			border-bottom-color: #f1498e;
    		border-bottom-width: 3px;
		}
		
		.botonSubproductos{
			border-bottom-color: #f9f63b;
    		border-bottom-width: 3px;
		}
		
		.botonActividades{
			border-bottom-color: #63d0ef;
    		border-bottom-width: 3px;
		}
		
		
		
	</style>	
	<%@ page import="org.apache.shiro.SecurityUtils" %>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<div ng-controller="mapaController as mapac" class="maincontainer all_page" id="title">
		
	
	    
	    <script type="text/ng-template" id="modalInfo.html">
        	<div class="modal-header">
            	<h3 class="modal-title" id="modal-title">Datos de {{infoc.objeto.nombreOjetoTipo}}</h3>
        	</div>
        	<div class="modal-body" id="modal-body">

				
				<div class="row">
					<div class="col-sm-6" style="text-align: right">
						<label for="id">Id</label>
					</div>
					<div class="col-sm-6">
						 {{infoc.objeto.id }}
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6" style="text-align: right">
						<label for="nombre">Nombre</label>
					</div>
					<div class="col-sm-6">
						{{ infoc.objeto.nombre }}
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6" style="text-align: right">
						<label for="nombre">Usuario que creo</label>
					</div>
					<div class="col-sm-6">
						{{ infoc.objeto.usuarioCreo }}
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6" style="text-align: right">
						<label for="nombre">Fecha de creación</label>
					</div>
					<div class="col-sm-6">
						{{ infoc.objeto.fechaCreacion }}
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6" style="text-align: right">
						<label for="nombre">Usuario que actualizo</label>
					</div>
					<div class="col-sm-6">
						{{ infoc.objeto.usuarioactualizo }}
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6" style="text-align: right">
						<label for="nombre">Fecha de actualizacion</label>
					</div>
					<div class="col-sm-6">
						{{ infoc.objeto.fechaactualizacion }}
					</div>
				</div>
				

				</div>

        	</div>
        	<div class="modal-footer">
            	<button class="btn btn-primary" type="button" ng-click="infoc.ok()">Aceptar</button>
        	</div>
    </script>
	    
	    <h3>Mapa {{ titulo }}</h3>
	    
	    
	    <div class="operation_buttons" align="right">
	    	 <div class="checkbox">
			    <label>
			      <input type="checkbox" ng-model="mostrarTodo" ng-click="mostrar(0)">
			      Todos
			    </label>
			    <label>
			      <input type="checkbox" ng-model="mostrarProyectos" ng-click="mostrar(1)">
			      Proyectos
			    </label>
			    <label>
			      <input type="checkbox" ng-model="mostrarComponentes" ng-click="mostrar(2)">
			      Componentes
			    </label>
			    <label>
			      <input type="checkbox" ng-model="mostrarProductos" ng-click="mostrar(3)">
			      Productos
			    </label>
			    <label>
			      <input type="checkbox" ng-model="mostrarSubproductos" ng-click="mostrar(4)">
			      Subproductos
			    </label>
			    <label>
			      <input type="checkbox" ng-model="mostrarActividades" ng-click="mostrar(5)">
			      Actividades
			    </label>
			  </div>
	    	 
	    </div>
	    
        <div  >
        	  <div class="modal-body" style="height: 450px; ">
	        	<ui-gmap-google-map id="mainmap"  center="map.center" zoom="map.zoom" options="map.options" events="map.events"  >
	      			
					<div ng-repeat="marca in marcas track by marca.id">
						<div ng-switch on ="marca.objetoTipoId">
							<div ng-switch-when="1">
								<ui-gmap-marker ng-if="mostrarTodo || mostrarProyectos" 
									 idkey="marca.id" coords="marca.posicion" icon = "marca.icon"
									 click="abrirInformacion(marca.objetoId,marca.objetoTipoId)" 
									 options="{title:marca.nombre}"  
									 >
								 </ui-gmap-marker>
							 </div>
							 <div ng-switch-when="2">
								<ui-gmap-marker ng-if="mostrarTodo || mostrarComponentes" 
									 idkey="marca.id" coords="marca.posicion" icon = "marca.icon"
									 click="abrirInformacion(marca.objetoId,marca.objetoTipoId)" 
									 options="{title:marca.nombre}"  
									 >
								 </ui-gmap-marker>
							 </div>
							 <div ng-switch-when="3">
								<ui-gmap-marker ng-if="mostrarTodo || mostrarProductos" 
									 idkey="marca.id" coords="marca.posicion" icon = "marca.icon"
									 click="abrirInformacion(marca.objetoId,marca.objetoTipoId)" 
									 options="{title:marca.nombre}"  
									 >
								 </ui-gmap-marker>
							 </div>
							 <div ng-switch-when="4">
								<ui-gmap-marker ng-if="mostrarTodo || mostrarSubproductos" 
									 idkey="marca.id" coords="marca.posicion" icon = "marca.icon"
									 click="abrirInformacion(marca.objetoId,marca.objetoTipoId)" 
									 options="{title:marca.nombre}"  
									 >
								 </ui-gmap-marker>
							 </div>
							 <div ng-switch-when="5">
								<ui-gmap-marker ng-if="mostrarTodo || mostrarActividades" 
									 idkey="marca.id" coords="marca.posicion" icon = "marca.icon"
									 click="abrirInformacion(marca.objetoId,marca.objetoTipoId)" 
									 options="{title:marca.nombre}"  
									 >
								 </ui-gmap-marker>
							 </div>
						</div>
					</div>
				</ui-gmap-google-map>
			</div>
		</div>
		
	   
	</div>
