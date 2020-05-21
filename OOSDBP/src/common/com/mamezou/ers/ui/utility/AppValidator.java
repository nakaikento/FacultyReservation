/*
 * AppValidator.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

//パッケージ宣言
package com.mamezou.ers.ui.utility;

import java.time.LocalDateTime;

//StringUtilsクラスのインポート
import com.mamezou.commons.utility.StringUtils;
//UserAccountクラスのインポート
import com.mamezou.ers.business.UserAccount;

/**
 * 施設予約システム用の各種検証メソッドを持つクラス。<br>
 * 一部のメソッドは、検証がエラーとなった場合にエラー内容を表す
 * エラーメッセージを標準出力に書き出します。<br>
 * なお、全てのメソッドはクラスメソッドであるため、本クラスは
 * インスタンス化できない仕様となっています。
 */
public class AppValidator {

    /** コンストラクタ */
    private AppValidator() {
        super();
    }

    /**
     * 指定されたユーザアカウントが、管理者かどうかを判定する。<br>
     * 管理者権限を持つユーザアカウントの場合 true が返されます。
     * @param userAccount 判定対象のユーザアカウント
     * @return 管理者の場合true。それ以外の場合false。
     */
    public static boolean isAdmin(UserAccount userAccount) {
        if (userAccount == null) {
            // 判定対象nullの場合、false
            return false;
        }
        return userAccount.getAuthority() == UserAccount.ADMINISTRATOR;
    }

    /**
     * 指定されたユーザアカウントが、予約者かどうかを判定する。<br>
     * 予約者権限を持つユーザアカウントの場合 true が返されます。
     * @param userAccount 判定対象のユーザアカウント
     * @return 予約者の場合true。それ以外の場合false。
     */
    public static boolean isReserveUser(UserAccount userAccount) {
        if (userAccount == null) {
            // 判定対象nullの場合、false
            return false;
        }
        return userAccount.getAuthority() == UserAccount.RESERVER;
    }

    /**
     * 必須入力項目の入力チェックを行い、メッセージを出力します。<br>
     * targetStrがnullまたは空文字列であった場合、エラーメッセージ
     * 「<em>itemName</em>は必須の入力項目です」が標準出力に
     * 表示されます。
     * @param  targetStr 検証対象の文字列
     * @param  itemName   検証対象の項目名
     * @return targetStr が nullではなく、かつ、空文字列でもない場合 true。
     *          それ以外の場合 false。
     */
    public static boolean validateRequiredItem(String targetStr, String itemName) {
        if (!StringUtils.isNotEmpty(targetStr)) {
            UIUtility.printMessage(itemName + "は必須の入力項目です。");
            return false;
        }
        return true;
    }

    /**
     * ログイン名が既定の条件を満たしているかを確認し、エラーメッセージを出力します。<br>
     * loginNameがnullまたは空文字列であった場合、エラーメッセージ
     * 「ログイン名は必須の入力項目です」が標準出力に表示されます。<br>
     * loginNameが5桁以上10桁以下ではない場合、エラーメッセージ
     * 「ログイン名は<em>min</em>桁以上<em>max</em>桁以下で入力して
     * ください。」が標準出力に表示されます。
     * @param  loginName チェック対象のログイン名
     * @return loginNameが条件を満たす場合 true。
     *          loginNameがnullまたは条件を満たさない場合 false
     */
    public static boolean validateLoginName(String loginName) {
        // 必須チェックに引っかかった場合、falseを返す。
        if (!validateRequiredItem(loginName, "ログイン名")) {
            return false;
        }
        // 必須チェックを通った場合、長さチェックを実施する。
        return withinRange(loginName, "ログイン名", 5, 10);
    }

    /**
     * パスワードが既定の条件を満たしているかを確認し、エラーメッセージを出力します。<br>
     * passwordがnullまたは空文字列であった場合、エラーメッセージ
     * 「パスワードは必須の入力項目です」が標準出力に表示されます。<br>
     * passwordが5桁以上10桁以下ではない場合、エラーメッセージ
     * 「パスワードは<em>min</em>桁以上<em>max</em>桁以下で入力して
     * ください。」が標準出力に表示されます。
     * @param  password チェック対象のパスワード
     * @return passwordが条件を満たす場合 true。
     *          passwordがnullまたは条件を満たさない場合 false
     */
    public static boolean validatePassword(String password) {
        // 必須チェックに引っかかった場合、falseを返す。
        if (!validateRequiredItem(password, "パスワード")) {
            return false;
        }
        // 必須チェックを通った場合、長さチェックを実施する。
        return withinRange(password, "パスワード", 5, 10);
    }

