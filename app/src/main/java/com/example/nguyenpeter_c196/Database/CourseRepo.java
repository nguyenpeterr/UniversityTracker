package com.example.nguyenpeter_c196.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

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
        allCourses = (List<CourseEntity>) courseDAO.getAllCourses();

    }

    public void insertCourse(CourseEntity course){
        dbExec.execute(() -> {
            courseDAO.insertCourse(course);
        });
        try {
            Thread.sleep(1000);
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
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void deleteCourse(CourseEntity course){
        dbExec.execute(() -> {
            courseDAO.deleteCourse(course);
        });
        try {
            Thread.sleep(1000);
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
            Thread.sleep(1000);
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

//    private static class InsertCourseAsyncTask extends AsyncTask<CourseEntity, Void, Void> {
//        private CourseDAO courseDAO;
//
//        private InsertCourseAsyncTask(CourseDAO courseDAO){
//            this.courseDAO = courseDAO;
//        }
//
//        @Override
//        protected Void doInBackground(CourseEntity... courseEntities) {
//            courseDAO.insertCourse(courseEntities[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteCourseAsyncTask extends AsyncTask<CourseEntity, Void, Void>{
//        private CourseDAO courseDAO;
//
//        private DeleteCourseAsyncTask(CourseDAO courseDAO){
//            this.courseDAO = courseDAO;
//        }
//
//        @Override
//        protected Void doInBackground(CourseEntity... courseEntities) {
//            courseDAO.deleteCourse(courseEntities[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void>{
//        private CourseDAO courseDAO;
//
//        private DeleteAllCoursesAsyncTask(CourseDAO courseDAO){
//            this.courseDAO = courseDAO;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            courseDAO.deleteAllCourses();
//            return null;
//        }
//    }
}
