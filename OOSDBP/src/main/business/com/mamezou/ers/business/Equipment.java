/*
 * Equipment.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

//  パッケージ宣言
package com.mamezou.ers.business;

/**
 * 施設クラス
 * @author Mamezou
 */
public class Equipment {

    /** 施設番号 */
    private int number;

    /** 施設名 */
    private String name;

    /** 定員数 */
    private int capacity;

    /** 内線番号 */
    private String extensionNumber;

    /**
     * コンストラクタ
     * */
    public Equipment() {
    }

    /**
     * コンストラクタ.<br>
     * 指定された値でインスタンスを初期化する。
     *
     * @param number          施設番号
     * @param name            施設名
     * @param capacity        定員数
     * @param extensionNumber 内線番号
     */
    public Equipment(int number, String name, int capacity, String extensionNumber) {
        setNumber(number);
        setName(name);
        setCapacity(capacity);
        setExtensionNumber(extensionNumber);
    }

    /**
     * 施設番号を設定する
     * @param  number 施設番号
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 施設番号を取得する
     * @return 施設番号
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * 施設名を設定する
     * @param name 施設名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 施設名を取得する
     * @return 施設名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 定員数を設定する
     * @param capacity 定員数
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 定員数を取得する
     * @return 定員数
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * 内線番号を設定する
     * @param extensionNumber 内線番号
     */
    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    /**
     * 内線番号を取得する
     * @return 内線番号
     */
    public String getExtensionNumber() {
        return this.extensionNumber;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + capacity;
        result = prime * result
                + ((extensionNumber == null) ? 0 : extensionNumber.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + number;
        return result;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) obj;
        if (capacity != other.capacity) {
            return false;
        }
        if (extensionNumber == null) {
            if (other.extensionNumber != null) {
                return false;
            }
        } else if (!extensionNumber.equals(other.extensionNumber)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (number != other.number) {
            return false;
        }
        return true;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Equipment [capacity=").append(capacity).append(
                ", extensionNumber=").append(extensionNumber).append(", name=")
                .append(name).append(", number=").append(number).append("]");
        return builder.toString();
    }


}
