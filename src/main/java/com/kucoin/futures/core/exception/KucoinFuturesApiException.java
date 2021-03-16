/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.exception;

/**
 * Created by zicong.lu on 2018/12/14.
 */
public class KucoinFuturesApiException extends RuntimeException {

    private static final long serialVersionUID = 8592824166988095909L;

    private String code;

    public KucoinFuturesApiException(String message) {
        super(message);
    }

    public KucoinFuturesApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public KucoinFuturesApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "KucoinFuturesApiException{"
                + "code='" + getCode() + '\''
                + ", message='" + getMessage() + '\''
                + '}';
    }
}
