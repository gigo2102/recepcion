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


INSTALACION WINDOWS
===================

1) Instalar

	* postgres 9.6 o 10
	* docker toolbox for windows (verificar funcionamiento de docker y docker-compose)

2) agregar en pg_hba.conf al final de todo

	# acceso remoto
	host    all             all              0.0.0.0/0              md5
	host    all             all              ::/0                   md5

3) reiniciar pg server para que lea las nuevas configuraciones

4) agregar excepcion de firewall para puerto 5432 en el server windows para que sea accedido desde docker toolbox

5) en la carpeta de la aplicacion correr

	docker-compose up -d --build --force-recreate recepcion_web
	
6) ingresar a http://192.168.99.100:8001/login


INSTALACION LINUX DEBIAN 9
==========================


1) Instalar postgres

	sudo su
	wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
	apt-get update
	apt-get install postgresql
	service postgresql status

2) Crear usuario y base de datos

	sudo su
	su - postgres
	createuser --interactive --pwprompt
	createdb recepcion

2) Ingresar a base de datos

	sudo su
	su - postgres
	psql
	\c recepcion;
	\q

3) Instalar git y java

	sudo su
	apt-get install git
	apt-get install software-properties-common
	add-apt-repository "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main"
	apt-get update
	apt-get install oracle-java8-installer
	javac -version

4) Instalar el sistema

	sudo su
	mkdir /opt/recepcion
	git clone https://github.com/gigo2102/recepcion.git
	cd recepcion
	git pull
	cp env-file.md /opt/recepcion/env-file
	cat /opt/recepcion/env-file
	source /opt/recepcion/env-file
	echo $MyService 
	echo $MyServiceArgs
	cp ejemplo-service-linux.sh /etc/init.d/$MyService
	cat /etc/init.d/$MyService
	ln -sf /home/admin/recepcion/releases/release-v1.0 /opt/recepcion
	mv /opt/recepcion/release-v1.0 /opt/recepcion/app
	ls /opt/$MyService
	chmod +x /etc/init.d/$MyService
	systemctl daemon-reload
	update-rc.d $MyService defaults
	service $MyService start
	service $MyService status
	tail -f /opt/$MyService/$MyService.log