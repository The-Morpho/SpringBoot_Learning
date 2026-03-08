package com.todo.ex03inmemorycrud.service;

import com.todo.ex03inmemorycrud.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final List<Note> notes = new ArrayList<>();
    private long nextId = 1;

    public Note create(Note note) {
        note.setId(nextId++);
        notes.add(note);
        return note;
    }

    public List<Note> findAll() {
        return notes;
    }

    public Optional<Note> findById(Long id) {
        return notes.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst();
    }

    public Optional<Note> update(Long id, Note newData) {
        Optional<Note> existingOpt = findById(id);
        if (existingOpt.isEmpty()) return Optional.empty();

        Note existing = existingOpt.get();
        existing.setTitle(newData.getTitle());
        existing.setContent(newData.getContent());
        return Optional.of(existing);
    }

    public boolean delete(Long id) {
        return notes.removeIf(n -> n.getId().equals(id));
    }
}