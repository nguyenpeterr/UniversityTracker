package com.example.nguyenpeter_c196.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nguyenpeter_c196.DAO.AssessmentDAO;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AssessmentRepo {
    private AssessmentDAO assessmentDAO;
    private AssessmentEntity mAssessment;
    private LiveData<List<AssessmentEntity>> allAssessments;
    private static final int NumberOfThreads = 4;
    static final ExecutorService dbExec = Executors.newFixedThreadPool(NumberOfThreads);

    public AssessmentRepo(Application application) {
        DatabaseBuilder database = DatabaseBuilder.getDatabaseInstance(application);
        assessmentDAO = database.assessmentDAO();
        allAssessments = assessmentDAO.getAllAssessments();

    }

    public void insertAssessment(AssessmentEntity assessment){
        dbExec.execute(()-> {
            assessmentDAO.insertAssessment(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateAssessment(AssessmentEntity assessment){
        dbExec.execute(()-> {
            assessmentDAO.updateAssessment(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAssessment(AssessmentEntity assessment){
        dbExec.execute(()-> {
            assessmentDAO.deleteAssessment(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void deleteAllAssessments(){
        dbExec.execute(()-> {
            assessmentDAO.deleteAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Get methods
    public LiveData<List<AssessmentEntity>> getAllAssessments(){
        return allAssessments;
    }

    public AssessmentEntity getAssessmentByID(int assessmentID){
        return assessmentDAO.getAssessmentByID(assessmentID);
    }

    public LiveData<List<AssessmentEntity>> getAssessmentByCourse(int assessmentCourse){
        return assessmentDAO.getAssessmentByCourse(assessmentCourse);
    }

//    private static class InsertAssessmentAsyncTask extends AsyncTask<AssessmentEntity, Void, Void> {
//        private AssessmentDAO assessmentDAO;
//
//        private InsertAssessmentAsyncTask(AssessmentDAO assessmentDAO){
//            this.assessmentDAO = assessmentDAO;
//        }
//
//        @Override
//        protected Void doInBackground(AssessmentEntity... assessmentEntities) {
//            assessmentDAO.insertAssessment(assessmentEntities[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteAssessmentAsyncTask extends AsyncTask<AssessmentEntity, Void, Void>{
//        private AssessmentDAO assessmentDAO;
//
//        private DeleteAssessmentAsyncTask(AssessmentDAO assessmentDAO){
//            this.assessmentDAO = assessmentDAO;
//        }
//
//        @Override
//        protected Void doInBackground(AssessmentEntity... assessmentEntities) {
//            assessmentDAO.deleteAssessment(assessmentEntities[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteAllAssessmentsAsyncTask extends AsyncTask<Void, Void, Void>{
//        private AssessmentDAO assessmentDAO;
//
//        private DeleteAllAssessmentsAsyncTask(AssessmentDAO assessmentDAO){
//            this.assessmentDAO = assessmentDAO;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            assessmentDAO.deleteAllAssessments();
//            return null;
//        }
//    }
}
