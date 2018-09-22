<h2>Nuevo Usuario</h2>

<#include "show_errors.ftl">

<form method="post" action="/usuarios" role="form">
	<div class="form-group">
		<label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre..." value="${model.nombre!''}" />
	</div>
	     <div class="form-group">
	     <label for="correo">Correo</label>
	    <input type="text" class="form-control" id="correo" name="correo" placeholder="Ingresar correo..." value="${model.correo!''}" />
	</div>
	       <div class="form-group">
	        <label for="pass">Contraseña</label>
		    <input type="text" class="form-control" id="pass" name="pass" placeholder="Ingresar contraseña..." value="${model.pass!''}" />
	</div>
	
	    <div class="form-group has-button">
        <label class="control-label" for="areaid">Area</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="areaid">
            <option value=""> ... </option>
            <#list model.areasList as area>
				<option value="${area.uuid}">${area.nombre}</option>
			</#list>
        </select>
    </div>
    
	<input class="btn btn-primary" type="submit" value="Crear" />
	<a class="btn btn-default" href="/usuarios">Volver</a>
</form>


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
    })
    
    </script>

