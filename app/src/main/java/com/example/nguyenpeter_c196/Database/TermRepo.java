package com.example.nguyenpeter_c196.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nguyenpeter_c196.DAO.TermDAO;
import com.example.nguyenpeter_c196.Entities.TermEntity;

import java.util.List;

public class TermRepo {
    private TermDAO termDAO;
    private LiveData<List<TermEntity>> allTerms;


    public TermRepo(Application application){
        Database database = Database.getDatabaseInstance(application);
        termDAO = database.termDAO();
        allTerms = termDAO.getAllTerms();

    }

    //Insert methods
    public void insertTerm(TermEntity term){
        new InsertTermAsyncTask(termDAO).execute(term);

    }

    //Delete methods
    public void deleteTerm(TermEntity term){
        new DeleteTermAsyncTask(termDAO).execute(term);

    }

    public void deleteAllTerms(){
        new DeleteAllTermsAsyncTask(termDAO).execute();

    }


    //Get methods
    public LiveData<List<TermEntity>> getAllTerms(){
        return allTerms;
    }

    public TermEntity getTermByID(int termID){
        return termDAO.getTermByID(termID);
    }

    private static class InsertTermAsyncTask extends AsyncTask<TermEntity, Void, Void> {
        private TermDAO termDAO;

        private InsertTermAsyncTask(TermDAO termDAO){
            this.termDAO = termDAO;
        }

        @Override
        protected Void doInBackground(TermEntity... termEntities) {
            termDAO.insertTerm(termEntities[0]);
            return null;
        }
    }

    private static class DeleteTermAsyncTask extends AsyncTask<TermEntity, Void, Void>{
        private TermDAO termDAO;

        private DeleteTermAsyncTask(TermDAO termDAO){
            this.termDAO = termDAO;
        }

        @Override
        protected Void doInBackground(TermEntity... termEntities) {
            termDAO.deleteTerm(termEntities[0]);
            return null;
        }
    }

    private static class DeleteAllTermsAsyncTask extends AsyncTask<Void, Void, Void>{
        private TermDAO termDAO;

        private DeleteAllTermsAsyncTask(TermDAO termDAO){
            this.termDAO = termDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            termDAO.deleteAllTerms();
            return null;
        }
    }
}
