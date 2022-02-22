package com.se.sample.note.service;

import com.se.sample.note.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    void delete(Long note);

    Note updateNote(Long noteId, Note note);

    Note getById(Long noteId);

    List<Note> findAll();

    Note add(Note note);
}
