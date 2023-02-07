package com.example.nguyenpeter_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class TermsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

    }

    public void onAddTerm(View v) {
        Intent i = new Intent(this, TermActivity.class);
        startActivity(i);
    }

}