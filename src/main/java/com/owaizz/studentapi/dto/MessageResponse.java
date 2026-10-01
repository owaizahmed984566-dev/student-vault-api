package com.owaizz.studentapi.dto;

import org.springframework.context.annotation.Primary;

public class MessageResponse {

    private String message;

    public MessageResponse(String message){

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
