package com.training.user.service.util;

/**
 * Created by dhaya on 13.12.14.
 */
public class RestServiceException extends RuntimeException {

    public RestServiceException(String message){
        super(message);
    }

    public RestServiceException(String message, Throwable e){
        super(message, e);
    }
}
