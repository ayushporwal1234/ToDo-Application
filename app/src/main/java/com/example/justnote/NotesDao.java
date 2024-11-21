package com.example.justnote;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    public void insert(Note note);
    @Update
    public void update(Note note);
    @Delete
    public void delete(Note note);

    @Query("Select * from notesTable")
    public LiveData<List<Note>> showAll();  //LiveData is a data structure  which is similar to Linked List but faster than linked list//
}
