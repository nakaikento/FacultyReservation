/*
 * UserReservationEnquiryUI.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.ui;

// java.timeパッケージのインポート
// LocalDate, LocalDateTime (日時関連)


// java.time.formatパッケージのインポート
// DateTimeParseException (日時関連)

// java.utilパッケージのインポート
// List (コレクション関連)

// com.mamezou.commons.utilityパッケージのインポート
//     DateUtils(日付関連)

// com.mamezou.ers.businessパッケージのインポート
//     Equipment, EquipmentReservationSystem, Reservation, UserAccount
import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.business.UserAccount;

// com.mamezou.ers.exceptionsパッケージのインポート
//     DataFormatException, DataIOException, DataValueException

// com.mamezou.ers.ui.utilityパッケージのインポート
//     UIUtility

/**
 * 予約照会画面
 * @author Mamezou
 */
public class UserReservationEnquiryUI {

    /** 施設予約システムクラスのインスタンスへの参照 */
    private EquipmentReservationSystem equipReservationSystem;

    /**
     * コンストラクタです。
     * @param system EquipmentReservationSystem
     */
    public UserReservationEnquiryUI(EquipmentReservationSystem system) {
        // EquipmentReservationSystemインスタンスの生成
        equipReservationSystem = system;
    }

    /**
     * 予約照会画面、および予約一覧画面間の遷移をコントロールする<br>
     * 予約対象の施設一覧を表示し、新規予約を受け付けます。
     * @param loginUser 認証済みのユーザアカウント
     */
    public void findReservation(UserAccount loginUser) {
/* UC02で実装します
        //=====================================================================
        // 実装のヒント!
        // ・利用するメソッドで、どういった例外がスローされるのかを確認し、キャッチする。
        // ・Listに対して繰り返し処理を行う際は、拡張for文を用いる。
        // ・日付を文字列からLocalDate型へ変換する場合、DateUtilsクラスを用いる
        // ・閲覧する予約の条件として入力された施設番号と日付は、予約一覧画面で表示するので、
        //  変数の有効範囲に注意する。
        //=====================================================================

        // 引数の予約者のユーザアカウントより、名前を取得する。
        String realName = loginUser.getRealName();

        // 検索結果の予約の一覧を格納するListを用意する。
        List<Reservation> reservationList = null;

        // 入力される施設番号保持する変数を初期化する。
        int inputEquipmentNumber = 0;

        // 入力される日付を保持する変数を初期化する。
        String inputDate = null;

        // 正しく入力されるまで繰り返す。
        while (true) {
            try {
                ■■■■■■■■■■■■■■■■;

                try {
                    // 施設番号の入力を促し、入力された施設番号を取得する。
                    inputEquipmentNumber = UIUtility.■■■■■■■■■■■■■;

                    if (inputEquipmentNumber == -1) {
                        // メソッドを抜ける。
                        ■■■;
                    }

                } catch (NumberFormatException e) {
                    // NumberFormatExceptionの捕捉
                    // 該当するエラーメッセージの表示
                    UIUtility.■■■■■■■■■■■■■■■■■■■■■■■■;

                    // 以降の処理は行わずに、ループの最初に戻る。
                    ■■■■
                }

                // 閲覧する予約の日付の入力を促し、入力された日付を取得する。
                inputDate = UIUtility.■■■■■■■■■■■■■■■■■■■■■■■;

                // 改行
                System.out.println();

                //===============================================
                //  入力された日付(String)をLocalDateに変換する
                //===============================================

                // 閲覧する予約の日付を保持する変数を初期化
                LocalDate formatedInputDate = null;

                try {
                    // 文字列として入力された利用開始日時をDate型に
                    // 変換する。文字列の書式は "YYYY/MM/DD" とする。
                    formatedInputDate = DateUtils.■■■■■■■■■■■■■■■;

                } catch (DateTimeParseException e) {
                    // DateTimeParseExceptionの捕捉
                    // 該当するエラーメッセージの表示
                    UIUtility.■■■■■■■■■■■■■■■■■■■■■■■■■■;

                    // 日付のフォーマットが不正な場合は、以降の処理は
                    // 行わずにループの最初に戻る。
                    continue;
                }

                //==============================================================
                // 入力された予約番号、利用開始日付から、登録されている予約で重複するデータを検索する。
                // 検索された結果を予約一覧に入れる。
                //==============================================================
                // 施設予約システムを使って、予約状況を検索し、Listで取得する。
                reservationList = ■■■■■■■■■■■■■■■■■■■(
                        inputEquipmentNumber, formatedInputDate);

                // ループを抜ける。
                ■■■;

            } catch (DataValueException e) {
                // DataValueExceptionの捕捉
                ■■■■■■■■■■■■■■■■■■■;
            } catch (DataFormatException e) {
                // DataFormatExceptionの捕捉
                ■■■■■■■■■■■■■■■■■■■;
            } catch (DataIOException e) {
                // DataIOExceptionの捕捉
                ■■■■■■■■■■■■■■■■■■■;
            }

            // 改行
            System.out.println();
        }

        //
        // 予約一覧画面を表示する。
        //
        ■■■■■■■■■■(
                ■■■■■■, ■■■■■■■■, ■■■■■■■■■■, ■■■■■);
*/
    }

