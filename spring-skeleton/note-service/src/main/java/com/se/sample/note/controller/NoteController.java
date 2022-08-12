package com.se.sample.note.controller;


import com.se.sample.note.model.Note;
import com.se.sample.note.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteService boteService;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        log.info("get all notes");
        return boteService.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        log.info("get all notes");
        return boteService.add(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        log.info("get note by id:{}", noteId);
        return boteService.getById(noteId);
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Note noteDetails) {
        log.info("update note by id:{}", noteId);
        Note updatedNote = boteService.updateNote(noteId, noteDetails);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        log.info("delete note by id:{}", noteId);
        boteService.delete(noteId);

        return ResponseEntity.ok().build();
    }
}
