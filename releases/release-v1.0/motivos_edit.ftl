<h2>Edicion de Motivo</h2>

<#include "show_errors.ftl">

<form method="post" action="/motivos/${model.uuid}/" role="form">
	<div class="form-group">
		<label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" value="${model.nombre!''}" />
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
	<a class="btn btn-default" href="/motivos">Volver</a>
</form>