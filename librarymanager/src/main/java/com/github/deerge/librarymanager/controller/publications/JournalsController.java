package com.github.deerge.librarymanager.controller.publications;

import com.github.deerge.librarymanager.api.publications.JournalApi;
import com.github.deerge.librarymanager.dto.publications.JournalDto;
import com.github.deerge.librarymanager.dto.publications.JournalSearchInput;
import io.micrometer.core.lang.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/journals")
public class JournalsController {
    private final JournalApi journalApi;

    public JournalsController(@NonNull JournalApi journalApi) {
        this.journalApi = journalApi;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(journalApi.getJournalById(id));
    }

    @PostMapping
    public ResponseEntity<JournalDto> createJournal(@Validated @RequestBody JournalDto journalDto) {
        return ResponseEntity.ok(journalApi.createJournal(journalDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> removeJournal(@PathVariable long id) {
        journalApi.removeJournal(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<JournalDto>> searchJournals(JournalSearchInput searchInput) {
        return ResponseEntity.ok(journalApi.searchJournals(searchInput));
    }
}
