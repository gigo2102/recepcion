<!DOCTYPE html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gobierno de la Ciudad de Buenos Aires</title>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
         
    
    <!-- PARA QUE ESTE HTML FUNCIONE CORRECTAMENTE DEBE VINCULAR BASTRAP.CSS -->
    <link rel="stylesheet" href="http://gcba.github.io/BAstrap/bastrap/bastrap.css">
   
     <!-- ESTILOS EXTRA -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- kendo css -->
	<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2018.3.911/styles/kendo.bootstrap-v4.min.css" />
    <script>
    	window.mapKendoParameters = function(options, type) {
	    	var opts = {
	    		skip: options.skip,
	    		take: options.take
	    	};
	    	if (options.filter != undefined && options.filter.filters[0] != undefined) {
	    		opts.filterField = options.filter.filters[0].field;
	    		opts.filterValue = options.filter.filters[0].value;
	    	}
	    	if (options.sort != undefined && options.sort[0] != undefined) {
	    		opts.sortField = options.sort[0].field;
	    		opts.sortDir= options.sort[0].dir;
	    	}
	    	return $.param(opts);
		};
    </script>
    
    <!-- personalizar -->
	  <style>
	  .ui-autocomplete-loading {
	    background: white url("images/ui-anim_basic_16x16.gif") right center no-repeat;
	  }
	  </style>


    <style>
       .bac-header {

            background-image: url(../images/logos/ba-header-vb.svg), none;
        }
        
