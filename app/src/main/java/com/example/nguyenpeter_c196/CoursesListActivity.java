package com.example.nguyenpeter_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class CoursesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

    }

    public void onAddCourseButtonClick(View v) {
        Intent i = new Intent(this, CoursesActivity.class);
        startActivity(i);
    }

}