/*
 * ReservationMaster.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.business;

// com.mamezou.ers.dataaccessパッケージのインポート
//     ReservationDataAccessor
import com.mamezou.ers.dataaccess.ReservationDataAccessor;

/**
 * 予約管理クラス<br>
 *
 * 予約データと予約インスタンスを管理するクラス。
 * 予約の登録と検索に関するメソッドを持つ。
 *
 * @author Mamezou
 */
public class ReservationMaster {

    /** 予約情報アクセスクラスのインスタンスを保持する変数 */
    private ReservationDataAccessor reservationDataAccessor;

    /**
     * コンストラクタ。
     * 属性reserveDataAccessorを初期化する。
     * */
    public ReservationMaster() {
        reservationDataAccessor = new ReservationDataAccessor();
    }

    /**
     * コンストラクタ。
     *
     * @param dataAccessor 使用するReservationDataAccessor
     */
    public ReservationMaster(ReservationDataAccessor dataAccessor) {
        reservationDataAccessor = dataAccessor;
    }

    /**
     * String[]型として表される予約情報をReservation型に変換する。<br>
     *
     * @param reservationData 変換対象となる予約情報
     * @return 引数の予約情報と同じデータをもつ予約インスタンス
     * @throws DataFormatException 指定された予約情報に不正なデータが含まれている場合。
     */
/* UC02で実装します
    private Reservation convertToReservation(String[] reservationData)
            ■■■ DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・日時の変換にはDateUtilsクラスのクラスメソッドを用いる。
        // ・配列要素へのアクセスにおいてArrayIndexOutOfBoundsException がスローされる可能性がある。
        // ・文字列から数値への変換においてNumberFormatException がスローされる可能性がある。
        // ・文字列からローカル日時に変換においてDateTimeParseException がスローされる可能性がある。
        //======================================================================

        try {
            // 取得した予約情報から予約番号を取り出し、数値に変換する。
            int reservationNumber = Integer.■■■■(reservationData[0]);

            // 取得した予約情報から利用開始日時を取り出し、LocalDateTime型に変換する。
            LocalDateTime startDateTime = DateUtils.■■■■■■■■■■■■(■■■■■■■■■);

            // 取得した予約情報から利用終了日時を取り出し、LocalDateTime型に変換する。
            LocalDateTime endDateTime = DateUtils.■■■■■■■■■■■■(■■■■■■■■■);

            // 取得した予約情報から、利用目的を取り出す。
            String purpose = reservationData[3];

            // 取得した予約情報から施設番号を取り出し、数値に変換する。
            int equipmentNumber = Integer.■■■■(reservationData[4]);

            // 取得した予約情報から予約者のユーザアカウント番号を取り出し、
            // 数値に変換する。
            int userAccountNumber = Integer.■■■■(reservationData[5]);

            // 取り出した予約の各情報をもとに、予約インスタンスを生成する
            Reservation foundReservation
                            = new Reservation(■■■■■■■■■,
                                                ■■■■■■■, ■■■■■■,
                                                ■■■■, ■■■■■■■■, ■■■■■■■■■);
            return foundReservation;

        } catch (ArrayIndexOutOfBoundsException e) {
            // ArrayIndexOutOfBoundsExceptionをキャッチし、
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new DataFormatException("■■■■■■■■■■■■■■■■■", e);

        } catch (NumberFormatException e) {
            // NumberFormatExceptionをキャッチし、
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new DataFormatException(
                      "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■"
                    + "■■■■■■■■■■■■■", e);

        } catch (DateTimeParseException e) {
            // DateTimeParseExceptionをキャッチし、
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new DataFormatException(
                      "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■"
                    + "■■■■■■■■■■■■■", e);
        }
    }
*/

    /**
     * 指定された検索条件を満たす予約を検索する。<br>
     * 1つの施設(検索条件の施設番号)の特定の日付(検索条件の日付)に
     * おける予約状況を検索し、そのリストを返します。
     * 言い換えると、検索条件の施設番号と一致し、かつ、検索条件の日付が予約の
     * 利用日(利用開始日時の日付)と一致する予約のリストを返します。
     *
     * @param targetEquipmentNumber  検索条件の施設番号
     * @param targetDate             検索対象の日付
     * @return 検索条件を満たす予約のリスト。要素の型はReservation型。
     *          検索条件を満たす予約がない場合、空(要素数0)のリスト。
     * @throws DataIOException 予約管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException 予約管理ファイルのデータに問題がある場合
     */
/* UC02で実装します
    public List<Reservation> findReservation(int targetEquipmentNumber, LocalDate targetDate)
            throws DataIOException, DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・予約情報アクセスクラスを用いて取得した予約情報はString[]型であるため、
        //   Reservation型に変換する必要がある。
        // ・Listに対して、繰り返しを行う際は、拡張for文を用いる。
        //======================================================================

        // 予約情報操作クラスを使い、引数の施設番号と検索対象の日付に合致する予約情報をListで取得する。
        List<String[]> reservationDataList = ■■■■■■■■■■■.■■■■■■■■(
                                                    targetEquipmentNumber, targetDate);

        // 検索した予約インスタンスを保持するためのListを用意する。
        List<Reservation> foundReservationList = new ArrayList<Reservation>();

        // 取得した全ての予約情報から、予約インスタンスを生成する。
        // 生成した予約インスタンスの参照は、用意したArrayListに格納する。
        for (String[] reservationData : reservationDataList) {

            Reservation foundReservation = ■■■■■■■■■■■■■■■■■■■;
            // 予約インスタンスの参照を追加
            foundReservationList.■■(foundReservation);
        }

        return foundReservationList;
    }
*/

