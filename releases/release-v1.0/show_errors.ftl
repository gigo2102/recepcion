<#if (errors)??>
	<div>
		<ul style="color:#e74c3c;">
			<#list errors as e>
				<li>ERROR: ${e}</li>
			</#list>
		</ul>
	</div>
</#if>
