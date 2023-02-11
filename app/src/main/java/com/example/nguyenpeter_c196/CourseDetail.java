package com.example.nguyenpeter_c196;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nguyenpeter_c196.Adapter.AssessmentAdapter;
import com.example.nguyenpeter_c196.Database.AssessmentRepo;
import com.example.nguyenpeter_c196.Database.CourseRepo;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;
import com.example.nguyenpeter_c196.Entities.CourseEntity;
import com.example.nguyenpeter_c196.util.DateManager;
import com.example.nguyenpeter_c196.util.ReceiverClass;
import java.util.List;

public class CourseDetail extends AppCompatActivity {

    TextView tvName, tvStart, tvEnd, tvStatus, tvInstructor, tvPhone, tvEmail, tvNote;
    RecyclerView recyclerView;
    int courseID;
    int termID;
    String courseName;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String instructor;
    String phone;
    String email;
    String note;
    final String alertStartMessage = "ALERT: Your course begins today!";
    final String alertEndMessage = "ALERT: Your course ends today!";
    CourseRepo cRepo;
    private boolean updated = false, alertMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_course);
        cRepo = new CourseRepo(getApplication());
        tvName = findViewById(R.id.detailCourseName);
        tvStart = findViewById(R.id.detailCourseStartDate);
        tvEnd = findViewById(R.id.detailCourseEndDate);
        tvStatus = findViewById(R.id.detail_course_status);
        tvInstructor = findViewById(R.id.detail_course_instructor);
        tvPhone = findViewById(R.id.detail_course_instructor_phone);
        tvEmail = findViewById(R.id.detail_course_instructor_email);
        tvNote = findViewById(R.id.detail_course_note);
        courseID = getIntent().getIntExtra("courseID", -1);
        courseName = getIntent().getStringExtra("courseName");
        courseStart = getIntent().getStringExtra("courseStart");
        courseEnd = getIntent().getStringExtra("courseEnd");
        courseStatus = getIntent().getStringExtra("courseStatus");
        instructor = getIntent().getStringExtra("instructor");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
        note = getIntent().getStringExtra("note");
        termID = getIntent().getIntExtra("termID", -1);

        if (courseID != -1) {
            tvName.setText(courseName);
            tvStart.setText(courseStart);
            tvEnd.setText(courseEnd);
            tvStatus.setText(courseStatus);
            tvInstructor.setText(instructor);
            tvPhone.setText(phone);
            tvEmail.setText(email);
            tvNote.setText(note);
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
        CourseEntity modifiedCourse = cRepo.getCourseByID(courseID);
        tvName.setText(modifiedCourse.getCourseName());
        tvStart.setText(modifiedCourse.getCourseStartDate());
        tvEnd.setText(modifiedCourse.getCourseEndDate());
        tvStatus.setText(modifiedCourse.getCourseStatus());
        tvInstructor.setText(modifiedCourse.getInstructor());
        tvPhone.setText(modifiedCourse.getPhone());
        tvEmail.setText(modifiedCourse.getEmail());
        tvNote.setText(modifiedCourse.getNote());
        updated = false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.set_course_start_alert:
                alertMessage = true;
                setReminder(courseStart);
                return true;
            case R.id.set_course_end_alert:
                alertMessage = false;
                setReminder(courseEnd);
                return true;
            case R.id.share_course_note:
                shareNote();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setReminder(String date) {
        Long alert = DateManager.toMillisec(date);
        Intent i = new Intent(this, ReceiverClass.class);
        String message = alertMessage ? alertStartMessage : alertEndMessage;
        i.putExtra("key", message);
        PendingIntent broadcast = PendingIntent.getBroadcast(this,
                MainActivity.alertCount(), i, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alert, broadcast);
    }

    private void shareNote() {
        Intent sendNote = new Intent();
        sendNote.setAction(Intent.ACTION_SEND);
        sendNote.putExtra(Intent.EXTRA_TITLE, courseName);
        sendNote.putExtra(Intent.EXTRA_TEXT, note);
        sendNote.setType("text/plain");
        Intent chooser = Intent.createChooser(sendNote, null);
        startActivity(chooser);
    }

    private void recyclerViewRefresh() {
        recyclerView = findViewById(R.id.course_detail_recycler);
        AssessmentRepo repo = new AssessmentRepo(getApplication());
        List<AssessmentEntity> assessments = repo.getAssessmentByCourse(courseID);
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);
    }

    public void onDeleteCourse(View view) {
        if (courseID != -1) {
            cRepo.deleteCourse(courseID);
            Intent i = new Intent(this, TermsListActivity.class);
            startActivity(i);
        }
    }

    public void onAddAssessment(View view) {
        Intent i = new Intent(this, AssessmentsActivity.class);
        i.putExtra("courseID", courseID);
        startActivity(i);
    }

    public void onModifyCourse(View view) {
        updated = true;
        Intent i = new Intent(this, CoursesActivity.class);
        i.putExtra("courseID", courseID);
        i.putExtra("courseName", courseName);
        i.putExtra("courseStart", courseStart);
        i.putExtra("courseEnd", courseEnd);
        i.putExtra("courseStatus", courseStatus);
        i.putExtra("instructor", instructor);
        i.putExtra("phone", phone);
        i.putExtra("email", email);
        i.putExtra("note", note);
        i.putExtra("termID", termID);
        startActivity(i);
    }
}