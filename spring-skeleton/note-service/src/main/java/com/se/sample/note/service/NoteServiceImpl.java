package com.se.sample.note.service;

import com.se.sample.note.exception.ResourceNotFoundException;
import com.se.sample.note.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    List<Note> notesList = new ArrayList<>();

     @Override
    public void delete(Long noteId) {
        notesList.stream()
                .filter(i -> i.getId()== noteId)
                .forEach(i -> notesList.remove(i));
    }

    @Override
    public Note updateNote(Long noteId, Note note) {
        notesList.add(note);
        return null;
    }

    @Override
    public Note getById(Long noteId) {
          return notesList.stream().filter(i -> i.getId() == noteId).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @Override
    public List<Note> findAll() {
        return notesList;
    }

    @Override
    public Note add(Note note) {


        notesList.add(note);
        return  note;

//        notesList.stream().filter(i -> i.getId() == note.getId())
//                .map(i -> {
//                    i.setTitle(note.getTitle());
//                    i.setContent(note.getContent());
//                    return note;
//
//                }).findFirst()
//                .orElseGet(
//                            notesList.add(note);
//                            return note;
//                        }
//                );

    }
}
