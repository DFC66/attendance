package com.dfc.entity;

import lombok.Data;

import javax.persistence.*;


import java.io.Serializable;

/**
 * @author: zsh
 * @Date:21:56 2018/5/4
 * @Description: 用户类
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 用户的id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String userName;
    /**
     * 密码
     */
    @Column(name = "password")
    private String passWord;
    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
