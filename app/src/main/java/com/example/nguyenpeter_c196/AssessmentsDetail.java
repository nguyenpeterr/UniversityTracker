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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenpeter_c196.Database.AssessmentRepo;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;
import com.example.nguyenpeter_c196.util.DateManager;
import com.example.nguyenpeter_c196.util.ReceiverClass;


public class AssessmentsDetail extends AppCompatActivity {

    TextView tvName, tvType, tvStart, tvEnd;
    int assessmentID;
    int courseID;
    String assessmentName;
    String assessmentType;
    String assessmentStart;
    String assessmentEnd;
    AssessmentRepo aRepo;
    final String alertStartMessage = "ALERT: Assessment begins today!";
    final String alertEndMessage = "ALERT: Assessment ends today!";
    private boolean update = false, alertMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_assessment);
        aRepo = new AssessmentRepo(getApplication());
        tvName = findViewById(R.id.detailAssessmentName);
        tvType = findViewById(R.id.detail_assessment_type);
        tvStart = findViewById(R.id.detailAssessmentStartDate);
        tvEnd = findViewById(R.id.detailAssessmentEndDate);
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        courseID = getIntent().getIntExtra("courseID", -1);
        assessmentName = getIntent().getStringExtra("assessmentName");
        assessmentType = getIntent().getStringExtra("assessmentType");
        assessmentStart = getIntent().getStringExtra("assessmentStart");
        assessmentEnd = getIntent().getStringExtra("assessmentEnd");
        if(assessmentID != -1) {
            tvName.setText(assessmentName);
            tvType.setText(assessmentType);
            tvStart.setText(assessmentStart);
            tvEnd.setText(assessmentEnd);
        }
    }

    private void refreshData() {
        AssessmentEntity updatedAssessment = aRepo.getAssessmentByID(assessmentID);
        tvName.setText(updatedAssessment.getAssessmentName());
        tvType.setText(updatedAssessment.getAssessmentType());
        tvStart.setText(updatedAssessment.getAssessmentStart());
        tvEnd.setText(updatedAssessment.getAssessmentEnd());
        update = false;
    }

    protected void onResume() {
        super.onResume();
        if(update) {
            refreshData();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessment_detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.set_assessment_start_alert:
                alertMessage = true;
                setReminder(assessmentStart);
                Toast.makeText(getApplicationContext(), "Alert for Start Date is set!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.set_assessment_end_alert:
                alertMessage = false;
                setReminder(assessmentEnd);
                Toast.makeText(getApplicationContext(), "Alert for End Date is set!", Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setReminder(String date) {
        Long trigger = DateManager.toMillisec(date);
        Intent i = new Intent(AssessmentsDetail.this, ReceiverClass.class);
        String mAlert = alertMessage ? alertStartMessage : alertEndMessage;
        i.putExtra("key", mAlert);
        PendingIntent sender = PendingIntent.getBroadcast(this, MainActivity.getAlertNumber(), i, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
    }

    public void onDeleteAssessment(View v) {
        if(assessmentID != -1) {
            aRepo.deleteAssessment(assessmentID);
            Intent i = new Intent(this, CoursesListActivity.class);
            startActivity(i);
        }
    }

    public void onModifyAssessment(View v) {
        update = true;
        Intent i = new Intent(this, AssessmentsActivity.class);
        i.putExtra("assessmentID", assessmentID);
        i.putExtra("assessmentName", assessmentName);
        i.putExtra("assessmentStart", assessmentStart);
        i.putExtra("assessmentEnd", assessmentEnd);
        i.putExtra("assessmentType", assessmentType);
        i.putExtra("courseID", courseID);
        startActivity(i);
    }

}