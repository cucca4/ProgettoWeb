<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>

<%
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Home";
%>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="/include/htmlHead.inc"%>  
  </head>
  <body>
    <%@include file="/include/header.inc"%>
    <main>
      <%if (loggedOn) {%>
      Benvenuto ${loggedUser.getFirstname()} ${loggedUser.getSurname()}!<br/>
      Clicca sulla voce "Rubrica" del menù per gestire i tuoi contatti.
      <%} else {%>
      Benvenuto.
      Fai il logon per gestire la tua rubrica.
      <%}%>
    </main>
    <%@include file="/include/footer.inc"%>
</html>
