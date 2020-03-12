package com.dfc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: zsh
 * @Date:16:13 2018/5/5
 * @Description: 学生信息类
 */

@Entity
@Table(name = "course_result")
@Data
@EntityListeners(AuditingEntityListener.class)
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


    @Column(name = "is_deleted")
    private Integer isDeleted;



    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;


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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
