package com.example.nguyenpeter_c196.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName="courses", foreignKeys = @ForeignKey(entity = TermEntity.class,parentColumns = "termID", childColumns = "termID", onDelete = ForeignKey.CASCADE))
public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseName;
    private String courseStartDate;
    private String courseEndDate;
    String courseStatus;
    private int termID;

    public CourseEntity(String courseName, String courseStartDate, String courseEndDate, String courseStatus, int termID) {
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.termID = termID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }
}
