<h2>Edicion de Personas</h2>

<#include "show_errors.ftl">

<form method="post" action="/personas/${model.uuid}/" role="form">
	
	<div class="form-group">
		<label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" value="${model.nombre!''}" />
	</div>
	
	<div class="form-group">
		<label for="apellido">Apellido</label>
        <input type="text" class="form-control" id="apellido" name="apellido" value="${model.apellido!''}" />
	</div>
	
	
	<div class="form-group">
		<label for="correo">Correo</label>
        <input type="text" class="form-control" id="correo" name="correo" value="${model.correo!''}" />
	</div>
	
	<div class="form-group">
		<label for="telefono">Telefono</label>
        <input type="text" class="form-control" id="telefono" name="telefono" value="${model.telefono!''}" />
	</div>
	
	  <div class="form-group">
		<label for="valorDocumento">Numero Documento</label>
        <input type="text" class="form-control" id="valorDocumento" name="valorDocumento" placeholder="Ingresar numero de documento..." value="${model.valorDocumento!''}" />
	</div>
	
	 
  
       <div class="form-group has-button">
        <label class="control-label" for="tipoDocumentoId"> Tipo Documento</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="tipoDocumentoId">
            <option value=""> ... </option>
             <#list model.tipoDocumentosList as tipodocumento>
         <option value="${tipodocumento.uuid}" <#if model.tipoDocumentoId == tipodocumento.uuid>selected</#if>>${tipodocumento.nombre}</option>
			</#list>
        </select>
    </div>
	
	<input class="btn btn-primary" type="submit" value="Guardar" />
	<a class="btn btn-default" href="/personas">Volver</a>
</form>