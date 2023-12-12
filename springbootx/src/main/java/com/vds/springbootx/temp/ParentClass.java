package com.vds.springbootx.temp;

import com.vds.springbootx.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value="parent", havingValue = "true")
public class ParentClass {
    @Autowired
    protected JournalEntryRepository journalEntryRepository;

    protected void save() {
        System.out.println("ParentClass save");
    }
}
