<table id="lista-tele" class="table"></table>
<script>
	function buscarVisitasParaTelevisor() {
		//$.getJSON('/televisor/ws_pantalla', function (ultimasVisitas){
			var ultimasVisitas = 
				[
				   {
				      "persona":{
				         "nombre":"Seba",
				         "apellido":"Alonso"
				      },
				      "area":{
				         "nombre":"Finanzas"
				      }
				   },
				   {
				      "persona":{
				         "nombre":"Gigo",
				         "apellido":"Caputo"
				      },
				      "area":{
				         "nombre":"Legales"
				      }
				   }
				];
				
			var elem = $("#lista-tele");
			elem.html("");
			ultimasVisitas.forEach(function(visita){
				var t = visita.persona.nombre + ' ' + visita.persona.apellido + ' - Area: ' + visita.area.nombre;
				$("<tr />", { html: "<td>" + t + "</td>", style: 'background-color:#ffff66;' }).appendTo(elem);
			});
		//});
	}

	$(function(){
		setInterval(buscarVisitasParaTelevisor, 2000);
	});
</script>
