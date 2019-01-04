<h2>Listado de Motivos</h2>


<form method="get" action="/motivos" role="form">
	<div class="form-group">
		<label for="nombre">Motivo a buscar</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar motivo a buscar..." value="" />
	</div>
	<input class="btn btn-primary" type="submit" value="Buscar" />
</form>

<table class="table table-striped">
	<tr>
		<th>Nombre</th>
		<th>Area</th>
		<th>Acciones</th>
	</tr>
	<#list model as unMotivo>
		<tr>
        	<td> ${unMotivo.nombre}</td>
        	<td> ${unMotivo.area.nombre}</td> 
	        <td> 
	         <a class="info" " href="/motivos/${unMotivo.uuid}/edit_form">Editar</a>
	            <a class="info" onclick="return confirm('Esta seguro que desea borrar el Motivo?')";  href="/motivos/${unMotivo.uuid}/delete">Borrar</a>
 
	        </td>
	    </tr>
	</#list>
</table>
<a class="btn btn-lg btn-default" href="/motivos/create_form">Nuevo</a>