    /**
     * 指定された検索条件を満たす予約を検索する。<br>
     * 検索条件の施設番号と一致し、かつ、その利用時間帯(利用開始日時～利用終了
     * 日時)が重なっている予約を検索します。
     * 予約インスタンスの参照を返す。
     *
     * @param targetEquipmentNumber  検索条件の施設番号
     * @param targetStartDateTime    検索条件の利用開始日時
     * @param targetEndDateTime      検索条件の利用終了日時
     * @return 検索条件を満たす予約インスタンス。検索条件を満たす予約がない場合 null。
     * @throws DataIOException 予約管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException 予約管理ファイルのデータに問題がある場合
     */
/* UC03で実装します
    public Reservation findReservation(int targetEquipmentNumber,
            LocalDateTime targetStartDateTime, LocalDateTime targetEndDateTime)
            throws DataIOException, DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・予約情報アクセスクラスを用いて取得した予約情報はString[]型であるため、Reservation型に
        //  変換する必要がある。
        //======================================================================

        // 検索条件(施設番号の一致、利用時間帯が重なる)を満たす予約情報を、予約情報管理クラスを使って
        // String型の配列で取得する。
        ■■■■ reservationData = ■■■■■■■■■■■■■■■■■■■■(
                ■■■■■■■■■■, ■■■■■■■■■■, ■■■■■■■■■);

        if (reservationData != null) {
            // 予約データが取得できた場合の処理
            ■■■■■ ■■■■■■■■■ = ■■■■■■■■■■■■■■■■■■■;
            return foundReservation;

        } else {
            ■■■■■■;
        }
    }
*/

    /**
     * 指定された検索条件を満たす予約を検索する。<br>
     * 検索条件の予約番号に一致する予約を検索します。
     *
     * @param targetReservationNumber  検索条件の予約番号
     * @return 検索条件を満たす予約インスタンス。
     *          検索条件を満たす予約がない場合 null。
     * @throws DataIOException 予約管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException 予約管理ファイルのデータに問題がある場合
     */
/* UC03で実装します
    public Reservation findReservation(int targetReservationNumber)
            throws DataIOException, DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・予約情報アクセスクラスを用いて取得した予約情報はString[]型であるため、Reservation型に
        //  変換する必要がある。
        //======================================================================

        // 検索条件(予約番号が一致する)を満たす予約情報を、予約情報管理クラスを使ってString型の配列で
        // 取得する。
        String[] reservationData
                    = ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■;

        if (■■■■■■■■■■■■) {
            // 予約情報が取得できた場合の処理
            // String[]で表される予約情報を、Reservationのインスタンスに変換する
            ■■■■■■ foundReservation = ■■■■■■■■■■■■■■■■■■■;

            // 変換されたReservationインスタンスを返す。
            return foundReservation;

        } else {
            // 予約情報が取得できなかった場合の処理
            return null;
        }

    }
*/

    /**
     * 予約を登録する。<br>
     * 引数の予約者のユーザアカウント番号、施設番号、利用開始日時、利用終了日時、
     * 利用目的で予約情報を登録し、登録した予約を表す予約インスタンスを返す。
     *
     * @param userAccountNumber 予約者のユーザアカウント番号
     * @param equipmentNumber   予約する施設の施設番号
     * @param startDateTime     予約する施設の利用開始日時
     * @param endDateTime       予約する施設の利用終了日時
     * @param purpose           予約する施設の利用目的
     * @return                  登録した予約を表す予約インスタンス
     * @throws DataFormatException 予約管理ファイルの形式に問題がある場合
     * @throws DataIOException  予約管理ファイルの書き込みに失敗した場合
     */
/* UC03で実装します
    public Reservation addReservation(int userAccountNumber, int equipmentNumber,
                                                LocalDateTime startDateTime, LocalDateTime endDateTime,
                                                String purpose)
                                            throws DataFormatException, DataIOException {

        //======================================================================
        // 実装のヒント!
        // ・予約情報アクセスクラスへ渡す予約情報はString型の配列である。
        // ・LocalDateTimeからStringへの変換にはDateUtilsクラスを用いて、
        //   "yyyy/MM/dd HH:mm"形式の文字列に変換する。
        //======================================================================

        // 予約情報操作クラスを使って、予約番号を取得する
        int newReservationNumber = ■■■■■■■■■■■■■■■■■■;

        // 予約情報を保持するためのString型の配列を用意する
        String[] newReservationData = new String[6];

        // 取得した予約情報から予約番号を文字列に変換し、配列に入れる
        newReservationData[0] = String.valueOf(newReservationNumber);

        // 引数の利用開始日時をString型に変換し、配列に入れる
        newReservationData[1] = ■■■■■■■■■■■■■■■■■■■■■■■■;

        // 引数の利用終了日時をString型に変換し、配列に入れる
        newReservationData[2] = ■■■■■■■■■■■■■■■■■■■■■■■

        // 引数の利用目的を配列に入れる
        newReservationData[3] = ■■■■;

        // 引数の施設番号を文字列に変換し、配列に入れる
        newReservationData[4] = ■■■■■■■■■■■■■■■■;

        // 引数のユーザアカウント番号をString型に変換し、配列に入れる
        newReservationData[5] = ■■■■■■■■■■■■■■■■■;

        // 予約データの保存
        ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■;

        return ■■■■■■■■■■■■■■■■■■■;
    }
*/
}
