<h2>Listado de Tipos de documento</h2>


<table class="table table-striped">
	<tr>
		<th>Nombre </th>
		<th> Acciones</th>
	</tr>
	<#list model as unTipoDocumento>
		<tr>
        	<td> ${unTipoDocumento.nombre}</td> 
	        <td> 
	         <a class="info" " href="/tipodocumentos/${unTipoDocumento.uuid}/edit_form">Editar</a>
	            <a class="info" onclick="return confirm('Esta seguro que desea borrar el tipo de documento?')";  href="/tipodocumentos/${unTipoDocumento.uuid}/delete">Borrar</a>
	        </td>
	    </tr>
	</#list>
</table>
<a class="btn btn-lg btn-default" href="/tipodocumentos/create_form">Nuevo</a>