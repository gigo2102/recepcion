

<#include "show_errors.ftl">


<form method="post" action="/login" role="form">
	<div class="form-group">
		<label for="nombre">Usuario</label>
        <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Ingresar nombre de usuario..." value="${model.usuario!''}" />
	</div>
	       <div class="form-group">
	        <label for="pass">Contraseña</label>
		    <input type="password" class="form-control" id="password" name="password" placeholder="Ingresar contraseña..." value="${model.password!''}" />
	</div>
    
	<input class="btn btn-primary" type="submit" value="Ingresar" />
</form>

