package com.vds.springbootx.temp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value="sub", havingValue = "true")
public class SubClass extends ParentClass {
    protected void save() {
        System.out.println("SubClass save");
    }
}
