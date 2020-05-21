/*
 * DateUtils.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

//パッケージ宣言
package com.mamezou.commons.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 日付・時間に関するユーティリティメソッドを提供するクラス<br>
 * 全てのメソッドはnullセーフ(引数にnullが渡されても
 * NullPointerExceptionが発生しない)となっています。<br>
 * なお、本クラスの全てのメソッドはクラスメソッドとして提供
 * されるため、インスタンス化できない仕様となっています。
 *
 * @author Mamezou
 */
public class DateUtils {

    /**
     * 2つの期間が重複しているかどうかを判定する。<br>
     * startTerm1からendTerm1で表される期間とstartTerm2からendTerm2で
     * 表される期間において、以下のいずれかが成り立つ場合重複するとみなす。
     * <ul>
     *   <li>2つの期間が一致する</li>
     *   <li>いずれかの期間が他方の期間内に含まれる</li>
     *   <li>いずれかの期間の開始日時が他方の期間に含まれる</li>
     *   <li>いずれかの期間の終了日時が他方の期間に含まれる</li>
     * </ul>
     * なお、いずれかの期間の終了日時と他方の開始日時が等しい場合、重複
     * するとはみなさない。
     * <dl>
     *   <dt>事前条件</dt>
     *   <dd>
     *     期間1および期間2の開始/終了日時には 開始日時≦終了日時 の
     *     関係が成り立っていること。
     *   </dd>
     * </dl>
     * @param startTerm1  期間1の開始日時
     * @param endTerm1    期間1の終了日時
     * @param startTerm2  期間2の開始日時
     * @param endTerm2    期間2の終了日時
     * @return 2つの期間が重複する場合 true。重複しない場合 false。
     */
    public static boolean isOverlappedTerm(LocalDateTime startTerm1, LocalDateTime endTerm1,
            LocalDateTime startTerm2, LocalDateTime endTerm2) {

        // 期間2の終了日時が期間1の開始日時より過去であれば含まれない
        // （期間2が期間1より前に位置している場合）
        if (endTerm2.compareTo(startTerm1) <= 0) {
            return false;
        }

        // 期間2の開始日時が期間1の終了日時がより未来であれば含まれない
        // （期間2が期間1より後に位置している場合）
        if (startTerm2.compareTo(endTerm1) >= 0) {
            return false;
        }

        return true;
    }

    /**
     * 日付を書式「yyyy/MM/dd」の文字列に変換する。
     *
     * @param date ローカル日付
     * @return 変換された文字列
     */
    public static String convertDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return date.format(formatter);
    }

    /**
     * 日時を書式「yyyy/MM/dd HH:mm」の文字列に変換する。
     *
     * @param dateTime 変換対象のローカル日時
     * @return 変換された文字列
     */
    public static String convertDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * 文字列で表された日付をLocalDate型に変換する。<br>
     * 変換フォーマットは「yyyy/M/d」。
     *
     * @param stringDate 日付を表わす文字列
     * @return 変換されたローカル日付
     * @throws DateTimeParseException 日付が正しいフォーマットで入力されない場合
     */
    public static LocalDate convertStringToDate(String stringDate)
            throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        return LocalDate.parse(stringDate, formatter);
    }

    /**
     * 文字列で表された日時をLocalDateTime型に変換する。<br>
     * 変換フォーマットは「yyyy/M/d H:m」。
     *
     * @param dateTimeStr 日時を表わす文字列
     * @return 変換されたローカル日時
     * @throws DateTimeParseException 日時が正しいフォーマットで入力されない場合
     */
    public static LocalDateTime convertStringToDateTime(String dateTimeStr)
            throws DateTimeParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d H:m");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}
