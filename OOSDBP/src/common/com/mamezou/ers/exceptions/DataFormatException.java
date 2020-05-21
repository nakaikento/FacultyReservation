/*
 * DataValueException.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.exceptions;

/**
 * 施設予約システムの不正データに関するエラー時に、
 * スローされる例外。
 * @author Mamezou
 */
@SuppressWarnings("serial")
public class DataFormatException extends Exception {

    /**
     * コンストラクタです。
     */
    public DataFormatException() {
        super();
    }

    /**
     * コンストラクタです。
     * @param message message
     * @param cause cause
     */
    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタです。
     * @param message message
     */
    public DataFormatException(String message) {
        super(message);
    }

    /**
     * コンストラクタです。
     * @param cause cause
     */
    public DataFormatException(Throwable cause) {
        super(cause);
    }
}
