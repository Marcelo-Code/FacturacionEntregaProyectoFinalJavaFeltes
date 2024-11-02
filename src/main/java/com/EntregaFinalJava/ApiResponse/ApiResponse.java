package com.EntregaFinalJava.ApiResponse;

public class ApiResponse<T> {
    private String messaString;
    private T data;

    public ApiResponse(String messaString, T data) {
        this.messaString = messaString;
        this.data = data;
    }

    public String getMessaString() {
        return messaString;
    }

    public Object getData() {
        return data;
    }
}
