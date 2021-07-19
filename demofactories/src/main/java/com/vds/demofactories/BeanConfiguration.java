package com.vds.demofactories;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public FactoryBean factoryBean() {
        return new FactoryBean();
    }
}
