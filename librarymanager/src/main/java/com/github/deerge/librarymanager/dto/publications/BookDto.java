package com.github.deerge.librarymanager.dto.publications;


import org.springframework.lang.NonNull;

public class BookDto extends PublicationDto {
    @NonNull
    private AuthorDto authorDto;

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }
}
