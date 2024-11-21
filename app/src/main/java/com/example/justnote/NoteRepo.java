package com.example.justnote;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepo {

    private NotesDao notesDao;
    private LiveData<List<Note>> noteList;

    public NoteRepo(Application application) {
        //application is a sub class of context//
        // Initialize all data member here
        NoteDatabase database = NoteDatabase.getInstance(application);
        notesDao = database.notesDao();
        noteList = notesDao.showAll();
    }

    public void insert(Note note) {new InsertTask(notesDao).execute(note);}

    public void update(Note note) {
        new UpdateTask(notesDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteTask(notesDao).execute(note);
    }

    public LiveData<List<Note>> showAll() {
        return noteList;
    }

    private static class InsertTask extends AsyncTask<Note, Void, Void> {
        private NotesDao notesDao;

        public InsertTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDao.insert(notes[0]);
            return null;
        }
    }
        private static class DeleteTask extends AsyncTask<Note, Void, Void> {
            private NotesDao notesDao;

            public DeleteTask(NotesDao notesDao) {
                this.notesDao = notesDao;
            }

            @Override
            protected Void doInBackground(Note... notes) {
                notesDao.delete(notes[0]);
                return null;
            }
        }
            private static class UpdateTask extends AsyncTask<Note, Void, Void> {
                private NotesDao notesDao;

                public UpdateTask(NotesDao notesDao) {
                    this.notesDao = notesDao;
                }

                @Override
                protected Void doInBackground(Note... notes) {
                    notesDao.update(notes[0]);
                    return null;
                }
            }
        }