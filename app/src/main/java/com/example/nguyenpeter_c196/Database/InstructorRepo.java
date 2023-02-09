package com.example.nguyenpeter_c196.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nguyenpeter_c196.DAO.InstructorDAO;
import com.example.nguyenpeter_c196.Entities.InstructorEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InstructorRepo {
    private InstructorDAO instructorDAO;

    private LiveData<List<InstructorEntity>> allInstructors;
    private static final int NumberOfThreads = 4;
    static final ExecutorService dbExec = Executors.newFixedThreadPool(NumberOfThreads);

    public InstructorRepo(Application application){
        DatabaseBuilder database = DatabaseBuilder.getDatabaseInstance(application);
        instructorDAO = database.instructorDAO();
        allInstructors = instructorDAO.getAllInstructors();

    }

    public void insertInstructor(InstructorEntity instructor){
        dbExec.execute(() -> {
            instructorDAO.insertInstructor(instructor);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateInstructor(InstructorEntity instructor){
        dbExec.execute(() -> {
            instructorDAO.updateInstructor(instructor);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void deleteInstructor(InstructorEntity instructor){
        dbExec.execute(() -> {
            instructorDAO.deleteInstructor(instructor);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllInstructors(){
        dbExec.execute(() -> {
            instructorDAO.deleteAllInstructors();
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Get methods
    public LiveData<List<InstructorEntity>> getAllInstructors(){
        return allInstructors;
    }

    public InstructorEntity getInstructorByID(int instructorID){
        return instructorDAO.getInstructorByID(instructorID);
    }

    public LiveData<List<InstructorEntity>> getInstructorByCourse(int instructorCourse){
        return instructorDAO.getInstructorByCourse(instructorCourse);
    }

//    private static class InsertInstructorAsyncTask extends AsyncTask<InstructorEntity, Void, Void> {
//        private InstructorDAO instructorDAO;
//
//        private InsertInstructorAsyncTask(InstructorDAO instructorDAO){
//            this.instructorDAO = instructorDAO;
//        }
//
//        @Override
//        protected Void doInBackground(InstructorEntity... instructorEntities) {
//            instructorDAO.insertInstructor(instructorEntities[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteInstructorAsyncTask extends AsyncTask<InstructorEntity, Void, Void>{
//        private InstructorDAO instructorDAO;
//
//        private DeleteInstructorAsyncTask(InstructorDAO instructorDAO){
//            this.instructorDAO = instructorDAO;
//        }
//
//        @Override
//        protected Void doInBackground(InstructorEntity... instructorEntities) {
//            instructorDAO.deleteInstructor(instructorEntities[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteAllInstructorsAsyncTask extends AsyncTask<Void, Void, Void>{
//        private InstructorDAO instructorDAO;
//
//        private DeleteAllInstructorsAsyncTask(InstructorDAO instructorDAO){
//            this.instructorDAO = instructorDAO;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            instructorDAO.deleteAllInstructors();
//            return null;
//        }
//    }
}
