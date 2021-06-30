package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebFilter(filterName = "filter2")
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Filter2 init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("#FILTER 2: " + new Date() + " - ServletPath :" +
                ((HttpServletRequest) request).getServletPath() + ", URL =" +
                ((HttpServletRequest) request).getRequestURL());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Filter2 destroy");
    }
}
