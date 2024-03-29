package com.vds.springbootx.temp;

import com.vds.springbootx.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@ConditionalOnProperty(value="parent", havingValue = "true")
public class ParentClass {
    @Autowired
    protected JournalEntryRepository journalEntryRepository;

    @PostConstruct
    public void init() {
        System.out.println("ParentClass init");
        save();
    }

    protected void save() {
        System.out.println(journalEntryRepository.findAll());
        System.out.println("ParentClass save");
    }
}
