package com.example.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc // customize the imported configuration from WebMvcConfigurerSupport
@ComponentScan(basePackages = {"com.example.springmvc"})
public class WebMvcConfig implements WebMvcConfigurer { // adding to the default Spring MVC configuration through the use of @EnableWebMvc.

    public WebMvcConfig() {
        System.out.println("Construct webmvc");
    }

    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
