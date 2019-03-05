<%@page import="java.util.List"%>

<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.User"%>
<%@page import="model.mo.Product"%>
<%@page import="model.mo.Orders"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%User user = (User)request.getAttribute("user");
  LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
  Boolean loggedOn = (Boolean)request.getAttribute("loggedOn");
  String applicationMessage = (String)request.getAttribute("applicationMessage");
  String actionPage = (String)request.getAttribute("actionPage");
  List<Orders> orders = (List<Orders>)request.getAttribute("orders");
  int i=0;
  
  String thisform = null;
  if(actionPage.equals("account")) thisform = "'block','none','none'";
  if(actionPage.equals("setpassword")) thisform = "'none','block','none'";
  if(actionPage.equals("delete")) thisform = "'none','none','block'";%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        
        <title>Dronazon</title>
        
        <script language="javascript">
            function whichForm(accountForm, passwordForm, deleteAccountForm){
                forms = document.querySelectorAll(".userform");
                forms[0].style.display = accountForm;
                forms[1].style.display = passwordForm;
                forms[2].style.display = deleteAccountForm;
            }
            
            function visualizeAccount(){
                whichForm("block","none","none");
            }
            
            function changePassword(){
                whichForm("none","block","none");
            }
            
            function deleteAccount(){
                whichForm("none","none","block");
            }
            
            function profileLoadHandler(){
                whichForm(<%=thisform%>);
            }
            
            function gotoOrders(){
                document.orders.submit();
            }
            
            function goToViewOrders(order_Id, totprice, status){
                
                document.getElementById("order_id").value=order_Id;
                document.getElementById("totprice").value=totprice;
                document.getElementById("status").value=status;
                
            }
            
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
                <li class="nav-item ">
                    <a class="nav-link" href="#">Home</a>
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
      
        <form class="form-inline mt-2 mt-md-0">
        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        </div>
    </nav>

    
    
    <div class="pt-5"></div>
    <div class="pt-3"></div>
    <div class="container bg-light mx-auto">
    
    <div class="pt-3"></div>    
        <ul class="list-unstyled">
            <li class="media">
                <img src="images/scatola.jpg" class="mr-3" width="120" height="120">
                <div class="media-body">
                    <h5 class="mt-0 mb-1">ORDINI</h5>
                     Visualizza la cronologia degli ordini effettuati o lo stato di quelli ancora da consegnare.
                     <section>
                        <form name="oForm" action="dispatcher" method="post">   
                        <br><br>
                        <div>
                            <%for(i=0; i<orders.size(); i++) {%>
                                <a href="javascript:goToViewOrders('<%=orders.get(i).getOrder_Id()%>', <%=orders.get(i).getTotprice()%>, <%=orders.get(i).getStatus()%>');">
                                    <article class=" clearfix">
                                    <img src="images/prova.png" alt="Chat" class="support-img-chat">
                                    <div class='support-article-centraldiv'>
                                        <h2 class='support-h2'>
                                        <%=orders.get(i).getOrder_Id()%>, 
                                        
                                        </h2>
                                        <h3 class='clearfix'>
                                        
                                        <br>
                                        <% if(orders.get(i).getStatus()== "elaboramento"){%>
                                        
                                            Ordine non ancora spedito
                                        
                                        <%} else {%>
                                            <% if(orders.get(i).getStatus()== "spedito"){%>
                                        
                                            Ordine spedito
                                        
                                        <%} else {%>
                                            <strong>Ordine consegnato</strong>
                                        <%}%>
                                        <%}%>
                                        </h3>
                                    </div>
                                    </article>
                                </a> 
                            <%}%>
                        
                            <% if(orders.size()==0) {%>
                                <article class="support-article-chat clearfix">
                                    <img src="images/error.png" alt="Chat" class="support-img-chat">
                                    <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        Nessun ordine effettuato!
                                    </h2>
                                    <h3 class='support-h3'>
                                        Effettua un ordine per visualizzarne i dettagli.
                                    </h3>
                                    </div>
                                </article>
                            <%}%>
                        
                        </div>  
                        </form>
                         
                     </section>
                </div>
            </li>
            <li class="media my-4">
            <img src="images/omino-ok.jpg" class="mr-3" width="120" height="120">
                <div class="media-body">
                    <h5 class="mt-0 mb-1">PROFILO</h5>
                    Controlla le informazioni sul tuo profilo o modifica l'indirizzo di consegna.
                    <section>
                            <form name="uForm" action="Dispatcher" method="post">
                            <label for="username"> Username </label>
                            <input type="text" id="username" name="username" maxlength="40" value="<%=user.getUsername()%>" required>
                            </br></br>
                            <label for="fistname"> Nome </label>
                            <input type="text" id="fistname" name="fistname" maxlength="40" value="<%=user.getFirstname()%>" required>
                            </br></br>
                            <label for="surname"> Cognome </label>
                            <input type="text" id="surname" name="surname" maxlength="40" value="<%=user.getSurname()%>" required>
                            </br></br>
                            <label for="email"> E-mail </label>
                            <input type="email" id="email" name="email" maxlength="40" value="<%=user.getEmail()%>" required>            
                            </br></br>
                            <label for="address"> Indirizzo </label>
                            <input type="address" id="address" name="address" maxlength="40" value="<%=user.getAddress()%>" required>            
                            </br></br>
                            <label for="city"> Città </label>
                            <input type="city" id="city" name="city" maxlength="40" value="<%=user.getCity()%>" required>            
                            </br></br>
                            <label for="cap"> Cap </label>
                            <input type="cap" id="cap" name="cap" maxlength="40" value="<%=user.getCap()%>" required>            
                            </br></br>
                            <input type="submit" value="Salva" class="submit submit-dimensioni">
                            <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
                            <input type="hidden" name="controllerAction" value="userArea.modify"/>
                            </form>
                    </section>
                    <section>
                            <form name="passwordForm" action="Dispatcher" method="post">
                            <label for="oldPassword"> Password </label>
                            <input type="password" id="oldPassword" name="oldpassword" maxlength="40" required>
                            </br></br>
                            <label for="newPassword"> New password </label>
                            <input type="password" id="newPassword" name="newpassword" maxlength="40" required>
                            </br></br>
                            <input type="submit" value="Salva" class="submit submit-dimensioni">
                            <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
                            <input type="hidden" name="controllerAction" value="userArea.modifyPassword"/>
                            </form>
                    </section>
                    <section >
                        <form name="deleteAccountForm" action="Dispatcher" method="post">
                        <label for="Password"> Password </label>
                        <input type="password" id="Password" name="password" maxlength="40" required>
                        </br></br>
                        <input type="submit" value="Elimina" class="submit">
                        <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
                        <input type="hidden" name="controllerAction" value="userArea.deleteAccount"/>
                        </form>
                    </section>
                </div>
            </li>
        </ul>
    </div>
</body>

</html>