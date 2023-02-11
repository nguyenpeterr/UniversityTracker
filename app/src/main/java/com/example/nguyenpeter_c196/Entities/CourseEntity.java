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
    String instructor;
    String phone;
    String email;
    String note;
    private int termID;

    public CourseEntity() {
    }

    public CourseEntity(int courseID, String courseName, String courseStartDate, String courseEndDate, String courseStatus, String instructor, String phone, String email, String note, int termID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.instructor = instructor;
        this.phone = phone;
        this.email = email;
        this.note = note;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseStartDate='" + courseStartDate + '\'' +
                ", courseEndDate='" + courseEndDate + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", instructor='" + instructor + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                ", termII=" + termID +
                '}';
    }
}