    /**
     * 予約照会画面を表示する
     * @param realName
     */
/* UC02で実装します
    private void showEquipmentList(String realName) {

        //=====================================================================
        // 実装のヒント!
        // ・利用するメソッドで、どういった例外がスローされるのかを確認し、キャッチする。
        // ・Listに対して、繰り返しを行う際は、拡張for文を用いる。
        //=====================================================================

        // 予約照会画面のヘッダーを表示する。
        UIUtility.■■■■■■■■■■■■■■■■■■■■■■■■;

        // 予約照会画面の入力説明メッセージを表示する。
        UIUtility.printMessage("施設番号と日付を入力してください。\r\n"
                + "予約者用メニュー画面へ戻る場合は、-1を入力してください。");

        try {
            // 登録済の全施設をListとして取得する。
            //  [ヒント] EquipmentReservationSystemインスタンスを使用する。
            ■■■■■■■■ equipments = ■■■■■■■■■■■■■■■■■■■■■;

            // 取得した施設の件数分繰り返す。
            for (■■■■■ equipment : equipments) {

                // 施設インスタンスから施設番号を取得する。
                int equipmentNumber = ■■■■■■■■■■■

                // 施設インスタンスから施設名を取得する。
                String equipmentName = ■■■■■■■■■■;

                // 施設インスタンスから定員を取得する。
                int capacity = ■■■■■■■■■■■■;

                // 施設インスタンスから内線番号を取得する。
                String extensionNumber = ■■■■■■■■■■■■■■■;

                // 取得した施設情報を表示する。
                System.out.println("[" + equipmentNumber + "]" + equipmentName
                        + " 定員：" + capacity + " 内線番号：" + extensionNumber);
            }

            // 改行
            System.out.println();

        } catch (DataFormatException e) {
            // DataFormatExceptionの捕捉
            ■■■■■■■■■■■■■■■■■■■;

        } catch (DataIOException e) {
            // DataIOExceptionの捕捉
            ■■■■■■■■■■■■■■■■■■■;
        }
    }
*/

    /**
     * 予約一覧画面を表示する
     * @param realName ユーザの実名
     * @param reserves 予約を照会し、合致した予約のリスト
     * @param equipmentNumber 照会時の条件として指定した施設番号
     * @param dateOfUse 照会時の条件として指定した利用日
     */
/* UC02で実装します
    private void showReservationList(String realName, List<Reservation> reserves,
            int equipmentNumber, String dateOfUse) {
        //============================================================
        // 予約照会で検索できた結果を一覧表示する。
        //============================================================

        // 予約一覧画面のヘッダーを出力する
        UIUtility.showHeader("予約一覧画面", realName, true);

        // 改行
        System.out.println();

        // 入力した施設番号を表示する。
        System.out.println(■■■■■■■■■■■■■■■);

        // 入力した日付の表示
        System.out.println(■■■■■■■■■■);

        // 予約情報の一覧を取得した予約の件数分繰り返す。
        for (■■■■■■ foundReservation : reserves) {

            // 予約インスタンスから、予約番号を取得する。
            int reservationNumber = foundReservation.■■■■■■;

            // 予約インスタンスから、利用開始日時を取得する。
            LocalDateTime startDateTime = foundReservation.■■■■■■■;

            // 利用開始日時をLocalDateTime型から、String型へ変換する。
            String startDateTimeStr = DateUtils.■■■■■■■■■■■■■■■■■;

            // 予約インスタンスから、利用終了日時を取得する。
            LocalDateTime endDateTime = foundReservation.■■■■■■;

            // 利用終了日時をLocalDateTime型から、String型へ変換する。
            String endDateTimeStr = DateUtils.■■■■■■■■■■■■■■■■;

            // 予約インスタンスから、利用目的を取得する。
            String purpose = foundReservation.■■■■■■;

            // 予約インスタンスから、施設インスタンスを取得する。
            Equipment equipment = foundReservation.■■■■■■■;

            // 施設インスタンスから、施設名を取得する。
            String equipmentName = equipment.■■■■■;

            // 予約インスタンスから、予約者のユーザアカウントを取得する。
            UserAccount subscriber = foundReservation.■■■■■■■■;

            // ユーザアカウントインスタンスから、予約者の実名を取得する。
            String realNameOfSubscriber = subscriber.■■■■■■■;

            // ユーザアカウントインスタンスから、予約者の部署名を取得する。
            String divisionName = subscriber.■■■■■■■■■;

            // ユーザアカウントインスタンスから、予約者の内線番号を取得する。
            String extensionNumber = subscriber.■■■■■■■■■■;

            // 検索できた予約の情報を表示する。
            System.out.println("[" + reservationNumber + "] " + equipmentName
                    + " " + startDateTimeStr + " " + endDateTimeStr + " "
                    + realNameOfSubscriber + " " + divisionName + " " + " 内線"
                    + extensionNumber + " " + purpose);
        }

        // 改行
        System.out.println();

        // エンターキーの入力を促し、エンターキーが押されるまで待ち受ける。
        UIUtility.getInputAsString("エンターキーを入力してください。");
    }
*/
}
