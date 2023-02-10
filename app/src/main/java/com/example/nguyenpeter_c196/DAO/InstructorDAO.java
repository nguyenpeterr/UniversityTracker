package com.example.nguyenpeter_c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nguyenpeter_c196.Entities.InstructorEntity;

import java.util.List;

@Dao
public interface InstructorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInstructor(InstructorEntity instructorEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllInstructors(List<InstructorEntity> instructors);

    @Update
    void updateInstructor(InstructorEntity instructorEntity);

    @Delete
    void deleteInstructor(InstructorEntity instructorEntity);

    @Query("SELECT * FROM instructors WHERE instructorID= :instructorID")
    InstructorEntity getInstructorByID(int instructorID);

    @Query("SELECT * FROM instructors WHERE courseID = :courseID")
    List<InstructorEntity> getInstructorByCourse(int courseID);

    @Query("SELECT * FROM instructors ORDER BY instructorName DESC")
    List<InstructorEntity> getAllInstructors();

    @Query("SELECT COUNT(*) FROM instructors")
    int getInstructorCount();

    @Query("DELETE FROM instructors")
    int deleteAllInstructors();

    @Query("SELECT COUNT(*) FROM instructors WHERE courseID = :courseID")
    int getInstructorCountByCourse(int courseID);

    @Query("SELECT COUNT(*) FROM instructors WHERE courseID IS NOT NULL")
    int getInstructorCountByAnyCourse();
}
