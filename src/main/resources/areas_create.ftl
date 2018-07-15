<h2>Nueva Area</h2>

<#include "show_errors.ftl">

<form method="post" action="/areas" role="form">
	<div class="form-group">
		<label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre..." value="${model.nombre!''}" />
	</div>
	<input class="btn btn-primary" type="submit" value="Crear" />
	<a class="btn btn-default" href="/areas">Volver</a>
</form>