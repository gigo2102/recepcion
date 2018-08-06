<h2>Listado de Usuarios</h2>

<table class="table table-striped">
	<tr>
		<th>Nombre</th>
		<th>correo</th>
		<th>pass</th>
		<th>Area</th>
		<th>Acciones</th>
	</tr>
	<#list model as unUsuario>
		<tr>
        	<td> ${unUsuario.nombre}</td>
        	<td> ${unUsuario.pass}</td>
        	<td> ${unUsuario.correo}</td>
        	<td> ${unUsuario.areaid.nombre}</td> 
	        <td> 
	         <a class="info" " href=" ">Editar</a>
	            <a class="info" onclick="return confirm('Esta seguro que desea borrar el Motivo?')";  href=" ">Borrar</a>
 
	        </td>
	    </tr>
	</#list>
</table>
<a class="btn btn-lg btn-default" href="/usuarios/create_form">Nuevo</a>