<h2>Listado de Personas</h2>

<form method="get" action="/personas" role="form">
	<div class="form-group">
		<label for="nombre">Persona a buscar</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar persona a buscar..." value="" />
	</div>
	<input class="btn btn-primary" type="submit" value="Buscar" />
</form>


<table class="table table-striped">
	<tr>
	    <th>Tipo DNI</th>
		<th>Numero de Documento</th>
		<th>Nombre</th>
		<th>Apellido</th>
		<th>Correo</th>
		<th>Telefono</th>
		<th>Acciones</th>
	</tr>
	<#list model as unPersona>
		<tr>
		    <td> ${unPersona.tipoDocumento.nombre}</td> 
		    <td> ${unPersona.valorDocumento}</td> 
        	<td> ${unPersona.nombre}</td>
        	<td> ${unPersona.apellido}</td>
        	<td> ${unPersona.correo}</td>
        	<td> ${unPersona.telefono}</td>
        	
	        <td> 
	         <a class="info" " href="/personas/${unPersona.uuid}/edit_form">Editar</a>
	           <a class="info" onclick="return confirm('Esta seguro que desea borrar esta persona?')";  href="/personas/${unPersona.uuid}/delete">Borrar</a>
 
	        </td>
	    </tr>
	</#list>
</table>
<a class="btn btn-lg btn-default" href="/personas/create_form">Nuevo</a>