package com.ivan.curso.springboot.error.springboot_error.models;

import java.util.Date;

public class Error {

    private String messge;
    private String error;
    private Integer status;
    private Date date;

    
    public Error() {
    }

    public Error(String messge, String error, Integer status, Date date) {
        this.messge = messge;
        this.error = error;
        this.status = status;
        this.date = date;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    
}
