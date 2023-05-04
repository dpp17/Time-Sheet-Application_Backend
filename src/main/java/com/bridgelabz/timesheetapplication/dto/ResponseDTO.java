package com.bridgelabz.timesheetapplication.dto;

import lombok.Data;

@Data
public class ResponseDTO {

//    private int errorCode;
    private String message;
    private Object data;


    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
