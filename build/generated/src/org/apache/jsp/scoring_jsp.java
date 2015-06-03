package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.example.dao.ProjectDao;

public final class scoring_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      out.write('\n');

    response.setHeader("Cache-Control", "no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);

      out.write("\n");
      out.write("\n");
      out.write("\n");

    if (session.getAttribute("username") == null) {

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Comparison</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\">\n");
      out.write("        <script src=\"js/bootstrap.js\"></script>\n");
      out.write("        <style>\n");
      out.write("            .maincontent{\n");
      out.write("            }\n");
      out.write("            .white{\n");
      out.write("                border-radius: 10px;\n");
      out.write("                display: block;\n");
      out.write("                position: absolute;\n");
      out.write("                top: 3%;\n");
      out.write("                width: 40%;\n");
      out.write("                height: 90%;\n");
      out.write("                background-color: white;\n");
      out.write("                padding:10px;\n");
      out.write("                z-index:1002;\n");
      out.write("                overflow: auto;\n");
      out.write("                opacity: .7;\n");
      out.write("            }\n");
      out.write("            .teacher{\n");
      out.write("                right: 1.5%;\n");
      out.write("            }\n");
      out.write("            .student{\n");
      out.write("                left: 1.5%;\n");
      out.write("            }\n");
      out.write("            .scoring{\n");
      out.write("                padding:3px;\n");
      out.write("                width: 12.5%;\n");
      out.write("                height: 32%;\n");
      out.write("                top: 62.9%;\n");
      out.write("                left: 43.5%;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body background = \"css/img/viewsBG.jpg\">\n");
      out.write("        <div class = \"maincontent\">\n");
      out.write("            <div class=\"white teacher\" align = \"center\">\n");
      out.write("                as\n");
      out.write("            </div>\n");
      out.write("            <div class = \"white scoring\" align = 'center'>\n");
      out.write("                <form method=\"POST\" action=\"UserController\">\n");
      out.write("                    <hr><input type = \"text\" name = \"score\" placeholder = \"Score\" style = \"width:90\"/><hr>\n");
      out.write("                    <input type = \"hidden\" name = \"action\" value = \"scoring\"/>\n");
      out.write("                    <input class = \"btn btn-sm btn-primary\" name = \"button\" type = \"submit\" value = \"Submit\"/><br><br>\n");
      out.write("                    <input class = \"btn btn-sm btn-primary\" name = \"button\" type = \"submit\" value = \"Back\"/>\n");
      out.write("                    </form>\n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"white student\" align = \"center\">\n");
      out.write("                hellol\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");

    } else {
        response.sendRedirect("index.jsp");
    }

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
