/*
 * UserMainUI.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.ui;

// com.mamezou.ers.businessパッケージのインポート
import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.business.UserAccount;

// com.mamezou.ers.ui.utilityパッケージのインポート
//     UIUtility
import com.mamezou.ers.ui.utility.UIUtility;

/**
 * 予約者用ユーザインターフェースクラス<br>
 * サービスメニューの表示、および入力を受け付ける役割を持ちます。
 */
public class UserMainUI {
    /** 施設予約システムクラスのインスタンスへの参照 */
    private EquipmentReservationSystem equipReservationSystem;

    /**
     * コンストラクタです。
     * @param system EquipmentReservationSystem
     */
    public UserMainUI(EquipmentReservationSystem system) {
        // EquipmentReservationSystemインスタンスの生成
        equipReservationSystem = system;
    }

    /**
     * 予約者サービスメニュー画面を表示し、ユーザの入力により
     * 各機能の画面に遷移する。
     * @param loginUser 認証されたユーザアカウント
     */
/* UC01で実装します
    public void show(UserAccount loginUser) {
        //=====================================================================
        // 実装のヒント!
        // ・利用するメソッドで、どういった例外がスローされるのかを確認し、
        //   キャッチする。
        // ・選択番号は数値のみ受け付ける。数値以外の入力があった場合は、エラー
        //   メッセージを表示した後、予約者サービスメニュー画面を再表示する。
        // ・エラーメッセージを表示する時は、UIUtility.printMessage を使用する。
        //=====================================================================

        ■■■ (true) {
            int selectedNumber = -1;

            // 機能番号として数値が入力されるまで繰り返す。
            try {
                ■■■■■(loginUser);

                // 番号の入力を促し、入力された機能番号を取得する。
                selectedNumber = UIUtility.■■■■■■■■■■■■■;

                // NumberFormatExceptionの捕捉
            } ■■■ (NumberFormatException e) {
                // 該当するエラーメッセージの表示
                UIUtility.printMessage("■■■■■■■■■■■■■■■■");
                continue;
            }

            // 機能番号に応じて、適切な処理を起動する
            switch (■■■■■■■) {
            case 1:
                // 予約照会の画面
                UserReservationEnquiryUI enquiryScreen = new UserReservationEnquiryUI(
                        equipReservationSystem);
                enquiryScreen.■■■■■■■■■■■■■;
                break;

            case 2:
                // 施設予約の画面
                UserMakingReservationUI reservationScreen = new UserMakingReservationUI(
                        equipReservationSystem);
                reservationScreen.■■■■■■■■■■■■■■;
                break;

            case 3:
                // 終了
                System.■■■■;
                break;

            // 1,2,3以外の入力があった場合
            default:
                break;
            }
        }
    }
*/

    /**
     * 予約者サービスメニュー画面のメニューを表示する。<br>
     * 単純にメニューを表示するのみで、入力の受け付けはしません。
     * @param loginUser 認証済みのユーザ
     */
/* UC01で実装します
    private void printMenu(UserAccount loginUser) {
        // 引数の予約者のユーザアカウントより、ユーザの実名を取得する。
        String loginUserName = ■■■■■■■■■■■■;

        // メニューを表示する
        // 予約者サービスメニューのヘッダーを表示する
        UIUtility.■■■■■("予約者サービスメニュー画面", loginUserName, true);

        // メニュー番号の選択を促す説明を表示する
        UIUtility.printMessage("■■■■■■■■■■■■■■■");

        // 予約者サービスメニューを表示する
        System.out.println("■■■■■■");
        System.out.println("■■■■■■");
        System.out.println("■■■■");

        // 改行
        System.out.println();
    }
*/
}
