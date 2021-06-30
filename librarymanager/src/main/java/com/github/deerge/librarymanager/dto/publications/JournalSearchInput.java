package com.github.deerge.librarymanager.dto.publications;

public class JournalSearchInput extends PublicationSearchInput {
    private String publicationNumber;

    public String getPublicationNumber() {
        return publicationNumber;
    }

    public void setPublicationNumber(String publicationNumber) {
        this.publicationNumber = publicationNumber;
    }
}
