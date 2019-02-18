package com.huxin.communication.http;

/**
 * Created by kyosky on 16/3/17.
 */
public class ApiException extends RuntimeException {

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

}
