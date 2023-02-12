package com.example.nguyenpeter_c196;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenpeter_c196.Adapter.CourseAdapter;
import com.example.nguyenpeter_c196.Database.CourseRepo;
import com.example.nguyenpeter_c196.Database.TermRepo;
import com.example.nguyenpeter_c196.Entities.CourseEntity;
import com.example.nguyenpeter_c196.Entities.TermEntity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class TermDetail extends AppCompatActivity {
    TextView tvName, tvStart, tvEnd;
    int termID;
    String termName;
    String termStartDate;
    String termEndDate;
    TermRepo tRepo;
    List<CourseEntity> courses;
    final String removeFail = "Please remove all courses before deleting term.";
    private boolean updated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_term);
        tRepo = new TermRepo(getApplication());
        tvName = findViewById(R.id.detailTermNameLabel);
        tvStart = findViewById(R.id.detailTermStartDate);
        tvEnd = findViewById(R.id.detailTermEndDate);
        termID = getIntent().getIntExtra("termID", -1);
        termName = getIntent().getStringExtra("termName");
        termStartDate = getIntent().getStringExtra("termStartDate");
        termEndDate = getIntent().getStringExtra("termEndDate");
        if (termID != -1) {
            tvName.setText(termName);
            tvStart.setText(termStartDate);
            tvEnd.setText(termEndDate);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewRefresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewRefresh();
        if (updated) {
            refreshData();
        }
    }

    private void refreshData() {
        TermEntity modifiedTerm = tRepo.getTermByID(termID);
        tvName.setText(modifiedTerm.getTermName());
        tvStart.setText(modifiedTerm.getTermStartDate());
        tvEnd.setText(modifiedTerm.getTermEndDate());
        updated = false;
    }

    private void recyclerViewRefresh() {
        RecyclerView recyclerView = findViewById(R.id.course_detail_recycler);
        CourseRepo cRepo = new CourseRepo(getApplication());
        courses = cRepo.getCoursesByTerm(termID);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onDeleteTerm(View view) {
        if (courses.isEmpty()) {
            if (termID != -1) {
                tRepo.deleteTerm(termID);
            }
            Intent i = new Intent(this, TermsListActivity.class);
            startActivity(i);
        }
        else {
            Snackbar message = Snackbar.make(view, removeFail, LENGTH_SHORT);
            message.show();
        }
    }

    public void onAddCourse(View view) {
        Intent i = new Intent(this, CoursesActivity.class);
        i.putExtra("termID", termID);
        startActivity(i);
    }

    public void onModifyTerm(View view) {
        updated = true;
        Intent i = new Intent(this, TermActivity.class);
        i.putExtra("termID", termID);
        i.putExtra("termName", termName);
        i.putExtra("termStartDate", termStartDate);
        i.putExtra("termEndDate", termEndDate);
        startActivity(i);
    }
}