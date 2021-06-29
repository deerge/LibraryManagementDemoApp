package com.github.deerge.librarymanager.api.publications;

import com.github.deerge.librarymanager.dto.publications.JournalDto;
import com.github.deerge.librarymanager.model.publications.Journal;
import com.github.deerge.librarymanager.repository.publications.JournalsRepository;
import io.micrometer.core.lang.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
}
