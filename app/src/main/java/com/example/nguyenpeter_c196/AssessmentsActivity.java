package com.example.nguyenpeter_c196;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenpeter_c196.Database.AssessmentRepo;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;
import com.example.nguyenpeter_c196.util.DateManager;

import java.util.Calendar;


public class AssessmentsActivity extends AppCompatActivity {

    EditText etName;
    TextView tvAssessmentStart, tvAssessmentEnd;
    int assessmentID;
    int courseID;
    String assessmentName;
    String assessmentStart;
    String assessmentEnd;
    String assessmentType;
    AssessmentRepo aRepo;
    private DatePickerDialog datePickerDialog;
    private Spinner assessmentTypeSpinner;
    private String selectedAssessmentType;
    boolean startSelected = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);

        spinnerSelect();
        aRepo = new AssessmentRepo(getApplication());
        etName = findViewById(R.id.et_assessment_name);
        tvAssessmentStart = findViewById(R.id.et_start_date);
        tvAssessmentEnd = findViewById(R.id.et_end_date);
        assessmentTypeSpinner = findViewById(R.id.assessment_spinner);
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        courseID = getIntent().getIntExtra("courseID", -1);
        assessmentName = getIntent().getStringExtra("assessmentName");
        assessmentStart = getIntent().getStringExtra("assessmentStart");
        assessmentEnd = getIntent().getStringExtra("assessmentEnd");
        assessmentType = getIntent().getStringExtra("assessmentType");
        if(assessmentID != -1) {
            etName.setText(assessmentName);
            getType(assessmentTypeSpinner);
            tvAssessmentStart.setText(assessmentStart);
            tvAssessmentEnd.setText(assessmentEnd);
        }
        datePicker();

    }

    public void spinnerSelect() {
        assessmentTypeSpinner = findViewById(R.id.assessment_spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.assessments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assessmentTypeSpinner.setAdapter(adapter);
        assessmentTypeSpinner.setSelection(1);
        assessmentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAssessmentType = assessmentTypeSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String getType(Spinner assessmentTypeSpinner) {
       return this.assessmentTypeSpinner.getSelectedItem().toString();
    }


    private void datePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month += 1;
            String date = DateManager.date(year, month, day);
            if (startSelected) {
                tvAssessmentStart.setText(date);
            }
            else {
                tvAssessmentEnd.setText(date);
            }

        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    public void onCalendarStart(View view) {
        startSelected = true;
        datePickerDialog.show();
    }

    public void onCalendarEnd(View view) {
        startSelected = false;
        datePickerDialog.show();
    }


    public void onSaveAssessment(View v) {
        AssessmentEntity assessment;
        int newAssessmentID;
        String type = assessmentTypeSpinner.getSelectedItem().toString();
        if(assessmentID == -1) {
            if(aRepo.isNewAssessment()) {
                newAssessmentID = 1;
            } else {
                newAssessmentID = aRepo.getAllAssessments().get(aRepo.getAllAssessments().size() - 1).getAssessmentID() + 1;
            }
            assessment = new AssessmentEntity(newAssessmentID, etName.getText().toString(), tvAssessmentStart.getText().toString(),tvAssessmentEnd.getText().toString(), type, courseID);
            aRepo.insertAssessment(assessment);
        } else {
            assessment = new AssessmentEntity(assessmentID, etName.getText().toString(), tvAssessmentStart.getText().toString(),tvAssessmentEnd.getText().toString(), type, courseID);
            aRepo.updateAssessment(assessment);
        }
        finish();
    }

}