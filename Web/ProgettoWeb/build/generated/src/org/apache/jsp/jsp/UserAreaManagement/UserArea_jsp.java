package org.apache.jsp.jsp.UserAreaManagement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import model.session.mo.LoggedUser;
import model.mo.User;
import model.mo.Product;
import model.mo.Orders;

public final class UserArea_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
User user = (User)request.getAttribute("user");
  LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
  Boolean loggedOn = (Boolean)request.getAttribute("loggedOn");
  String applicationMessage = (String)request.getAttribute("applicationMessage");
  String actionPage = (String)request.getAttribute("actionPage");
  List<Orders> orders = (List<Orders>)request.getAttribute("orders");
  int i=0;
  
  String thisform = null;
  if(actionPage.equals("account")) thisform = "'block','none','none'";
  if(actionPage.equals("setpassword")) thisform = "'none','block','none'";
  if(actionPage.equals("delete")) thisform = "'none','none','block'";
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        \n");
      out.write("        <title>Dronazon</title>\n");
      out.write("        \n");
      out.write("        <script language=\"javascript\">\n");
      out.write("            function whichForm(accountForm, passwordForm, deleteAccountForm){\n");
      out.write("                forms = document.querySelectorAll(\".userform\");\n");
      out.write("                forms[0].style.display = accountForm;\n");
      out.write("                forms[1].style.display = passwordForm;\n");
      out.write("                forms[2].style.display = deleteAccountForm;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function visualizeAccount(){\n");
      out.write("                whichForm(\"block\",\"none\",\"none\");\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function changePassword(){\n");
      out.write("                whichForm(\"none\",\"block\",\"none\");\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function deleteAccount(){\n");
      out.write("                whichForm(\"none\",\"none\",\"block\");\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function profileLoadHandler(){\n");
      out.write("                whichForm(");
      out.print(thisform);
      out.write(");\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function gotoOrders(){\n");
      out.write("                document.orders.submit();\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function goToViewOrders(order_Id, totprice, status){\n");
      out.write("                \n");
      out.write("                document.getElementById(\"order_id\").value=order_Id;\n");
      out.write("                document.getElementById(\"totprice\").value=totprice;\n");
      out.write("                document.getElementById(\"status\").value=status;\n");
      out.write("                \n");
      out.write("            }\n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <body class=\"clearfix\">\n");
      out.write("    <nav class=\"navbar navbar-expand-md navbar-dark fixed-top bg-dark clearfix\">\n");
      out.write("        <a class=\"navbar-brand\" href=\"#\">Dronazon</a>\n");
      out.write("        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarCollapse\" aria-controls=\"navbarCollapse\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("        </button>\n");
      out.write("        <div class=\"collapse navbar-collapse\" id=\"navbarCollapse\">\n");
      out.write("            <ul class=\"navbar-nav mr-auto\">\n");
      out.write("                <li class=\"nav-item active\">\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">Home</a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">Catalogo</a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link \" href=\"#\">Area Personale<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                </li>\n");
      out.write("            </ul>  \n");
      out.write("            <form class=\"form-inline mt-2 mt-md-0\" name=\"logout\" action=\"Dispatcher\" method=\"post\"> \n");
      out.write("            <input class=\"btn btn-outline-danger my-2 my-sm-0 mr-sm-2\" type=\"submit\" value=\"Logout\">\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManagement.logout\">\n");
      out.write("            </form>\n");
      out.write("      \n");
      out.write("        <form class=\"form-inline mt-2 mt-md-0\">\n");
      out.write("        <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Search\" aria-label=\"Search\">\n");
      out.write("        <button class=\"btn btn-outline-success my-2 my-sm-0\" type=\"submit\">Search</button>\n");
      out.write("        </form>\n");
      out.write("        </div>\n");
      out.write("    </nav>\n");
      out.write("\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    <div class=\"pt-5\"></div>\n");
      out.write("    <div class=\"pt-3\"></div>\n");
      out.write("    <div class=\"container bg-light mx-auto\">\n");
      out.write("    \n");
      out.write("    <div class=\"pt-3\"></div>    \n");
      out.write("        <ul class=\"list-unstyled\">\n");
      out.write("            <li class=\"media\">\n");
      out.write("                <img src=\"images/scatola.jpg\" class=\"mr-3\" width=\"120\" height=\"120\">\n");
      out.write("                <div class=\"media-body\">\n");
      out.write("                    <h5 class=\"mt-0 mb-1\">ORDINI</h5>\n");
      out.write("                     Visualizza la cronologia degli ordini effettuati o lo stato di quelli ancora da consegnare.\n");
      out.write("                     <section>\n");
      out.write("                        <form name=\"oForm\" action=\"dispatcher\" method=\"post\">\n");
      out.write("                            \n");
      out.write("                        <br><br>\n");
      out.write("                        <div>\n");
      out.write("                            ");
for(i=0; i<orders.size(); i++) {
      out.write("\n");
      out.write("                            \n");
      out.write("                                <a href=\"javascript:goToViewOrders('");
      out.print(orders.get(i).getOrder_Id());
      out.write("', ");
      out.print(orders.get(i).getTotprice());
      out.write(',');
      out.write(' ');
      out.print(orders.get(i).getStatus());
      out.write("');\">\n");
      out.write("                            \n");
      out.write("                                    <article class=\" clearfix\">\n");
      out.write("                                    <img src=\"images/prova.png\" alt=\"Chat\" class=\"support-img-chat\">\n");
      out.write("                                    <div class='support-article-centraldiv'>\n");
      out.write("                                        <h2 class='support-h2'>\n");
      out.write("                                        ");
      out.print(orders.get(i).getOrder_Id());
      out.write(", \n");
      out.write("                                        \n");
      out.write("                                        </h2>\n");
      out.write("                                        <h3 class='clearfix'>\n");
      out.write("                                        \n");
      out.write("                                        <br>\n");
      out.write("                                        ");
 if(orders.get(i).getStatus()== "elaboramento"){
      out.write("\n");
      out.write("                                        \n");
      out.write("                                            Ordine non ancora spedito\n");
      out.write("                                        \n");
      out.write("                                        ");
} else {
      out.write("\n");
      out.write("                                            ");
 if(orders.get(i).getStatus()== "spedito"){
      out.write("\n");
      out.write("                                        \n");
      out.write("                                            Ordine spedito\n");
      out.write("                                        \n");
      out.write("                                        ");
} else {
      out.write("\n");
      out.write("                                            <strong>Ordine consegnato</strong>\n");
      out.write("                                        ");
}
      out.write("\n");
      out.write("                                        ");
}
      out.write("\n");
      out.write("                                        </h3>\n");
      out.write("                                    </div>\n");
      out.write("                 \n");
      out.write("                                    </article>\n");
      out.write("                            \n");
      out.write("                                </a>\n");
      out.write("                                    \n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                        \n");
      out.write("                            ");
 if(orders.size()==0) {
      out.write("\n");
      out.write("                                <article class=\"support-article-chat clearfix\">\n");
      out.write("                                    <img src=\"images/error.png\" alt=\"Chat\" class=\"support-img-chat\">\n");
      out.write("                                    <div class='support-article-centraldiv'>\n");
      out.write("                                    <h2 class='support-h2'>\n");
      out.write("                                        Nessun ordine effettuato!\n");
      out.write("                                    </h2>\n");
      out.write("                                    <h3 class='support-h3'>\n");
      out.write("                                        Effettua un ordine per visualizzarne i dettagli.\n");
      out.write("                                    </h3>\n");
      out.write("                                    </div>\n");
      out.write("                                </article>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                        \n");
      out.write("                        </div>  \n");
      out.write("                        </form>\n");
      out.write("                         \n");
      out.write("                     </section>\n");
      out.write("                </div>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"media my-4\">\n");
      out.write("            <img src=\"images/omino-ok.jpg\" class=\"mr-3\" width=\"120\" height=\"120\">\n");
      out.write("                <div class=\"media-body\">\n");
      out.write("                    <h5 class=\"mt-0 mb-1\">PROFILO</h5>\n");
      out.write("                    Controlla le informazioni sul tuo profilo o modifica l'indirizzo di consegna.\n");
      out.write("                    <section>\n");
      out.write("                            <form name=\"uForm\" action=\"Dispatcher\" method=\"post\">\n");
      out.write("                            <label for=\"username\"> Username </label>\n");
      out.write("                            <input type=\"text\" id=\"username\" name=\"username\" maxlength=\"40\" value=\"");
      out.print(user.getUsername());
      out.write("\" required>\n");
      out.write("                            </br></br>\n");
      out.write("                            <label for=\"fistname\"> Nome </label>\n");
      out.write("                            <input type=\"text\" id=\"fistname\" name=\"fistname\" maxlength=\"40\" value=\"");
      out.print(user.getFirstname());
      out.write("\" required>\n");
      out.write("                            </br></br>\n");
      out.write("                            <label for=\"surname\"> Cognome </label>\n");
      out.write("                            <input type=\"text\" id=\"surname\" name=\"surname\" maxlength=\"40\" value=\"");
      out.print(user.getSurname());
      out.write("\" required>\n");
      out.write("                            </br></br>\n");
      out.write("                            <label for=\"email\"> E-mail </label>\n");
      out.write("                            <input type=\"email\" id=\"email\" name=\"email\" maxlength=\"40\" value=\"");
      out.print(user.getEmail());
      out.write("\" required>            \n");
      out.write("                            </br></br>\n");
      out.write("                            <label for=\"address\"> Indirizzo </label>\n");
      out.write("                            <input type=\"address\" id=\"address\" name=\"address\" maxlength=\"40\" value=\"");
      out.print(user.getAddress());
      out.write("\" required>            \n");
      out.write("                            </br></br>\n");
      out.write("                            <label for=\"city\"> Città </label>\n");
      out.write("                            <input type=\"city\" id=\"city\" name=\"city\" maxlength=\"40\" value=\"");
      out.print(user.getCity());
      out.write("\" required>            \n");
      out.write("                            </br></br>\n");
      out.write("                            <label for=\"cap\"> Cap </label>\n");
      out.write("                            <input type=\"cap\" id=\"cap\" name=\"cap\" maxlength=\"40\" value=\"");
      out.print(user.getCap());
      out.write("\" required>            \n");
      out.write("                            </br></br>\n");
      out.write("                            <input type=\"submit\" value=\"Salva\" class=\"submit submit-dimensioni\">\n");
      out.write("                            <input type=\"hidden\" name=\"userId\" value=\"");
      out.print(user.getUserId());
      out.write("\"/>\n");
      out.write("                            <input type=\"hidden\" name=\"controllerAction\" value=\"UserArea.modify\"/>\n");
      out.write("                            </form>\n");
      out.write("                    </section>\n");
      out.write("                    <section>\n");
      out.write("                            <form name=\"passwordForm\" action=\"Dispatcher\" method=\"post\">\n");
      out.write("                            <label for=\"oldPassword\"> Password </label>\n");
      out.write("                            <input type=\"password\" id=\"oldPassword\" name=\"oldpassword\" maxlength=\"40\" required>\n");
      out.write("                            </br></br>\n");
      out.write("                            <label for=\"newPassword\"> New password </label>\n");
      out.write("                            <input type=\"password\" id=\"newPassword\" name=\"newpassword\" maxlength=\"40\" required>\n");
      out.write("                            </br></br>\n");
      out.write("                            <input type=\"submit\" value=\"Salva\" class=\"submit submit-dimensioni\">\n");
      out.write("                            <input type=\"hidden\" name=\"userId\" value=\"");
      out.print(user.getUserId());
      out.write("\"/>\n");
      out.write("                            <input type=\"hidden\" name=\"controllerAction\" value=\"UserArea.modifyPassword\"/>\n");
      out.write("                            </form>\n");
      out.write("                    </section>\n");
      out.write("                    <section >\n");
      out.write("                        <form name=\"deleteAccountForm\" action=\"Dispatcher\" method=\"post\">\n");
      out.write("                        <label for=\"Password\"> Password </label>\n");
      out.write("                        <input type=\"password\" id=\"Password\" name=\"password\" maxlength=\"40\" required>\n");
      out.write("                        </br></br>\n");
      out.write("                        <input type=\"submit\" value=\"Elimina\" class=\"submit\">\n");
      out.write("                        <input type=\"hidden\" name=\"userId\" value=\"");
      out.print(user.getUserId());
      out.write("\"/>\n");
      out.write("                        <input type=\"hidden\" name=\"controllerAction\" value=\"UserArea.deleteAccount\"/>\n");
      out.write("                        </form>\n");
      out.write("                    </section>\n");
      out.write("                </div>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
