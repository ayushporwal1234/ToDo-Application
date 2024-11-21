package com.example.justnote;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepo noteRepo;
    private LiveData<List<Note>> noteList;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepo = new NoteRepo(application);
        noteList = noteRepo.showAll();
    }

    public void insert(Note note)
    {
        noteRepo.insert(note);
    }
    public void delete(Note note)
    {
        noteRepo.delete(note);
    }
    public void update(Note note)
    {
        noteRepo.update(note);
    }

    public LiveData<List<Note>> getAllNotes()
    {
        return noteList;
    }
}
