<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.Product"%>

<!doctype html>

<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    Product product = (Product) request.getAttribute("product");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
%>

<html lang="it">
<head>
    
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
                    <a class="nav-link" href="/homeManagement/home.html">Home <span class="sr-only"></span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Catalogo</a>
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
            <input type="hidden" name="controllerAction" value="ProdottoManagement.logon">
            </form>
        <%} else {%>
            <form class="form-inline mt-2 mt-md-0" name="logout" action="Dispatcher" method="post"> 
            <input class="btn btn-outline-danger my-2 mr-sm-0 mr-sm-2" type="submit" value="Logout">
            <input type="hidden" name="controllerAction" value="ProdottoManagement.logout">
            </form>
      
         <%}%>
         
        <form class="form-inline mt-2 mt-md-0" name="serch" action="Dispatcher" method="post"> 
        <input class="form-control mr-sm-2" type="text" placeholder="Cerca" aria-label="Search">
        <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit">Cerca</input>
        <input type="hidden" name="controllerAction" value="ProdottoManagement.view">
        </form>
        </div>
    </nav>

    <div class="pt-5"></div>
    <div class="pt-3"></div>
    <div class="container bg-light mx-auto">
            <div class="pt-4"></div>
            <div class="float-left">
                <img src="images/products/<%=product.getBrand()%>-<%=product.getModel()%>.jpg" class="mr-3" width="320" height="320">
            </div><br>
            <div class="media-body">
                <h5 class="mt-0 mb-1"><%=product.getBrand()%></h5>
                <h6 class="mt-0 mb-1"><%=product.getModel()%></h6>
                <%=product.getDescription()%>
            </div>
            <div class="pt-3"></div>
            
            <% if(!loggedOn) {%>     
            <div class="alert alert-warning" role="alert">
                Effettua il login per cominciare lo shopping!
            
            
        <%} else {%>
            <button type="button" class="btn btn-warning">Compra</button>
        <%}%>
            <div class="pt-xl-5"></div>
            <div class="pt-xl-5"></div>
            <div class="pt-xl-5"></div>
            <div class="pt-xl-5"></div>
            <div class="pt-xl-5"></div>
            <div class="pt-xl-5"></div>

    </div>
</body>

</html>