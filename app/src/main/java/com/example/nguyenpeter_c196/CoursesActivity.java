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

import com.example.nguyenpeter_c196.Database.CourseRepo;
import com.example.nguyenpeter_c196.Entities.CourseEntity;
import com.example.nguyenpeter_c196.util.DateManager;

import java.util.Calendar;


public class CoursesActivity extends AppCompatActivity {

    EditText etCourseName, etInstructor, etPhone, etEmail, etNote;
    TextView startDate;
    TextView endDate;
    String courseName;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String instructor;
    String phone;
    String email;
    String note;
    int termID;
    int courseID;
    CourseRepo cRepo;
    private DatePickerDialog datePickerDialog;
    private Spinner courseStatusSpinner;
    private String selectedCourseStatus;
    private boolean startSelected = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);

        spinnerSelect();
        cRepo = new CourseRepo(getApplication());
        etCourseName = findViewById(R.id.et_course_name);
        startDate = findViewById(R.id.et_course_start_date);
        endDate = findViewById(R.id.et_course_end_date);
        etInstructor = findViewById(R.id.et_instructor_name);
        etPhone = findViewById(R.id.et_instructor_phone);
        etEmail = findViewById(R.id.et_instructor_email);
        etNote = findViewById(R.id.et_input_note);
        courseStatusSpinner = findViewById(R.id.course_spinner);
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
            etCourseName.setText(courseName);
            startDate.setText(courseStart);
            endDate.setText(courseEnd);
            getStatus(courseStatusSpinner);
            etInstructor.setText(instructor);
            etPhone.setText(phone);
            etEmail.setText(email);
            etNote.setText(note);
        }
        datePicker();
    }

    public void spinnerSelect() {
        courseStatusSpinner = findViewById(R.id.course_spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.course_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        courseStatusSpinner.setAdapter(adapter);
        courseStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourseStatus = courseStatusSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void datePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month += 1;
            String date = DateManager.date(year, month, day);
            if (startSelected) {
                startDate.setText(date);
            } else {
                endDate.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    private String getStatus(Spinner spinner) {
        return courseStatusSpinner.getSelectedItem().toString();
    }


    public void onSaveCourse(View view) {
        CourseEntity course;
        int newCourseID;
        String status = courseStatusSpinner.getSelectedItem().toString();
        if (courseID == -1) {
            if (cRepo.isNewCourse()) {
                newCourseID = 1;
            }
            else {
                newCourseID = cRepo.getAllCourses().get(cRepo.getAllCourses().size() -1).getCourseID() + 1;
            }
            course = new CourseEntity(newCourseID, etCourseName.getText().toString(), startDate.getText().toString(),
                    endDate.getText().toString(), status,
                    etInstructor.getText().toString(), etPhone.getText().toString(),
                    etEmail.getText().toString(), etNote.getText().toString(), termID);
            cRepo.insertCourse(course);
        }
        else {
            course = new CourseEntity(courseID, etCourseName.getText().toString(), startDate.getText().toString(),
                    endDate.getText().toString(), status,
                    etInstructor.getText().toString(), etPhone.getText().toString(),
                    etEmail.getText().toString(), etNote.getText().toString(), termID);
            cRepo.updateCourse(course);
        }
        finish();
    }

    public void onSelectStart(View view) {
        startSelected = true;
        datePickerDialog.show();
    }

    public void onSelectEnd(View view) {
        startSelected = false;
        datePickerDialog.show();
    }


}