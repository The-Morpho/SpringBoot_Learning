package com.todo.ex04dtovalidationerrors.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteCreateRequest {

    @NotBlank(message = "title is required")
    @Size(min = 3, message = "title must be at least 3 characters")
    private String title;

    @NotBlank(message = "content is required")
    private String content;

    public NoteCreateRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}