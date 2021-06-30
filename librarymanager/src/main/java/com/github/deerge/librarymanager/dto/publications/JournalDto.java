package com.github.deerge.librarymanager.dto.publications;

import javax.validation.constraints.NotBlank;

public class JournalDto extends PublicationDto {
    @NotBlank
    private String publicationNumber;

    public String getPublicationNumber() {
        return publicationNumber;
    }

    public void setPublicationNumber(String publicationNumber) {
        this.publicationNumber = publicationNumber;
    }
}
