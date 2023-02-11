package com.example.nguyenpeter_c196.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.Database;
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

@androidx.room.Database(entities = {AssessmentEntity.class, CourseEntity.class, InstructorEntity.class, NoteEntity.class, TermEntity.class}, version = 3, exportSchema = false)
public abstract class DatabaseBuilder extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    public abstract InstructorDAO instructorDAO();
    public abstract NoteDAO noteDAO();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabaseInstance(final Context context) {
        if(INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "C196.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}
