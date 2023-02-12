package com.example.nguyenpeter_c196;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenpeter_c196.Adapter.TermAdapter;
import com.example.nguyenpeter_c196.Database.TermRepo;
import com.example.nguyenpeter_c196.Entities.TermEntity;

import java.util.ArrayList;


public class TermsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewRefresh();
    }


    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewRefresh();
    }

    private void recyclerViewRefresh() {
        RecyclerView recyclerView = findViewById(R.id.term_recycler_view);
        TermRepo repo = new TermRepo(getApplication());
        ArrayList<TermEntity> terms = (ArrayList<TermEntity>) repo.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term_list, menu);
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

    public void onAddTerm(View v) {
        Intent i = new Intent(this, TermActivity.class);
        startActivity(i);
    }

}