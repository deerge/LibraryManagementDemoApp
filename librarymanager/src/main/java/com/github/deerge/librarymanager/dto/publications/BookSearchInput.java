package com.github.deerge.librarymanager.dto.publications;

public class BookSearchInput extends PublicationSearchInput {
    private String authorFirstName;
    private String authorLastName;
    private String authorMiddleName;

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorMiddleName() {
        return authorMiddleName;
    }

    public void setAuthorMiddleName(String authorMiddleName) {
        this.authorMiddleName = authorMiddleName;
    }
}
