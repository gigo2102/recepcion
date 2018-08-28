<h2>Nueva Visita</h2>

<#include "show_errors.ftl">


<form method="get" action="/visitas" role="form">

    <div class="form-group has-button">
        <label class="control-label" for="tipovisitaId">Tipo Visita</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="tipovisitaId">
            <option value=""> ... </option>
            <#list model.tipovisitasList as tipovisita>
				<option value="${tipovisita.uuid}">${tipovisita.nombre}</option>
			</#list>
        </select>
    </div>
    
	<div class="form-group">
		<label for="nombre">Persona a buscar</label>
        <input type="text" id="nombre" name="nombre" />
	</div>

	<input class="btn btn-primary" type="submit" value="Buscar" />
	<a class="btn btn-default" href="/personas/create_form">Agregar Persona</a>

    <div class="form-group has-button">
        <label class="control-label" for="areaid">Area</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="areaid">
            <option value=""> ... </option>
            <#list model.areasList as area>
				<option value="${area.uuid}">${area.nombre}</option>
			</#list>
        </select>
    </div> 

	<div class="form-group">
		<label for="nombre">Observaciones</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar observacion..." value="${model.nombre!''}" />
	</div>
	 

	<input class="btn btn-primary" type="submit" value="Crear" />
	<a class="btn btn-default" href="/visitas">Volver</a>

</form>


  <script>
	  $( function() {	 
	    $( "#nombre" ).autocomplete({
	      source: "/personas/search",
	      minLength: 2,
	      response: function( event, ui ) {
	        ui.content = ui.content.map(function(x){
	        	var v = x.tipoDocumento.nombre + " " + x.valorDocumento +
	        				" - " + x.nombre + " " + x.apellido; 
	        	return {
	        				id: x.uuid,
	        				value: x.uuid,
	        				label: v
        				};
	        });
	        
	        console.log(ui.content);
	      }
	    });
	  } );
  </script>