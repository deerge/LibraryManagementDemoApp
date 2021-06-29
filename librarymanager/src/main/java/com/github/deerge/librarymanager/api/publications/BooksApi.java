package com.github.deerge.librarymanager.api.publications;

import com.github.deerge.librarymanager.dto.publications.AuthorDto;
import com.github.deerge.librarymanager.dto.publications.BookDto;
import com.github.deerge.librarymanager.model.publications.Book;
import com.github.deerge.librarymanager.repository.publications.BooksRepository;
import io.micrometer.core.lang.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BooksApi {
    private final BooksRepository booksRepository;
    private final ModelMapper modelMapper;

    public BooksApi(@NonNull BooksRepository booksRepository,
                    @NonNull ModelMapper modelMapper) {
        this.booksRepository = booksRepository;
        this.modelMapper = modelMapper;
    }

//    @PreAuthorize("isAuthenticated()")
    public BookDto getBookById(long id) {
        var book = booksRepository.getById(id);
        var authorDto = modelMapper.map(book.getAuthor(), AuthorDto.class);
        var bookDto = modelMapper.map(book, BookDto.class);
        if (bookDto.getAuthorDto() == null) {
            bookDto.setAuthorDto(authorDto);
        }

        return bookDto;
    }

    public BookDto createBook(BookDto bookDto) {
        var book = modelMapper.map(bookDto, Book.class);
        book = booksRepository.save(book);
        return modelMapper.map(booksRepository.getById(book.getId()), BookDto.class);
    }

    public void removeBook(long id) {
        booksRepository.deleteById(id);
    }
}
