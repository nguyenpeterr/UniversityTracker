package com.example.nguyenpeter_c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.nguyenpeter_c196.Entities.AssessmentEntity;

import java.util.List;

@Dao
public interface AssessmentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssessment(AssessmentEntity assessmentEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllAssessments(List<AssessmentEntity> assessments);

    @Delete
    void deleteAssessment(AssessmentEntity assessmentEntity);

    @Query("SELECT * FROM assessments WHERE assessmentID = :assessmentID")
    AssessmentEntity getAssessmentByID(int assessmentID);

    @Query("SELECT * FROM assessments WHERE courseID = :courseID")
    LiveData<List<AssessmentEntity>> getAssessmentByCourse(int courseID);

    @Query("SELECT * FROM assessments ORDER BY assessmentDate DESC")
    LiveData<List<AssessmentEntity>> getAllAssessments();

    @Query("DELETE FROM assessments")
    int deleteAllAssessments();

    @Query("SELECT COUNT(*) FROM assessments")
    int getAssessmentCount();

    @Query("SELECT COUNT(*) FROM assessments WHERE courseID = :courseID")
    int getAssessmentCountByCourse(int courseID);

    @Query("SELECT COUNT(*) FROM assessments WHERE courseID IS NOT NULL")
    int getAssessmentCountByAnyCourse();
}
