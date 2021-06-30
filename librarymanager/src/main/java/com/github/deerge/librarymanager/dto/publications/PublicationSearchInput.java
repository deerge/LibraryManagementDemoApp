package com.github.deerge.librarymanager.dto.publications;

import java.util.Date;

public class PublicationSearchInput {
    private String title;
    private String textLanguage;
    private Date issueDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextLanguage() {
        return textLanguage;
    }

    public void setTextLanguage(String textLanguage) {
        this.textLanguage = textLanguage;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
