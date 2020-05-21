/*
 * EquipmentReservationSystemTestUC01.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.business.UserAccount;
import com.mamezou.ers.business.UserAccountMaster;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.exceptions.DataValueException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC01で実装する範囲のEquipmentReservationSystemクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class EquipmentReservationSystemTestUC01 extends BeanTestCase {

    @Override
    protected Class<?> getBeanClass() {
        return EquipmentReservationSystem.class;
    }

    @Test
    public void インスタンス変数の宣言と初期値の設定() throws Exception {
        EquipmentReservationSystem system = new EquipmentReservationSystem();

        Field f = fields.get("userAccountMaster");
        assertThat("インスタンス変数 userAccountMaster が定義されていません",
                f, is(notNullValue()));
        assertThat("インスタンス変数 userAccountMasterが null です.",
                f.get(system), is(notNullValue()));
        
        f = fields.get("reservationMaster");
        assertThat("インスタンス変数 reservationMaster が定義されていません",
                f, is(notNullValue()));
        assertThat("インスタンス変数 reservationMasterが null です.",
                f.get(system), is(notNullValue()));
        
        f = fields.get("equipmentMaster");
        assertThat("インスタンス変数 equipmentMaster が定義されていません",
                f, is(notNullValue()));
        assertThat("インスタンス変数 equipmentMasterが null です.",
                f.get(system), is(notNullValue()));
    }

    /* UC01で実装
     * 存在するIDとパスワードの組み合わせを指定すると正しくデータが取得できることをテストする
     */
    @Test
    public void testFindUserAccountByIdAndPassword() throws Exception {
        UserAccountMaster uamMock = new UserAccountMaster() {
            @Override
            public UserAccount findUserAccount(String loginName, String password) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat("", loginName, is(equalTo("soramame")));
                assertThat("", password, is(equalTo("soramame")));
                return new UserAccount(1, "soramame", "soramame", "そらまめ", "4101", "教育事業部", UserAccount.RESERVER);
            }
        };
        
        // テスト対象インスタンスのインスタンス変数をスタブに置き換える
        EquipmentReservationSystem system = new EquipmentReservationSystem();
        Field f = fields.get("userAccountMaster");
        f.set(system, uamMock);
        
        // テスト対象メソッドの呼び出し
        UserAccount actual = system.findUserAccount("soramame", "soramame");

        //取得できたか否か
        assertThat("存在するはずのユーザアカウントが取得できません", actual, is(notNullValue()));

        //取得できた場合、含まれるデータが正しいか(テスト対象のメソッド内で改変されていないことをテスト)
        assertThat("取得したユーザアカウント番号が異なります", actual.getNumber(), is(1));
        assertThat("取得したユーザのログイン名が異なります", actual.getLoginName(), is(equalTo("soramame")));
        assertThat("取得したユーザのパスワードが異なります", actual.getPassword(), is(equalTo("soramame")));
        assertThat("取得したユーザの実名が異なります", actual.getRealName(), is(equalTo("そらまめ")));
        assertThat("取得したユーザの内線番号が異なります", actual.getExtensionNumber(), is(equalTo("4101")));
        assertThat("取得したユーザの部署が異なります", actual.getDivisionName(), is(equalTo("教育事業部")));
        assertThat("取得したユーザの権限が異なります", actual.getAuthority(), is(1));
    }

    /* UC01で実装
     * IDとパスワードの組み合わせが無効なものを指定すると例外が発生することをテストする
     */
    @Test(expected=DataValueException.class)
    public void testCannotFindUserAccountByIdAndPassword_01() throws Exception {
        UserAccountMaster uamMock = new UserAccountMaster() {
            @Override
            public UserAccount findUserAccount(String loginName, String password) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat("", loginName, is(equalTo("soramame")));
                assertThat("", password, is(equalTo("hoge")));
                
                // 無効なIDとパスワードの組なので、null を返す
                return null;
            }
        };

        EquipmentReservationSystem system = new EquipmentReservationSystem();
        Field f = fields.get("userAccountMaster");
        f.set(system, uamMock);

        // 有効なログイン名、無効なパスワードを指定した場合
        system.findUserAccount("soramame", "hoge");
    }

    /* UC01で実装
     * IDとパスワードの組み合わせが無効なものを指定すると例外が発生することをテストする
     */
    @Test(expected=DataValueException.class)
    public void testCannotFindUserAccountByIdAndPassword_02() throws Exception {
        UserAccountMaster uamMock = new UserAccountMaster() {
            @Override
            public UserAccount findUserAccount(String loginName, String password) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat("", loginName, is(equalTo("hoge")));
                assertThat("", password, is(equalTo("soramame")));
                
                // 無効なIDとパスワードの組なので、null を返す
                return null;
            }
        };

        EquipmentReservationSystem system = new EquipmentReservationSystem();
        Field f = fields.get("userAccountMaster");
        f.set(system, uamMock);

        // 無効なログイン名、誰かが使用しているパスワードを指定した場合
        system.findUserAccount("hoge", "soramame");
    }

    /* UC01で実装
     * IDとパスワードの組み合わせが無効なものを指定すると例外が発生することをテストする
     */
    @Test(expected=DataValueException.class)
    public void testCannotFindUserAccountByIdAndPassword_03() throws Exception {
        UserAccountMaster uamMock = new UserAccountMaster() {
            @Override
            public UserAccount findUserAccount(String loginName, String password) throws DataIOException,
                    DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat("", loginName, is(equalTo("hoge")));
                assertThat("", password, is(equalTo("hoge")));
                
                // 無効なIDとパスワードの組なので、null を返す
                return null;
            }
        };

        EquipmentReservationSystem system = new EquipmentReservationSystem();
        Field f = fields.get("userAccountMaster");
        f.set(system, uamMock);

        // 無効なログイン名、誰も使用していないパスワードを指定した場合
        system.findUserAccount("hoge", "hoge");
    }
}
