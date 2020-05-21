/*
 * UserAccountMasterTestUC02.java
 * Copyright (C) 2011 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.business.UserAccount;
import com.mamezou.ers.business.UserAccountMaster;
import com.mamezou.ers.dataaccess.UserAccountDataAccessor;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC02で実装する範囲のUserAccountMasterクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class UserAccountMasterTestUC02 extends BeanTestCase {

    private UserAccountMaster master;
    
    @Override
    protected Class<?> getBeanClass() {
        return UserAccountMaster.class;
    }

    @Before
    public void setUp() {
        master = new UserAccountMaster();
    }

    /*
     * 存在するユーザアカウント番号を指定すると正しく利用者データを取得できることをテストする
     */
    @Test
    public void testFindUserAccountByAccountNumber() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public String[] findUserAccount(int targetUserNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat("", targetUserNumber, is(equalTo(1)));
                // 見つかった場合のダミーデータを返す
                return new String[] {"1", "soramame", "soramame", "そらまめ", "4101", "教育事業部", "1"};
            }
        };
        
        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "userAccountDataAccessor", uadMock);

        // 期待値を用意する
        UserAccount expected = new UserAccount(1, "soramame", "soramame", "そらまめ", "4101", "教育事業部", UserAccount.RESERVER);

        // 実測値を取得する
        UserAccount actual = master.findUserAccount(1);

        // 実測値のオブジェクトが持つ属性値が正しい(期待値と等しい)ことを確認する
        assertThat("ユーザアカウント情報が異なります.", actual, is(equalTo(expected)));
    }

    /*
     * 存在しないユーザアカウント番号を指定するとNullが返ることをテストする
     */
    @Test
    public void testFindNullUserAccountByAccountNumber() throws Exception {
        // データアクセス層のスタブを用意する
        UserAccountDataAccessor uadMock = new UserAccountDataAccessor() {
            @Override
            public String[] findUserAccount(int targetUserNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat("UserAccountDataAccessor#findUserAccount(int) の引数が期待値と異なります", targetUserNumber, is(equalTo(555)));
                // 見つかない場合のダミーデータを返す
                return null;
            }
        };
        
        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "userAccountDataAccessor", uadMock);

        UserAccount actual = master.findUserAccount(555);
        assertThat("存在しないはずのユーザアカウントが取得されました", actual, is(nullValue()));
    }
}
