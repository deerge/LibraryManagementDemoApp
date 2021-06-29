package com.github.deerge.librarymanager.repository.publications;

import com.github.deerge.librarymanager.model.publications.Book;
import org.springframework.stereotype.Repository;


@Repository
public interface BooksRepository extends PublicationRepository<Book> {
}
