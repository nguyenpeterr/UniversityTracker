package com.example.nguyenpeter_c196.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes", foreignKeys = @ForeignKey(entity = AssessmentEntity.class, parentColumns = "assessmentID", childColumns = "assessmentID", onDelete = ForeignKey.CASCADE))
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    private int noteID;
    private String noteTitle;
    private String noteText;
    private int assessmentID;

    public NoteEntity(String noteTitle, String noteText, int assessmentID) {
        this.noteTitle = noteTitle;
        this.noteText = noteText;
        this.assessmentID = assessmentID;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }
}
