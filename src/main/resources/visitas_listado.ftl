<h2>Listado de Visitas</h2>

<form method="get" action="/visitas" role="form">
	<div class="form-group">
		<label for="valordocumento">Visita a buscar</label>
        <input type="text" class="form-control" id="valordocumento" name="valordocumento" placeholder="Ingresar persona a buscar..." value="" />
	</div>
	<input class="btn btn-primary" type="submit" value="Buscar" />
</form>
          


<table class="table table-striped">
	<tr>
		<th>Tipo de Visita</th>
		<th>Nombre</th>
		<th>Area</th>
		<th>Motivo</th>
		<th>Observacion</th>
	</tr>
	<#list model as unVisita>
		<tr>
		   <td> ${unVisita.tipovisita.nombre}</td>
		   	<td> ${unVisita.persona.nombre}</td>
		   	  <td> ${unVisita.motivo.nombre}</td> 
		   <td> ${unVisita.area.nombre}</td> 
        	<td> ${unVisita.observaciones}</td>
       
	    </tr>
	</#list>
</table>

<tr>
	<ul class="pagination">
 <li class="disabled"><a href="personas" class="glyphicon glyphicon-chevron-left"></a></li>
 <li class="active"><a href="personas">1 <span class="sr-only">(current)</span></a></li>
 <li><a href="#">2</a></li>
 <li><a href="#">3</a></li>
 
 <li><a href="#" class="glyphicon glyphicon-chevron-right"></a></li>
</ul>
  </tr>
  
<a class="btn btn-lg btn-default" href="/visitas/create_form">Nuevo</a>