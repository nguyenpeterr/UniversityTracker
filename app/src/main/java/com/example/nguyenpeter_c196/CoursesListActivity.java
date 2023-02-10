package com.example.nguyenpeter_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nguyenpeter_c196.Database.CourseRepo;
import com.example.nguyenpeter_c196.Adapter.CourseAdapter;
import com.example.nguyenpeter_c196.Entities.CourseEntity;

import java.util.ArrayList;


public class CoursesListActivity extends AppCompatActivity {

    public static final int addCourseCount = 1;
    public static final int editCourseCount = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

    }

    private void courseListView() {
        RecyclerView rView = findViewById(R.id.recycler_view);
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setHasFixedSize(true);

        CourseRepo repo = new CourseRepo(getApplication());
        ArrayList<CourseEntity> courses = (ArrayList<CourseEntity>) repo.getAllCourses();
        final CourseAdapter courseAdapter = new CourseAdapter();
        rView.setAdapter(courseAdapter);
        courseAdapter.setCourses(courses);
    }



    public void onAddCourseButton(View v) {
        Intent i = new Intent(this, CoursesActivity.class);
        startActivity(i);
    }

}