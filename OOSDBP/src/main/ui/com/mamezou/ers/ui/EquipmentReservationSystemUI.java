/*
 * EquipmentReservationSystemUI.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.ui;

// com.mamezou.ers.businessパッケージのインポート
//     Equipment, EquipmentReservationSystem, Reservation, UserAccount
import com.mamezou.ers.business.EquipmentReservationSystem;
// com.mamezou.ers.exceptionsパッケージのインポート
//     DataFormatException, DataIOException, DataValueException
// com.mamezou.ers.ui.utilityパッケージのインポート
//     AppValidator,UIUtility

/**
 * アプリケーションの起動から、画面遷移を管理する
 * @author Mamezou
 */
public class EquipmentReservationSystemUI {

    /** 施設予約システムクラスのインスタンスの参照を格納する変数 */
    private static EquipmentReservationSystem equipReservationSystem;

    /** コンストラクタ */
    public EquipmentReservationSystemUI() {
        super();
    }

    /**
     * アプリケーションの起動と画面遷移を管理する。
     * @param args 起動パラメータ(未使用)
     */
/* UC01で実装する
    public static void main(String[] args) {

        //======================================================================
        // 実装のヒント!
        // ・login処理で、ログイン名に-1を入力した場合は、loginメソッド
        //   からnullが戻ってくる。この場合、システムは終了する。
        // ・switch ～ case 文では、必ず default をつける。
        //======================================================================

        // EquipmentReservationSystemのインスタンスを生成し、
        // 属性equipReservationSystemを初期化する。
        equipReservationSystem = ■■■■■■■■■■■■■■■■;

        // ログイン処理により、ユーザアカウントインスタンスを取得する。
        UserAccount loginUser = ■■■■;

        // ログインしたユーザが、管理者か予約者かを確認する。
        // ログイン処理により取得したユーザアカウントインスタンスがnullの場合、
        // システムを終了する。
        if (■■■■■■■■■) {
            System.exit(0);

            // 管理者の場合
        } else if (AppValidator.■■■■■■■■■) {
            // AdminMainUIのインスタンスを生成する
            AdminMainUI adminUI = ■■■■■■■■■■■■■■■■■■■■;
            adminUI.■■■■■■■■;

            // 予約者の場合
        } else if (AppValidator.■■■■■■■■■■■■) {
            // UserMainUIのインスタンスを生成する
            UserMainUI userUI = ■■■■■■■■■■■■■■■■■■■;
            userUI.■■■■■■■■;

            // ログインしたユーザの権限が、管理者でも予約者でもない場合、
            // エラーメッセージを表示する。
        } else {
           ■■■■■■■■■■■("利用者管理ファイルのユーザ権限に誤りがあります。");
        }
    }
*/

    /**
     * ログイン処理を行う。<br>
     * 
     * ログインを促し、ログインしたユーザを表すユーザアカウントインスタンスを
     * 返す。ユーザアカウントに-1が入力された場合は、nullを返す。
     * 認証が取れなかった場合は、エラーメッセージを表示する。
     * 
     * @return ログインしたユーザのユーザアカウント
     */
/* UC01で実装する
    private static UserAccount login() {

        //======================================================================
        // 実装のヒント!
        // ・ループ内で、以降の処理を行わずにループの最初に戻るには、continueを
        //   使用する。
        // ・利用するメソッドで、どういった例外がスローされるのかを確認し、
        //   キャッチする。
        // ・エラーメッセージを表示する時は、UIUtility.printMessage を使用する。
        //======================================================================

        // 正しく入力されるまで繰り返します。
        while (true) {

            try {

                //===========================================================
                //  ログインを促すメッセージを表示し、入力値を取得する。
                //===========================================================

                // 認証画面のヘッダーを表示する
                UIUtility.showHeader("認証画面", "", false);

                // 認証画面の入力を促す説明文を表示する。
                UIUtility.printMessage("ログイン名とパスワードを入力してください。\r\n"
                                     + "終了する場合は、ログイン名に-1を入力してください。");

                // ログイン名の入力を促し、入力された文字列を取得する。
                String loginName = UIUtility.■■■■■■■■■■■■■■■■;

                // 取得したログイン名が「-1」であれば
                // nullを返す。
                if (loginName != null && loginName.matches("-1")) {
                    return null;
                }

                // パスワードの入力を促し、入力されたパスワードを取得する。
                String password = UIUtility.■■■■■■■■■■■■■■■■;

                //===========================================================
                // 入力値をチェックする。
                //===========================================================

                // ログイン名が条件を満たしているかをチェックする。
                if (!AppValidator.■■■■■■■■■■■■■■) {
                    // 既定の条件を満たしていない場合は、以降の処理を行わずに
                    // ループの先頭に戻る。
                    continue;
                }

                // パスワードが条件を満たしているかをチェックする。
                if (!AppValidator.■■■■■■■■■■■■■) {
                    // 既定の条件を満たしていない場合は、以降の処理を行わずに
                    // ループの先頭に戻る。
                    continue;
                }

                //===========================================================
                // 入力されたログイン名とパスワードに合致するユーザアカウント
                // を検索する。
                //===========================================================

                // 入力されたログイン名・パスワードに合致するユーザアカウント
                // を検索し、そのインスタンスを取得する。
                UserAccount loginUser = equipReservationSystem.■■■■■■■(
                                                                ■■■■■■■■■■);

                // 入力されたログイン名、パスワードに合致するユーザアカウント
                // インスタンスを返す。
                return loginUser;

            // DataValueExceptionの捕捉
            } catch (DataValueException e) {
                // DataValueExceptionのエラーメッセージを出力する。
                UIUtility.printMessage(e.getMessage());

            // DataFormatExceptionの捕捉
            } catch (DataFormatException e) {
                // DataFormatExceptionのエラーメッセージを出力する。
                UIUtility.printMessage(e.getMessage());

            // DataIOExceptionの捕捉
            } catch (DataIOException e) {
                // 該当するエラーメッセージを出力する。
                UIUtility.printMessage(e.getMessage());
            }
        }
    }
*/
}
