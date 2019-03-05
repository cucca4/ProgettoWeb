package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.session.mo.LoggedUser;

public final class ProdottoView_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("<!doctype html>\n");
      out.write("\n");

    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    String applicationMessage = (String) request.getAttribute("applicationMessage");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html lang=\"it\">\n");
      out.write("<head>\n");
      out.write("    <!-- Required meta tags -->\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("\n");
      out.write("    <!-- Bootstrap CSS -->\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap-grid.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap-reboot.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap-grid.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-4.3.1-dist/css/bootstrap-reboot.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/homestyle.css\">\n");
      out.write("\n");
      out.write("    <title>Dronazon</title>\n");
      out.write("</head>\n");
      out.write("<body class=\"clearfix\">\n");
      out.write("    <nav class=\"navbar navbar-expand-md navbar-dark fixed-top bg-dark clearfix\">\n");
      out.write("        <a class=\"navbar-brand\" href=\"#\">Dronazon</a>\n");
      out.write("        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarCollapse\" aria-controls=\"navbarCollapse\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("        </button>\n");
      out.write("        <div class=\"collapse navbar-collapse\" id=\"navbarCollapse\">\n");
      out.write("            <ul class=\"navbar-nav mr-auto\">\n");
      out.write("                <li class=\"nav-item active\">\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">Home <span class=\"sr-only\">(current)</span></a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link\" href=\"#\">Catalogo</a>\n");
      out.write("                </li>\n");
      out.write("      \n");
      out.write("                ");
 if(loggedOn) {
      out.write("\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link \" href=\"#\">Area Personale</a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <span class=\"nav-link nav-message\"> ");
      out.print(applicationMessage);
      out.write("</span>\n");
      out.write("                </li>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </ul>\n");
      out.write("      \n");
      out.write("        ");
 if(!loggedOn) {
      out.write("     \n");
      out.write("            <form class=\"form-inline mt-2 mt-md-0\" name=\"login\" action=\"Dispatcher\" method=\"post\"> \n");
      out.write("            <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Username\" aria-label=\"Username\" name=\"username\" id=\"username\">\n");
      out.write("            <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Password\" aria-label=\"Password\" name=\"password\" id=\"password\">\n");
      out.write("            <input class=\"btn btn-outline-success my-2 my-sm-0 mr-sm-2\" type=\"submit\">\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManagement.logon\">\n");
      out.write("            </form>\n");
      out.write("        ");
} else {
      out.write("\n");
      out.write("            <form class=\"form-inline mt-2 mt-md-0\" name=\"logout\" action=\"Dispatcher\" method=\"post\"> \n");
      out.write("            <input class=\"btn btn-outline-danger my-2 my-sm-0 mr-sm-2\" type=\"submit\" value=\"Logout\">\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManagement.logout\">\n");
      out.write("            </form>\n");
      out.write("      \n");
      out.write("         ");
}
      out.write("\n");
      out.write("      \n");
      out.write("        <form class=\"form-inline mt-2 mt-md-0\">\n");
      out.write("        <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Search\" aria-label=\"Search\">\n");
      out.write("        <button class=\"btn btn-outline-success my-2 my-sm-0\" type=\"submit\">Search</button>\n");
      out.write("        </form>\n");
      out.write("        </div>\n");
      out.write("    </nav>\n");
      out.write("\n");
      out.write("    <div class=\"pt-5\"></div>\n");
      out.write("    <div class=\"pt-3\"></div>\n");
      out.write("    <div class=\"container bg-light mx-auto\">\n");
      out.write("    <div class=\"card margin-card mx-auto\" style=\"width: 18rem;\">\n");
      out.write("            <img src=\"images/prova.jpg\" class=\"card-img-top\" >\n");
      out.write("            <div class=\"card-body\">\n");
      out.write("                <p class=\"card-text\">Some quick example text to build on the card title and make up the bulk of the card's content.</p>\n");
      out.write("            </div>\n");
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
