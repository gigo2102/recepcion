<h2>Listado de Personas</h2>

<div id="grid"></div>
<script>
    $(document).ready(function () {
        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                    	url: '/personas/kendo_search',
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
                			nombre: { type: 'string' },
                			apellido: { type: 'string' },
                			telefono: { type: 'string' },
                			valorDocumento: { type: 'string' }
                		}
                	}
                },
                pageSize: 10,
                serverPaging: true,
                serverFiltering: true,
                serverSorting: true
            },
            height: 350,
            groupable: false,
            sortable: true,
            filterable: true,
            pageable: {
                refresh: true,
                pageSizes: false,
                buttonCount: 5
            },
            columns: [
				{ field: "nombre", title: "Nombre" },
				{ field: "apellido", title: "Apellido" },
				{ field: "telefono", title: "Tel." },
				{ field: "valorDocumento", title: "Doc." },
				{ template: "<a class='' href='/personas/#:data.uuid#/edit_form'>Editar</a>" }
			]
        });
    });
</script>

<a class="btn btn-lg btn-default" href="/personas/create_form">Nuevo</a>