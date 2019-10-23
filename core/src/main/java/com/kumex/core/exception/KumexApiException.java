/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.exception;

/**
 * Created by zicong.lu on 2018/12/14.
 */
public class KumexApiException extends RuntimeException {

    private static final long serialVersionUID = 8592824166988095909L;

    private String code;

    public KumexApiException(String message) {
        super(message);
    }

    public KumexApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public KumexApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "KumexApiException{"
                + "code='" + getCode() + '\''
                + ", message='" + getMessage() + '\''
                + '}';
    }
}
