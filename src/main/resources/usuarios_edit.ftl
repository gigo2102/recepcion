<h2>Edicion de Usuarios</h2>

<#include "show_errors.ftl">

<form method="post" action="/usuarios/${model.uuid}/" role="form">
	
	<div class="form-group">
		<label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" value="${model.nombre!''}" />
	</div>
	
	<div class="form-group">
		<label for="correo">Correo</label>
        <input type="text" class="form-control" id="correo" name="correo" value="${model.correo!''}" />
	</div>
	
	<div class="form-group">
		<label for="pass">Contrasena</label>
        <input type="text" class="form-control" id="pass" name="pass" value="${model.pass!''}" />
	</div>
	 
    <div class="form-group has-button">
        <label class="control-label" for="areaId">Area</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="areaId">
            <option value=""> ... </option>
            <#list model.areasList as area>
				<option value="${area.uuid}" <#if model.areaId == area.uuid>selected</#if>>${area.nombre}</option>
			</#list>
        </select>
    </div>
	
	<input class="btn btn-primary" type="submit" value="Guardar" />
	<a class="btn btn-default" href="/usuarios">Volver</a>
</form>