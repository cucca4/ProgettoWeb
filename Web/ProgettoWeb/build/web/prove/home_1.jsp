
<%@page import="model.session.mo.LoggedUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
%>
<html lang="it">
    <head>
        <script>

            function goToProdottoView(search){
                document.getElementById("search").value=search;
                document.ProdottoView.submit();
            }      
            function goToHome(){
                document.Home.submit();
            }
            function goToCatalog(){
                document.Catalog.submit();
            }
            function goToRegistration(){
                document.Registration.submit();
            }        
        </script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-grid.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-reboot.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-grid.min.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-reboot.min.css">
        <link rel="stylesheet" type="text/css" href="css/homestyle.css">
        <link rel="stylesheet" href="../../bootstrap-4.3.1-dist/css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="../../bootstrap-4.3.1-dist/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="../../bootstrap-4.3.1-dist/css/bootstrap-grid.css" type="text/css"/>
        <link rel="stylesheet" href="../../bootstrap-4.3.1-dist/css/bootstrap-grid.css" type="text/css">

        <title>Dronazon</title>
    </head>
    <body class="clearfix">
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark clearfix">
            <a class="navbar-brand" href="">Dronazon</a>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="javascript:goToHome();">Home <span class="sr-only">(current)</span></a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:goToRegistration();">Registrazione</a>
                    </li>
                   <% if(!loggedOn){%>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:goToRegistration();">Registrazione</a>
                    </li>
                    <%}%>
                    <% if(loggedOn) {%>
                    <span class="nav-link nav-message"> <%=applicationMessage%></span>
                    <li class="nav-item">
                        <form class="form-inline mt-2 mt-md-0" name="userArea" action="Dispatcher" method="post"> 
                            <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit" value="Area Personale">
                            <input type="hidden" name="controllerAction" value="UserArea.view">
                        </form>
                    </li>
                    <%}%>
                </ul>

                <% if(!loggedOn) {%>     
                    <form class="form-inline mt-2 mt-md-0" name="login" action="Dispatcher" method="post"> 
                        <input class="form-control mr-sm-2" type="text" placeholder="Username" aria-label="Username" name="username" id="username">
                        <input class="form-control mr-sm-2" type="text" placeholder="Password" aria-label="Password" name="password" id="password">
                        <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit">
                        <input type="hidden" name="controllerAction" value="HomeManagement.logon">
                    </form>
                <%} else {%>
                    <form class="form-inline mt-2 mt-md-0" name="logout" action="Dispatcher" method="post"> 
                        <input class="btn btn-outline-danger my-2 mr-sm-0 mr-sm-2" type="submit" value="Logout">
                        <input type="hidden" name="controllerAction" value="HomeManagement.logout">
                    </form>

                <%}%>
                <form class="form-inline mt-2 mt-md-0" name="search" action="Dispatcher" method="post"> 
                    <input class="form-control mr-sm-2" type="text" placeholder="Cerca" aria-label="search" name="search" id="search">
                    <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit" value="Vai">
                    <input type="hidden" name="controllerAction" value="ProdottoManagement.view">
                </form>
            </div>
        </nav>
        <div class="pt-5"></div>
        <div class="pt-3"></div>
        <div class="container bg-light mx-auto">
            <div class="pt-3"></div> 
            <form name="catalogView" action="Dispatcher"  method="post">
                <input class="form-control mr-sm-2" type="text" placeholder="Marca" aria-label="Marca" name="brand" id="brand">
                <div class="pt-3"></div>
                <input class="form-control mr-sm-2" type="text" placeholder="Categoria" aria-label="Categoria" name="category" id="category">
                <div class="pt-3"></div>
                <input class="btn btn-success my-2 my-sm-0 mr-sm-2" type="submit" value="Filtra">
                <input type="hidden" name="controllerAction" value="CatalogManagement.filter">
            </form>
            <div class="pt-5"></div>
        </div>
    </body>
    <form name="ProdottoView" action="Dispatcher"  method="post">
        <input type="hidden" name="search" id="search">
        <input type="hidden" name="controllerAction" value="ProdottoManagement.view">
    </form>
    <form name="Registration" action="Dispatcher"  method="post">
        <input type="hidden" name="controllerAction" value="UserArea.viewReg">
    </form>
    <form name="Home" action="Dispatcher"  method="post">
        <input type="hidden" name="controllerAction" value="HomeManagement.view">
    </form>
    <form name="Catalog" action="Dispatcher"  method="post">
        <input type="hidden" name="controllerAction" value="CatalogManagement.home">
    </form>
    <footer>
            <% if(!loggedOn) {%>
            <form action="Dispatcher" method="post">
                <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit">Admin Area</input>
                <input type="hidden" name="controllerAction" value="AdminManagement.viewLogin">
            </form>
            <% } %>
    </footer>
