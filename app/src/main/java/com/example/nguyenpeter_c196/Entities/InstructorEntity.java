package com.example.nguyenpeter_c196.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "instructors", foreignKeys = @ForeignKey(entity = CourseEntity.class, parentColumns = "courseID", childColumns = "courseID", onDelete = ForeignKey.CASCADE))
public class InstructorEntity {
    @PrimaryKey(autoGenerate = true)
    private int instructorID;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhone;
    private int courseID;

    public InstructorEntity(String instructorName, String instructorEmail, String instructorPhone, int courseID) {
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.courseID = courseID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
