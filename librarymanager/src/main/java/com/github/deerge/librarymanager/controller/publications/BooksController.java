package com.github.deerge.librarymanager.controller.publications;

import com.github.deerge.librarymanager.api.publications.BooksApi;
import com.github.deerge.librarymanager.dto.publications.BookDto;
import com.github.deerge.librarymanager.dto.publications.BookSearchInput;
import io.micrometer.core.lang.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/books")
public class BooksController {
    private final BooksApi booksApi;

    public BooksController(@NonNull BooksApi booksApi) {
        this.booksApi = booksApi;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(booksApi.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@Validated @RequestBody BookDto bookDto) {
        return ResponseEntity.ok(booksApi.createBook(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> removeBook(@PathVariable long id) {
        booksApi.removeBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> searchBooks(@NonNull BookSearchInput searchInput) {
        var books = booksApi.searchBooks(searchInput);

        return ResponseEntity.ok(books);
    }
}
