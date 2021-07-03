package com.example.springmvc.config;

import com.example.springmvc.filter.LoginFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR);

        FilterRegistration.Dynamic loginFilter = servletContext.addFilter("loginFilter", LoginFilter.class);
        loginFilter.addMappingForUrlPatterns(dispatcherTypes, false, "/home");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("AppInitializer: getRootConfigClasses");
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("AppInitializer: getServletConfigClasses");
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("AppInitializer: getServletMappings");
        return new String[]{"/"};
    }

//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{new LoginFilter()};
//    }
}
