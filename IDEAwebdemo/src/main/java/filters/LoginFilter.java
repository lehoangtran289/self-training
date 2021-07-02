package filters;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "loginFilter", urlPatterns = "/home")
public class LoginFilter implements Filter {
    private static final Map<String, String> accounts;

    static {
        accounts = new HashMap<>();
        accounts.put("admin", "admin");
        accounts.put("user", "user");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("login filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do login filter");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (accounts.containsKey(username) && StringUtils.equals(accounts.get(username), password)) {
            chain.doFilter(request, response);
            System.out.println();
            System.out.println("after LOGINFILTER response");
        } else {
            ((HttpServletRequest)request).getSession().setAttribute("error", "Login fail");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("login filter destroy");
    }
}
