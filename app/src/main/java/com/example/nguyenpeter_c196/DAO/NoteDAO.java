package com.example.nguyenpeter_c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nguyenpeter_c196.Entities.NoteEntity;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity noteEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNotes(List<NoteEntity> notes);

    @Update
    void updateNote(NoteEntity noteEntity);

    @Delete
    void deleteNote(NoteEntity noteEntity);

    @Query("SELECT * FROM notes WHERE noteID = :noteID")
    NoteEntity getNoteByID(int noteID);

    @Query("SELECT * FROM notes ORDER BY noteTitle DESC")
    List<NoteEntity> getAllNotes();

    @Query("SELECT * FROM notes WHERE assessmentID = :assessmentID")
    List<NoteEntity> getNoteByAssessment(int assessmentID);

    @Query("DELETE FROM notes")
    int deleteAllNotes();

    @Query("SELECT COUNT(*) FROM notes WHERE assessmentID = :assessmentID")
    int getNoteCountByAssessment(int assessmentID);

    @Query("SELECT COUNT(*) FROM notes")
    int getNoteCount();

    @Query("SELECT COUNT(*) FROM notes WHERE assessmentID IS NOT NULL")
    int getNoteCountByAnyAssessment();

}