/*HEADER*/
        .border-gradient {
            border-top: 6px solid #fdd306;
        }
        #header {
            height: 85px;
            border-bottom: 1px solid #cccccc;
        }
        #header #nombre-sitio a{
            text-decoration: none;
        }
        #header #nombre-sitio h1{
            float: left;
            color: #444444;
        }
        #header #logo-sitio .bac-header{
            height: 40px !important;
        }
        #header #ba-logo {
            float: right;
            padding: 0;

            width: 80px;
            margin-top: 20px;
        }

        #header #ba-logo:hover/*,
        #header #bac-logo:hover*/ {
            background-color: transparent;
        }

        #header #logo-mobile{
            text-align: center;
        }
        #header #logo-mobile .nombre-sitio{
            font-size: 26px;
            font-family: "CHANEWEI", Helvetica, Arial, sans-serif !important;
            letter-spacing: -0.0px !important;
            line-height: initial !important;

            display: inline-block;

            color: #444444;
        }
        #header #logo-mobile .contenedor-logo{
            display: inline-block;
        }

        #header #logo-mobile .contenedor-logo .logo-noclaim{
            width: 50px;
            height: 26px;

            display: inline-block;

            background-image: url(ba-logo-noclaim.svg), none;
            background-repeat: no-repeat;
            background-size: 100%;
        }
        
        .icons-header{
            float: right !important;
        }
        .icons-header p {display: none;}

        
        /* Responsive Logo BA */
        @media screen and (max-width: 480px)  {
        #ba-logo {
           display: none;
        }
        .icons-header{float: left!important;}
        .icons-header span{display: none;}
        .icons-header p {display: block;}
        }
		
		.navbar-header { float: none; }

      </style>
      

    <style>

        .h1 footer-row {
            color: #fff;
        }

        .footer-row {
            margin-bottom: 20px;
        }

        /* Logos */
        .container-ba {
            border-right: solid 1px #666
        }

        /* contenedor links */
        .footer-nav{
            margin-top: 70px;
            margin: 10px;
      
         

        }

        /* contenedor redes */
        .container-redes {
            width: 250px;
            margin: 0 auto;
        }

       /* contenedor ciudad */
        .container-ciudad {
            align-content: center;
            border-top: solid 1px #666;
            margin-top: 50px;
        }
        /* logo ciudad */
        .logo_footer {
            margin-top: 10px;
            width: 82%;
        }
        .logo_footer_ciudad{
            margin: 10px auto;
        }


        /* estilo listado de links */
        .primera_columna {
            padding-left: 0px;
        }

        .footer-nav ul {
            padding-left: 0px;
        }

        .footer-nav ul li {
            font-size: 14px;
            line-height: 16px;
            text-decoration: none;
        }

        .footer-nav ul li a {
              color: #ccc;
              font-family: "OpenSans", Helvetica, Arial, sans-serif;
              text-decoration: none;
              font-size: bold;
              line-height: 3em;

            }

        #ba-footer {
          padding: 60px 0;
          background: #333;
          margin-top: 20px;
        }


        /* contenedor teléfonos */
        .container-telefonos {
            border-top: solid 1px #666;
            margin-top: 100px;
            margin-bottom: 25px;

        }


        /* alinear glyphicon y h3 */
         .shortcut span,
        #ba-footer .shortcut span {
          color: #aaa;
          text-align: left;
          margin: 0;
          width: inherit;
          height: inherit;
          border-radius: 0;
          display: inline;
          margin: 10px;
          font-size: 30px !important;
          position: relative;
          -ms-behavior: url(../css/pie.htc);
        }

        #ba-footer .shortcut span {
          position: relative;
          top: 5px;
        }



         /* social */

        .mini-social a {
          width: 40px;
          height: 40px;
          display: inline-block;
          background-repeat: no-repeat;
          background-size: contain;
          text-indent: -9999px;
        }
        .social-fb {
          background-image: url(../images/social/social-fb.png);
          background-image: url(../images/social/social-fb.svg), none;
          background-size: cover;
          -ms-behavior: url(../css/backgroundsize.min.htc);
        }
        .social-tw {
          background-image: url(../images/social/social-tw.png);
          background-image: url(../images/social/social-tw.svg), none;
          background-size: cover;
          -ms-behavior: url(../css/backgroundsize.min.htc);
        }
        .social-gp {
          background-image: url(../images/social/social-gp.png);
          background-image: url(../images/social/social-gp.svg), none;
          background-size: cover;
          -ms-behavior: url(../css/backgroundsize.min.htc);
        }
        .social-yt {
          background-image: url(../images/social/social-yt.png);
          background-image: url(../images/social/social-yt.svg), none;
          background-size: cover;
          -ms-behavior: url(../css/backgroundsize.min.htc);
        }
        .social-fl {
          background-image: url(../images/social/social-fl.png);
          background-image: url(../images/social/social-fl.svg), none;
          background-size: cover;
          -ms-behavior: url(../css/backgroundsize.min.htc);
        }
        .social-rss {
          background-image: url(../images/social/social-rss.png);
          background-image: url(../images/social/social-rss.svg), none;
          background-size: cover;
          -ms-behavior: url(../css/backgroundsize.min.htc);
        }
        .social-cc {
          background-image: url(../images/social/social-cc.png);
          background-image: url(../images/social/social-cc.svg), none;
          background-size: cover;
          -ms-behavior: url(../css/backgroundsize.min.htc);
          margin-top: 8px;
        }

        .social-in {
          background-image: url(../images/social/social-in.png);
          background-image: url(../images/social/social-in.svg), none;
          background-size: cover;
          -ms-behavior: url(../css/backgroundsize.min.htc);
        }
        .social-fav {
          float: right !important;
          margin-right: 0 !important;
          font-size: 40px;
        }
        .social-fav a {
          color: #ccc;
          text-indent: 0;
        }
        .social-fav a.active,
        .social-fav a.corazonLleno {
          color: #d13f34;
        }
        .ba-social {
          display: table;
          /*margin: 0 auto;*/
          padding: 16px 0;
        }
        .ba-social ul {
          padding: 0;
        }
        .ba-social li {
          list-style: none;
          float: left;
          margin: 0 15px 16px 0;
        }
        .ba-social li:last-child {
          margin-right: 0;
        }
        .ba-social a {
          width: 60px;
          height: 60px;
          display: inline-block;
          background-repeat: no-repeat;
          background-size: contain;
          -ms-behavior: url(../css/backgroundsize.min.htc);
        }
        .ba-medios a{
          width: 90px;
          height: 90px;
        }
        .footer-row .mini-social {
          width: auto;
        }
        @media (max-width:767px) {
          .footer-row .mini-social {
            width: auto;
            float: none;

          }
        }
        .mini-social {
          padding: 0;
          margin: 0;
          overflow: auto;
        }
        .mini-social li {
          list-style: none;
          float: left;
          margin-right: 10px;
          height: 40px;
          overflow: hidden;
        }
        .mini-social a:hover,
        .mini-social a:focus,
        .ba-social a:hover,
        .ba-social a:focus {
          opacity: 0.8;
        }
        .mini-social a:active,
        .ba-social a:active {
          opacity: 1;
        }

        @media (max-width: 560px) {
          .ba-social a {
            width: 60px;
            height: 60px;
          }
        }

        .footer-shortcuts {
          border-top: solid 1px #222;
          padding-top: 48px;
          padding-bottom: 48px;
        }

                /* shortcuts */
        .row-shortcut {
          margin: 30px 0 0 0;
        }

        .shortcut {
          text-align: center;
          text-decoration: none;
          padding-top: 24px;
          padding-bottom: 12px;
          color: #333;
          border-radius: 6px;
          position: relative;
          -ms-behavior: url(../css/pie.htc);
          display: block;
          background-color: transparent;
        }

        .shortcut:hover,
        .shortcut:focus {
          background: #555555;
          text-decoration: none;
        }

        .shortcut:hover p,
        .shortcut:focus p {
          color: #e5e5e5;
        }


        .shortcut:active {
          background-color: #f1f1f1;
          text-decoration: none;
        }

        .bg-white-1 .shortcut:hover,
        .bg-white-1 .shortcut:focus {
          background: #e9e9e9;
        }

        .bg-white-1 .shortcut:active {
          background-color: #ccc;
        }

        .shortcut span {
          color: #fff;
          text-align: center;
          margin: 0 auto 24px auto;
          width: 30px;
          height: 30px;
          border-radius: 56px;
          position: relative;
          -ms-behavior: url(../css/pie.htc);
          display: block;
        }

        .shortcut span.glyphicon {
          padding-top: 0px;
          font-size: 30px;
          top: 0px;
        }

        .shortcut p {
          color: #888;
        }

        .shortcut h3 {
          color: #fff;
          font-size: 32px;
          margin-bottom: 12px;
        }

        /* shortcut list + tramites + emergencias */

        .shortcut-list {
          text-align: left;
          overflow: auto;
          padding-top: 0;
          float: left;
        }
        
        .shortcut-list:last-of-type {
          background-image: none;
        }
        .shortcut-list span {
          float: left;
          margin-right: 30px;
          width: 80px;
          height: 80px;
          margin-bottom: 8px;
        }
        .shortcut-list span.h1 {
        padding-top: 18px;
        }
        .shortcut-list h3 {
          margin-top: 16px;
        }
        .shortcut-list p {
          margin-bottom: 0;
          color: #333;
          font-size: 16px;
          line-height: 24px;
        }
        .shortcut-list-step {
          float: left;
          width: 100px;
        }
        .lt-ie9 .shortcut-list-step {
          width: 98px;
        }
        .shortcut-list-info {
          float: left;
          margin-bottom: 16px;
        }

        .chosen-container {
         padding: 0px;
         width: 100% !important;
         border: 0px;
         box-shadow: none !important;
        }

        @media (max-width: 991px) {
          .shortcut-list,
          .shortcut-list:hover,
          .shortcut-list:focus,
          .shortcut-list:active {
            background-image: none;
          }
          .shortcut-list-info {
            width: 100%;
          }
        }
        @media (min-width: 992px) {
          .shortcut-list-info {
            width: 445px;
          }
        }
        @media (min-width:1200px) {
          .shortcut-list-info {
            width: 580px;
          }
        }

        .ba-emergencia .main-container > .container {
          padding-left: 30px;
          padding-right: 30px;
        }
        .ba-emergencia table {
          text-align: left;
        }

        /* shortcut normal */
        .row-shortcut {
          margin: 16px 0px;
        }
        @media (max-width: 767px) {
          .ba-home .main-container .shortcut p,
          .ba-home .shortcut-tramite {
            display: none;
          }
          .openid-connect-login-btn-top-bar {
              padding-left: 32px;
              margin-bottom: -10px;
            }
            .navbar-top.navbar-primary{
              padding-bottom: 0px;
            }
        }


        @media (max-width:991px) {
          .row-shortcut .col-sm-6:nth-of-type(2n+1) {
            clear: left;
          }

          .footer-nav {
                text-align: left;
            }
         .primera_columna {
            padding-left: 15px;
        }

         .container-redes {
            margin-top: 30px;
        }
        }
        
        @media (max-width: 468px) {
        .footer-nav {
            text-align: center;
            }
        }

      </style>
      
      
          <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      
  </head>
  <body>
     <header id="header">
     <div class="border-gradient">
        <div class="container h100">
            <div class="row h100">

                <div id="nombre-sitio" class="col-sm-6 col-md-6 h100">
                    <!-- NOMBRE DEL SITIO -->
                    <a href="/areas">
                        <h1>Sistema de Registro de Visitas</h1>
                    </a>
                </div>

                <div id="logo-sitio" class="col-sm-6 col-md-6 h100">
                    <!-- LOGO -->
                    <div class="w100 h100 float-right display-table">
                        <div class="display-table-cell vertical-align-middle">
                            <a href="http://disfrutemosba.buenosaires.gob.ar" style="text-decoration: none;">
                                <div id="ba-logo" class="navbar-brand bac-header" data-original-title="" title=""></div>
                            </a>
                        </div>
                    </div>
                </div>
               </div>
        </div>
        </div>
        
