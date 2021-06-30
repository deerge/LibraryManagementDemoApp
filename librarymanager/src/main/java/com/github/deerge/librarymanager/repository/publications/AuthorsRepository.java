package com.github.deerge.librarymanager.repository.publications;

import com.github.deerge.librarymanager.model.publications.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends CrudRepository<Author, Long> {

    Optional<Author> findAuthorByFirstNameAndLastNameAndMiddleName(String firstName,
                                                                   String lastName,
                                                                   String middleName);
}
