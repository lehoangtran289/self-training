package com.vds.springbootx.beans;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyBean2 {
    private String hello = "first attempt";

    public String hello() {
        return "henlo Bean2";
    }
}
