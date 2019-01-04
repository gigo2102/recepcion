<h2>Nuevo Tipo de Visita</h2>

<#include "show_errors.ftl">

<form method="post" action="/tipovisitas" role="form">
	<div class="form-group">
		<label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre..." value="${model.nombre!''}" />
	</div>
	<input class="btn btn-primary" type="submit" value="Crear" />
	<a class="btn btn-default" href="/tipovisitas">Volver</a>
</form>