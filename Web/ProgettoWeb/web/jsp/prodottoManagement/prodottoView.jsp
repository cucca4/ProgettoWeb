<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.Product"%>

<!doctype html>

<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    Product product = (Product) request.getAttribute("product");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String notfoundMessage = (String) request.getAttribute("notfoundMessage");
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

    <title>Dronazon</title>
</head>
<body class="clearfix">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark clearfix">
        <a class="navbar-brand" href="#">Dronazon</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="javascript:goToHome();">Home <span class="sr-only"></span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:goToCatalog();">Catalogo</a>
                </li>
      
                <% if(loggedOn) {%>
                <span class="nav-link nav-message"> <%=applicationMessage%></span>
                <li class="nav-item">
                    
                    <form class="form-inline mt-2 mt-md-0" name="userarea" action="Dispatcher" method="post"> 
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
                <input type="hidden" name="model" value="<%=product.getModel()%>">
                <input type="hidden" name="controllerAction" value="ProdottoManagement.logon">
                </form>
            <%} else {%>
                <form class="form-inline mt-2 mt-md-0" name="logout" action="Dispatcher" method="post"> 
                <input class="btn btn-outline-danger my-2 mr-sm-0 mr-sm-2" type="submit" value="Logout">
                <input type="hidden" name="model" value="<%=product.getModel()%>">
                <input type="hidden" name="controllerAction" value="ProdottoManagement.logout">
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
            <div class="pt-4"></div>
            <% if( notfoundMessage == "trovato") {%> 
                <div class="float-left">
                    <img src="images/products/<%=product.getBrand()%>-<%=product.getModel()%>.jpg" class="mr-3" width="320" height="320">
                </div><br>
                <div class="media-body">
                    <h5 class="mt-0 mb-1"><%=product.getBrand()%>  <%=product.getModel()%></h5>
                    <h5 class="mt-0 mb-1"><%=product.getPrice()%> Euro</h5>
                    <%=product.getDescription()%>
                </div>
                <div class="pt-3"></div>

                <% if(!loggedOn) {%> 
                <div class="pt-4"></div>
                <div class="pt-4"></div>
                <div class="pt-4"></div>
                <div class="pt-4"></div>
                <div class="alert alert-warning" role="alert">
                    Effettua il login per cominciare lo shopping!
                </div>
                <%} else {%>
                    Numero pezzi disponibili in magazzino: <%=product.getQty()%>
                    <input class=" form-inline mt-2 mt-md-0" type="number" aria-label="Qty" name="qty" id="qty">
                    <div class="pt-1"></div>
                    <button type="button" class="btn btn-warning ">Aggiungi al carrello</button>
                <%}%>
            <%} else {%>
                <h1><%=notfoundMessage%></h1>
            <%}%>
            <div class="pt-xl-5"></div>
            <div class="pt-xl-5"></div>
            <div class="pt-xl-5"></div>
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
</html>