<h2>Nueva Visita</h2>

<#include "show_errors.ftl">


<form method="post" action="/visitas" role="form">

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
        <input type="hidden" id="personaId" name="personaId" />
	</div>

	<a class="btn btn-default" href="/personas/create_form" target="_blank">Agregar Persona</a>

    <div class="form-group has-button">
        <label class="control-label" for="areaid">Area</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="areaId">
            <option value=""> ... </option>
            <#list model.areasList as area>
				<option value="${area.uuid}">${area.nombre}</option>
			</#list>
        </select>
    </div> 
    
       <div class="form-group has-button">
        <label class="control-label" for="motivoId">Motivo</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="motivoId">
            <option value=""> ... </option>
            <#list model.motivosList as motivo>
				<option value="${motivo.uuid}">${motivo.nombre}</option>
			</#list>
        </select>
    </div>
    
	<div class="form-group">
		<label for="nombre">Observaciones</label>
        <input type="text" class="form-control" id="observaciones" name="observaciones" placeholder="Ingresar observacion..." value="${model.nombre!''}" />
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
	        ui.content.forEach(function(x){
	        	var v = x.tipoDocumento.nombre + " " + x.valorDocumento +
	        				" - " + x.nombre + " " + x.apellido; 
	        	x.value = v;
				x.label = v;
	        });
	      },
	      select: function(event, ui){
	      	$("#personaId").val(ui.item.uuid);
	      }
	    });
	  } );
  </script>
  
   <script>
	  $( function() {
 
  
    var correo = document.querySelector("#correo").value;
    
    /* validar correo  */
 if(correo != "") {
 
        var regex = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
 
        if(!regex.test(#correo)) {
 
            alert("Usar arroba.");
 
            return false;
 
        }
 
    }
    </script>
  
  
  