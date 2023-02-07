package com.example.nguyenpeter_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class AssessmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);

    }

    public void onCancelAssessment(View v) {
        //return back to assessment list on cancel button
        Intent i = new Intent(this, AssessmentsListActivity.class);
        startActivity(i);
    }

}