<style>
	.kendoGrid {
		width: 500px;
		background-color:#C0392B;
	}
	.sinAtender{
		background-color:#f4e242;
	}     
</style>

<script id="template-fila" type="text/x-kendo-template">
    <tr data-uid="#: data.uuid #"
			#if(!data.fueAtendido){#
	            class="sinAtender"
	        #}#
		>
        <td>#:data.tipoVisitaNombre #</td>
        <td>#:data.areaNombre #</td>
        <td>#:data.motivoNombre #</td>
        <td>#:data.personaValorDocumento #</td>
        <td>#:data.personaNombre #</td>
        <td>#:data.personaApellido #</td>
        <td>#:data.observaciones #</td>
        <td>
			#if(!data.fueAtendido){#
	            <a href='/visitas/atender?uuid=#:data.uuid #'>Atender</a>
	        #}#
        </td>
    </tr>
</script>

<div id="grid"></div>
<script>
    $(document).ready(function () {
        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                    	url: '/visitas/kendo_search',
                    	type: 'get',
                    	dataType: 'json',
                    	contentType: 'application/json'
                    },
                    parameterMap: mapKendoParameters
                },
                schema: {
                	data: 'data',
                	total: 'total',
                	model: {
                		fields: {
                			uuid: { type: 'string' },
                			observaciones: { type: 'string' },
							areaNombre: { type: 'string' },
							motivoNombre: { type: 'string' },
							personaNombre: { type: 'string' },
							personaApellido: { type: 'string' },
							personaValorDocumento: { type: 'string' },
							tipoVisitaNombre: { type: 'string' },
							fueAtendido: { type: 'boolean' }
                		}
                	}
                },
                pageSize: 10,
                serverPaging: true,
                serverFiltering: true,
                serverSorting: true
            },
        	rowTemplate: kendo.template($("#template-fila").html()),
            height: 380,
            groupable: false,
            sortable: true,
            filterable: true,
            pageable: {
                refresh: true,
                pageSizes: false,
                buttonCount: 5
            },
            columns: [
				{ title: "Tipo Visita" },
				{ title: "Area" },
				{ title: "Motivo" },
				{ title: "Documento" },
				{ title: "Nombre" },
				{ title: "Apellido" },
				{ title: "Observaciones" },
				{ title: "Acciones" }		
			]
        });
    });
    
    var ds = new kendo.data.DataSource({
    data: [{
        personaNombre: "Teo",
        areaNombre:"Legales"
    }],
});
</script>

<a class="btn btn-lg btn-default" href="/visitas/create_form">Nuevo</a>