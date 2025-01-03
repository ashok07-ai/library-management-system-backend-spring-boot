package com.ashok.library_management_system.payload.response;

public class APIResponse<T> {
    private String message;
    private boolean status;
    private T data;

    public APIResponse(String message, boolean status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public APIResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public APIResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}