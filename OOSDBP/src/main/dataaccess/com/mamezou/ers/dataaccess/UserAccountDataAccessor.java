/*
 * UserAccountDataAccessor.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.dataaccess;

// com.mamezou.ers.exceptionsパッケージのインポート
//    DataFormatException, DataIOException
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;

// java.io パッケージのインポート
//    IOException
import java.io.IOException;

// java.utilパッケージのインポート
//    ArrayList, List (コレクション関連)
import java.util.ArrayList;
import java.util.List;

/**
 * ユーザアカウント情報操作クラス
 * @author Mamezou
 */
public class UserAccountDataAccessor {

    /** ユーザアカウント管理ファイルのパス（デフォルト） */
    private static final String DEFAULT_FILE_PATH = "userAccount.csv";

    /** 永続化基礎クラスのインスタンス */
    private BaseDataAccessor baseAccessor;

    /**
     * コンストラクタです。
     */
    public UserAccountDataAccessor() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * コンストラクタです。
     * @param filePath ユーザアカウント管理ファイルのパス
     */
    public UserAccountDataAccessor(String filePath) {
        baseAccessor = new BaseDataAccessor(filePath);
    }

    /**
     * 全てのユーザのユーザアカウント情報を取得する。<br>
     * ユーザアカウント情報はユーザアカウント管理ファイルから読み込み、
     * Listとして返される。Listの1要素はStringの配列として格納されており、
     * ユーザ1人分のユーザアカウント情報を表す。<br>
     * ユーザアカウント管理ファイルに1件も登録されていない場合、空のListが
     * 返却される。
     * @return 読み込んだユーザアカウント情報。Listの要素の型はString[]
     * @throws DataIOException  ユーザアカウント管理ファイルの読み込みに失敗した場合
     */
/* UC01で実装します
    private List<String[]> load() ■■■ ■■■■■■■■ {
        try {
            ■■■■■■■ accountDataList = ■■■■■■■■■■■■■
            baseAccessor.■■■■■■■■■■■;
            return accountDataList;
        } catch (■■■■■■ e) {
            ■■■ new ■■■■■■■■(
                        "ユーザアカウント管理ファイルの読み込みに失敗しました。", e);
        }
    }
*/

    /**
     * 検索条件を満たすユーザを検索し、そのユーザアカウント情報をString型の
     * 配列として返す。<br>
     * 引数のログイン名とパスワードに合致するユーザアカウント情報を
     * ユーザアカウント管理ファイルから検索し、Stringの配列で返す。<br>
     * 該当するユーザアカウントが存在しない場合、nullを返す。
     * @param targetLoginName        検索条件となるログイン名
     * @param targetPassword         検索条件となるパスワード
     * @return                       ユーザアカウント情報
     * @throws DataIOException       ユーザアカウント管理ファイルの読み込みに
     *                                失敗した場合
     * @throws DataFormatException   ユーザアカウント管理ファイルのデータの
     *                                ログイン名、パスワードに問題があった場合
     */
/* UC01で実装します
    public ■■■■ findUserAccount(String targetLoginName,
            String targetPassword) throws ■■■■■■■■, DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・Listに対して、繰り返しを行う際は、拡張for文を用いる。
        // ・ユーザアカウント、パスワードの文字列を比較する時は同値性の比較を行う。
        // ・配列の要素にアクセスする際には、ArrayIndexOutOfBoundsException がスローされる可能性がある。
        //======================================================================

        try {
            // ユーザアカウント管理ファイルから、登録されている
            // 全アカウント情報を取得する。
            List<String[]> accountDataList = ■■■;

            // ユーザアカウント情報の検索を繰り返す
            for (String[] accountData : ■■■■■■■■) {

                // 取得したユーザアカウント情報からログイン名を取り出す。
                String loginName = ■■■■■■■;

                // 取得したユーザアカウント情報からパスワードを取り出す。
                String password = ■■■■■■■;

                // 引数のログイン名とパスワードが、取得したユーザアカウント情報の
                // ログイン名とパスワードとそれぞれ合致した場合
                if (■■■■■■■■■■■■■■■■■
                        && ■■■■■■■■■■■■■■■■) {
                    // ユーザアカウント情報のString型の配列の参照を返す。
                    return accountData;
                }
            }

            // 引数のログイン名とパスワードに合致する
            // ユーザアカウント情報が見つからなかった場合は、nullを返す。
            return null;

            // ArrayIndexOutOfBoundsExceptionの捕捉
        } catch (ArrayIndexOutOfBoundsException e) {
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            throw new DataFormatException(
                        "■■■■■■■■■■■■■■■■■■■■■■■■"
                      + "■■■■■■■■■■■■■■■■■", ■);
        }
    }
*/

