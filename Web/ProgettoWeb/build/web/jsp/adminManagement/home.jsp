
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedAdmin"%>
<%@page import="model.mo.User"%>
<%@page import="model.mo.Orders"%>
<%@page import="java.util.List"%>


<% LoggedAdmin loggedAdmin = (LoggedAdmin)request.getAttribute("loggedadmin");
   String countMessage = (String)request.getAttribute("countMessage");
   User user = (User)request.getAttribute("user");
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
            <% if(user.getUsername() != null) {%>
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
        <section class="clearfix">
            <form class="form-inline mt-2 mt-md-0" name="prodAdmin" action="Dispatcher" method="post"> 
                Per la gestione dei prodotti clicca qui<br>
            <input class="btn btn-outline-info my-2 mr-sm-0 mr-sm-2" type="submit" value="Vai">
            <input type="hidden" name="controllerAction" value="AdminManagement.viewProdAdmin">
            </form>
        </section>
    </div>
    </body>
</html>
