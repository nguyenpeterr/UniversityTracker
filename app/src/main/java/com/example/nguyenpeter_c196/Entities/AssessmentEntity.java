package com.example.nguyenpeter_c196.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments", foreignKeys = @ForeignKey(entity = CourseEntity.class, parentColumns = "courseID", childColumns = "courseID", onDelete = ForeignKey.CASCADE))
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentName;
    private String assessmentStart;
    private String assessmentEnd;
    private String assessmentType;
    private int courseID;

    public AssessmentEntity(int assessmentID, String assessmentName, String assessmentStart, String assessmentEnd, String assessmentType, int courseID) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
        this.assessmentType = assessmentType;
        this.courseID = courseID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentStart() {
        return assessmentStart;
    }

    public void setAssessmentStart(String assessmentStart) {
        this.assessmentStart = assessmentStart;
    }

    public String getAssessmentEnd() {
        return assessmentEnd;
    }

    public void setAssessmentEnd(String assessmentEnd) {
        this.assessmentEnd = assessmentEnd;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String toString() {
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                ", assessmentName='" + assessmentName + '\'' +
                ", assessmentStart'" + assessmentStart + '\'' +
                ", assessmentEnd'" + assessmentEnd + '\'' +
                ", assessmentType'" + assessmentType + '\'' +
                ", courseID" + courseID + '}';
    }
}
