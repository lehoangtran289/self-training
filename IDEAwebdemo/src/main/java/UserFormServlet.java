import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "UserFormServlet", value = "/user")
public class UserFormServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

       // Write the response message, in an HTML page
       try (PrintWriter out = response.getWriter()) {
          out.println("<!DOCTYPE html>");
          out.println("<html><head>");
          out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
          out.println("<title>Echo Servlet</title></head>");
          out.println("<body><h2>You have enter</h2>");

          String username = request.getParameter("username");
          if (StringUtils.isEmpty(username)) {
             out.println("<p>Name: MISSING</p>");
          } else {
             out.println("<p>Name: " + username + "</p>");
          }

          String gender = request.getParameter("gender");
          if (gender == null) {
             out.println("<p>Gender: MISSING</p>");
          } else if (gender.equals("m")) {
             out.println("<p>Gender: male</p>");
          } else {
             out.println("<p>Gender: female</p>");
          }

          // Retrieve the value of the query parameter "age" (from pull-down menu)
          String age = request.getParameter("age");
          if (age == null) {
             out.println("<p>Age: MISSING</p>");
          } else if (age.equals("1")) {
             out.println("<p>Age: &lt; 1 year old</p>");
          } else if (age.equals("99")) {
             out.println("<p>Age: 1 to 99 years old</p>");
          } else {
             out.println("<p>Age: &gt; 99 years old</p>");
          }

          String[] languages = request.getParameterValues("language");
          if (languages == null || languages.length == 0) {
             out.println("<p>Languages: NONE</p>");
          } else {
             out.println("<p>Languages: ");
             for (String language : languages) {
                if (language.equals("english")) {
                   out.println("English");
                } else if (language.equals("vnese")) {
                   out.println("Vietnamese");
                }
             }
             out.println("</p>");
          }

          // Retrieve the value of the query parameter "instruction" (from text area)
          String description = request.getParameter("description");
          // Get null if the parameter is missing from query string.
          if (description == null
                  || (description = htmlFilter(description.trim())).length() == 0
                  || description.equals("Enter your description here...")) {
             out.println("<p>Instruction: NONE</p>");
          } else {
             out.println("<p>Instruction: " + description + "</p>");
          }

          // Retrieve the value of the query parameter "secret" (from hidden field)
          String secret = request.getParameter("secret");
          out.println("<p>Secret: " + secret + "</p>");

          // Get all the names of request parameters
          Enumeration<String> names = request.getParameterNames();
          out.println("<p>Request Parameter Names are: ");
          if (names.hasMoreElements()) {
             out.print(htmlFilter(names.nextElement().toString()));
          }
          do {
             out.print(", " + htmlFilter(names.nextElement().toString()));
          } while (names.hasMoreElements());
          out.println(".</p>");

          out.println("<a href='form_input.html'>BACK</a>");
          out.println("</body></html>");
       }
    }

    // Redirect POST request to GET request.
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    private static String htmlFilter(String message) {
        if (message == null) return null;
        int len = message.length();
        StringBuilder result = new StringBuilder(len + 20);
        char aChar;

        for (int i = 0; i < len; ++i) {
            aChar = message.charAt(i);
            switch (aChar) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(aChar);
            }
        }
        return (result.toString());
    }
}
