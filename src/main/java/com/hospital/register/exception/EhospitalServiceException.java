/**
 * Witontek.com.
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package com.hospital.register.exception;


public class EhospitalServiceException extends RuntimeException {
    /**  */
    private static final long serialVersionUID = 6936242481831254512L;
    
    private String code;
    
    public EhospitalServiceException(String code, String message) {
        super(message);
        this.code = code;
    }
    
    public EhospitalServiceException(String code, String message, Throwable throwable){
        super(message, throwable);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
