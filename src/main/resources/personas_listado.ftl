<h2>Listado de Personas</h2>

<form method="get" action="/personas" role="form">
	<div class="form-group">
		<label for="apellido">Persona a buscar</label>
        <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingresar persona a buscar..." value="" />
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
<tr>
	<ul class="pagination">
 <li class="disabled"><a href="/personas" class="glyphicon glyphicon-chevron-left"></a></li>
 <li class="active"><a href="/personas">1 <span class="sr-only">(current)</span></a></li>
 <li><a href="#">2</a></li>
 <li><a href="#">3</a></li>
 
 <li><a href="#" class="glyphicon glyphicon-chevron-right"></a></li>
</ul>
  </tr>

<a class="btn btn-lg btn-default" href="/personas/create_form">Nuevo</a>