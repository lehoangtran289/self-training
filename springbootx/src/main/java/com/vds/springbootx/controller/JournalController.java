package com.vds.springbootx.controller;

import com.vds.springbootx.entity.JournalEntry;
import com.vds.springbootx.service.JournalEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class JournalController {
    private final JournalEntryService journalEntryService;

    public JournalController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @GetMapping("/journal")
    public ResponseEntity<List<JournalEntry>> getAll() {
        return ResponseEntity.ok(journalEntryService.getAllJournals());
    }

    @PostMapping("/journal")
    public ResponseEntity<?> insertJournal(@RequestBody JournalEntry entry) {
        journalEntryService.save(entry);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/journal/title/{title}")
    public ResponseEntity<List<JournalEntry>> getJournalsByTitleContains(@PathVariable String title) {
        return ResponseEntity.ok(journalEntryService.getJournalsByTitleContains(title));
    }

}

