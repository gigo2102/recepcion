<h2>Listado de Tipos de Visitas</h2>



<table class="table table-striped">
	<tr>
		<th>Nombre </th>
		<th> Acciones</th>
	</tr>
	<#list model as unTipoVisita>
		<tr>
        	<td> ${unTipoVisita.nombre}</td> 
	        <td> 
	         <a class="info" " href="/tipovisitas/${unTipoVisita.uuid}/edit_form">Editar</a>
	           <a class="info" onclick="return confirm('Esta seguro que desea borrar el tipo de visita?')";  href="/tipovisitas/${unTipoVisita.uuid}/delete">Borrar</a>
 
	        </td>
	    </tr>
	</#list>
</table>
<a class="btn btn-lg btn-default" href="/tipovisitas/create_form">Nuevo</a>