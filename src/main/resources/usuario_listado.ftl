<h2>Listado de Usuarios</h2>

<table class="table table-striped">
	<tr>
		<th>Nombre</th>
		<th>Correo</th>
		<th>Contraseña</th>
		<th>Area</th>
		<th>Acciones</th>
	</tr>
	<#list model as unUsuario>
		<tr>
		   <td> ${unUsuario.area.nombre}</td> 
        	<td> ${unUsuario.nombre}</td>
        	<td> ${unUsuario.correo}</td>
        	<td> ${unUsuario.pass}</td>
        	
	        <td> 
	        <a class="info" " href="/usuarios/${unUsuario.uuid}/edit_form">Editar</a>
	            <a class="info" onclick="return confirm('Esta seguro que desea borrar este usuario?')";  href="/usuarios/${unUsuario.uuid}/delete">Borrar</a>
 
	        </td>
	    </tr>
	</#list>
</table>
<a class="btn btn-lg btn-default" href="/usuarios/create_form">Nuevo</a>