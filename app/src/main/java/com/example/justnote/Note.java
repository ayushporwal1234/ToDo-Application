package com.example.justnote;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notesTable") // here we create a table in the database
public class Note {   // here we create a note class

    private String tittle;
    private String disp;


    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Note(String tittle, String disp) {
        this.tittle = tittle;
        this.disp = disp;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }
}