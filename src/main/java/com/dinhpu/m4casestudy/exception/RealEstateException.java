package com.dinhpu.m4casestudy.exception;

public class RealEstateException extends RuntimeException{
    public RealEstateException(String message) {
        super(message);
    }

    public RealEstateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RealEstateException(Throwable cause) {
        super(cause);
    }
}
