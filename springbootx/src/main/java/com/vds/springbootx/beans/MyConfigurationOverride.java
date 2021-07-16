package com.vds.springbootx.beans;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class MyConfigurationOverride {
    @Bean
    public MyBean2 myBean2() {
        MyBean2 b = new MyBean2();
        b.setHello("second attempt");
        return b;
    }

    @Bean
    @ConditionalOnProperty("multiple.beans")
    public MyBean2 myBeanX() {
        MyBean2 b = new MyBean2();
        b.setHello("second attempt");
        return b;
    }
}
