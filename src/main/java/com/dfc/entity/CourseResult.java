package com.dfc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zsh
 * @Date:16:13 2018/5/5
 * @Description: 学生信息类
 */

@Entity
@Table(name = "course_result")
@Data
public class CourseResult  {
//    private static final long serialVersionUID=1L;


    public CourseResult() {
    }

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 学生的学号
     */
    @Column(name = "number",length = 32)
    private String number;


    /**
     * 课程数字代码
     */
    @Column(name = "course_code",length = 6)
    private Integer courseCode;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Integer courseCode) {
        this.courseCode = courseCode;
    }

    public CourseResult(String number, Integer courseCode) {
        this.number = number;
        this.courseCode = courseCode;
    }
}
