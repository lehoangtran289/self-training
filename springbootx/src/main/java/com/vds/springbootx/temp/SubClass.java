package com.vds.springbootx.temp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@ConditionalOnProperty(value="sub", havingValue = "true")
public class SubClass extends ParentClass {

    @PostConstruct
    public void init() {
        System.out.println("SubClass init");
        save();
    }

    protected void save() {
        System.out.println(journalEntryRepository.findAll());
        System.out.println("SubClass save");
    }
}
