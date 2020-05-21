/*
 * DataIOException.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.exceptions;

/**
 * 施設予約システムにおける外部記憶との入出力エラー時に、
 * スローされる例外。
 * @author Mamezou
 */
@SuppressWarnings("serial")
public class DataIOException extends Exception {

    /**
     * コンストラクタです。
     */
    public DataIOException() {
        super();
    }

    /**
     * コンストラクタです。
     * @param message message
     * @param cause cause
     */
    public DataIOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタです。
     * @param message message
     */
    public DataIOException(String message) {
        super(message);
    }

    /**
     * コンストラクタです。
     * @param cause cause
     */
    public DataIOException(Throwable cause) {
        super(cause);
    }
}
