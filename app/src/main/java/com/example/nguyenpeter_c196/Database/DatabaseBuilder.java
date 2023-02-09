package com.example.nguyenpeter_c196.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.nguyenpeter_c196.DAO.AssessmentDAO;
import com.example.nguyenpeter_c196.DAO.CourseDAO;
import com.example.nguyenpeter_c196.DAO.InstructorDAO;
import com.example.nguyenpeter_c196.DAO.NoteDAO;
import com.example.nguyenpeter_c196.DAO.TermDAO;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;
import com.example.nguyenpeter_c196.Entities.CourseEntity;
import com.example.nguyenpeter_c196.Entities.InstructorEntity;
import com.example.nguyenpeter_c196.Entities.NoteEntity;
import com.example.nguyenpeter_c196.Entities.TermEntity;

@androidx.room.Database(entities = {AssessmentEntity.class, CourseEntity.class, InstructorEntity.class, NoteEntity.class, TermEntity.class}, version = 1)
public abstract class DatabaseBuilder extends RoomDatabase {
    private static final String DATABASE_NAME = "C196.db";
    private static volatile DatabaseBuilder databaseInstance;
    private static final Object LOCK = new Object();


    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    public abstract InstructorDAO instructorDAO();
    public abstract NoteDAO noteDAO();

    public static DatabaseBuilder getDatabaseInstance(Context context) {

        if (databaseInstance == null) {
            synchronized (LOCK) {
                if (databaseInstance == null) {

                    databaseInstance = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();
                    Toast.makeText(context, "DatabaseBuilder being created", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return databaseInstance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(databaseInstance).execute();

        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private TermDAO termDAO;
        private CourseDAO courseDAO;
        private AssessmentDAO assessmentDAO;
        private InstructorDAO instructorDAO;
        private NoteDAO noteDAO;

        private PopulateDbAsyncTask(DatabaseBuilder db) {

            termDAO = db.termDAO();
            courseDAO = db.courseDAO();
            assessmentDAO = db.assessmentDAO();
            instructorDAO = db.instructorDAO();
            noteDAO = db.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //termDAO.insertTerm(new TermEntity("Term 1", "2019-12-01", "2019-12-31"));
            // termDAO.insertTerm(new TermEntity("Term 2", "2019-12-02", "2019-12-30"));
            // termDAO.insertTerm(new TermEntity("Term 3", "2019-12-03", "2019-12-29"));
            return null;
        }
    }
}
