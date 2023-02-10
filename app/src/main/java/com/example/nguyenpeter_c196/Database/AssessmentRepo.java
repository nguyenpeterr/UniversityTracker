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
    private List<AssessmentEntity> allAssessments;
    private static final int NumberOfThreads = 4;
    static final ExecutorService dbExec = Executors.newFixedThreadPool(NumberOfThreads);

    public AssessmentRepo(Application application) {
        DatabaseBuilder database = DatabaseBuilder.getDatabaseInstance(application);
        assessmentDAO = database.assessmentDAO();
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

    public void deleteAssessment(int assessmentID){
        dbExec.execute(()-> {
            assessmentDAO.deleteAssessmentByID(assessmentID);
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

    public List<AssessmentEntity> getAllAssessments(){
        dbExec.execute(()-> {
            allAssessments = assessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allAssessments;
    }

    public AssessmentEntity getAssessmentByID(int assessmentID){
        dbExec.execute(()-> {
            mAssessment = assessmentDAO.getAssessmentByID(assessmentID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAssessment;
    }

    public List<AssessmentEntity> getAssessmentByCourse(int assessmentCourse){
        dbExec.execute(()-> {
            allAssessments = assessmentDAO.getAssessmentByCourse(assessmentCourse);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allAssessments;
    }

}
