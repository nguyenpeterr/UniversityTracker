package com.example.nguyenpeter_c196.Database;

import android.app.Application;

import com.example.nguyenpeter_c196.DAO.CourseDAO;
import com.example.nguyenpeter_c196.Entities.CourseEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepo {

    private CourseDAO courseDAO;
    private CourseEntity mCourse;
    private List<CourseEntity> allCourses;
    private static final int NumberOfThreads = 4;
    static final ExecutorService dbExec = Executors.newFixedThreadPool(NumberOfThreads);


    public CourseRepo(Application application){
        DatabaseBuilder database = DatabaseBuilder.getDatabaseInstance(application);
        courseDAO = database.courseDAO();
        allCourses = courseDAO.getAllCourses();

    }

    public void insertCourse(CourseEntity course){
        dbExec.execute(() -> {
            courseDAO.insertCourse(course);
        });
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCourse(CourseEntity course){
        dbExec.execute(() -> {
            courseDAO.updateCourse(course);
        });
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void deleteCourse(int courseID){
        dbExec.execute(() -> {
            courseDAO.getCourseByID(courseID);
        });
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCourses(){
        dbExec.execute(() -> {
            courseDAO.deleteAllCourses();
        });
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<CourseEntity> getAllCourses(){
        return (List<CourseEntity>) allCourses;
    }

    public CourseEntity getCourseByID(int courseID){
        return courseDAO.getCourseByID(courseID);
    }

    public List<CourseEntity> getCoursesByTerm(int termID){
        return courseDAO.getCoursesByTerm(termID);
    }

    public boolean isNewCourse() {
        getAllCourses();
        return allCourses.isEmpty();
    }
}
