<%@page import="model.session.mo.LoggedUser"%>
<!doctype html>

<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
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
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
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
            <input type="hidden" name="controllerAction" value="HomeManagement.logon">
            </form>
        <%} else {%>
            <form class="form-inline mt-2 mt-md-0" name="logout" action="Dispatcher" method="post"> 
            <input class="btn btn-outline-danger my-2 mr-sm-0 mr-sm-2" type="submit" value="Logout">
            <input type="hidden" name="controllerAction" value="HomeManagement.logout">
            </form>
      
         <%}%>
      
        <form class="form-inline mt-2 mt-md-0">
        <form class="form-inline mt-2 mt-md-0" name="login" action="Dispatcher" method="post"> 
        <input class="form-control mr-sm-2" type="text" placeholder="Cerca" aria-label="Search">
        <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit">Cerca</input>
        <input type="hidden" name="controllerAction" value="">
        </form>
        </div>
    </nav>

    <div class="pt-5"></div>
    <div class="pt-3"></div>
    <div class="container bg-light mx-auto">
    <div class="row">
        <div class="col">
            <div class="card margin-card mx-auto" style="width: 18rem;">
            <img src="images/prova.jpg" class="card-img-top" >
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
            </div> 
        </div>
        <div class="col">
            <div class="card margin-card mx-auto" style="width: 18rem;">
            <img src="images/prova.jpg" class="card-img-top" >
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
            </div>
        </div>
        <div class="col">
            <div class="card margin-card mx-auto" style="width: 18rem;">
            <img src="images/prova.jpg" class="card-img-top" >
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card margin-card mx-auto" style="width: 18rem;">
            <img src="images/prova.jpg" class="card-img-top" >
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
            </div> 
        </div>
        <div class="col">
            <div class="card margin-card mx-auto" style="width: 18rem;">
            <img src="images/prova.jpg" class="card-img-top" >
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
            </div>
        </div>
        <div class="col">
            <div class="card margin-card mx-auto" style="width: 18rem;">
            <img src="images/prova.jpg" class="card-img-top" >
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="card margin-card mx-auto" style="width: 18rem;">
            <img src="images/prova.jpg" class="card-img-top" >
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
            </div> 
        </div>
        <div class="col">
            <div class="card margin-card mx-auto" style="width: 18rem;">
            <img src="images/prova.jpg" class="card-img-top" >
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
            </div>
        </div>
        <div class="col">
            <div class="card margin-card mx-auto" style="width: 18rem;">
            <img src="images/prova.jpg" class="card-img-top" >
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
            </div>
        </div>
    </div>
    </div>
</body>

</html>