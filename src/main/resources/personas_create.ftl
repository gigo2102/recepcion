<h2>Nueva Persona</h2>

<#include "show_errors.ftl">

<form method="post" action="/personas" role="form">

    <div class="form-group has-button">
        <label class="control-label" for="tipodocumentosid">Tipo de Documento</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="tipodocumentoid">
            <option value=""> ... </option>
            <#list model.tipodocumentosList as tipodocumento>
				<option value="${tipodocumento.uuid}">${tipodocumento.nombre}</option>
			</#list>
        </select>
    </div>
    <div class="form-group">
		<label for="nombre">Numero Documento</label>
        <input type="text" class="form-control" id="valordocumento" name="valordocumento" placeholder="Ingresar numero de documento..." value="${model.valordocumento!''}" />
	</div>
	
	<div class="form-group">
		<label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre..." value="${model.nombre!''}" />
	</div> 
	
		<div class="form-group">
		<label for="apellido">Apellido</label>
        <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingresar apellido..." value="${model.apellido!''}" />
	</div>
	
		<div class="form-group">
		<label for="telefono">Telefono</label>
        <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Ingresar Telefono..." value="${model.telefono!''}" />
	</div>
	
		<div class="form-group">
		<label for="correo">Correo</label>
        <input type="text" class="form-control" id="correo" name="correo" placeholder="Ingresar Correo..." value="${model.correo!''}" />
	</div>
	
	
 
	<input class="btn btn-primary" type="submit" value="Crear" />
	<a class="btn btn-default" href="/personas">Volver</a>
</form>