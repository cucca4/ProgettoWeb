
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
        <script language="javascript">
            
            function logout(){
                b = document.logoutForm;
                b.submit();
            }
            
            function goOnCreateProduct(){
                c = document.createAbsFlightForm;
                c.submit();
            }
            
            function goOnProductModify(){
                c = document.absFlightsForm;
                c.submit();
            }
            
            function onLoadHandler(){
                
            }            
            
            window.addEventListener("load", onLoadHandler);
            
        </script>
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
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Catalogo</a>
                </li>
            </ul>
        </div>
    </nav>
      <main class="main">
        <section class="action bkColor clearfix">
          <div class="actionimage">
            <img src="images/notes.png" alt="" width="40%">
          </div>
          <div>
            <p class="actiondescription">
              <span>Crea inserzione.</span>
            </p>
            <a class="actionlink" href="javascript:goOnCreateProduct()">Crea</a>
          </div>
        </section>
        <section class="action bkColor clearfix">
          <div>
            <img src="images/prova.png" alt="" width="40%">
          </div>
          <div class="actioninfo">
            <p class="actiondescription">
              <span>Modifica, ricerca, eliminazione di prodotti</span>
            </p>
            <a class="actionlink" href="javascript:goOnProductModify()">Conferma</a>
          </div>
        </section>
      </main>
      <form name="logoutForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="AdminManagement.view">
      </form> 
      
      <form name="createProduct" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="ProdottoManagement.viewCreatePage">
      </form> 
      
      <form name="absFlightsForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="ProdottoManagement.view">
      </form>
        
    </body>
</html>
