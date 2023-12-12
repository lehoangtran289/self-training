package com.vds.springbootx.service;

import com.vds.demofactories.FactoryBean;
import com.vds.springbootx.entity.JournalEntry;
import com.vds.springbootx.repository.JournalEntryRepository;
import com.vds.springbootx.utils.CustomLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class JournalEntryService {
    private final FactoryBean factoryBean;
    private final JournalEntryRepository journalEntryRepository;
    private final String message;
    private final CustomLogger log;

    public JournalEntryService(FactoryBean factoryBean, JournalEntryRepository journalEntryRepository,
                               @Value("${message}") String message, CustomLogger log) {
        this.factoryBean = factoryBean;
        this.journalEntryRepository = journalEntryRepository;
        this.message = message;
        this.log = log;
        initData();
        log.info(message);
        log.info(factoryBean.getMessage());
    }

    private void initData() {
        try {
            this.save(
                    new JournalEntry("title1", "summary1", "1/1/2011"),
                    new JournalEntry("title2", "summary2", "2/2/2011"),
                    new JournalEntry("title3", "summary3", "3/3/2011"),
                    new JournalEntry("title4", "summary4", "4/4/2011")
            );
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void save(JournalEntry... journalEntries) {
        for (JournalEntry journalEntry : journalEntries) {
            journalEntryRepository.save(journalEntry);
        }
    }

    public List<JournalEntry> getAllJournals() {
        return journalEntryRepository.findAll();
    }

    public List<JournalEntry> getJournalsByTitleContains(String title) {
        return journalEntryRepository.findAllByTitleContaining(title);
    }
}
