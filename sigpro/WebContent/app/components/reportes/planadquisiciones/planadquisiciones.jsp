<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ page import="org.apache.shiro.SecurityUtils" %>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	
	<style>
	    .ui-grid-tree-header-row {
	        font-weight: normal !important;
	    }
	   
	    .ui-grid-tree-padre {
	        font-weight: bold;
	    }
	</style>
	
	<div ng-controller="planAdquisicionesController as controller" class="maincontainer all_page" id="title">
		<shiro:lacksPermission name="24010">
			<p ng-init="controller.redireccionSinPermisos()"></p>
		</shiro:lacksPermission>
		<div class="panel panel-default">
		  <div class="panel-heading"><h3>Plan de adquisiciones AÑO FISCAL {{controller.anio}}</h3></div>
		</div>
	    <br>
	    <div class="container" style="width: 100%">
	    	<div style="height: 100%; width: 100%; height: 20%">
	    		<div class="row">
	    			<div class="form-group col-sm-3">
						<select  class="inputText" ng-model="controller.prestamo"
							ng-options="a.text for a in controller.prestamos" 
							ng-change="controller.getComponentes(controller.prestamo.value);"></select>
						<label for="tObjeto" class="floating-label">Préstamos</label>
	    			</div>
					<div class="form-group col-sm-2" >
						<label class="btn btn-default" ng-click="controller.generar();" uib-tooltip="Generar" 
							tooltip-placement="bottom">
						<span class="glyphicon glyphicon-new-window"></span></label>
					</div>
					<div class="operation_buttons" align="right">
						<div class="btn-group">
							<label class="btn btn-primary"  ng-click="controller.exportarExcel()" uib-tooltip="Exportar" ng-hide="!controller.exportar">
							<span class="glyphicon glyphicon glyphicon-export" aria-hidden="true">&nbsp;Exportar</span></label>
						</div>
					</div>
				</div>
	    	</div>
	    	<div style="height: 100%; width: 100%">
		    	<div id="grid1" ui-grid="controller.gridOptions" style="width: 100%; height: 400px"
					ui-grid-grouping 
					ui-grid-edit 
					ui-grid-row-edit 
					ui-grid-resize-columns 
					ui-grid-cellNav 
					ui-grid-pinning
					class="grid">
				</div>	
	    	</div>
	    </div>
</div>