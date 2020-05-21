/*
 * UserMakingReservationUI.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.ui;

import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.business.UserAccount;

/**
 * 施設予約画面
 * @author Mamezou
 */
public class UserMakingReservationUI {

    /** 施設予約システムクラスのインスタンスへの参照 */
    private EquipmentReservationSystem equipReservationSystem;

    /**
     * コンストラクタです。
     * @param system EquipmentReservationSystem
     */
    public UserMakingReservationUI(EquipmentReservationSystem system) {
        // EquipmentReservationSystemインスタンスの生成
        equipReservationSystem = system;
    }

    /**
     * 施設予約画面を表示し、予約登録を行う。<br>
     * 施設を予約するための予約情報の入力を取得し、
     * 入力された予約情報で予約を登録する。<br>
     * @param loginUser ログイン中の予約者を表すユーザアカウント
     */
    public void reserveEquipment(UserAccount loginUser) {
/* UC03で実装します
        //=====================================================================
        // 実装のヒント!
        // ・利用するメソッドで、どういった例外がスローされるのかを確認し、キャッチする。
        // ・利用開始日時、利用終了日時はDate型に変換する。
        //   変換には、UIUtility.convertStringToDateTime を使用する。
        // ・エラーメッセージを表示する時は、UIUtility.printMessage を使用する。
        //=====================================================================

        // 引数の予約者のユーザアカウントから、ユーザの実名を取得する。
        String loginUserName = ■■■■■■■■■■■■;

        // 正しく入力されるまで繰り返す。
        while (■■) {
            try {
                // 施設予約画面のヘッダーを表示する。
                UIUtility.showHeader("施設予約画面", loginUserName, true);

                // 施設予約を促す説明メッセージを表示する。
                String msg = "施設番号、利用開始日時、利用終了日時、利用目的(空白可)を入力してください。\r\n"
                           + "予約者用メニュー画面へ戻る場合は、-1を入力してください。";
                UIUtility.printMessage(msg);

                // 施設一覧を表示するメソッドを呼び出す。
                ■■■■■■■■■■;

                //============================================================
                //  施設予約の項目の入力を促す画面メッセージを表示し、
                //  入力値を取得する。
                //============================================================

                // 入力される施設番号を初期化する
                int inputEquipmentNumber = 0;

                ■■ {
                    // 施設番号の入力を促し、入力された施設番号を取得する。
                    inputEquipmentNumber = ■■■■■■■■■■■■■■■■■■■;

                    // 施設番号に-1が入力された場合、予約者サービスメニュー画面へ戻る
                    if (inputEquipmentNumber == -1) {
                        // ループを抜ける
                        ■■■;
                    }

                } ■■■ (NumberFormatException e) {
                    // NumberFormatExceptionの捕捉
                    // 該当するエラーメッセージの表示
                    UIUtility.printMessage("■■■■■■■■■■■■■■■■■");

                    // 以降の処理は行わずに、ループの最初に戻る。
                    ■■■■■;
                }

                // 予約する利用開始日時を保持する変数を初期化する。
                LocalDateTime startDateTime = null;

                // 予約する利用終了日時を保持する変数を初期化する。
                LocalDateTime endDateTime = null;

                ■■ {
                    // 利用開始日時の入力を促し、入力された利用開始日時を取得する
                    String inputStartDateTime = ■■■■■■■■■■■■■(
                                                    "■■■■■■■■■■■■■■■■■■■■■");

                    // 入力された利用開始日時(文字列)をDate型オブジェクトに変換する
                    startDateTime = ■■■■■■■■■■■■■■■■(■■■■■■■■■);

                    // 利用終了日時の入力を促し、入力された利用終了日時を取得する
                    String inputEndDateTime = ■■■■■■■■■■■■■(
                                                    "■■■■■■■■■■■■■■■■■■■■■");

                    // 入力された利用終了日時(文字列)をDate型オブジェクトに変換する
                    endDateTime = ■■■■■■■■■■■■■■■■(■■■■■■■■);

                } ■■■ (DateTimeParseException e) {
                    // DateTimeParseExceptionの捕捉
                    // 該当するエラーメッセージの表示
                    UIUtility.printMessage("■■■■■■■■■■■■■■■■■■■■■");

                    // 日時のフォーマットが不正な場合は、以降の処理は行わずにループの最初に戻る。
                    ■■■■■;
                }

                // 利用目的の入力を促し、入力された利用目的を取得する
                String inputPurpose = ■■■■■■■■■■■■■■■■■■■■■■■■■;

                // 改行
                System.out.println();

                //============================================================
                //  入力値の有効性を検証する
                //============================================================

                // 利用開始日時が未来の日時であるかをチェックする。
                if (■■■■■■■■■■■■■■■■■■■■■) {
                    // 利用開始日時が未来の日時でない場合は、ループの最初に戻る。
                    ■■■■■;
                }

                // 利用終了日時が未来の日時であるかをチェック
                if (■■■■■■■■■■■■■■■■■■■■) {
                    // 利用終了日時が未来の日時でない場合は、ループの最初に戻る。
                    ■■■■■;
                }

                // 利用開始日時と利用終了日時の大小関係チェック
                if (■■■■■■■■■■■■■■■■■■■■■■■■■■■) {
                    // 利用開始日時が、利用終了日時より未来の場合は、ループの最初に戻る。
                    ■■■■■;
                }

                // 改行
                System.out.println();

                //============================================================
                //  入力された情報で、施設を予約する。
                //============================================================

                // 施設予約システムクラスを使って施設を予約し、予約インスタンスを取得する。
                ■■■■■■ newReservation = ■■■■■■■■■■■■■■■■■■■(
                                                ■■■■■■■■■■,
                                                ■■■■■■■, ■■■■■■, ■■■■■■, ■■■■■);

                // 施設予約の完了画面を表示する。
                ■■■■■■■■■■■■■■■■■■;

                // ループを抜ける
                break;

            } catch (■■■■■■■■■■) {
                // DataValueExceptionの捕捉
                // DataValueExceptionのエラーメッセージの表示
                UIUtility.printMessage(■■■■■■■);

            } catch (■■■■■■■■■■) {
                // DataFormatExceptionの捕捉
                // DataFormatExceptionのエラーメッセージの表示
                UIUtility.printMessage(■■■■■■■);

            } catch (■■■■■■■■■■) {
                // DataIOExceptionの捕捉
                // DataIOExceptionのエラーメッセージの表示
                UIUtility.printMessage(■■■■■■■);
            }
        }
*/
    }

