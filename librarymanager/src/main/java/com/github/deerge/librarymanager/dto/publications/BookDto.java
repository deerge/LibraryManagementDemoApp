package com.github.deerge.librarymanager.dto.publications;


import javax.validation.constraints.NotNull;

public class BookDto extends PublicationDto {
    @NotNull
    private AuthorDto author;

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
}
