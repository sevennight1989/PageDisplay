package com.zp.gossiptripe.common;

/**
 * Created by Percy on 11-29 0029.
 */

public class Response<T> {
    private String responseCode;
    private String responseMessage;
    private T data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
