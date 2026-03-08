package com.todo.ex05_swagger_tests.service;

import com.todo.ex05_swagger_tests.model.Note;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {

    @Test
    void create_shouldAssignId_andStoreNote() {
        NoteService service = new NoteService();

        Note created = service.create(new Note(null, "Title", "Content"));

        assertNotNull(created.getId());
        assertEquals(1L, created.getId());
        assertEquals(1, service.findAll().size());
    }

    @Test
    void findById_shouldReturnEmpty_whenNotFound() {
        NoteService service = new NoteService();

        assertTrue(service.findById(999L).isEmpty());
    }
}