<h2>Nueva Persona</h2>

<#include "show_errors.ftl">

<form method="post" action="/personas" role="form">

   <div class="form-group has-button">
        <label class="control-label" for="tipoDocumentoid"> Tipo Documento</label>
        <select class="form-control chosen-select" data-placeholder="Elegí una opción" name="tipoDocumentoId">
            <option value=""> ... </option>
            <#list model.tipodocumentosList as tipodocumento>
				<option value="${tipodocumento.uuid}" <#if model.tipoDocumentoId?? && model.tipoDocumentoId == tipodocumento.uuid>selected</#if>>${tipodocumento.nombre}</option>
			</#list>
        </select>
    </div>

 
    <div class="form-group">
		<label for="valorDocumento">Numero Documento</label>
        <input type="text" class="form-control" id="valorDocumento" name="valorDocumento" placeholder="Ingresar numero de documento..." value="${model.valordocumento!''}" />
	</div>
	
	<div class="form-group">
		<label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre..." value="${model.nombre!''}" />
	</div> 
	
		<div class="form-group">
		<label for="apellido">Apellido</label>
        <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingresar apellido..." value="${model.apellido!''}" />
	</div>
	
		<div class="form-group">
		<label for="telefono">Telefono</label>
        <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Ingresar Telefono..." value="${model.telefono!''}" />
	</div>
		<div class="form-group">
		<label for="correo">Correo</label>
        <input type="text" class="form-control" id="correo" name="correo" placeholder="Ingresar Correo..." value="${model.correo!''}" />
	</div>
	

<input class="btn btn-primary" type="submit" value="Crear" id="btnCrear"/>
	<a class="btn btn-default" href="/personas">Volver</a>
</form>

<script>
$(function() {
	$("#btnCrear").click(function (event) {
		/* validar correo  */
		var correo = document.querySelector("#correo").value;
		if(correo != "") {
			var regex = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			if(!regex.test(correo)) {
				alert("Usar arroba en el correo.");
				return false;
			}
			return true;
		}

		return true;
	});
});
</script>




