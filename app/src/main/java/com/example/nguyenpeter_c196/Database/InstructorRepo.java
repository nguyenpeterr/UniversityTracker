package com.example.nguyenpeter_c196.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nguyenpeter_c196.DAO.InstructorDAO;
import com.example.nguyenpeter_c196.Entities.InstructorEntity;

import java.util.List;

public class InstructorRepo {
    private InstructorDAO instructorDAO;

    private LiveData<List<InstructorEntity>> allInstructors;

    public InstructorRepo(Application application){
        Database database = Database.getDatabaseInstance(application);
        instructorDAO = database.instructorDAO();
        allInstructors = instructorDAO.getAllInstructors();

    }

    public void insertInstructor(InstructorEntity instructor){
        new InsertInstructorAsyncTask(instructorDAO).execute(instructor);

    }


    public void deleteInstructor(InstructorEntity instructor){
        new DeleteInstructorAsyncTask(instructorDAO).execute((Runnable) instructor);

    }

    public void deleteAllMentors(){
        new DeleteAllInstructorsAsyncTask(instructorDAO).execute();

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

    private static class InsertInstructorAsyncTask extends AsyncTask<InstructorEntity, Void, Void> {
        private InstructorDAO instructorDAO;

        private InsertInstructorAsyncTask(InstructorDAO instructorDAO){
            this.instructorDAO = instructorDAO;
        }

        @Override
        protected Void doInBackground(InstructorEntity... instructorEntities) {
            instructorDAO.insertInstructor(instructorEntities[0]);
            return null;
        }
    }

    private static class DeleteInstructorAsyncTask extends AsyncTask<InstructorEntity, Void, Void>{
        private InstructorDAO instructorDAO;

        private DeleteInstructorAsyncTask(InstructorDAO instructorDAO){
            this.instructorDAO = instructorDAO;
        }

        @Override
        protected Void doInBackground(InstructorEntity... instructorEntities) {
            instructorDAO.deleteInstructor(instructorEntities[0]);
            return null;
        }
    }

    private static class DeleteAllInstructorsAsyncTask extends AsyncTask<Void, Void, Void>{
        private InstructorDAO instructorDAO;

        private DeleteAllInstructorsAsyncTask(InstructorDAO instructorDAO){
            this.instructorDAO = instructorDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            instructorDAO.deleteAllInstructors();
            return null;
        }
    }
}
