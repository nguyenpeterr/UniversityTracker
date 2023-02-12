package com.example.nguyenpeter_c196.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nguyenpeter_c196.Entities.TermEntity;

import java.util.List;

@Dao
public interface TermDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(TermEntity termEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTerms(List<TermEntity> terms);

    @Update
    void updateTerm(TermEntity termEntity);

    @Delete
    void deleteTerm(TermEntity termEntity);

    @Query("SELECT * FROM terms WHERE termID == :termID")
    TermEntity getTermByID(int termID);

    @Query("SELECT * FROM terms ORDER BY termStartDate ASC")
    List<TermEntity> getAllTerms();

    @Query("DELETE FROM terms")
    int deleteAllTerms();

    @Query("DELETE FROM terms WHERE termID == :termID")
    void deleteTermByID(int termID);

}
