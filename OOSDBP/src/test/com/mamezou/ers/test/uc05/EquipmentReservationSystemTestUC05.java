/*
 * EquipmentReservationSystemTestUC05.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.business.UserAccount;
import com.mamezou.ers.business.UserAccountMaster;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.exceptions.DataValueException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC05で実装する範囲のEquipmentReservationSystemクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class EquipmentReservationSystemTestUC05 extends BeanTestCase {

    private EquipmentReservationSystem system;

    @Override
    protected Class<?> getBeanClass() {
        return EquipmentReservationSystem.class;
    }

    @Before
    public void setUp() throws Exception {
        system = new EquipmentReservationSystem();
    }

    /**
     * 利用者情報を新規に追加登録するテスト.(登録成功)<br>
     * <p>
     * 期待する結果 = ユーザアカウント番号が発行され、登録した情報を持つユーザアカウントインスタンスが返される.
     * <p>
     */
    @Test
    public void testAddUserAccount() throws Exception {
        // UserAccountMasterのスタブを用意する
        UserAccountMaster uamMock = new UserAccountMaster() {
            @Override
            public UserAccount findUserAccount(String loginName) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "UserAccountMaster#findUserAccount(String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "ログイン名", loginName), loginName, is(equalTo("azuki")));
                return null;
            }
            
            @Override
            public UserAccount addUserAccount(String loginName, String password, String realName,
                    String extensionNumber, String divisionName, int authority) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "UserAccountMaster#addUserAccount(String, String, String, String, String, int)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "ログイン名", loginName), loginName, is("azuki"));
                assertThat(String.format(msg, "パスワード", password), password, is("azuki"));
                assertThat(String.format(msg, "実名", realName), realName, is("小豆"));
                assertThat(String.format(msg, "内線番号", extensionNumber), extensionNumber, is("2323"));
                assertThat(String.format(msg, "部署名", divisionName), divisionName, is("庶務2課"));
                assertThat(String.format(msg, "権限", authority),authority, is(UserAccount.RESERVER));
                
                return new UserAccount(4, "azuki", "azuki", "小豆", "2323", "庶務2課", UserAccount.RESERVER);
            }
        };
        
        // テスト対象インスタンスのインスタンス変数をスタブに置き換える
        setMockObject(system, "userAccountMaster", uamMock);
        
        // 期待値を準備する
        UserAccount expected = new UserAccount(4, "azuki", "azuki", "小豆", "2323", "庶務2課", 1);

        // 実測値を取得する
        // (ID="azuki" Password="azuki" 実名="小豆" 内線番号="2323" 所属="庶務2課" 権限=1 で登録)
        UserAccount actual = system.addUserAccount("azuki", "azuki", "小豆", "2323", "庶務2課", 1);

        // 実測値と期待値がその属性を含めて等しいこと確認する
        assertThat("ユーザアカウント情報が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * 利用者情報を新規に追加登録するテスト.(登録失敗:同一のログイン名のユーザが登録済である)<br>
     * <p>
     * 期待する結果 = 例外 {@link DataValueException} が発生する.
     * <p>
     */
    @Test
    public void testAddUserAccount_AlreadyExists() throws Exception {
        // UserAccountMasterのスタブを用意する
        UserAccountMaster uamMock = new UserAccountMaster() {
            @Override
            public UserAccount findUserAccount(String loginName) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "UserAccountMaster#findUserAccount(String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "ログイン名", loginName), loginName, is(equalTo("soramame")));
                // null 以外を返す
                return new UserAccount();
            }
            
            @Override
            public UserAccount addUserAccount(String loginName, String password, String realName,
                    String extensionNumber, String divisionName, int authority) throws DataIOException,
                    DataFormatException {
                fail("実行されてはいけないメソッドが実行されました.");
                return null;
            }
        };
        
        // テスト対象インスタンスのインスタンス変数をスタブに置き換える
        setMockObject(system, "userAccountMaster", uamMock);
        
        try {
            system.addUserAccount("soramame", "soramame", "そらまめ", "2323", "庶務2課", 1);
            fail("例外DataValueExceptionが発生しませんでした.");
        } catch (DataValueException e) {
            assertThat("例外メッセージが改変されています.", e.getMessage(), is("ログイン名は既に登録されています。"));
        }
    }

}
