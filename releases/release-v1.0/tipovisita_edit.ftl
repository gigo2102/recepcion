<h2>Edicion de Tipo de visita</h2>

<#include "show_errors.ftl">

<form method="post" action="/tipovisitas/${model.uuid}/" role="form">
	<div class="form-group">
		<label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" value="${model.nombre!''}" />
	</div>
	<input class="btn btn-primary" type="submit" value="Guardar" />
	<a class="btn btn-default" href="/tipovisitas">Volver</a>
</form>