
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedAdmin"%>
<%@page import="model.mo.User"%>
<%@page import="model.mo.Orders"%>
<%@page import="java.util.List"%>


<% LoggedAdmin loggedAdmin = (LoggedAdmin)request.getAttribute("loggedadmin");
   String createMessage = (String) request.getAttribute("createMessage");
   String deleteMessage = (String) request.getAttribute("deleteMessage");
   String countMessage = (String) request.getAttribute("countMessage");
   List<Orders> Listorders = (List<Orders>) request.getAttribute("Listorders");
   User user = (User) request.getAttribute("user");
   int i;
%>

<!DOCTYPE html>

<html>
    <head>
        <title>Dronazon</title>
       <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

 
    <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-grid.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-reboot.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-grid.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap-reboot.min.css">
    <link rel="stylesheet" type="text/css" href="css/homestyle.css">
    
</head>
<body class="clearfix">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary clearfix">
        <a class="navbar-brand" href="">Dronazon-Admin</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <form class="form-inline mt-2 mt-md-0" name="logout" action="Dispatcher" method="post"> 
                        <input class="btn btn-danger my-2 mr-sm-0 mr-sm-2" type="submit" value="Logout">
                        <input type="hidden" name="controllerAction" value="AdminManagement.logout">
                    </form>
                </li>
            </ul>
            <form class="form-inline mt-2 mt-md-0">
            <form class="form-inline mt-2 mt-md-0" name="login" action="Dispatcher" method="post"> 
            <input class="form-control mr-sm-2" type="text" placeholder="Cerca" aria-label="Search">
            <input class="btn btn-success my-2 my-sm-0 mr-sm-2" type="submit">
            <input type="hidden" name="controllerAction" value="ProdottoManagement.view">
            </form>
        </div>
    </nav>
    <div class="pt-5"></div>
    <div class="pt-3"></div>
    <div class="container bg-light mx-auto">
        <div class="pt-4"></div>
        <section class="clearfix">
          <form class="form-inline mt-2 mt-md-0" name="create" action="Dispatcher" method="post"> 
                <span>Crea nuova inserzione</span>
                <input class="form-control ml-sm-2 mr-sm-2" type="text" placeholder="Codice ID" aria-label="Codice ID" name="Prod_Id" id="Prod_Id">
                <input class="form-control ml-sm-2 mr-sm-2" type="text" placeholder="Marca" aria-label="brand" name="brand" id="brand">
                <input class="form-control mr-sm-2" type="text" placeholder="Modello" aria-label="model" name="model" id="model">
                <input class="form-control mr-sm-2" type="text" placeholder="Descrizione" aria-label="description" name="description" id="description">
                <input class="form-control mr-sm-2" type="text" placeholder="Categoria" aria-label="category" name="category" id="category">
                <input class="form-control mr-sm-2" type="text" placeholder="prezzo" aria-label="price" name="price" id="price">
                <input class="form-control mr-sm-2" type="text" placeholder="Quantità" aria-label="qty" name="qty" id="qty">
                <input class="btn btn-outline-info my-2 mr-sm-0 mr-sm-2" type="submit" value="Crea">
                <input type="hidden" name="controllerAction" value="ProdottoManagement.createProduct">
            </form>
            <% if(createMessage != null) {%>
                <%=createMessage%>
            <%}%>
        </section>
        <div class="pt-3"></div>
        <section class="clearfix">
            <form class="form-inline mt-2 mt-md-0" name="delete" action="Dispatcher" method="post"> 
               Elimina prodotto inserendo il Modello<br>
            <input class="form-control  ml-sm-2 mr-sm-2" type="text" placeholder="Modello" aria-label="Modello" name="model" id="model">
            <input class="btn btn-outline-info my-2 mr-sm-0 mr-sm-2" type="submit" value="Elimina">
            <input type="hidden" name="controllerAction" value="ProdottoManagement.deleteProduct">
            </form>
            <% if(deleteMessage != null) {%>
                <%=deleteMessage%>
            <%}%>
        </section>
        <div class="pt-3"></div>
        <section class="clearfix">
            <form class="form-inline mt-2 mt-md-0" name="numOrders" action="Dispatcher" method="post"> 
                Controlla il numero di ordini effettuati da un utente inserendo il suo Username<br>
            <input class="form-control ml-sm-2 mr-sm-2" type="text" placeholder="Username" aria-label="Username" name="username" id="username">
            <input class="btn btn-outline-info my-2 mr-sm-0 mr-sm-2" type="submit" value="Cerca">
            <input type="hidden" name="controllerAction" value="AdminManagement.count">
            </form>
            <% if(countMessage != null) {%>
                <%=countMessage%>
            <%}%>
            <div class="pt-2"></div>
        </section>
        <div class="pt-3"></div>
        <section class="clearfix">
            <form class="form-inline mt-2 mt-md-0" name="numOrders" action="Dispatcher" method="post"> 
                Visualizza le informazioni di un utente inserendo il suo Username<br>
            <input class="form-control ml-sm-2 mr-sm-2" type="text" placeholder="Username" aria-label="Username" name="username" id="username">
            <input class="btn btn-outline-info my-2 mr-sm-0 mr-sm-2" type="submit" value="Cerca">
            <input type="hidden" name="controllerAction" value="AdminManagement.findUser">
            </form>
            <% if(user != null) {%>
            Username: <%=user.getUsername()%>
                Password: <%=user.getPassword()%><br>
                Nome: <%=user.getFirstname()%>
                Cognome: <%=user.getSurname()%><br>
                Email: <%=user.getEmail()%><br>
                Città: <%=user.getCity()%>
                Cap: <%=user.getCap()%>
                Indirizzo: <%=user.getAddress()%>
            <%}%>
            <div class="pt-2"></div>
        </section>
        <div class="pt-2"></div> 
        <% if((Listorders.size()) != 0) {%>
            <section class="clearfix">
                <div class="pt-3"></div>
                Lista degli ordini effettuati su Dronazon!<br>
                <div class="pt-2"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-sm">
                            Order ID
                        </div>
                        <div class="col-sm">
                            Compratore
                        </div>
                        <div class="col-sm">
                            Totale €
                        </div>
                        <div class="col-sm">
                            Stato
                        </div>
                    </div> 
                    <% for(i=0;i<Listorders.size();i++) { %>
                    <div class="row">
                      <div class="col-sm">
                        <%=Listorders.get(i).getOrder_Id()%>
                      </div>
                      <div class="col-sm">
                        <%=Listorders.get(i).getBuyer()%>
                      </div>
                      <div class="col-sm">
                        <%=Listorders.get(i).getTotprice()%>
                      </div>
                        <div class="col-sm">
                        <%=Listorders.get(i).getStatus()%>
                      </div>
                    </div>
                    <%}%>
                </div>
                <div class="pt-2"></div>
            </section>
        <%} else {%>
            <form class="form-inline mt-2 mt-md-0" name="Listorders" action="Dispatcher" method="post"> 
            <input class="btn btn-outline-danger my-2 mr-sm-0 mr-sm-2" type="submit" value="Lista totale degli ordini">
            <input type="hidden" name="controllerAction" value="AdminManagement.viewOrders">
            </form>
        <%}%>   
    </div>
    </body>
</html>
