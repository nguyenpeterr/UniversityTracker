package com.example.nguyenpeter_c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.nguyenpeter_c196.Entities.CourseEntity;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourse(CourseEntity courseEntity);

    //Sample data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCourses(List<CourseEntity> courses);

    @Delete
    void deleteCourse(CourseEntity courseEntity);

    @Query("SELECT * FROM courses WHERE courseID = :courseID")
    CourseEntity getCourseByID(int courseID);

    @Query("SELECT * FROM courses WHERE termID = :termID")
    LiveData<List<CourseEntity>> getCoursesByTerm(int termID);

    @Query("SELECT * FROM courses ORDER BY courseStartDate DESC")
    LiveData<List<CourseEntity>> getAllCourses();

    @Query("DELETE FROM courses")
    int deleteAllCourses();

    @Query("SELECT COUNT(*) FROM courses")
    int getCourseCount();

    //Are these even necessary?
    @Query("SELECT COUNT(*) FROM courses WHERE termID = :termID")
    int getCountByTerm(int termID);

    @Query("SELECT COUNT(*) FROM courses WHERE termID IS NOT NULL")
    int getCountByAnyTerm();

}