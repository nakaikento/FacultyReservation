/*
 * UserAccountMasterTestUC05.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.mamezou.ers.business.UserAccount;
import com.mamezou.ers.business.UserAccountMaster;
import com.mamezou.ers.dataaccess.UserAccountDataAccessor;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC05で実装する範囲のUserAccountMasterクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class UserAccountMasterTestUC05 extends BeanTestCase {

    /** テスト対象クラスのインスタンス(への参照) */
    private UserAccountMaster master = new UserAccountMaster();

    @Override
    protected Class<?> getBeanClass() {
        return UserAccountMaster.class;
    }

    /**
     * 検索条件に合致するユーザアカウントが1件存在する場合のテスト.
      * <p>
      * 検索条件 = {ログイン名 = soramame}
      * </p>
      * <p>
      * 期待する結果 = 1件のユーザアカウントが取得できる.
      * <p>
     * 存在するIDを指定すると正しく利用者データを取得できることをテストする
     */
    @Test
    public void testFindUserAccountById() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public String[] findUserAccount(String targetLoginName) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat(targetLoginName, is(equalTo("soramame")));
                
                // ダミーの戻り値を返す
                return new String[]{"1", "soramame", "soramame", "そらまめ", "4101", "教育事業部", "1"};
            }
        };

        // スタブをテスト対象インスタンスに設定する
        UserAccountMaster master = new UserAccountMaster(uadMock);

        // 期待値を準備する
        UserAccount expected = new UserAccount(1, "soramame", "soramame", "そらまめ", "4101", "教育事業部", 1);

        // テスト対象のメソッドを実行し、実測値を取得する(ID="soramame")
        UserAccount actual = master.findUserAccount("soramame");

        // 実測値と期待値がその属性を含めて等しいこと確認する
        // 実測値のオブジェクトが持つ属性値が正しい(期待値と等しい)ことを確認する
        assertThat("ユーザアカウント情報が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * 検索条件に合致するユーザアカウントが存在しない場合のテスト.
      * <p>
      * 検索条件 = {ログイン名 = mamezou}
      * </p>
      * <p>
      * 期待する結果 = null が返される.
      * <p>
     * 存在するIDを指定すると正しく利用者データを取得できることをテストする
     */
    @Test
    public void testFindUserAccountById_NoData() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public String[] findUserAccount(String targetLoginName) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "UserAccountDataAccessor#findUserAccount(String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "ログイン名", targetLoginName), targetLoginName, is(equalTo("mamezou")));
                
                // ダミーの戻り値を返す
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "userAccountDataAccessor", uadMock);

        // テスト対象のメソッドを実行し、実測値を取得する(ID="mamezou")
        UserAccount actual = master.findUserAccount("mamezou");

        assertThat("存在しないはずのユーザアカウントが取得されました", actual, is(nullValue()));
    }

    /**
     * 利用者情報を新規に追加登録するテスト.(登録成功)<br>
     * <p>
     * 期待する結果 = ユーザアカウント番号が発行され、登録した情報を持つユーザアカウントインスタンスが返される.
     * <p>
     */
    @Test
    public void testAddUserAccount() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public void addUserAccount(String[] userAccountData) throws DataIOException {
                // 引数が正しく渡っていることを確認する
                String msg = "UserAccountDataAccessor#findUserAccount(String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "ユーザアカウント番号", userAccountData[0]), userAccountData[0], is("4"));
                assertThat(String.format(msg, "ログイン名", userAccountData[1]), userAccountData[1], is("azuki"));
                assertThat(String.format(msg, "パスワード", userAccountData[2]), userAccountData[2], is("azuki"));
                assertThat(String.format(msg, "実名", userAccountData[3]), userAccountData[3], is("小豆"));
                assertThat(String.format(msg, "内線番号", userAccountData[4]), userAccountData[4], is("2323"));
                assertThat(String.format(msg, "部署名", userAccountData[5]), userAccountData[5], is("庶務2課"));
                assertThat(String.format(msg, "権限", userAccountData[6]), userAccountData[6], is("1"));
            }

            @Override
            public int getNextSeq() throws DataIOException {
                return 4;
            }

            @Override
            public String[] findUserAccount(int targetUserNumber) throws DataIOException, DataFormatException {
                return new String[]{"4", "azuki", "azuki", "小豆", "2323", "庶務2課", "1"};
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "userAccountDataAccessor", uadMock);

        // 期待値を準備する
        UserAccount expected = new UserAccount(4, "azuki", "azuki", "小豆", "2323", "庶務2課", UserAccount.RESERVER);

        // テスト対象のメソッドを実行し、実測値を取得する
        // (ID="azuki" Password="azuki" 実名="小豆" 内線番号="2323" 所属="庶務2課" 権限=1 で登録)
        UserAccount actual = master.addUserAccount("azuki", "azuki", "小豆", "2323", "庶務2課", UserAccount.RESERVER);


        // 実測値と期待値がその属性を含めて等しいこと確認する
        assertThat("ユーザアカウント情報が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * 利用者情報を新規に追加登録するテスト.(登録失敗:ユーザアカウント番号の採番に失敗)<br>
     * <p>
     * 期待する結果 = 例外 {@link DataIOException} が発生する.
     *                (データアクセス層で発生した例外がそのまま通知される)
     * <p>
     */
    @Test
    public void testAddUserAccount_NumberingFailed() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public int getNextSeq() throws DataIOException {
                throw new DataIOException("テスト用ダミーメッセージ");
            }

            @Override
            public void addUserAccount(String[] userAccountData) throws DataIOException {
                fail("実行されてはいけないメソッドが実行されました.");
            }

            @Override
            public String[] findUserAccount(int targetUserNumber) throws DataIOException, DataFormatException {
                fail("実行されてはいけないメソッドが実行されました.");
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "userAccountDataAccessor", uadMock);

        // テスト対象のメソッドを実行し、実測値を取得する
        try {
            master.addUserAccount("daizu", "daizu", "大豆", "0930", "なんでもやる課", UserAccount.RESERVER);
            fail("例外DataIOExceptionが発生しませんでした.");
        } catch (DataIOException e) {
            // 例外メッセージの期待値と実測値を比較することで、データアクセス層で発生した例外が
            // テスト対象メソッド内で改変されていないことを確認する.
            assertThat("例外メッセージが改変されています.", e.getMessage(), is("テスト用ダミーメッセージ"));
        }
    }

    /**
     * 利用者情報を新規に追加登録するテスト.(登録失敗:ファイル書き込みに失敗)<br>
     * <p>
     * 期待する結果 = 例外 {@link DataIOException} が発生する.
     *                (データアクセス層で発生した例外がそのまま通知される)
     * <p>
     */
    @Test
    public void testAddUserAccount_FileWritingFailed() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public int getNextSeq() throws DataIOException {
                return 123;
            }

            @Override
            public void addUserAccount(String[] userAccountData) throws DataIOException {
                throw new DataIOException("テスト用ダミーメッセージ");
            }

            @Override
            public String[] findUserAccount(int targetUserNumber) throws DataIOException, DataFormatException {
                fail("実行されてはいけないメソッドが実行されました.");
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "userAccountDataAccessor", uadMock);

        // テスト対象のメソッドを実行し、実測値を取得する
        try {
            master.addUserAccount("daizu", "daizu", "大豆", "0930", "なんでもやる課", UserAccount.RESERVER);
            fail("例外DataIOExceptionが発生しませんでした.");
        } catch (DataIOException e) {
            // 例外メッセージの期待値と実測値を比較することで、データアクセス層で発生した例外が
            // テスト対象メソッド内で改変されていないことを確認する.
            assertThat("例外メッセージが改変されています.", e.getMessage(), is("テスト用ダミーメッセージ"));
        }
    }

    /**
     * 利用者情報を新規に追加登録するテスト.(登録失敗:ファイルの書式が不正)<br>
     * <p>
     * 期待する結果 = 例外 {@link DataFormatException} が発生する.
     *                (データアクセス層で発生した例外がそのまま通知される)
     * <p>
     */
    @Test
    public void testAddUserAccount_IllegalFileFormat() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public int getNextSeq() throws DataIOException {
                return 123;
            }

            @Override
            public void addUserAccount(String[] userAccountData) throws DataIOException {
                // 特に何もしない
            }

            @Override
            public String[] findUserAccount(int targetUserNumber) throws DataIOException, DataFormatException {
                throw new DataFormatException("テスト用ダミーメッセージ");
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "userAccountDataAccessor", uadMock);

        // テスト対象のメソッドを実行する
        try {
            master.addUserAccount("daizu", "daizu", "大豆", "0930", "なんでもやる課", UserAccount.RESERVER);
            fail("例外DataFormatExceptionが発生しませんでした.");
        } catch (DataFormatException e) {
            // 例外メッセージの期待値と実測値を比較することで、データアクセス層で発生した例外が
            // テスト対象メソッド内で改変されていないことを確認する.
            assertThat("例外メッセージが改変されています.", e.getMessage(), is("テスト用ダミーメッセージ"));
        }
    }

    /**
     * 利用者情報を新規に追加登録するテスト.(登録失敗:ファイル読込に失敗)<br>
     * <p>
     * 期待する結果 = 例外 {@link DataIOException} が発生する.
     *                (データアクセス層で発生した例外がそのまま通知される)
     * <p>
     */
    @Test
    public void testAddUserAccount_FileReadingFailed() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public int getNextSeq() throws DataIOException {
                return 123;
            }

            @Override
            public void addUserAccount(String[] userAccountData) throws DataIOException {
                // 特に何もしない
            }

            @Override
            public String[] findUserAccount(int targetUserNumber) throws DataIOException, DataFormatException {
                throw new DataIOException("テスト用ダミーメッセージ");
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "userAccountDataAccessor", uadMock);

        // テスト対象のメソッドを実行する
        try {
            master.addUserAccount("daizu", "daizu", "大豆", "0930", "なんでもやる課", UserAccount.RESERVER);
            fail("例外DataIOExceptionが発生しませんでした.");
        } catch (DataIOException e) {
            // 例外メッセージの期待値と実測値を比較することで、データアクセス層で発生した例外が
            // テスト対象メソッド内で改変されていないことを確認する.
            assertThat("例外メッセージが改変されています.", e.getMessage(), is("テスト用ダミーメッセージ"));
        }
    }

}
