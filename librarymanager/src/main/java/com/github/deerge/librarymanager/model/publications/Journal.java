package com.github.deerge.librarymanager.model.publications;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("JOURNAL")
public class Journal extends Publication {
    @Column(name = "publication_number", length = 100)
    private String publicationNumber;

    public String getPublicationNumber() {
        return publicationNumber;
    }

    public void setPublicationNumber(String publicationNumber) {
        this.publicationNumber = publicationNumber;
    }
}