    /**
     * 文字列長の範囲を検証し、エラーメッセージを出力します。
     * targetStrの文字列長が指定された範囲内に収まるか検証する。
     * 指定された範囲内に収まらない場合、エラーメッセージ
     * 「<em>itemName</em>は<em>min</em>桁以上<em>max</em>桁以下で入力して
     * ください。」が標準出力に表示されます。
     * @param targetStr ログイン名、またはパスワード
     * @param itemName  項目名
     * @param min       文字列長の最小値
     * @param max       文字列長の最大値
     * @return min ≦ targetStrの長さ ≦ max ならば true。
     *          それ以外の場合、false。
     */
    private static boolean withinRange(
                                String targetStr, String itemName,
                                                int min, int max) {
        // 範囲チェックに引っかかった場合、エラーメッセージを出力し、falseを返す。
        if (!StringUtils.checkRangeOfLength(min, max, targetStr)) {
            UIUtility.printMessage(itemName + "は" + min + "桁以上" + max
                    + "桁以下で入力してください。");
            return false;
        }
        return true;
    }

    /**
     * 内線番号の有効性を検証し、エラーメッセージを出力します。<br>
     * extensionNumberが4桁の数字でない場合、エラーメッセージ
     * 「内線番号は4桁の数値で入力してください。」を標準出力に
     * 表示されます。ただし、extensionNumberが空文字列( "" )である
     * 場合を除きます。
     * @param  extensionNumber 検証対象の内線番号を意味する文字列
     * @return extensionNumberが空文字列または4桁の数字で構成される場合 true。
     *          それ以外の場合 false (extensionNumberがnullである場合も含む)。
     */
    public static boolean validateExtensionNumber(String extensionNumber) {
        // 空文字列である場合はOK
        if (StringUtils.checkLength(0, extensionNumber)) {
            return true;
        }

        //4桁でない場合、エラーメッセージを出力し、falseを返す。
        if (!StringUtils.checkLength(4, extensionNumber)) {
            //エラーメッセージの表示
            UIUtility.printMessage("内線番号は4桁の数値で入力してください。");
            return false;
        }

        //数字のみで構成されていない場合、エラーメッセージを出力し、falseを返す。
        if (!StringUtils.isNumeric(extensionNumber)) {
            //エラーメッセージの表示
            UIUtility.printMessage("内線番号は4桁の数値で入力してください。");
            return false;
        }

        return true;
    }

    /**
     * 未来の日時であるか検証し、エラーメッセージを出力します。<br>
     * 未来の日時ではない場合、エラーメッセージ
     * 「日時は未来の日付を入力してください。」を標準出力に表示されます。
     * @param  dateTime 日時
     * @return dateTimeが未来の日時である場合 true。
     *          dateTimeと等しい、または過去の日時である場合 false。
     */
    public static boolean isFutureDate(LocalDateTime dateTime) {
        // 未来の日付でない場合、エラーメッセージを出力し、falseを返す。
        if (!dateTime.isAfter(LocalDateTime.now())) {
            UIUtility.printMessage("日時は未来の日付を入力してください。");
            return false;
        }
        return true;
    }

    /**
     * 2つのローカル日時が期間を表しているか判定し、エラーメッセージを出力します。<br>
     * startDateTimeがendDateTimeより未来の場合falseを返す。
     * @param startDateTime   開始日時
     * @param endDateTime   終了日時
     * @return startDateTime &lt endDateTime が成り立つ場合 true。それ以外はfalse
     */
    public static boolean isValidTerm(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        // startDateTime < endDateTimeで無い場合、エラーメッセージを出力し、falseを返す。
//        if (!endDateTime.isAfter(startDateTime)) {
        if (!startDateTime.isBefore(endDateTime)) {
            UIUtility.printMessage("正しい開始日時、終了日時を入力してください。");
            return false;
        }
        return true;
    }

    /**
     * 権限(authority)の有効性を検証し、エラーメッセージを出力します。<br>
     * 無効な権限である場合、エラーメッセージ「権限は0または1で入力してください」
     * を標準出力に表示します。
     * 有効な権限および権限を表す値については「設計資料：CSVファイルフォーマット」を
     * 参照のこと。<br>
     * @param authority  検証対象の権限
     * @return authorityがシステム内で有効な権限を表す場合 true、表さない場合 false
     */
    public static boolean validateAuthority(int authority) {
        // authorityが権限を表す値でない場合、エラーメッセージを出力し、falseを返す。
        if (!isAuthority(authority)) {
            UIUtility.printMessage("権限は0または1で入力してください");
            return false;
        }
        return true;
    }

    /**
     * 指定された値が、権限を表す値かどうかを返します。
     * @param authority 検証する権限値
     * @return true(権限を表す値である)/false(権限を表す値でない)
     */
    private static boolean isAuthority(int authority) {
        return authority == UserAccount.ADMINISTRATOR
                    || authority == UserAccount.RESERVER;
    }

    /**
     * 施設の定員数の有効性を検証し、エラーメッセージを出力します。<br>
     * 定員数が無効な値(0以下)である場合、エラーメッセージ「定員は1名以上で入力して
     * ください」を標準出力に表示します。
     * @param capacity 検証対象の定員数
     * @return 有効である場合 true、無効な場合false
     */
    public static boolean validateCapacity(int capacity) {
        // 定員が1名未満である場合、エラーメッセージを出力し、falseを返す。
        if (capacity <= 0) {
            UIUtility.printMessage("定員は1名以上で入力してください");
            return false;
        }
        return true;
    }
}
