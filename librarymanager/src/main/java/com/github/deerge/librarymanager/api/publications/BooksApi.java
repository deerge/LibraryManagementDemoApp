package com.github.deerge.librarymanager.api.publications;

import com.github.deerge.librarymanager.dto.publications.BookDto;
import com.github.deerge.librarymanager.dto.publications.BookSearchInput;
import com.github.deerge.librarymanager.model.publications.Author;
import com.github.deerge.librarymanager.model.publications.Book;
import com.github.deerge.librarymanager.repository.publications.AuthorsRepository;
import com.github.deerge.librarymanager.repository.publications.BooksRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<BookDto> searchBooks(BookSearchInput searchInput) {
        Author author = null;
        if (StringUtils.isNotBlank(searchInput.getAuthorLastName())
                || StringUtils.isNotBlank(searchInput.getAuthorFirstName())
                || StringUtils.isNotBlank(searchInput.getAuthorMiddleName())) {
            author = new Author();
            author.setFirstName(searchInput.getAuthorFirstName());
            author.setLastName(searchInput.getAuthorLastName());
            author.setMiddleName(searchInput.getAuthorMiddleName());
        }

        var exampleBook = new Book();
        exampleBook.setIssueDate(searchInput.getIssueDate());
        exampleBook.setTitle(searchInput.getTitle());
        exampleBook.setTextLanguage(searchInput.getTextLanguage());
        exampleBook.setAuthor(author);

        var books = booksRepository.search(exampleBook);
        var result = books.stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .collect(Collectors.toList());

        return result;
    }
}
