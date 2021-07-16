package com.vds.springbootx.repository;

import com.vds.springbootx.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Integer> {
    List<JournalEntry> findAllByTitleContaining(String title);
}
