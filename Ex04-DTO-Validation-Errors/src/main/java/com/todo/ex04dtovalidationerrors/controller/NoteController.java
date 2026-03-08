package com.todo.ex04dtovalidationerrors.controller;

import com.todo.ex04dtovalidationerrors.dto.NoteCreateRequest;
import com.todo.ex04dtovalidationerrors.dto.NoteUpdateRequest;
import com.todo.ex04dtovalidationerrors.model.Note;
import com.todo.ex04dtovalidationerrors.service.NoteService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<Note> create(@Valid @RequestBody NoteCreateRequest req) {
        Note note = new Note(null, req.getTitle(), req.getContent());
        Note created = noteService.create(note);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public List<Note> findAll() {
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable Long id) {
        return noteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @Valid @RequestBody NoteUpdateRequest req) {
        Note newData = new Note(null, req.getTitle(), req.getContent());

        return noteService.update(id, newData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = noteService.delete(id);
        if (deleted) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
