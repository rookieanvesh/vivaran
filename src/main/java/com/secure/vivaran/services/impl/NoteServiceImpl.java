package com.secure.vivaran.services.impl;

import com.secure.vivaran.models.Note;
import com.secure.vivaran.repositories.NoteRepository;
import com.secure.vivaran.services.AuditLogService;
import com.secure.vivaran.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    private AuditLogService auditLogService;

    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        auditLogService.logNoteCreation(username,note);
        return noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Note note = noteRepository.findById(noteId).orElseThrow(()
                -> new RuntimeException("Note not found"));
        note.setContent(content);
        //save the updated note into the db and then return the same
        auditLogService.logNoteUpdate(username,note);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Note note = noteRepository.findById(noteId).orElseThrow(
                () -> new RuntimeException("Record not found")
        );
        auditLogService.logNoteDeletion(username,noteId);
        noteRepository.delete(note);
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        List<Note> personalNotes = noteRepository
                     .findByOwnerUsername(username);
        return noteRepository
                .findByOwnerUsername(username);
    }
}
