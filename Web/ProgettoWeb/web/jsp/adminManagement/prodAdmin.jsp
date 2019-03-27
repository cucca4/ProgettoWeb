
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedAdmin"%>
<%@page import="model.mo.User"%>
<%@page import="model.mo.Orders"%>
<%@page import="java.util.List"%>
<%@page import="model.mo.Product"%>

<% LoggedAdmin loggedAdmin = (LoggedAdmin)request.getAttribute("loggedadmin");
   String createMessage = (String) request.getAttribute("createMessage");
   String deleteMessage = (String) request.getAttribute("deleteMessage");
   List<Orders> Listorders = (List<Orders>) request.getAttribute("Listorders");
   List<Product> prodqty = (List<Product>) request.getAttribute("prodqty");
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
                <form class="form-inline mt-2 mt-md-0" name="search" action="Dispatcher" method="post"> 
                    <input class="form-control mr-sm-2" type="text" placeholder="Cerca" aria-label="search" name="search" id="search">
                    <input class="btn btn-success my-2 my-sm-0 mr-sm-2" type="submit" value="Vai">
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
                    <input class="form-control ml-sm-2 mr-sm-2" type="number" placeholder="Codice ID" aria-label="Codice ID" name="Prod_Id" id="Prod_Id">
                    <input class="form-control ml-sm-2 mr-sm-2" type="text" placeholder="Marca" aria-label="brand" name="brand" id="brand">
                    <input class="form-control mr-sm-2" type="text" placeholder="Modello" aria-label="model" name="model" id="model">
                    <input class="form-control mr-sm-2" type="text" placeholder="Descrizione" aria-label="description" name="description" id="description">
                    <input class="form-control mr-sm-2" type="text" placeholder="Categoria" aria-label="category" name="category" id="category">
                    <input class="form-control mr-sm-2" type="number" placeholder="prezzo" aria-label="price" name="price" id="price">
                    <input class="form-control mr-sm-2" type="number" placeholder="Quantità" aria-label="qty" name="qty" id="qty">
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
            <section class="clearfix">
                <form class="form-inline mt-2 mt-md-0" name="update" action="Dispatcher" method="post"> 
                    Aggiorna lo stato di un ordine inserendo codice ID dell'ordine e il nuovo stato<br>
                    <input class="form-control  ml-sm-2 mr-sm-2" type="number" placeholder="Order ID" aria-label="Order ID" name="order_Id" id="order_Id">
                    <input class="form-control mr-sm-2" type="text" placeholder="Status" aria-label="Status" name="status" id="status">
                    <input class="btn btn-outline-info my-2 mr-sm-0 mr-sm-2" type="submit" value="Aggiorna">
                    <input type="hidden" name="controllerAction" value="AdminManagement.updateOrder">
                </form>
            </section>
            <div class="pt-2"></div> 
            <section class="clearfix">
                <div class="pt-2"></div>
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
                            Pezzi
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
                        <%=Listorders.get(i).getDescription()%>
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
            <div class="pt-3"></div>
            </section>
            <section class="clearfix">
                <form class="form-inline mt-2 mt-md-0" name="update" action="Dispatcher" method="post"> 
                    Aggiorna lo stato di un prodotto inserendo codice ID del prodotto e il nuovo stato<br>
                    <input class="form-control  ml-sm-2 mr-sm-2" type="number" placeholder="Prod ID" aria-label="Prod ID" name="prod_Id" id="prod_Id">
                    <input class="form-control  ml-sm-2 mr-sm-2" type="text" placeholder="Modello" aria-label="Model ID" name="model" id="model">
                    <input class="form-control  ml-sm-2 mr-sm-2" type="text" placeholder="Prezzo" aria-label="Price" name="price" id="price">
                    <input class="form-control  ml-sm-2 mr-sm-2" type="number" placeholder="Quantità" aria-label="Qty" name="qty" id="qty">
                    <input class="btn btn-outline-info my-2 mr-sm-0 mr-sm-2" type="submit" value="Aggiorna">
                    <input type="hidden" name="controllerAction" value="ProdottoManagement.update">
                </form>
            </section>
            <section class="clearfix">
                <div class="pt-2"></div>
                Lista della fornitura del magazzino<br>
                <div class="pt-2"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-sm">
                            Product ID
                        </div>
                        <div class="col-sm">
                            Modello
                        </div>
                        <div class="col-sm">
                            Prezzo €
                        </div>
                        <div class="col-sm">
                            Quantità
                        </div>
                    </div> 
                    <% for(i=0;i<prodqty.size();i++) { %>
                    <div class="row">
                      <div class="col-sm">
                        <%=prodqty.get(i).getProd_Id()%>
                      </div>
                      <div class="col-sm">
                        <%=prodqty.get(i).getModel()%>
                      </div>
                      <div class="col-sm">
                        <%=prodqty.get(i).getPrice()%>
                      </div>
                        <div class="col-sm">
                        <%=prodqty.get(i).getQty()%>
                      </div>
                    </div>
                    <%}%>
                </div>
            <div class="pt-3"></div>
            </section>    
        </div>
    </body>
</html>