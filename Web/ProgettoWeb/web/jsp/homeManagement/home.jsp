
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>
<!DOCTYPE html>

<html lang="it">
    <head>
        
        <script>

            function goToProdottoView(search){

                document.getElementById("search").value=search;

                document.ProdottoView.submit();
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
            <link rel="stylesheet" href="../../css/homestyle.css" type="text/css"/>
            <link rel="stylesheet" href="../../css/homestyle.css" type="text/css">

        <title>Dronazon</title>
    </head>
    <body class="clearfix">
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark clearfix">
            <a class="navbar-brand" href="">Dronazon</a>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Catalogo</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="Registrazione.jsp">Registrazione</a>
                    </li>
                </ul>
                <form class="form-inline mt-2 mt-md-0" name="login" action="Dispatcher" method="post"> 
                    <input class="form-control mr-sm-2" type="text" placeholder="Username" aria-label="Username" name="username" id="username">
                    <input class="form-control mr-sm-2" type="text" placeholder="Password" aria-label="Password" name="password" id="password">
                    <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit">
                    <input type="hidden" name="controllerAction" value="HomeManagement.logon">
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
        <div class="row">
            <div class="card margin-card mx-auto" style="width: 19rem;">
                <a href="javascript:goToProdottoView('mavic2enterprise');">
                <img src="/ProgettoWeb/images/products/dji-mavic2enterprise.jpg" class="card-img-top" width="300" height="300" alt="dji-mavic2enterprise">
                <div class="card-body">
                    <p class="card-title">DJI Mavic 2 enterprise</p>
                </div>
            </div> 

            <div class="card margin-card mx-auto" style="width: 19rem;">
                <a href="javascript:goToProdottoView('mavicair');">
                <img src="/ProgettoWeb/images/products/dji-mavicair.jpg" class="card-img-top" width="300" height="300" alt="dji-mavic2enterprise">
                <div class="card-body">
                    <p class="card-title">DJI Mavic air</p>
                </div>
            </div>

            <div class="card margin-card mx-auto" style="width: 19rem;">
                <a href="javascript:goToProdottoView('matrice219RTK-G');">
                <img src="/ProgettoWeb/images/products/dji-matrice219RTK-G.jpg" class="card-img-top" width="300" height="300" alt="dji-mavic2enterprise">
                <div class="card-body">
                    <p class="card-title">DJI Matrice219RTK-G</p>
                </div>
            </div>
        </div>

       <div class="row">
                  <div class="card margin-card mx-auto" style="width: 19rem;">
                      <a href="javascript:goToProdottoView('anafi');">
                <img src="/ProgettoWeb/images/products/parrot-anafi.jpg" class="card-img-top" width="300" height="250" alt="parrot-anafi" >
                <div class="card-body">
                    <p class="card-text">Parrot Anafi</p>
                </div>
            </div> 
           <div class="card margin-card mx-auto" style="width: 19rem;">
               <a href="javascript:goToProdottoView('typhoonH');">
                <img src="/ProgettoWeb/images/products/yuneec-typhoonH.jpg" class="card-img-top" width="300" height="300" alt="dji-mavic2enterprise">
                <div class="card-body">
                    <p class="card-title">Yuneec Typhoon-H</p>
                </div>
            </div>

            <div class="card margin-card mx-auto" style="width: 19rem;">
                <a href="javascript:goToProdottoView('beepop2');">
                <img src="/ProgettoWeb/images/products/parrot-beepop2.jpg" class="card-img-top" width="300" height="300" alt="dji-mavic2enterprise">
                <div class="card-body">
                    <p class="card-title">Parrot Beepop 2</p>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="card margin-card mx-auto" style="width: 19rem;">
                <a href="javascript:goToProdottoView('mi4k');">
                <img src="/ProgettoWeb/images/products/xiaomi-mi4k.jpg" class="card-img-top" width="300" height="250" alt="xiaomi-mi4k" >
                <div class="card-body">
                    <p class="card-text">xiaomi mi4k</p>
                </div>
            </div> 
            <div class="card margin-card mx-auto" style="width: 19rem;">
                <a href="javascript:goToProdottoView('inspire1pro');">
                <img src="/ProgettoWeb/images/products/dji-inspire1pro.jpg" class="card-img-top" width="300" height="300" alt="dji-mavic2enterprise">
                <div class="card-body">
                    <p class="card-title">DJI Inspire 1 Pro</p>
                </div>
            </div>

            <div class="card margin-card mx-auto" style="width: 19rem;">
                <a href="javascript:goToProdottoView('hubsan-x4');">
                <img src="/ProgettoWeb/images/products/hubsan-x4.jpg" class="card-img-top" width="300" height="300" alt="dji-mavic2enterprise">
                <div class="card-body">
                    <p class="card-title">Hubsan X4</p>
                </div>
            </div>
        </div>
        </div>
        
        <form name="ProdottoView" action="Dispatcher"  method="post">
            <input type="hidden" name="search" id="search" >
            <input type="hidden" name="controllerAction" value="ProdottoManagement.view">
        </form>
        <footer>

            <form action="Dispatcher" method="post">
                Admin Area
                <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit">
                <input type="hidden" name="controllerAction" value="AdminManagement.viewLogin">
            </form>
        </footer>
    </body>
</html>
