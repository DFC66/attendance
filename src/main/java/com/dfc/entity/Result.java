package com.dfc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: zsh
 * @Date:8:17 2018/5/12
 * @Description:
 */
@Data
public class Result<T>  implements Serializable {
    private static final long serialVersionUID = -6957361951748382519L;
    private int code;
    private String msg;
    private String url;
    private T message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
