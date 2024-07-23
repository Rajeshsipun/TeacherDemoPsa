package com.NewSchool.Teacher.Dto;

import javax.xml.crypto.Data;
import java.util.Date;

public class ErrorDetails {

    private String  errorMassage;
    private Date date;
    private String webRequest;

    public ErrorDetails(String errorMassage, Date date, String webRequest) {
        this.errorMassage = errorMassage;
        this.date = date;
        this.webRequest = webRequest;
    }

    public String getErrorMassage() {
        return errorMassage;
    }

    public Date getDate() {
        return date;
    }

    public String getWebRequest() {
        return webRequest;
    }
}


