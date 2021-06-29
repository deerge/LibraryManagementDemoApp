package com.github.deerge.librarymanager.api.publications;

import com.github.deerge.librarymanager.dto.publications.BookDto;
import com.github.deerge.librarymanager.model.publications.Author;
import com.github.deerge.librarymanager.model.publications.Book;
import com.github.deerge.librarymanager.repository.publications.AuthorsRepository;
import com.github.deerge.librarymanager.repository.publications.BooksRepository;
import io.micrometer.core.lang.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@Transactional
public class BooksApi {
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;
    private final ModelMapper modelMapper;

    public BooksApi(@NonNull BooksRepository booksRepository,
                    @NonNull AuthorsRepository authorsRepository,
                    @NonNull ModelMapper modelMapper) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.modelMapper = modelMapper;
    }

    public BookDto getBookById(long id) {
        var book = booksRepository.getById(id);

        return modelMapper.map(book, BookDto.class);
    }

    public BookDto createBook(BookDto bookDto) {
        var authorDto = bookDto.getAuthor();
        var maybeAuthor = authorsRepository
                .findAuthorByFirstNameAndLastNameAndMiddleName(
                        authorDto.getFirstName(),
                        authorDto.getLastName(),
                        authorDto.getMiddleName()
                );
        var author = maybeAuthor
                .or(() -> Optional.of(authorsRepository.save(modelMapper.map(authorDto, Author.class))))
                .orElseThrow(() -> new EntityNotFoundException("Could not get or create author"));
        var book = modelMapper.map(bookDto, Book.class);
        book.setAuthor(author);
        book = booksRepository.save(book);
        return modelMapper.map(booksRepository.getById(book.getId()), BookDto.class);
    }

    public void removeBook(long id) {
        booksRepository.deleteById(id);
    }
}
