
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedAdmin"%>

<% LoggedAdmin loggedAdmin = (LoggedAdmin)request.getAttribute("loggedadmin");%>
<!DOCTYPE html>

<html>
    <head>
        <title>Dronazon</title>
       <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
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
        <a class="navbar-brand" href="#">Dronazon - Admin</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <form class="form-inline mt-2 mt-md-0" name="logout" action="Dispatcher" method="post"> 
                        <input class="btn btn-outline-danger my-2 mr-sm-0 mr-sm-2" type="submit" value="Logout">
                        <input type="hidden" name="controllerAction" value="AdminManagement.logout">
                    </form>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Catalogo</a>
                </li>
            </ul>
        </div>
    </nav>
      <main class="">
        <section class="clearfix">
          <div class="">
            <img src="images/notes.png" alt="" width="40%">
          </div>
          <div>
            <p class="">
              <span>Crea inserzione.</span>
            </p>
            <a class="">Crea</a>
          </div>
        </section>
        <section class="clearfix">
          <div>
            <img src="images/prova.png" alt="" width="40%">
          </div>
          <div class="">
            <p class="">
              <span>Modifica, ricerca, eliminazione di prodotti</span>
            </p>
            <a class="" >Conferma</a>
          </div>
        </section>
      </main>
    <%/*%>
      <form name="logoutForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="AdminManagement.view">
      </form> 
      
      <form name="createProduct" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="ProdottoManagement.viewCreatePage">
      </form> 
      
      <form name="absFlightsForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="ProdottoManagement.view">
      </form>
    <%*/%>
        
    </body>
</html>
