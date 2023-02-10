package com.example.nguyenpeter_c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nguyenpeter_c196.Entities.AssessmentEntity;

import java.util.List;

@Dao
public interface AssessmentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssessment(AssessmentEntity assessmentEntity);


    @Update
    void updateAssessment(AssessmentEntity assessmentEntity);

    @Delete
    void deleteAssessment(AssessmentEntity assessmentEntity);

    @Query("DELETE FROM assessments WHERE assessmentId == :assessmentID")
    void deleteAssessmentByID(int assessmentID);

    @Query("SELECT * FROM assessments WHERE assessmentID = :assessmentID")
    AssessmentEntity getAssessmentByID(int assessmentID);

    @Query("SELECT * FROM assessments WHERE courseID = :courseID")
    List<AssessmentEntity> getAssessmentByCourse(int courseID);

    @Query("DELETE FROM assessments")
    int deleteAllAssessments();


    @Query("SELECT * FROM assessments")
    List<AssessmentEntity> getAllAssessments();
}
