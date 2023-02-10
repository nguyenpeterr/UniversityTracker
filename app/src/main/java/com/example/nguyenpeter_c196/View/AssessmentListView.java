package com.example.nguyenpeter_c196.View;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.nguyenpeter_c196.Database.AssessmentRepo;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;

import java.util.List;

public class AssessmentListView extends AndroidViewModel {

    private AssessmentRepo assessmentRepo;
    public LiveData<List<AssessmentEntity>> allAssessments;

    public AssessmentListView(@NonNull Application application) {
        super(application);
        assessmentRepo = new AssessmentRepo(application);
        allAssessments = assessmentRepo.getAllAssessments();
    }

    public void insertAssessment(AssessmentEntity assessment) {
        assessmentRepo.insertAssessment(assessment);
    }

    public void updateAssessment(AssessmentEntity assessment) {
        assessmentRepo.updateAssessment(assessment);
    }

    public void deleteAssessment(AssessmentEntity assessment) {
        assessmentRepo.deleteAssessment(assessment);
    }

    public void deleteAllAssessments() {
        assessmentRepo.deleteAllAssessments();
    }

    public LiveData<List<AssessmentEntity>> getAllAssessments() {
        return allAssessments;
    }

    public LiveData<List<AssessmentEntity>> getAssessmentByCourse(int courseID) {
        return assessmentRepo.getAssessmentByCourse(courseID);
    }



}
