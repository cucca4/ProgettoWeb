package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
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
      out.write("            </ul>  \n");
      out.write("            <form class=\"form-inline mt-2 mt-md-0\" name=\"login\" action=\"Dispatcher\" method=\"post\"> \n");
      out.write("            <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Username\" aria-label=\"Username\" name=\"username\" id=\"username\">\n");
      out.write("            <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"Password\" aria-label=\"Password\" name=\"password\" id=\"password\">\n");
      out.write("            <input class=\"btn btn-outline-success my-2 my-sm-0 mr-sm-2\" type=\"submit\">\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManagement.logon\">\n");
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
      out.write("                </div>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"media my-4\">\n");
      out.write("            <img src=\"images/omino-ok.jpg\" class=\"mr-3\" width=\"120\" height=\"120\">\n");
      out.write("                <div class=\"media-body\">\n");
      out.write("                    <h5 class=\"mt-0 mb-1\">PROFILO</h5>\n");
      out.write("                    Controlla le informazioni sul tuo profilo o modifica l'indirizzo di consegna.\n");
      out.write("                </div>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"media\">\n");
      out.write("            <img src=\"images/cartedicredito.jpg\" class=\"mr-3\" width=\"120\" height=\"120\" >\n");
      out.write("                <div class=\"media-body\">\n");
      out.write("                    <h5 class=\"mt-0 mb-1\">OPZIONI PAGAMENTO</h5>\n");
      out.write("                    Modifica o aggiunti metodi di pagamento.\n");
      out.write("                </div>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
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
