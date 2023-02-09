package com.example.nguyenpeter_c196.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nguyenpeter_c196.DAO.NoteDAO;
import com.example.nguyenpeter_c196.Entities.NoteEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepo {
    private NoteDAO noteDAO;

    private LiveData<List<NoteEntity>> allNotes;
    private static final int NumberOfThreads = 4;
    static final ExecutorService dbExec = Executors.newFixedThreadPool(NumberOfThreads);


    public NoteRepo(Application application){
        DatabaseBuilder database = DatabaseBuilder.getDatabaseInstance(application);
        noteDAO = database.noteDAO();
        allNotes = noteDAO.getAllNotes();

    }


    public void insertNote(NoteEntity note){
        dbExec.execute(() -> {
            noteDAO.insertNote(note);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateNote(NoteEntity note){
        dbExec.execute(() -> {
            noteDAO.updateNote(note);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void deleteNote(NoteEntity note){
        dbExec.execute(() -> {
            noteDAO.deleteNote(note);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllNotes(){
        dbExec.execute(() -> {
            noteDAO.deleteAllNotes();
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Get methods
    public LiveData<List<NoteEntity>> getAllNotes(){
        return allNotes;
    }

    public NoteEntity getNoteByID(int noteID){
        return noteDAO.getNoteByID(noteID);
    }

    public LiveData<List<NoteEntity>> getNotesByAssessment(int noteAssessment){
        return noteDAO.getNoteByAssessment(noteAssessment);
    }
//
//    private static class InsertNoteAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
//        private NoteDAO noteDAO;
//
//        private InsertNoteAsyncTask(NoteDAO noteDAO){
//            this.noteDAO = noteDAO;
//        }
//
//        @Override
//        protected Void doInBackground(NoteEntity... noteEntities) {
//            noteDAO.insertNote(noteEntities[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteNoteAsyncTask extends AsyncTask<NoteEntity, Void, Void>{
//        private NoteDAO noteDAO;
//
//        private DeleteNoteAsyncTask(NoteDAO noteDAO){
//            this.noteDAO = noteDAO;
//        }
//
//        @Override
//        protected Void doInBackground(NoteEntity... noteEntities) {
//            noteDAO.deleteNote(noteEntities[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{
//        private NoteDAO noteDAO;
//
//        private DeleteAllNotesAsyncTask(NoteDAO noteDAO){
//            this.noteDAO = noteDAO;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            noteDAO.deleteAllNotes();
//            return null;
//        }
//    }
}
