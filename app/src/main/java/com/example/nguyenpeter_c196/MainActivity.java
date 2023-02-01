package com.example.nguyenpeter_c196;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

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

}