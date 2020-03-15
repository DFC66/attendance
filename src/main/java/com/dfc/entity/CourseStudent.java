package com.dfc.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseStudent {
    private  Student student;

    private  String  faceImg;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }
}
