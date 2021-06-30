import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "SessionServlet", value = "/session-test")
public class SessionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Integer accessCount = 0;
        synchronized (session) {
            accessCount = (Integer) session.getAttribute("accessCount");
            accessCount = accessCount == null ? 0 : accessCount + 1;
            session.setAttribute("accessCount", accessCount);
        }

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>Session Test</title></head><body>");
            out.println("<h2>Access count = " + accessCount + "</h2>");
            out.println("<p>(Session ID is " + session.getId() + ")</p>");

            out.println("<p>(Session creation time is " + new Date(session.getCreationTime()) + ")</p>");
            out.println("<p>(Session last access time is " + new Date(session.getLastAccessedTime()) + ")</p>");
            out.println("<p>(Session max inactive interval  is " + session.getMaxInactiveInterval() + " seconds)</p>");

            out.println("<p><a  href='" + request.getRequestURI() + "'>Refresh</a>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }
}
