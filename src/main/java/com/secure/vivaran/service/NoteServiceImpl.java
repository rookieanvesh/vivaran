package com.secure.vivaran.service;

import com.secure.vivaran.models.Note;
import com.secure.vivaran.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        return noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Note note = noteRepository.findById(noteId).orElseThrow(()
                -> new RuntimeException("Note not found"));
        note.setContent(content);
        //save the updated note into the db and then return the same
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        noteRepository.deleteById(noteId);
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        //List<Note> personalNotes = noteRepository
        //                .findByOwnerUsername(username);
        return noteRepository
                .findByOwnerUsername(username);
    }
}