    /**
     * 検索条件を満たすユーザを検索し、そのユーザアカウント情報をString型の
     * 配列として返す。<br>
     * 引数のユーザアカウント番号と一致するユーザアカウント番号を持つ
     * ユーザの情報を、ユーザアカウント管理ファイルから検索し、String型の
     * 配列で返す。<br>
     * 該当するユーザアカウントが存在しない場合、nullを返す。
     * @param targetUserNumber        ユーザアカウント番号
     * @return                        ユーザアカウント情報
     * @throws DataIOException
     *          ユーザアカウント管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException
     *          ユーザアカウント管理ファイルのデータのユーザアカウント番号に
     *          問題があった場合
     */
/* UC02で実装します。
    public String[] findUserAccount(int targetUserNumber)
            ■■■ ■■■■■■■■, ■■■■■■■■■■ {

        //======================================================================
        // 実装のヒント!
        // ・Listに対して、繰り返しを行う際は、拡張for文を用いる。
        // ・配列の要素にアクセスする際には、ArrayIndexOutOfBoundsException が
        //   スローされる可能性がある。
        // ・文字列を数値に変換する際には、NumberFormatException がスローされる
        //   可能性がある。
        //======================================================================

        ■■ {

            // ユーザアカウント管理ファイルに登録されている全てのアカウント情報を取得する。
            List<String[]> accountDataList = ■■■;

            // ユーザアカウント情報の検索を繰り返す
            ■■ (String[] userAccountData : ■■■■■■■■) {

                // 取得したユーザアカウント情報からユーザのユーザアカウント番号を
                // 取り出し、数値に変換する。
                int userNumber = ■■■■■■■■(userAccountData[0]);

                // 引数のユーザアカウント番号と、取得したユーザのユーザアカウント
                // 番号が合致する場合
                if (■■■■■■■■ == ■■■■■) {

                    // 合致したユーザアカウント情報のString型の配列の参照を返す。
                    return userAccountData;

                }

            }

            // 引数のユーザアカウント番号に合致するユーザが見つからなかった場合、
            // nullを返す。
            return null;

            // ArrayIndexOutOfBoundsExceptionの捕捉
        } ■■■ (ArrayIndexOutOfBoundsException e) {
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new DataFormatException(
                        "ユーザアカウント管理ファイルのデータ(ユーザアカウント番号の位置)が不正です。", e);
            // NumberFormatExceptionの捕捉
        } ■■■ (NumberFormatException e) {
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new DataFormatException(
                        "ユーザアカウント管理ファイルのデータ(ユーザアカウント番号)の数値変換に失敗しました。", e);
        }
    }
*/

    /**
     * 検索条件を満たすユーザを検索し、そのユーザアカウント情報をString型の
     * 配列として返す。<br>
     * 引数のログイン名に一致するユーザアカウント情報を、ユーザアカウント管理
     * ファイルから検索し、Stringの配列で返す。<br>
     * なければnullを返す。
     * @param targetLoginName       検索条件となるログイン名
     * @return                      ユーザアカウント情報
     * @throws DataIOException      ユーザアカウント管理ファイルの読み込みに
     *                               失敗した場合
     * @throws DataFormatException  ユーザアカウント管理ファイルのデータの
     *                               ユーザアカウント、パスワードに問題があった場合
     */
/* UC05で実装します。
    public String[] findUserAccount(String targetLoginName)
            throws DataIOException, DataFormatException {
    }
*/

    /**
     * ユーザアカウント情報を追加保存する。<br>
     * 引数のString型配列に格納されているユーザアカウント情報を
     * ユーザアカウント管理ファイルに追加保存する。
     * @param  userAccountData ユーザアカウント情報
     * @throws IOException ユーザアカウント管理ファイルの書き込みに失敗した場合
     */
/* UC05で実装します
    private void save(String[] userAccountData) throws DataIOException {
    }
*/

    /**
     * ユーザアカウント情報を追加保存する。<br>
     * 引数のString型配列に格納されているユーザアカウント情報を
     * ユーザアカウント管理ファイルに追加保存する。
     * @param  userAccountData ユーザアカウント情報
     * @throws DataIOException ユーザアカウント管理ファイルの書き込みに失敗した場合
     */
/* UC05で実装します。    
    public void addUserAccount(String[] userAccountData) throws DataIOException {
    }
*/

    /**
     * 新しく付与するべきユーザアカウント番号を返す。<br>
     * ユーザアカウント管理ファイルから、ユーザアカウント情報を読み込み、
     * ユーザアカウントの件数+1の値を返す。<br>
     * ユーザアカウント番号は1から始まる。
     * @return             登録されているユーザアカウントの件数+1の値
     * @throws DataIOException ユーザアカウント管理ファイルの読み込みに失敗した場合
     */
/* UC05で実装します
    public int getNextSeq() throws DataIOException {
    }
*/
}
