package com.github.deerge.librarymanager.repository.publications;

import com.github.deerge.librarymanager.model.publications.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends CrudRepository<Author, Long> {

    @Query("from Author a where a.firstName = :firstName and a.lastName = :lastName " +
            "and a.middleName = :middleName")
    Optional<Author> findAuthorByFirstNameAndLastNameAndMiddleName(@Param("firstName") String firstName,
                                                                   @Param("lastName") String lastName,
                                                                   @Param("middleName") String middleName);
}
