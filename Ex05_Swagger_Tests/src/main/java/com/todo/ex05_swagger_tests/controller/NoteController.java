package com.todo.ex05_swagger_tests.controller;

import com.todo.ex05_swagger_tests.model.Note;
import com.todo.ex05_swagger_tests.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // POST /api/notes
    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note note) {
        Note created = noteService.create(note);
        return ResponseEntity.status(201).body(created);
    }

    // GET /api/notes
    @GetMapping
    public List<Note> findAll() {
        return noteService.findAll();
    }

    // GET /api/notes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable Long id) {
        return noteService.findById(id)
                .map(ResponseEntity::ok)          // 200
                .orElse(ResponseEntity.notFound().build()); // 404
    }

    // PUT /api/notes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note note) {
        return noteService.update(id, note)
                .map(ResponseEntity::ok) // 200
                .orElse(ResponseEntity.notFound().build()); // 404
    }

    // DELETE /api/notes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = noteService.delete(id);
        if (deleted) return ResponseEntity.noContent().build(); // 204
        return ResponseEntity.notFound().build(); // 404
    }
}