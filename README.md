TODO
====

notificaciones de visitas pendientes
	en visita agregar fueAtendido que por defecto viene en false X
	en el layout usar una fcion javascript que setinterval X
	en cada interval ejecutar un jquery ajax a un webservice json de "pendientes para mi area"
	hacer el ws json que invoque a una query en model, a dicha query pasarle el id de usuario logueado
	la query filtra por usuario y hace join a areas, luego motivos, luego visitas y filtra las visitas no atendidas
	luego con la rta del ws una vez de nuevo en el javascript, hacemos:
		si hay 0 elementos, ocultamos el div html en overlay
		si hay 1 o mas elementos, mostramos el div html en overlay con un <ul> y <li> por visita y un boton de atender
	si apretan el boton atender, vamos a una ruta donde marcamos esa visita con fueAtendida en true
	luego hacemos redirect a una vista de detalle de la visita
	
https://docs.telerik.com/kendo-ui/controls/data-management/grid/how-to/Layout/style-rows-cells-based-on-data-item-values#use-a-row-template
	
https://www.telerik.com/forums/conditionally-changing-the-row-color

.red{
    
  background-color:#C0392B;
}

var ds = new kendo.data.DataSource({
    data: [{
        Nombre: "Teo",
        Area:"Legales"
    }],
    pageSize: 10
});


$("#grid").kendoGrid({
    dataSource: ds,
    rowTemplate: '<tr class="#:Area==\"Legales\"? \"red\" : \"white\"#" data-uuid="#= uuid #"><td>#: Nombre #</td><td>#:ReportClassDescription #</td></tr>'
});

TO READ
=======

css y html con bootstrap y luego bastrap
	https://www.w3schools.com/bootstrap/default.asp
	http://gcba.github.io/BAstrap/#css
java spark
http verbs, requests y response get put post delete
html y xml y json
free marker engine templates
rest web services
kendo grids
junit 4
sql2o
postgres 10
jcommander arguments
loggers
sentry
maven



