package com.dfc.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: zsh
 * @Date:21:26 2018/5/9
 * @Description:
 */
@Data
@Entity
@Table(name = "course")
@EntityListeners(AuditingEntityListener.class)
public class Course {
    /**
     * 课程的id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    /**
     * 课程数字代码
     */
    @Column(name = "course_code")
    private Integer courseCode;


    /**
     * 课程图片路径
     */
    @Column(name = "img_path")
    private String imgPath;


    /**
     * 课程的名字
     */
    @Column(name = "name")
    private String name;


    /**
     * 课程老师
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * 课程的开始时间
     */
    @Column(name = "start_time")
    private String startTime;
    /**
     * 课程的结束时间
     */
    @Column(name = "end_time")
    private String endTime;
    /**
     * 课程星期几
     */
    @Column(name = "today")
    private String today;
    /**
     * 上课教室
     */
    @Column(name = "room")
    private String room;
    /**
     * 教室的经度坐标 longitude
     */
    @Column(name = "longitude")
    private String longitude;
    /**
     * dimension 教室的纬度坐标
     */
    @Column(name = "latitude")
    private String latitude;
    /**
     * 上课班级
     */
    @Column(name = "classess")
    private String classess;


    @Column(name = "signin_status")
    private Integer signinStatus;


    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Integer courseCode) {
        this.courseCode = courseCode;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getClassess() {
        return classess;
    }

    public void setClassess(String classess) {
        this.classess = classess;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getSigninStatus() {
        return signinStatus;
    }

    public void setSigninStatus(Integer signinStatus) {
        this.signinStatus = signinStatus;
    }
}
