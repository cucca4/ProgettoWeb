
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String applicationMessage = (String) request.getAttribute("applicationMessage");
%>

<html>
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
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                 <li class="nav-item active">
                     <a class="nav-link" href="javascript:goToHome();">Home</a>
                 </li>
                </ul>
        </nav>
        <div class="pt-4"></div>
        <div class="pt-4"></div>
        <main>
            <div class="pt-3"></div>
            <div class="container bg-light mx-auto">
                <div class="pt-2"></div>
                <div class="pt-2"></div>
                <% if(applicationMessage == "Username o utente già esistente"){%>
                <%=applicationMessage%>
                <%}%>
                <section>
                    <form name="RegistrationForm" action="Dispatcher" method="post">
                        <div class="form-row">
                            <div class="form-group col-md-6">                          
                                <label for="inputEmail4">Email</label>
                                <input type="email" class="form-control"  id="email" name="email" maxlength="40" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputUsername4">Username</label>
                                    <input type="text" class="form-control" id="username" name="username" maxlength="40" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputPassword4">Password</label>
                                <input type="text" class="form-control" id="password" name="password" maxlength="40" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputFirstname4">Nome</label>
                                <input type="text" class="form-control"  id="firstname" name="firstname" maxlength="40" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputSurname4">Cognome</label>
                                <input type="text" class="form-control" id="surname" name="surname" maxlength="40" required>
                            </div>
                        </div>
                        <div class="form-row">   
                            <div class="form-group col-md-6">
                                <label for="inputAddress">Indirizzo</label>
                                <input type="text" class="form-control" id="address" name="address" maxlength="40" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputCity">Città</label>
                                <input type="text" class="form-control" id="city" name="city" maxlength="40" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputCap">Cap</label>
                                <input type="number" class="form-control" id="cap" name="cap" maxlength="40" required>
                            </div>
                        </div>
                        <input class="btn btn-warning my-2 my-sm-0 mr-sm-2" type="submit" value="Registrati">
                        <input type="hidden" name="controllerAction" value="UserArea.createUser"/>
                    </form>
                </section>
                <div class="pt-2"></div>
            </div>
        </main>
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
            <form action="Dispatcher" method="post">
                <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit">Admin Area</input>
                <input type="hidden" name="controllerAction" value="AdminManagement.viewLogin">
            </form>
    </footer>
</html>
