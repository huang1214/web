package com.aca.springboot.entities;

/**
 * @Author: 周全
 * @Date: 2019/10/10 18:25
 * @Description:
 */
public class Message {
    private Integer code;  //返回状态码
    private String message;  //提示信息
    private Object data;    //返回数据


    public Message() {
    }

    public Message(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
