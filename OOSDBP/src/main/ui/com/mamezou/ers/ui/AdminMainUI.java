/*
 * AdminMainUI.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.ui;

// com.mamezou.ers.businessパッケージのインポート
// EquipmentReservationSystem, UserAccount
import com.mamezou.ers.business.EquipmentReservationSystem;
// com.mamezou.ers.ui.utilityパッケージのインポート
//     UIUtility

/**
 * 管理者用メニュー画面
 * @author Mamezou
 */
public class AdminMainUI {

    /** 施設予約システムクラスのインスタンスへの参照 */
    private EquipmentReservationSystem equipReservationSystem;

    /**
     * コンストラクタです。
     * @param system EquipmentReservationSystem
     */
    public AdminMainUI(EquipmentReservationSystem system) {
        // ReserveEquipmentSystemインスタンスを生成する
        equipReservationSystem = system;
    }

    /**
     * 管理者サービスメニュー画面を表示する。<br>
     * 管理者サービスメニュー画面を表示し、入力された選択番号を返す。
     * @param  adminUser  ログイン中の管理者を表すユーザアカウント
     */
/* UC01で実装します
    public void show(UserAccount adminUser) {
        //======================================================================
        // 実装のヒント!
        // ・利用するメソッドでスローされる例外を確認し、キャッチする。
        // ・選択番号は数値のみ受け付ける。数値以外の入力があった場合は、エラー
        //   メッセージを表示した後、管理者サービスメニュー画面を再表示する。
        // ・エラーメッセージを表示する時は、UIUtility.printMessage を使用する。
        //======================================================================

        // システムが終了するまで繰り返す。
        while (true) {

            int selectedNumber = -1;

            // 管理者サービスメニュー画面を表示する
            ■■■■■(adminUser);
            ■■ {
                // 選択番号の入力を促し、入力された選択番号を取得する。
                selectedNumber = UIUtility.■■■■■■■■■■■■■;

                // NumberFormatExceptionの捕捉
            } catch (NumberFormatException e) {

                // 該当するエラーメッセージの表示
                UIUtility.printMessage("選択番号は数値で入力してください。");
                continue;
            }

            // 選択された番号により、管理者の各サービス画面を表示する。
            ■■■ (selectedNumber) {
            ■■ 1:
                // 施設登録画面
                ■■■■■■■■ equipmentScreen
                                    = new AdminEquipmentUI(equipReservationSystem);
                equipmentScreen.■■■■■■■■■■■■;
                break;

            ■■ 2:
                // ユーザ登録画面
                ■■■■■■■ accountScreen
                                = new AdminAccountUI(equipReservationSystem);
                accountScreen.■■■■■■■■;
                break;

            ■■ 3:
                // 終了
                System.exit(0);
                break;

            // 1,2,3以外の入力があった場合
            ■■■■:
                break;
            }
        }
    }
*/

    /**
     * 管理者サービスメニュー画面のメニューを表示する。<br>
     * 単純にメニューを表示するのみで、入力の受け付けはしません。
     * @param adminUser 認証済みの管理者ユーザ
     */
/* UC01で実装します
    private void printMenu(UserAccount adminUser) {

        // 管理者サービスメニュー画面のヘッダーを表示する。
        UIUtility.showHeader("管理者サービスメニュー画面", "", false);

        // メニュー番号の選択を促す説明を表示する。
        UIUtility.printMessage("メニュー番号を入力してください。");

        // 管理者サービスメニューを表示する。
        System.out.println("[1]施設登録");
        System.out.println("[2]ユーザ登録");
        System.out.println("[3]終了");
        // 改行
        System.out.println();

    }
*/
}
