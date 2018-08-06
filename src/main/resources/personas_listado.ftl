<h2>Listado de Personas</h2>

<table class="table table-striped">
	<tr>
	    <th>Tipo DNI</th>
		<th>Nombre</th>
		<th>Apellido</th>
		<th>Numero documento</th>
		<th>Correo</th>
		<th>Telefono</th>
		<th>Acciones</th>
	</tr>
	<#list model as unPersona>
		<tr>
		    <td> ${unPersona.tipodocumento.nombre}</td> 
		    <td> ${unPersona.valordocumento.nombre}</td> 
        	<td> ${unPersona.nombre}</td>
        	<td> ${unPersona.apellido}</td>
        	<td> ${unPersona.correo}</td>
        	<td> ${unPersona.telefono}</td>
        	
	        <td> 
	         <a class="info" "">Editar</a>
	            <a class="info" onclick="return confirm('Esta seguro que desea borrar esta persona?')";  href=" ">Borrar</a>
 
	        </td>
	    </tr>
	</#list>
</table>
<a class="btn btn-lg btn-default" href="/personas/create_form">Nuevo</a>