package com.example.nguyenpeter_c196.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.nguyenpeter_c196.Entities.AssessmentEntity;
import com.example.nguyenpeter_c196.Entities.CourseEntity;
import com.example.nguyenpeter_c196.Entities.InstructorEntity;
import com.example.nguyenpeter_c196.Entities.NoteEntity;
import com.example.nguyenpeter_c196.Entities.TermEntity;

@androidx.room.Database(entities = {AssessmentEntity.class, CourseEntity.class, InstructorEntity.class, NoteEntity.class, TermEntity.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static final String DATABASE_NAME = "C196.db";
    private static volatile Database databaseInstance;
    private static final Object LOCK = new Object();


}
