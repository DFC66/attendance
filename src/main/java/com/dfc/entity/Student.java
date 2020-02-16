package com.dfc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zsh
 * @Date:16:13 2018/5/5
 * @Description: 学生信息类
 */
@Data
@Entity
@Table(name = "student")
public class Student  implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 学生的id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 学生的学号
     */
    @Column(name = "number")
    @JsonProperty(value = "number")
    private String number;
    /**
     * 学生的班级
     */
    @Column(name = "classes")
    @JsonProperty(value = "classes")
    private String classes;
    /**
     * 学生的姓名
     */
    @Column(name = "name")
    @JsonProperty(value = "name")
    private String name;

    @Column(name = "openid")
    @JsonProperty(value = "openid")
    private String openid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
