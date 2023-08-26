package org.example.review.engine.dao.models;

public class ReviewMetadata {
    private String text_content;
    private String file_link;

    public String getText_content() {
        return text_content;
    }

    public String getFile_link() {
        return file_link;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public void setFile_link(String file_link) {
        this.file_link = file_link;
    }
}