</header>

   <div style="background-color: #fff;">

      <!-- NAVEGACIÓN PRINCIPAL -->
      <nav class="navbar navbar-default" role="navigation">
        <div class="container">
          <div class="row">
            <div class="navbar-header">
              <ul class="nav navbar-nav">
               </ul>
                <div class="icons-header">


 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 
 <#if isLoggedIn>
 	<div class="btn-group">
 		Bienvenido ${currentUser.userName}!
 	</div>
 	<div class="btn-group">
		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
			Menu <span class="caret"></span>
  		</button>
      	<ul class="dropdown-menu" role="menu">
	        <li><a href="/visitas">Visitas</a></li>
			 <#if currentUser.roles?seq_contains("admin")>
			    <li><a href="/personas">Personas</a></li>
		        <li><a href="/usuarios">Usuarios</a></li>
		        <li><a href="/areas">Areas</a></li>
		        <li><a href="/motivos">Motivos</a></li>
		        <li><a href="/tipovisitas">Tipo de Visita</a></li>
		        <li><a href="/tipodocumentos">Tipo de Documento</a></li>
	        </#if>
	        <li class="divider"></li>
	        <li><a href="/logout">Cerrar Sesion</a></li>
    	</ul>
	</div>
</#if>

             
      </nav>
      <!-- FIN DE NAVEGACIÓN PRINCIPAL -->
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://kendo.cdn.telerik.com/2018.3.911/js/kendo.all.min.js"></script>

	<div class="container">
		<#include bodyTemplate>
	</div>

    <footer id="ba-footer">
      <div class="container">
        <div class="row footer-row">
            <div class="container-ba-border">
              <a href="#"><h3>SRV</h3></a>
           </div>
        </div>
      </div>
      
      <div id="visitas-pendientes-listado" class="alert alert-primary" style="display:none; position: fixed;bottom: 10px;z-index: 1000;margin: 20px;right: 20px;">
      	<a href="/visitas"><span></span> Visitas Pendientes</a>
  	  </div>

