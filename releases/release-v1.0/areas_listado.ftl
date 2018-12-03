<h2>Listado de Areas</h2>

<form method="get" action="/areas" role="form">
	<div class="form-group">
		<label for="nombre">Area a buscar</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar area a buscar..." value="" />
	</div>
	<input class="btn btn-primary" type="submit" value="Buscar" />
</form>

<table class="table table-striped">
	<tr>
		<th>Nombre </th>
		<th> Acciones</th>
	</tr>
	<#list model as unArea>
		<tr>
        	<td> ${unArea.nombre}</td> 
	        <td> 
	         <a class="info" " href="/areas/${unArea.uuid}/edit_form">Editar</a>
	            <a class="info" onclick="return confirm('Esta seguro que desea borrar el area?')";  href="/areas/${unArea.uuid}/delete">Borrar</a>
 
	        </td>
	    </tr>
	</#list>
</table>
<a class="btn btn-lg btn-default" href="/areas/create_form">Nuevo</a>