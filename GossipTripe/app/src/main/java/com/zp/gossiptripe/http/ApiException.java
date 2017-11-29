package com.zp.gossiptripe.http;

/**
 * Created by Percy on 11-29 0029.
 */
public class ApiException extends RuntimeException {
    private String errorCode;

    public ApiException(String code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