<script>
	function beep() {
	    var snd = new Audio("data:audio/wav;base64,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qJrx4KOQjahZxWbcZgztj2c49nKmkId44S71j0c8eV9yDK6uPRzx5X18eDvjvQ6yKo9ZSS6l//8elePK/Lf//IInrOF/FvDoADYAGBMGb7FtErm5MXMlmPAJQVgWta7Zx2go+8xJ0UiCb8LHHdftWyLJE0QIAIsI+UbXu67dZMjmgDGCGl1H+vpF4NSDckSIkk7Vd+sxEhBQMRU8j/12UIRhzSaUdQ+rQU5kGeFxm+hb1oh6pWWmv3uvmReDl0UnvtapVaIzo1jZbf/pD6ElLqSX+rUmOQNpJFa/r+sa4e/pBlAABoAAAAA3CUgShLdGIxsY7AUABPRrgCABdDuQ5GC7DqPQCgbbJUAoRSUj+NIEig0YfyWUho1VBBBA//uQZB4ABZx5zfMakeAAAAmwAAAAF5F3P0w9GtAAACfAAAAAwLhMDmAYWMgVEG1U0FIGCBgXBXAtfMH10000EEEEEECUBYln03TTTdNBDZopopYvrTTdNa325mImNg3TTPV9q3pmY0xoO6bv3r00y+IDGid/9aaaZTGMuj9mpu9Mpio1dXrr5HERTZSmqU36A3CumzN/9Robv/Xx4v9ijkSRSNLQhAWumap82WRSBUqXStV/YcS+XVLnSS+WLDroqArFkMEsAS+eWmrUzrO0oEmE40RlMZ5+ODIkAyKAGUwZ3mVKmcamcJnMW26MRPgUw6j+LkhyHGVGYjSUUKNpuJUQoOIAyDvEyG8S5yfK6dhZc0Tx1KI/gviKL6qvvFs1+bWtaz58uUNnryq6kt5RzOCkPWlVqVX2a/EEBUdU1KrXLf40GoiiFXK///qpoiDXrOgqDR38JB0bw7SoL+ZB9o1RCkQjQ2CBYZKd/+VJxZRRZlqSkKiws0WFxUyCwsKiMy7hUVFhIaCrNQsKkTIsLivwKKigsj8XYlwt/WKi2N4d//uQRCSAAjURNIHpMZBGYiaQPSYyAAABLAAAAAAAACWAAAAApUF/Mg+0aohSIRobBAsMlO//Kk4soosy1JSFRYWaLC4qZBYWFRGZdwqKiwkNBVmoWFSJkWFxX4FFRQWR+LsS4W/rFRb/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////VEFHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAU291bmRib3kuZGUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMjAwNGh0dHA6Ly93d3cuc291bmRib3kuZGUAAAAAAAAAACU=");  
	    snd.play();
	}

	function buscarVisitasPendientes() {
		$.getJSON('/visitas/ws_pendientes', function (visitasPendientes){
			var cantidadVisitasPendientes = visitasPendientes.length;
			var elem = $("#visitas-pendientes-listado");
			if (cantidadVisitasPendientes == 0) {
				elem.hide();
			} else {
				$("span", elem).html(cantidadVisitasPendientes.toString());
				elem.show();
				beep();
			}
		});
	}

	$(function(){
		setInterval(buscarVisitasPendientes, 3000);
	});
</script>
      
    </footer>

  </body>
</html>
