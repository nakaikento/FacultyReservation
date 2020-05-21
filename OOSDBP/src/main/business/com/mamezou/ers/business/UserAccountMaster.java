/*
 * UserAccountMaster.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.business;

import com.mamezou.ers.dataaccess.UserAccountDataAccessor;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;

/**
 * ユーザアカウント管理クラス<br>
 * ユーザアカウントを管理します。具体的には、永続化層とビジネス
 * ロジック層との間でやり取りされるユーザアカウント情報を橋渡し
 * することが責務です。
 * @author Mamezou
 */
public class UserAccountMaster {

    /**
     * ユーザアカウントのインスタンスを永続化するオブジェクト
     */
    private UserAccountDataAccessor userAccountDataAccessor;

    /**
     * コンストラクタ
     */
    public UserAccountMaster() {
        // UserAccountDataAccessorインスタンスを生成し、その参照を
        // インスタンス変数userAccountDataAccessorに代入する。
        userAccountDataAccessor = new UserAccountDataAccessor();
    }

    /**
     * コンストラクタ。
     * @param dataAccessor 使用するUserAccountDataAccessor
     */
    public UserAccountMaster(UserAccountDataAccessor dataAccessor) {
        userAccountDataAccessor = dataAccessor;
    }

    /**
     * Stringの配列で表されたユーザアカウント情報をUserAccountインスタンス
     * として返す。<br>
     * 事前条件：引数 userAccountData が null ではないこと。
     * String配列の各要素の意味および出現順序については、別途、永続化に
     * 関する設計資料を参照のこと。
     * @param userAccountData ユーザアカウント情報を表す配列。nullは認めない。
     * @return userAccountData で指定された情報を内包するUserAccountインスタンス。
     * @throws DataFormatException
     */
/* UC01で実装します
    private UserAccount convertToUserAccount(String[] userAccountData)
            throws DataFormatException {
        //==========================================================================
        // 実装のヒント!
        // ・ユーザアカウントインスタンスを生成する際は、必要に応じて型を変換する必要がある。
        // ・配列の要素にアクセスする際には、ArrayIndexOutOfBoundsException がスローされる可能性がある。
        // ・文字列を数値に変換する際には、NumberFormatException がスローされる可能性がある。
        //==========================================================================
        try {
            // 引数のユーザアカウント情報からユーザアカウント番号を
            // 取り出し、数値に変換する。
            int number = Integer.parseInt(userAccountData[0]);

            // 引数のユーザアカウント情報から、ログイン名を取り出す。
            String loginName = userAccountData[■];

            // 引数のユーザアカウント情報から、パスワードを取り出す。
            String password = userAccountData[■];

            // 引数のユーザアカウント情報から、ユーザの実名を取り出す。
            String realName = userAccountData[■];

            // 引数のユーザアカウント情報から、内線番号を取り出す。
            String extensionNumber = userAccountData[■];

            // 引数のユーザアカウント情報から、部署名を取り出す。
            String divisionName = userAccountData[■];

            // 引数のユーザアカウント情報から権限を取り出し、数値に変換する。
            int authority = Integer.■■■■(userAccountData[■]);

            // 引数のユーザアカウント情報で、ユーザアカウントインスタンスを生成する。
            UserAccount account
                    = new UserAccount(■■■, ■■■■■, ■■■■, ■■■■,
                                          ■■■■■■■■, ■■■■■■, ■■■■■);
            return account;
        } catch (ArrayIndexOutOfBoundsException e) {
            // ArrayIndexOutOfBoundsExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            throw new DataFormatException(
                            "■■■■■■■■■■■■■■■■■■■■■■■", e);

        } catch (NumberFormatException e) {
            // NumberFormatExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            throw new DataFormatException(
                        "ユーザアカウント管理ファイルのデータ"
                      + "(ユーザアカウント番号または権限)の数値変換に失敗しました。", e);
        }
    }
*/

