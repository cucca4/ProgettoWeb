
<%@page session="false"%>
<%@page import="java.util.List"%>
<%@page import="model.mo.PushedProduct"%>
<%@page import="model.session.mo.LoggedUser"%>
<!doctype html>


<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    List<PushedProduct> pushedProduct = (List<PushedProduct>) request.getAttribute("pushedProduct");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    int c;
%>

<html lang="it">
    
    <head>
        <script>

            function goToProdottoView(Prod_Id){
                document.getElementById("Prod_Id").value = Prod_Id;
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
            <a class="navbar-brand" href="">Dronazon</a>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="javascript:goToHome();">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:goToCatalog();">Catalogo</a>
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
            <div class="row">
                <%for(c=0; c<3; c++ ) { %>
                    <div class="card margin-card mx-auto" style="width: 19rem;">
                        <a href="javascript:goToProdottoView(<%=pushedProduct.get(c).getProd_Id() %>);">
                        <img src="images/products/<%=pushedProduct.get(c).getBrand()%>-<%=pushedProduct.get(c).getModel()%>.jpg" class="card-img-top" width="300" height="250" alt="<%=pushedProduct.get(c).getBrand()%>-<%=pushedProduct.get(c).getModel()%>">
                        <div class="card-body">
                            <p class="card-text"><%=pushedProduct.get(c).getBrand()%> <%=pushedProduct.get(c).getModel()%></p>
                        </div>
                        </a>
                    </div> 
                <%}%>
            </div>
            <div class="row">
                <%for(c=3; c<6; c++ ) { %>
                    <div class="card margin-card mx-auto" style="width: 19rem;">
                        <a href="javascript:goToProdottoView(<%=pushedProduct.get(c).getProd_Id()%>);">
                        <img src="images/products/<%=pushedProduct.get(c).getBrand()%>-<%=pushedProduct.get(c).getModel()%>.jpg" class="card-img-top" width="300" height="250" alt="<%=pushedProduct.get(c).getBrand()%>-<%=pushedProduct.get(c).getModel()%>" >
                        <div class="card-body">
                            <p class="card-text"><%=pushedProduct.get(c).getBrand()%> <%=pushedProduct.get(c).getModel()%></p>
                        </div>
                        </a>
                    </div> 
                <%}%>
            </div>
            <div class="row">
                <%for(c=6; c<9; c++ ) { %>
                    <div class="card margin-card mx-auto" style="width: 19rem;">
                        <a href="javascript:goToProdottoView(<%=pushedProduct.get(c).getProd_Id()%>);">
                        <img src="images/products/<%=pushedProduct.get(c).getBrand()%>-<%=pushedProduct.get(c).getModel()%>.jpg" class="card-img-top" width="300" height="250" alt="<%=pushedProduct.get(c).getBrand()%>-<%=pushedProduct.get(c).getModel()%>" >
                        <div class="card-body">
                            <p class="card-text"><%=pushedProduct.get(c).getBrand()%> <%=pushedProduct.get(c).getModel()%> </p>
                        </div>
                        </a>
                    </div> 
                 <%}%> 
            </div>
        </div>
    </body>

    <form name="ProdottoView" action="Dispatcher"  method="post">
        <input type="hidden" name="Prod_Id" id="Prod_Id">
        <input type="hidden" name="controllerAction" value="PushedProductManagement.click">
    </form>
    <form name="Registration" action="Dispatcher"  method="post">
        <input type="hidden" name="controllerAction" value="UserArea.viewReg">
    </form>
    <form name="Home" action="Dispatcher"  method="post">
        <input type="hidden" name="controllerAction" value="HomeManagement.view">
    </form>
    <form name="Catalog" action="Dispatcher"  method="post">
        <input type="hidden" name="controllerAction" value="CatalogManagement.view">
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