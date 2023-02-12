package com.example.nguyenpeter_c196;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenpeter_c196.Database.TermRepo;
import com.example.nguyenpeter_c196.Entities.TermEntity;
import com.example.nguyenpeter_c196.util.DateManager;

import java.util.Calendar;

public class TermActivity extends AppCompatActivity {

    EditText etTermName;
    TextView tvStart, tvEnd;
    int termID;
    String termName;
    String termStartDate;
    String termEndDate;
    TermRepo tRepo;
    private DatePickerDialog datePickerDialog;
    private boolean startSelected = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        tRepo = new TermRepo(getApplication());
        etTermName = findViewById(R.id.et_term_name);
        tvStart = findViewById(R.id.et_term_start_date);
        tvEnd = findViewById(R.id.et_term_end_date);
        termID = getIntent().getIntExtra("termID", -1);
        termName = getIntent().getStringExtra("termName");
        termStartDate = getIntent().getStringExtra("termStartDate");
        termEndDate = getIntent().getStringExtra("termEndDate");
        if (termID != -1) {
            etTermName.setText(termName);
            tvStart.setText(termStartDate);
            tvEnd.setText(termEndDate);
        }
        datePicker();
    }

    private void datePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month += 1;
            String date = DateManager.date(year, month, day);
            if (startSelected) {
                tvStart.setText(date);
            } else {
                tvEnd.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    public void onSaveTerm(View view) {
        TermEntity term;
        int newTermID;
        if (termID == -1) {
            if (tRepo.isNewTerm()) {
                newTermID = 1;
            }
            else {
                newTermID = tRepo.getAllTerms().get(tRepo.getAllTerms().size() -1).getTermID() + 1;
            }
            term = new TermEntity(newTermID, etTermName.getText().toString(), tvStart.getText().toString(), tvEnd.getText().toString());
            tRepo.insertTerm(term);
        }
        else {
            term = new TermEntity(termID, etTermName.getText().toString(), tvStart.getText().toString(), tvEnd.getText().toString());
            tRepo.updateTerm(term);
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