    /**
     * 引数で指定された条件でユーザアカウントを検索する。<br>
     * loginNameとpasswordに合致するユーザアカウントインスタンスを検索し、
     * そのインスタンスへの参照を返す。
     * @param loginName 検索条件となるログイン名
     * @param password 検索条件となるパスワード
     * @return ユーザアカウントインスタンスへの参照。
     *          合致するユーザアカウントが存在しない場合 null。
     * @throws DataIOException 永続化されたデータの読み込みに失敗した場合
     * @throws DataFormatException 永続化されたデータに書式上の不整合がある場合
     */
/* UC01で実装します
    public UserAccount findUserAccount(String loginName, String password)
            throws DataIOException, DataFormatException {

        //=====================================================================
        // 実装のヒント!
        // ・ユーザアカウント情報アクセスインスタンスから戻ってくるユーザアカウント情報は、Stringの配列型である。
        //=====================================================================

        // ユーザアカウント情報アクセスクラスを使い、引数のユーザアカウントとパスワードに合致する
        // ユーザアカウント情報をString型の配列で取得する。
        String[] foundData = ■■■■■■■■■■■■.findUserAccount(loginName, password);

        if (foundData != null) {
            // 合致するユーザアカウント情報がある場合の処理
            // Stringの配列で表されたユーザアカウント情報をUserAccountのインスタンスに変換する
            UserAccount foundAccount = ■■■■■■■■■■(foundData);

            // ユーザアカウントインスタンスへの参照を返す
            return foundAccount;

            // 条件に合致するユーザアカウントが存在しない場合
        } else {

            // null を返す
            return null;

        }
    }
*/

    /**
     * 引数で指定された条件でユーザアカウントを検索する。<br>
     * userNumber に合致するユーザアカウントインスタンスを検索し、
     * そのインスタンスへの参照を返す。
     * @param   targetUserAccountNumber 検索条件となるユーザアカウント番号
     * @return ユーザアカウントインスタンスへの参照。
     *          合致するユーザアカウントが存在しない場合 null。
     * @throws DataIOException 永続化されたデータの読み込みに失敗した場合
     * @throws DataFormatException 永続化されたデータに書式上の不整合がある場合
     */
/* UC02で実装します
    public UserAccount findUserAccount(int targetUserAccountNumber)
            throws DataIOException, DataFormatException {

        //=====================================================================
        // 実装のヒント!
        // ・ユーザアカウント情報アクセスインスタンスから戻ってくるユーザアカウント情報は、Stringの配列型である。
        //=====================================================================

        // ユーザアカウント情報操作クラスを使い、引数のユーザアカウント番号に
        // 合致するユーザアカウント情報をString型の配列として取得する。
        ■■■■ foundData = ■■■■■■■■■■■■■■■■■■■■■■■■■■;

        if (■■■■■ != null) {
            // 合致するユーザアカウント情報がある場合の処理
            // Stringの配列で表されたユーザアカウント情報をUserAccountのインスタンスに変換する
            UserAccount foundAccount = ■■■■■■■■■■■■■■■■;

            // ユーザアカウントインスタンスへの参照を返す
            ■■■ foundAccount;

        } else {
            // 条件に合致するユーザアカウントが存在しない場合、null を返す
            ■■■ null;

        }
    }
*/

    /**
     * 引数で指定された条件でユーザアカウントを検索する。<br>
     * userNumber と loginName に合致するユーザアカウントインスタンスを検索し、
     * そのインスタンスへの参照を返す。
     * @param loginName 検索条件となるログイン名
     * @return ユーザアカウントインスタンスへの参照。
     *          合致するユーザアカウントが存在しない場合 null。
     * @throws DataIOException 永続化されたデータの読み込みに失敗した場合
     * @throws DataFormatException 永続化されたデータに書式上の不整合がある場合
     */
/* UC05で実装する
    public UserAccount findUserAccount(String loginName)
            ■■■■■■■■■■■■■■■■■■■■■■ {
    }
*/

    /**
     * ユーザアカウント情報を追加登録する。<br>
     * 引数のユーザアカウント情報で、ユーザアカウントを登録する。
     * @param loginName       登録するログイン名
     * @param password        登録するパスワード
     * @param realName        登録するユーザの実名
     * @param extensionNumber 登録する内線番号
     * @param divisionName    登録する部署名
     * @param authority       登録する権限
     * @return 追加登録したユーザアカウント
     * @throws DataIOException    ユーザアカウントの登録に失敗した場合
     * @throws DataFormatException ユーザアカウントの登録に失敗した場合
     */
/* UC05で実装します。
    public UserAccount addUserAccount(String loginName, String password,
            String realName, String extensionNumber, String divisionName,
            int authority) throws DataIOException, DataFormatException {
    }
*/
}
