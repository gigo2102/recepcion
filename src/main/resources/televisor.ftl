<table id="lista-tele" class="table"></table>
<script>
	function buscarVisitasParaTelevisor() {
		$.getJSON('/televisor/ws_pantalla', function (ultimasVisitas){
			var elem = $("#lista-tele");
			elem.html("");
			ultimasVisitas.forEach(function(visita){
				var t = visita.persona.nombre + ' ' + visita.persona.apellido + ' - Area: ' + visita.area.nombre;
				$("<tr />", { html: "<td>" + t + "</td>", style: 'background-color:#F7DC6F;' + 'font-family:verdana;' + 'font-size:200%;'}).appendTo(elem);
			});
		});
	}

	$(function(){
		setInterval(buscarVisitasParaTelevisor, 2000);
	});
</script>
