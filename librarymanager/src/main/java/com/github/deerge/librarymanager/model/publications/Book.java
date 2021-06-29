package com.github.deerge.librarymanager.model.publications;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BOOK")
public class Book extends Publication {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "pub_authors_publications",
            joinColumns = @JoinColumn(name = "publication_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
