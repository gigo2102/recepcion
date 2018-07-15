<h2>Nueva Visita</h2>

<#include "show_errors.ftl">

<form method="post" action="/motivos" role="form">
	<div class="form-group">
		<label for="nombre">Descripcion</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre..." value="${model.nombre!''}" />
	</div>
	 
    <div class="form-group has-button">
        <label class="control-label" for="inputSuccess2">Area</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="areaId">
            <option value=""> ... </option>
            <#list model.areasList as area>
				<option value="${area.uuid}">${area.nombre}</option>
			</#list>
        </select>
    </div>
	<input class="btn btn-primary" type="submit" value="Crear" />
	<a class="btn btn-default" href="/motivos">Volver</a>
</form>