    /**
     * 予約完了画面を表示する
     * @param reservation 新規に登録された予約
     */
/* UC03で実装します
    private void showReservationInfo(Reservation reservation) {

        // 予約インスタンスから予約者(ユーザアカウント)インスタンスを
        // 取得し、予約者の実名を取得する。
        UserAccount subscriber = ■■■■■■■■■■■■■■;
        String subscriberName = ■■■■■■■■■■■■;

        // 施設予約完了画面のヘッダーを出力する
        UIUtility.■■■■■■■■■■■■■■■■■■■■■■■■■■;

        // 画面の説明を表示する
        UIUtility.printMessage("以下の予約を確定しました。");

        // 予約インスタンスから予約番号を取得する。
        int reservationNumber = ■■■■■■■■■■■■;

        // 予約インスタンスから予約対象の施設インスタンスを取得し、
        // その施設名を取得する。
        ■■■■■ equipment = ■■■■■■■■■■■■■;
        String equipmentName = ■■■■■■■■■■;

        // 予約インスタンスから利用開始日時と利用終了日時を取得し、
        // それぞれ文字列に変換する
        ■■■■■■■ startDateTime = ■■■■■■■■■■■■■■■■■;
        String startDateTimeStr = DateUtils.■■■■■■■■■■■■■■■■■■■;

        ■■■■■■■ endDateTime = ■■■■■■■■■■■■■■;
        String endDateTimeStr = ■■■■■■■■■■■■■■■■■■■■■■■;

        // 予約インスタンスから利用目的を取得する
        String purpose = ■■■■■■■■■■■■;

        // 登録した予約の情報を表示する
        System.out.println("[" + reservationNumber + "]" + equipmentName + " "
                + startDateTimeStr + " " + endDateTimeStr + " " + purpose);

        // 改行
        System.out.println();

        // エンターキーの入力を促し、エンターキーが押されるまで待ち受ける。
        UIUtility.■■■■■■■■■■■■■■■■■■■■■■■■;

    }
*/

    /**
     * システムに登録されている施設の一覧を表示する。<br>
     * 表示の書式は<br>
     *   [<em>施設番号</em>] <em>施設名</em> 定員：<em>定員数</em> 内線番号：<em>内線番号</em>
     * <br>となる
     */
/* UC03で実装する
    private void showEquipmentList() {
        //=====================================================================
        // 実装のヒント!
        // ・利用するメソッドで、どういった例外がスローされるのかを確認し、
        //   キャッチする。
        // ・Listに対して、繰り返しを行う際は、拡張for文を用いる。
        //=====================================================================
        try {
            // 登録済の全施設をListとして取得する。
            //  [ヒント] EquipmentReservationSystemインスタンスを使用する。
            List<Equipment> equipments = ■■■■■■■■■■■■■■■■■■■■■;

            // 取得した施設の件数分繰り返す。
            for (Equipment equipment : equipments) {

                // 施設インスタンスから施設番号を取得する。
                int equipmentNumber = equipment.getNumber();

                // 施設インスタンスから施設名を取得する。
                String equipmentName = equipment.getName();

                // 施設インスタンスから定員を取得する。
                int capacity = equipment.getCapacity();

                // 施設インスタンスから内線番号を取得する。
                String extensionNumber = equipment.getExtensionNumber();

                // 取得した施設情報を表示する。
                System.out.println("[" + equipmentNumber + "]" + equipmentName
                        + " 定員：" + capacity + " 内線番号：" + extensionNumber);
            }

            // 改行
            System.out.println();

            // DataFormatExceptionの捕捉
        } catch (DataFormatException e) {
            UIUtility.printMessage(e.getMessage());

            // DataIOExceptionの捕捉
        } catch (DataIOException e) {
            UIUtility.printMessage(e.getMessage());
        }
    }
*/
}
