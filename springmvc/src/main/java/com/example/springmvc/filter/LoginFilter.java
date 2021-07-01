package com.example.springmvc.filter;

import com.example.springmvc.entity.User;
import com.example.springmvc.service.UserService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init filter");
        userService = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext()).getBean(UserService.class);
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userService.list().contains(new User(username, password))) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getServletContext().getContextPath() + "/");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
