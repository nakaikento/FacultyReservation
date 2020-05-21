/*
 * DataValueException.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

//パッケージ宣言
package com.mamezou.ers.exceptions;

/**
 * 施設予約システムのアプリケーションエラー時に、
 * スローされる例外。
 * @author Mamezou
 */
@SuppressWarnings("serial")
public class DataValueException extends Exception {

    /**
     * コンストラクタです。
     */
    public DataValueException() {
        super();
    }

    /**
     * コンストラクタです。
     * @param message message
     * @param cause cause
     */
    public DataValueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタです。
     * @param message message
     */
    public DataValueException(String message) {
        super(message);
    }

    /**
     * コンストラクタです。
     * @param cause cause
     */
    public DataValueException(Throwable cause) {
        super(cause);
    }
}
