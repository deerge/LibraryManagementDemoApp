package com.github.deerge.librarymanager.api.publications;

import com.github.deerge.librarymanager.dto.publications.JournalDto;
import com.github.deerge.librarymanager.dto.publications.JournalSearchInput;
import com.github.deerge.librarymanager.model.publications.Journal;
import com.github.deerge.librarymanager.repository.publications.JournalsRepository;
import io.micrometer.core.lang.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class JournalApi {

    private final JournalsRepository journalsRepository;
    private final ModelMapper modelMapper;

    public JournalApi(@NonNull JournalsRepository journalsRepository, @NonNull ModelMapper modelMapper) {
        this.journalsRepository = journalsRepository;
        this.modelMapper = modelMapper;
    }

    public JournalDto getJournalById(long id) {
        return modelMapper.map(journalsRepository.getById(id), JournalDto.class);
    }

    public JournalDto createJournal(JournalDto journalDto) {
        var journal = modelMapper.map(journalDto, Journal.class);
        journal = journalsRepository.save(journal);
        return modelMapper.map(journalsRepository.getById(journal.getId()), JournalDto.class);
    }

    public void removeJournal(long id) {
        journalsRepository.deleteById(id);
    }

    public List<JournalDto> searchJournals(JournalSearchInput searchInput) {
        var exampleJournal = new Journal();
        exampleJournal.setIssueDate(searchInput.getIssueDate());
        exampleJournal.setTitle(searchInput.getTitle());
        exampleJournal.setTextLanguage(searchInput.getTextLanguage());
        exampleJournal.setPublicationNumber(searchInput.getPublicationNumber());

        var journalDtos = journalsRepository.search(exampleJournal)
                .stream()
                .map(j -> modelMapper.map(j, JournalDto.class))
                .collect(Collectors.toList());
        return journalDtos;
    }
}
