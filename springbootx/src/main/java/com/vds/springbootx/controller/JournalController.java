package com.vds.springbootx.controller;

import com.vds.springbootx.entity.JournalEntry;
import com.vds.springbootx.service.JournalEntryService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public ResponseEntity<List<JournalEntry>> getAll() {
        return ResponseEntity.ok(journalEntryService.getAllJournals());
    }

    @GetMapping("/test")
    @Transactional
    public ResponseEntity<Object> objectParamTest(
            ParamObject obj,
            ParamObject2 obj2
    ) {
        System.out.println(obj);
        System.out.println(obj2);
        return ResponseEntity.ok(null);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class ParamObject {
        private String code;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class ParamObject2 {
        private String code;
        private String name2;
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

