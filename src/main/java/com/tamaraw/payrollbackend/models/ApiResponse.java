package com.tamaraw.payrollbackend.models;

public class ApiResponse<T> {

    private String message;

    private T body;

    public ApiResponse (String message, T body) {
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
