package org.apache.jsp.jsp.prodottoManagement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.session.mo.LoggedUser;

public final class prodottoView_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!doctype html>\r\n");
      out.write("\r\n");

    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    String applicationMessage = (String) request.getAttribute("applicationMessage");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html lang=\"it\">\r\n");
      out.write("<head>\r\n");
      out.write("    <!-- Required meta tags -->\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("\r\n");
      out.write("    <!-- Bootstrap CSS -->\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap-grid.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap-reboot.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap-grid.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap-reboot.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/homestyle.css\">\r\n");
      out.write("\r\n");
      out.write("    <title>Dronazon</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"clearfix\">\r\n");
      out.write("    <nav class=\"navbar navbar-expand-md navbar-dark fixed-top bg-dark clearfix\">\r\n");
      out.write("        <a class=\"navbar-brand\" href=\"#\">Dronazon</a>\r\n");
      out.write("        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarCollapse\" aria-controls=\"navbarCollapse\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("        </button>\r\n");
      out.write("        <div class=\"collapse navbar-collapse\" id=\"navbarCollapse\">\r\n");
      out.write("            <ul class=\"navbar-nav mr-auto\">\r\n");
      out.write("                <li class=\"nav-item active\">\r\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">Home <span class=\"sr-only\">(current)</span></a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">Catalogo</a>\r\n");
      out.write("                </li>\r\n");
      out.write("      \r\n");
      out.write("                ");
 if(loggedOn) {
      out.write("\r\n");
      out.write("                <span class=\"nav-link nav-message\"> ");
      out.print(applicationMessage);
      out.write("</span>\r\n");
      out.write("                <li class=\"nav-item\">\r\n");
      out.write("                    \r\n");
      out.write("                    <form class=\"form-inline mt-2 mt-md-0\" name=\"userarea\" action=\"Dispatcher\" method=\"post\"> \r\n");
      out.write("                    <input class=\"btn btn-outline-success my-2 my-sm-0 mr-sm-2\" type=\"submit\" value=\"Area Personale\">\r\n");
      out.write("                    <input type=\"hidden\" name=\"controllerAction\" value=\"UserArea.view\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                </li>\r\n");
      out.write("                ");
}
      out.write("\r\n");
      out.write("            </ul>\r\n");
      out.write("      \r\n");
      out.write("        ");
 if(!loggedOn) {
      out.write("     \r\n");
      out.write("            <form class=\"form-inline mt-2 mt-md-0\" name=\"login\" action=\"Dispatcher\" method=\"post\"> \r\n");
      out.write("            <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Username\" aria-label=\"Username\" name=\"username\" id=\"username\">\r\n");
      out.write("            <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Password\" aria-label=\"Password\" name=\"password\" id=\"password\">\r\n");
      out.write("            <input class=\"btn btn-outline-success my-2 my-sm-0 mr-sm-2\" type=\"submit\">\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"ProdottoManagement.logon\">\r\n");
      out.write("            </form>\r\n");
      out.write("        ");
} else {
      out.write("\r\n");
      out.write("            <form class=\"form-inline mt-2 mt-md-0\" name=\"logout\" action=\"Dispatcher\" method=\"post\"> \r\n");
      out.write("            <input class=\"btn btn-outline-danger my-2 mr-sm-0 mr-sm-2\" type=\"submit\" value=\"Logout\">\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"ProdottoManagement.logout\">\r\n");
      out.write("            </form>\r\n");
      out.write("      \r\n");
      out.write("         ");
}
      out.write("\r\n");
      out.write("      \r\n");
      out.write("        <form class=\"form-inline mt-2 mt-md-0\">\r\n");
      out.write("        <form class=\"form-inline mt-2 mt-md-0\" name=\"login\" action=\"Dispatcher\" method=\"post\"> \r\n");
      out.write("        <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Cerca\" aria-label=\"Search\">\r\n");
      out.write("        <input class=\"btn btn-outline-success my-2 my-sm-0 mr-sm-2\" type=\"submit\">Cerca</input>\r\n");
      out.write("        <input type=\"hidden\" name=\"controllerAction\" value=\"\">\r\n");
      out.write("        </form>\r\n");
      out.write("        </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"pt-5\"></div>\r\n");
      out.write("    <div class=\"pt-3\"></div>\r\n");
      out.write("    <div class=\"container bg-light mx-auto\">\r\n");
      out.write("            <div class=\"pt-4\"></div>\r\n");
      out.write("            <div class=\"float-left\">\r\n");
      out.write("                <img src=\"images/prova.jpg\" class=\"mr-3\" width=\"320\" height=\"320\">\r\n");
      out.write("            </div><br>\r\n");
      out.write("            <div class=\"media-body\">\r\n");
      out.write("                <h5 class=\"mt-0 mb-1\">brand-model-price</h5>\r\n");
      out.write("                product description.\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"pt-3\"></div>\r\n");
      out.write("            <button type=\"button\" class=\"btn btn-warning\">Compra</button>\r\n");
      out.write("            <div class=\"pt-xl-5\"></div>\r\n");
      out.write("            <div class=\"pt-xl-5\"></div>\r\n");
      out.write("            <div class=\"pt-xl-5\"></div>\r\n");
      out.write("            <div class=\"pt-xl-5\"></div>\r\n");
      out.write("            <div class=\"pt-xl-5\"></div>\r\n");
      out.write("            <div class=\"pt-xl-5\"></div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
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
