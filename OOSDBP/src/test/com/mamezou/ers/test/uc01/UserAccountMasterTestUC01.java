/*
 * UserAccountMasterTestUC01.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import com.mamezou.ers.business.UserAccount;
import com.mamezou.ers.business.UserAccountMaster;
import com.mamezou.ers.dataaccess.UserAccountDataAccessor;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC01で実装する範囲のUserAccountMasterクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class UserAccountMasterTestUC01 extends BeanTestCase {

    @Override
    protected Class<?> getBeanClass() {
        return UserAccountMaster.class;
    }

    @Test
    public void インスタンス変数の宣言と初期値の設定() throws Exception {
        UserAccountMaster master = new UserAccountMaster();
        Field f = fields.get("userAccountDataAccessor");
        
        assertThat("インスタンス変数 userAccountDataAccessor が定義されていません",
                f, is(notNullValue()));
        assertThat("インスタンス変数 userAccountDataAccessorが null です.",
                f.get(master), is(notNullValue()));
    }
    
    /**
     * 存在するIDとパスワードを指定すると正しく利用者データを取得できることをテストする
     */
    @Test
    public void testFindUserAccountByIdAndPassword() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public String[] findUserAccount(String targetLoginName, String targetPassword) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat(targetLoginName, is(equalTo("soramame")));
                assertThat(targetPassword, is(equalTo("soramame")));
                
                // ダミーの戻り値を返す
                return new String[]{"1", "soramame", "soramame", "そらまめ", "4101", "教育事業部", "1"};
            }
        };

        // スタブをテスト対象インスタンスに設定する
        UserAccountMaster master = new UserAccountMaster(uadMock);


        // 実測値を取得する
        UserAccount actual = master.findUserAccount("soramame", "soramame");

        // 期待値を用意する
        UserAccount expected = new UserAccount(1, "soramame", "soramame", "そらまめ", "4101", "教育事業部", 1);

        // 実測値のオブジェクトが持つ属性値が正しい(期待値と等しい)ことを確認する
        assertThat("ユーザアカウント情報が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * テストデータに存在しない組み合わせのIDとパスワードを指定するとnullが返ることを確認する.
     * (ID="soramame", password="hoge"; IDは正規のものだが、パスワードが異なる場合)
     */
    @Test
    public void testFindNullUserAccountByIdAndPassword_01() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public String[] findUserAccount(String targetLoginName, String targetPassword) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat(targetLoginName, is(equalTo("soramame")));
                assertThat(targetPassword, is(equalTo("hoge")));
                // ダミーの戻り値を返す
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        UserAccountMaster master = new UserAccountMaster(uadMock);


        // 実測値を取得する
        UserAccount actual = master.findUserAccount("soramame", "hoge");

        // 実測値が null であることを確認する
        assertThat("存在しないはずのユーザアカウントが取得されました", actual, is(nullValue()));
    }

    /**
     * テストデータに存在しない組み合わせのIDとパスワードを指定するとnullが返ることを確認する.
     * (ID="hoge", password="soramame"; 存在しないIDと誰かが利用しているパスワードの場合)
     */
    @Test
    public void testFindNullUserAccountByIdAndPassword_02() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public String[] findUserAccount(String targetLoginName, String targetPassword) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat(targetLoginName, is(equalTo("hoge")));
                assertThat(targetPassword, is(equalTo("soramame")));
                // ダミーの戻り値を返す
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        UserAccountMaster master = new UserAccountMaster(uadMock);


        // 実測値を取得する
        UserAccount actual = master.findUserAccount("hoge", "soramame");

        // 実測値が null であることを確認する
        assertThat("存在しないはずのユーザアカウントが取得されました", actual, is(nullValue()));
    }

    /**
     * テストデータに存在しない組み合わせのIDとパスワードを指定するとnullが返ることを確認する.
     * (ID="hoge", password="hoge"; IDもパスワードも存在しない場合)
     */
    @Test
    public void testFindNullUserAccountByIdAndPassword_03() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public String[] findUserAccount(String targetLoginName, String targetPassword) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat(targetLoginName, is(equalTo("hoge")));
                assertThat(targetPassword, is(equalTo("hoge")));
                // ダミーの戻り値を返す
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        UserAccountMaster master = new UserAccountMaster(uadMock);


        // 実測値を取得する
        UserAccount actual = master.findUserAccount("hoge", "hoge");

        // 実測値が null であることを確認する
        assertThat("存在しないはずのユーザアカウントが取得されました", actual, is(nullValue()));
    }
}
