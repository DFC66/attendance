package com.dfc.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: zsh
 * @Date:22:11 2018/5/9
 * @Description:
 */
@Data
@Entity
@Table(name = "signin")
@EntityListeners(AuditingEntityListener.class)
public class Signin {


    /**
     * 签到情况id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 签到人姓名
     */
    @Column(name = "stu_name")
    private String name;

    /**
     * 签到学号
     */
    @Column(name = "number")
    private String number;

    /**
     * 签到班级
     */
    @Column(name = "classes")
    private String classes;


    @Column(name = "course_code")
    private  Integer courseCode;


    /**
     * 课程名
     */
    @Column(name = "course_name")
    private String courseName;


    /**
     * 老师名
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * 签到教室
     */
    @Column(name = "room")
    private String room;
    /**
     * 星期几
     */
    @Column(name = "week_day")
    private String weekDay;




    /**
     * 签到IP
     */
    @Column(name = "ip")
    private String ip;



    /**
     * 签到时间
     */

    @Column(name = "signin_time")
    private String signinTime;



    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "signin_date")
    private String signinDate;



    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "signin_result")
    private  String signinResult;


    public String getSigninResult() {
        return signinResult;
    }

    public void setSigninResult(String signinResult) {
        this.signinResult = signinResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSigninTime() {
        return signinTime;
    }

    public void setSigninTime(String signinTime) {
        this.signinTime = signinTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Integer courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }


    public String getSigninDate() {
        return signinDate;
    }

    public void setSigninDate(String signinDate) {
        this.signinDate = signinDate;
    }
}
