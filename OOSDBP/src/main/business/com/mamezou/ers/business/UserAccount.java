/*
 * UserAccount.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.business;

/**
 * ユーザアカウントクラス<br>
 * ユーザアカウントには権限があり、予約者権限と管理者権限に分類される。
 * これらの双方を同時に持つことはできない。
 * @author Mamezou
 */
public class UserAccount {
    //クラス変数の定義
    /** 予約者の権限を表す定数 */
    public static final int RESERVER = 1;

    /** 管理者の権限を表す定数 */
    public static final int ADMINISTRATOR = 0;

    //インスタンス変数の定義
    /** ユーザアカウント番号 */
    private int number;

    /** ログイン名 */
    private String loginName;

    /** パスワード */
    private String password;

    /** ユーザの実名 */
    private String realName;

    /** 内線番号 */
    private String extensionNumber;

    /** 部署名 */
    private String divisionName;

    /** 権限 */
    private int authority;

    /**
     * コンストラクタ
     */
    public UserAccount() {
        this(0, "", "", "", "", "", RESERVER);
    }

    /**
     * コンストラクタ<br>
     * 引数で指定された値を、インスタンスがもつ初期値として設定する。
     * @param number           ユーザアカウント番号の初期値
     * @param loginName        ログイン名の初期値
     * @param password         パスワードの初期値
     * @param realName         ユーザの実名の初期値
     * @param extensionNumber  内線番号の初期値
     * @param divisionName     部署名の初期値
     * @param authority        権限の初期値
     */
    public UserAccount(int number, String loginName, String password, String realName,
            String extensionNumber, String divisionName, int authority) {

        setNumber(number);
        setLoginName(loginName);
        setPassword(password);
        setRealName(realName);
        setExtensionNumber(extensionNumber);
        setDivisionName(divisionName);
        setAuthority(authority);
    }

    /**
     * ユーザアカウント番号を設定する
     * @param number ユーザアカウント番号
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * ユーザアカウント番号を取得する
     * @return ユーザアカウント番号
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * ログイン名を設定する
     * @param loginName ログイン名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * ログイン名を取得する
     * @return ログイン名
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * パスワードを設定する
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * パスワードを取得する
     * @return パスワード
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * ユーザアカウントの実名を設定する
     * @param realName ユーザアカウントの実名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * ユーザアカウントの実名を取得する
     * @return ユーザアカウントの実名
     */
    public String getRealName() {
        return this.realName;
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

    /**
     * 部署名を設定する
     * @param divisionName 部署名
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * 部署名を取得する
     * @return 部署名
     */
    public String getDivisionName() {
        return this.divisionName;
    }

    /**
     * 権限を設定する
     * @param authority 権限
     */
    public void setAuthority(int authority) {
        this.authority = authority;
    }

    /**
     * 権限を取得する
     * @return 権限
     */
    public int getAuthority() {
        return this.authority;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + authority;
        result = prime * result
                + ((divisionName == null) ? 0 : divisionName.hashCode());
        result = prime * result
                + ((extensionNumber == null) ? 0 : extensionNumber.hashCode());
        result = prime * result
                + ((loginName == null) ? 0 : loginName.hashCode());
        result = prime * result + number;
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        result = prime * result
                + ((realName == null) ? 0 : realName.hashCode());
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
        if (!(obj instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) obj;
        if (authority != other.authority) {
            return false;
        }
        if (divisionName == null) {
            if (other.divisionName != null) {
                return false;
            }
        } else if (!divisionName.equals(other.divisionName)) {
            return false;
        }
        if (extensionNumber == null) {
            if (other.extensionNumber != null) {
                return false;
            }
        } else if (!extensionNumber.equals(other.extensionNumber)) {
            return false;
        }
        if (loginName == null) {
            if (other.loginName != null) {
                return false;
            }
        } else if (!loginName.equals(other.loginName)) {
            return false;
        }
        if (number != other.number) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (realName == null) {
            if (other.realName != null) {
                return false;
            }
        } else if (!realName.equals(other.realName)) {
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
        builder.append("UserAccount [authority=").append(authority).append(
                ", divisionName=").append(divisionName).append(
                ", extensionNumber=").append(extensionNumber).append(
                ", loginName=").append(loginName).append(", number=").append(
                number).append(", password=").append(password).append(
                ", realName=").append(realName).append("]");
        return builder.toString();
    }
}
