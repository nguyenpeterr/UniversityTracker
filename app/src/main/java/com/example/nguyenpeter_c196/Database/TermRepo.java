package com.example.nguyenpeter_c196.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nguyenpeter_c196.DAO.TermDAO;
import com.example.nguyenpeter_c196.Entities.TermEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TermRepo {
    private TermDAO termDAO;
    private TermEntity mTerm;
    private List<TermEntity> allTerms;
    private static final int NumberOfThreads = 4;
    static final ExecutorService dbExec = Executors.newFixedThreadPool(NumberOfThreads);


    public TermRepo(Application application){
        DatabaseBuilder database = DatabaseBuilder.getDatabaseInstance(application);
        termDAO = database.termDAO();
    }


    public void insertTerm(TermEntity term) {
        dbExec.execute(()-> {
            termDAO.insertTerm(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateTerm(TermEntity term) {
        dbExec.execute(()-> {
            termDAO.updateTerm(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void deleteTerm(TermEntity term){
        dbExec.execute(()-> {
            termDAO.deleteTerm(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllTerms(){
        dbExec.execute(()-> {
            termDAO.deleteAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public List<TermEntity> getAllTerms(){
        dbExec.execute(()-> {
            allTerms = termDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allTerms;
    }

    public TermEntity getTermByID(int termID){
        dbExec.execute(()-> {
            mTerm = termDAO.getTermByID(termID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mTerm;
    }


//    private static class InsertTermAsyncTask extends AsyncTask<TermEntity, Void, Void> {
//        private TermDAO termDAO;
//
//        private InsertTermAsyncTask(TermDAO termDAO){
//            this.termDAO = termDAO;
//        }
//
//        @Override
//        protected Void doInBackground(TermEntity... termEntities) {
//            termDAO.insertTerm(termEntities[0]);
//            return null;
//        }
//    }

//    private static class DeleteTermAsyncTask extends AsyncTask<TermEntity, Void, Void>{
//        private TermDAO termDAO;
//
//        private DeleteTermAsyncTask(TermDAO termDAO){
//            this.termDAO = termDAO;
//        }
//
//        @Override
//        protected Void doInBackground(TermEntity... termEntities) {
//            termDAO.deleteTerm(termEntities[0]);
//            return null;
//        }
//    }

//    private static class DeleteAllTermsAsyncTask extends AsyncTask<Void, Void, Void>{
//        private TermDAO termDAO;
//
//        private DeleteAllTermsAsyncTask(TermDAO termDAO){
//            this.termDAO = termDAO;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            termDAO.deleteAllTerms();
//            return null;
//        }
//    }
}
