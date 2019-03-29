<%@page import="java.util.List"%>
<%@page import="model.session.mo.Cart"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.Product"%>

<!doctype html>

<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    Cart cart = (Cart) request.getAttribute("cart");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    Float Tot = 0.0f;
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
            <form class="form-inline mt-2 mt-md-0" name="logout" action="Dispatcher" method="post"> 
                <input class="btn btn-outline-danger my-2 mr-sm-0 mr-sm-2" type="submit" value="Logout">
                <input type="hidden" name="controllerAction" value="HomeManagement.logout">
            </form>
         
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
        <% for(int i = 0; i < cart.getProductList().size(); i++){ %>
        ID Prodotto: <%=cart.getProductList().get(i) %>     Quantit�: <%=cart.getProductQty().get(i)%>      Prezzo unitario: <%=cart.getProductPrice().get(i)%><br>
        <form class="form-inline mt-2 mt-md-0" name="UpdateIdOrder" action="Dispatcher" method="post"> 
            <input class="btn btn-outline-warning my-2 my-sm-0 mr-sm-2" type="submit" value="Rimuovi">
            <input type="hidden" name="Id" id="Id" value="<%=cart.getProductList().get(i) %>">
            <input type="hidden" name="controllerAction" value="CartManagement.update">
        </form>
        <% Tot = Tot + (cart.getProductPrice().get(i)*cart.getProductQty().get(i));%>
        <%}%>
        <div class="pt-3"></div>
        Totale ordine Euro <%=Tot%>
        
        <div class="list-group">
            <a href="#" class="list-group-item list-group-item-action active">
              Scegli metodo di pagamento
            </a>
            <a href="#" class="list-group-item list-group-item-action">Paypal</a>
            <a href="#" class="list-group-item list-group-item-action">PostePay</a>
            <a href="#" class="list-group-item list-group-item-action">Carta di credito</a>
        </div>
        <form class="form-inline mt-2 mt-md-0" name="createOrder" action="Dispatcher" method="post"> 
            <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit" value="Ordina">
            <input type="hidden" name="Tot" id="Tot" value="<%=Tot%>">
            <input type="hidden" name="buyer" id="buyer" value="<%=loggedUser.getUsername()%>">
            <input type="hidden" name="controllerAction" value="CartManagement.order">
        </form>
    
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