package com.todo.ex05_swagger_tests.controller;

import com.todo.ex05_swagger_tests.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
@Import(NoteService.class) // on importe le service réel (simple)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void postNotes_shouldReturn201_andCreatedNoteJson() throws Exception {
        String json = """
            {
              "title": "My Note",
              "content": "Hello"
            }
            """;

        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("My Note"))
                .andExpect(jsonPath("$.content").value("Hello"));
    }

    @Test
    void getNotes_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk());
    }
}