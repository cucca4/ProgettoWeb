
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.User"%>
<%@page import="model.mo.Product"%>
<%@page import="model.mo.Orders"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = (User)request.getAttribute("user");
    LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
    Boolean loggedOn = (Boolean)request.getAttribute("loggedOn");
    String applicationMessage = (String)request.getAttribute("applicationMessage");
    String actionPage = (String)request.getAttribute("actionPage");
    List<Orders> orders = (List<Orders>)request.getAttribute("orders");
    int i;
%>
<!DOCTYPE html>



<html>
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
                <li class="nav-item ">
                    <a class="nav-link" href="/homeManagement/home.html">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Catalogo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#">Area Personale<span class="sr-only">(current)</span></a>
                </li>
            </ul>  
            <form class="form-inline mt-2 mt-md-0" name="logout" action="Dispatcher" method="post"> 
                <input class="btn btn-outline-danger my-2 my-sm-0 mr-sm-2" type="submit" value="Logout">
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
    <div class="pt-3"></div>   
        <img src="images/scatola.jpg" class="mr-3" width="120" height="120">
        <div class="media-body">
            <h1 class="mt-0 mb-1">ORDINI:</h1>
            <section>
                <form name="oForm" action="dispatcher" method="post">
                    <div>
                        <%for(i=0; i<orders.size(); i++) {%>
                            <article class=" clearfix">
                                <div>
                                    <h4>
                                        Order Id: <%=orders.get(i).getOrder_Id()%><br>
                                        Totale €: <%=orders.get(i).getTotprice()%>
                                    <br>
                                    Stato: 
                                    <% if(orders.get(i).getStatus()== "elaborazione"){%>

                                        Ordine non ancora spedito

                                    <%} else {%>
                                        <% if(orders.get(i).getStatus()== "spedito"){%>
                                        Ordine spedito

                                    <%} else {%>
                                        <strong>Ordine consegnato</strong>
                                    <%}%>
                                    <%}%>
                                    </h4>
                                </div>
                                </article>
                        <%}%>
                        <% if(orders.size()==0) {%>
                                <img src="images/error.png" height="200px" width="200px">
                                <div>
                                <h2>
                                    Nessun ordine effettuato!
                                </h2>
                                <h3 class='support-h3'>
                                    Effettua un ordine per visualizzarne i dettagli.
                                </h3>
                                </div>
                        <%}%>
                    </div>  
                </form>
            </section>
        </div>
    <div class="pt-3"></div>
    <img src="images/omino-ok.jpg" class="mr-3" width="120" height="120">
        <div class="media-body">
            <h1 class="mt-0 mb-1">PROFILO</h1>
            Controlla le informazioni sul tuo profilo o modificane i parametri.
            <br>
            <section>
                    <form name="uForm" action="Dispatcher" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-6">                          
                            <label for="inputEmail4">Email</label>
                            <input type="email" class="form-control"  id="email" name="email" maxlength="40" value="<%=user.getEmail()%>" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputUsername4">Username</label>
                            <input type="text" class="form-control"  id="username" name="username" maxlength="40" value="<%=user.getUsername()%>" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputFirstname4">Nome</label>
                            <input type="text" class="form-control"  id="firstname" name="firstname" maxlength="40" value="<%=user.getFirstname()%>" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputSurname4">Cognome</label>
                            <input type="text" class="form-control" id="surname" name="surname" maxlength="40"  value="<%=user.getSurname()%>" required>
                        </div>
                    </div>
                    <div class="form-row">   
                        <div class="form-group">
                            <label for="inputAddress">Indirizzo</label>
                            <input type="text" class="form-control" id="address" name="address" maxlength="40" value="<%=user.getAddress()%>" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputCity">Città</label>
                            <input type="text" class="form-control" id="city" name="city" maxlength="40" value="<%=user.getCity()%>" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputCap">Cap</label>
                            <input type="number" class="form-control" id="cap" name="cap" maxlength="40" value="<%=user.getCap()%>" required>
                        </div>
                    </div>
                    <input type="submit" value="Aggiorna" class="btn btn-outline-warning my-2 mr-sm-0 mr-sm-2">
                    <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
                    <input type="hidden" name="controllerAction" value="UserArea.modify"/>

                    </form>
            </section>
            <div class="pt-3"></div>
            <h1 class="mt-0 mb-1">CAMBIA PASSWORD</h1>
            <div class="pt-1"></div>
            <% if(applicationMessage == "Password errata"){%>
                <h1><%=applicationMessage%></h1>
            <%}%>
            <section>
                    <form name="passwordForm" action="Dispatcher" method="post">
                    <label for="oldPassword"> Password </label>
                    <input type="password" id="oldPassword" name="oldpassword" maxlength="40" required>
                    </br>
                    <label for="newPassword"> Nuova password </label>
                    <input type="password" id="newPassword" name="newpassword" maxlength="40" required>
                    </br>
                    <input type="submit" value="Salva" class="btn btn-outline-warning my-2 mr-sm-0 mr-sm-2">
                    <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
                    <input type="hidden" name="controllerAction" value="UserArea.modifyPassword"/>
                    </form>
            </section>
            <div class="pt-3"></div>
            <h1 class="mt-0 mb-1">ELIMINA ACCOUNT</h1>
            <div class="pt-1"></div>
            <% if(applicationMessage == "Password errata,impossibile eliminare account"){%>
             <h1><%=applicationMessage%></h1>
            <%}%>
            <section>
                <form name="deleteAccountForm" action="Dispatcher" method="post">
                    <label for="Password"> Password </label>
                    <input type="password" id="Password" name="password" maxlength="40" required>
                    </br>
                    <input type="submit" value="Elimina" class="btn btn-outline-danger my-2 mr-sm-0 mr-sm-2">
                    <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
                    <input type="hidden" name="controllerAction" value="UserArea.deleteAccount"/>
                </form>
            </section>
        </div>
    </div>
</body>
</html>