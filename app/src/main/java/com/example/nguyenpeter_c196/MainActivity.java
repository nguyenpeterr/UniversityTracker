package com.example.nguyenpeter_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static int alertNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Application Home");
    }

    public void launchTerms(View v) {
        //launch terms list
        Intent i = new Intent(this, TermsListActivity.class);
        startActivity(i);
    }

    public void launchCourses(View v) {
        //launch courses list
        Intent i = new Intent(this, CoursesListActivity.class);
        startActivity(i);
    }

    public void launchAssessments(View v) {
        //launch assessments list
        Intent i = new Intent(this, AssessmentsListActivity.class);
        startActivity(i);
    }

    public static int getAlertNumber() {
        alertNumber++;
        return alertNumber;
    }

}
