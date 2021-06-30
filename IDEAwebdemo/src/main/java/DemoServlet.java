import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "DemoServlet",
        value = "/demo-servlet",
        initParams = {
                @WebInitParam(name = "initParam1", value = "value 1"),
                @WebInitParam(name = "initParam2", value = "value 2")
        }
)
public class DemoServlet extends HttpServlet {
    private Map<String, String> initParams = new HashMap<>();
    private String dbDriver;
    private String dbUrl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        Enumeration<String> e = config.getInitParameterNames();
        while (e.hasMoreElements()) {
            String initParamName = e.nextElement();
            String initParamValue = config.getInitParameter(initParamName);
            this.initParams.put(initParamName, initParamValue);
        }
        this.dbDriver = config.getServletContext().getInitParameter("jdbcDriver");
        this.dbUrl = config.getServletContext().getInitParameter("databaseUrl");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>MyServlet.java:doGet(): Servlet code!</title>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1>This is a simple java servlet.</h1>");
            writer.println("@WebInitParam</br>");
            initParams.forEach((k, v) -> {
                writer.println("<p>");
                writer.println(k + " - " + v);
                writer.println("</p>");
            });
            writer.println("</br>Web Context Param</br>");
            writer.println("<p>");
                writer.println(dbDriver + " -> " + dbUrl);
                writer.println("</p>");
            writer.println();

            writer.println("</body>");
            writer.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
