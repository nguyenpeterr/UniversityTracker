package com.example.nguyenpeter_c196.View;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.nguyenpeter_c196.Database.AssessmentRepo;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AssessmentView extends AndroidViewModel {
    private LiveData<List<AssessmentEntity>> allAssessments;
    public MutableLiveData<AssessmentEntity> mLiveAssessment = new MutableLiveData<>();
    private AssessmentRepo assessmentRepo;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AssessmentView(@NonNull Application application) {
        super(application);
    }
}
