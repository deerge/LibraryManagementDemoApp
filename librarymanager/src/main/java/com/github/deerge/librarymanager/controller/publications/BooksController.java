package com.github.deerge.librarymanager.controller.publications;

import com.github.deerge.librarymanager.api.publications.BooksApi;
import com.github.deerge.librarymanager.dto.publications.BookDto;
import io.micrometer.core.lang.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/book")
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
    public ResponseEntity<BookDto> createBook(@Validated BookDto bookDto) {
        return ResponseEntity.ok(booksApi.createBook(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> removeBook(@PathVariable long id) {
        booksApi.removeBook(id);
        return ResponseEntity.ok().build();
    }
}
