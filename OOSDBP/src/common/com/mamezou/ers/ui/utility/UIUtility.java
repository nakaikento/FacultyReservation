/*
 * UIUtility.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

//パッケージ宣言
package com.mamezou.ers.ui.utility;

//StdinDataReaderクラスのインポート
import com.mamezou.commons.io.StandardInputReader;

/**
 * ユーザインタフェースのユーティリティクラス
 * @author Mamezou
 */
public class UIUtility {

    /**
     * コンストラクタ<br>
     * 本クラスはクラスメソッドのみで構成されるユーティリティクラスのため、
     * インスタンス化できません。
     */
    private UIUtility() {
    }

    /**
     * 施設予約システムの各画面のヘッダーを表示する。<br>
     * 管理者画面のヘッダーでは、ログインしているユーザの実名を表示しないので、
     * showUserNameflgにfalseを指定する。<br>
     * 予約者画面のヘッダーでは、ログインしているユーザの実名を表示するので、
     * 引数のshowUserNameflgにtrueを指定する。<br>
     * @param title 画面名称
     * @param loginUserName ログインしているユーザの実名
     * @param showUserNameflg ユーザの実名の表示有無
     */
    public static void showHeader(String title, String loginUserName,
            boolean showUserNameflg) {

        //改行
        System.out.println();
        //ヘッダーの表示
        System.out.println("＝＝＝施設予約システム－" + title + "＝＝＝");

        //引数のshowUserNameflgがtrueの時、ユーザの実名を表示する
        if (showUserNameflg) {
            System.out.println("ユーザ名：" + loginUserName);
        }

        //改行
        System.out.println();
    }

    /**
     * 前後に改行をつけてメッセージを表示する。<br>
     * 各画面の画面説明文の表示や、エラーメッセージの表示に使用する。
     * @param message 表示するメッセージ
     */
    public static void printMessage(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    /**
     * 標準入力から受け取ったデータを文字列(String)として返す。<br>
     * 引数promptMsgは入力を促すプロンプトとして出力されます。
     * @param promptMsg 入力を促すメッセージ
     * @return 入力された文字列
     */
    public static String getInputAsString(String promptMsg) {
        return StandardInputReader.getInputString(promptMsg);
    }

    /**
     * 標準入力から受け取ったデータをint型の数値として返す。
     * 引数promptMsgは入力を促すプロンプトとして出力されます。
     * @param promptMsg 入力を促すメッセージ
     * @return 入力された数値
     * @throws java.lang.NumberFormatException
     *          入力されたデータが数値に変換できない(0～9, 先頭の- 以外)場合。
     */
    public static int getInputAsInt(String promptMsg) {
        return StandardInputReader.getInputInt(promptMsg);
    }
}
