package com.example.nguyenpeter_c196;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenpeter_c196.Adapter.AssessmentAdapter;
import com.example.nguyenpeter_c196.Database.AssessmentRepo;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;

import java.util.ArrayList;


public class AssessmentsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = findViewById(R.id.assessment_recycler_view);
        AssessmentRepo repo = new AssessmentRepo(getApplication());
        ArrayList<AssessmentEntity> assessments = (ArrayList<AssessmentEntity>) repo.getAllAssessments();
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);

    }

    public void faAddAssessment(View v) {
        Intent i = new Intent(this, AssessmentsActivity.class);
        startActivity(i);
    }

}