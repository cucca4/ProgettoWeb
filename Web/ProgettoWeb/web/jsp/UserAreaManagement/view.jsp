<%@page session="false"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.Contact"%>

<%int i = 0;
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Rubrica";

  String selectedInitial = (String) request.getAttribute("selectedInitial");
  List<String> initials = (List<String>) request.getAttribute("initials");
  List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");
%>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="/include/htmlHead.inc"%>
    <style>
      
      #newContactButtonSection {
        margin: 12px 0;
      }      
      
      #initialSelector {
        margin: 12px 0;
      }

      .initial {  
        color: #a3271f;
        letter-spacing: 2px;
      }

      .selectedInitial { 
        color: black;
        letter-spacing: 2px;
      }

      #contacts {
        margin: 12px 0;
      }

      #contacts article {
          float: left;
          width: 250px;
          border-width: 1px;
          border-style: solid;
          border-radius: 10px;
          border-color: #a3271f;
          padding: 10px 8px 10px 20px;
          margin: 0 18px 16px 0;
          background: linear-gradient(to right,#fdfbfb,#ebedee);
          box-shadow: 0 3px 2px #777;
      }

      #contacts article h1 a {
          color: #a3271f;
      }

      #trashcan {
        float: right;
      }

      .phone {
        font-size: 0.9em;
        color: #a3271f
      }

      .email {
        font-size: 0.8em;
        color: #a3271f
      } 
      
      address {
        font-size: 0.8em;
        font-style: italic;  
        color: black;
      }
      
    </style>
    <script language="javascript">

      function insertContact() {
        document.insertForm.submit();
      }

      function mainOnLoadHandler() {
        document.querySelector("#newContactButton").addEventListener("click", insertContact);
      }

      function changeInitial(inital) {
        document.changeInitialForm.selectedInitial.value = inital;
        document.changeInitialForm.submit();
      }

      function modifyContact(contactId) {
        f = document.modifyForm;
        f.contactId.value = contactId;
        f.submit();
      }

      function deleteContact(code) {
        document.deleteForm.contactId.value = code;
        document.deleteForm.submit();
      }

    </script>
  </head>
  <body>
    <%@include file="/include/header.inc"%>
    <main>
      <section id="pageTitle">
        <h1>Contatti: <%=selectedInitial%></h1>
      </section>
      <section id="newContactButtonSection">
        <input type="button" id="newContactButton" name="newContactButton" 
               class="button" value="Nuovo Contatto"/>
      </section>
      <nav id="initialSelector">
        <%if (selectedInitial.equals("*")) { %>
          <span class="selectedInitial">*</span>
        <%} else {%>
          <a class="initial" href="javascript:changeInitial('*');">*</a>
        <%}%>      
        <%for (i = 0; i < initials.size(); i++) {
            if (initials.get(i).equals(selectedInitial)) {%>
              <span class="selectedInitial"><%=initials.get(i)%></span>
          <%} else {%>
              <a class="initial" href="javascript:changeInitial('<%=initials.get(i)%>');"><%=initials.get(i)%></a>
          <%}%>  
        <%}%>
      </nav>
      <section id="contacts" class="clearfix">
        <%for (i = 0; i < contacts.size(); i++) {%>           
          <article>
            <a href="javascript:deleteContact(<%=contacts.get(i).getContactId()%>)">
              <img id="trashcan" src="images/trashcan.png" width="22" height="22"/>
            </a>
            <h1>
            <a href="javascript:modifyContact(<%=contacts.get(i).getContactId()%>)">
              <%=contacts.get(i).getSurname()%>, <%=contacts.get(i).getFirstname()%>
            </a>
            </h1>
            <span class="phone"><%= contacts.get(i).getPhone()%></span>
            <br/>
            <span class="email"><%= contacts.get(i).getEmail()%></span>
            <address>
              <%= contacts.get(i).getAddress()%><br/>
              <%= contacts.get(i).getCity()%><br/>   
            </address>
   
          </article>
        <%}%>
     </section>
     
      <form name="changeInitialForm" method="post" action="Dispatcher">
        <input type="hidden" name="selectedInitial"/>
        <input type="hidden" name="controllerAction" value="AddressBookManagement.view"/>      
      </form>    

      <form name="insertForm" method="post" action="Dispatcher">
        <input type="hidden" name="selectedInitial" value="<%=selectedInitial%>"/>
        <input type="hidden" name="controllerAction" value="AddressBookManagement.insertView"/>   <!--insertview:action legata ala visualizzazione della form vuota-->  
      </form>     

      <form name="modifyForm" method="post" action="Dispatcher">
        <input type="hidden" name="contactId"/>
        <input type="hidden" name="selectedInitial" value="<%=selectedInitial%>"/>
        <input type="hidden" name="controllerAction" value="AddressBookManagement.modifyView"/>     
      </form>     

      <form name="deleteForm" method="post" action="Dispatcher">     
        <input type="hidden" name="contactId"/>      
        <input type="hidden" name="selectedInitial" value="<%=selectedInitial%>"/>    
        <input type="hidden" name="controllerAction" value="AddressBookManagement.delete"/>
      </form>  
        
    </main>
    <%@include file="/include/footer.inc"%>
  </body>
</html>
