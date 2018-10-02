<h2>Listado de Visitas</h2>

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
							tipoVisitaNombre: { type: 'string' }
                		}
                	}
                },
                pageSize: 10,
                serverPaging: true,
                serverFiltering: true,
                serverSorting: true
            },
            height: 550,
            groupable: false,
            sortable: true,
            filterable: true,
            pageable: {
                refresh: true,
                pageSizes: false,
                buttonCount: 5
            },
            columns: [
				{ field: "uuid", title: "Id" },
				{ field: "observaciones", title: "Observaciones" },
				{ field: "areaNombre", title: "Area" },
				{ field: "motivoNombre", title: "Motivo" },
				{ field: "personaNombre", title: "Nombre" },
				{ field: "personaApellido", title: "Apellido" },
				{ field: "personaValorDocumento", title: "Documento" },
				{ field: "tipoVisitaNombre", title: "Tipo Visita" }
			]
        });
    });
</script>

<a class="btn btn-lg btn-default" href="/visitas/create_form">Nuevo</a>