/*
 * UserAccountTest.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.business;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.business.UserAccount;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UserAccountクラスのテストクラス.
 *
 * UserAccountクラスはあらかじめ提供されるクラスです.
 *
 * @author Mamezou Co., Ltd.
 */
public class UserAccountTest extends BeanTestCase {

    /** テスト対象となる UserAccountクラスのインスタンス */
    private UserAccount anUserAccount;

    @Override
    protected Class<UserAccount> getBeanClass() {
        return UserAccount.class;
    }

    @Before
    public void setUp() {
        anUserAccount = new UserAccount();
    }

    /**
     * テスト対象のUserAccountクラスに、ユーザアカウント番号を表すインスタンス変数 number が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_number() {
        assertThat("施設番号を表すインスタンス変数 number が定義されていません",
                fields.get("number"), is(notNullValue()));
    }

    /**
     * テスト対象のUserAccountクラスに、ログイン名を表すインスタンス変数 loginName が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_loginName() {
        assertThat("ログイン名を表すインスタンス変数 loginName が定義されていません",
                fields.get("loginName"), is(notNullValue()));
    }

    /**
     * テスト対象のUserAccountクラスに、パスワードを表すインスタンス変数 password が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_password() {
        assertThat("パスワードを表すインスタンス変数 password が定義されていません",
                fields.get("password"), is(notNullValue()));
    }

    /**
     * テスト対象のUserAccountクラスに、ユーザの実名を表すインスタンス変数 realName が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_realName() {
        assertThat("ユーザの実名を表すインスタンス変数 realName が定義されていません",
                fields.get("realName"), is(notNullValue()));
    }

    /**
     * テスト対象のUserAccountクラスに、内線番号を表すインスタンス変数 extensionNumber が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_extensionNumber() {
        assertThat("内線番号を表すインスタンス変数 extensionNumber が定義されていません",
                fields.get("extensionNumber"), is(notNullValue()));
    }

    /**
     * テスト対象のUserAccountクラスに、部署名を表すインスタンス変数 divisionName が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_divisionName() {
        assertThat("部署名を表すインスタンス変数 divisionName が定義されていません",
                fields.get("divisionName"), is(notNullValue()));
    }

    /**
     * テスト対象のUserAccountクラスに、権限を表すインスタンス変数 authority が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_authority() {
        assertThat("権限を表すインスタンス変数 authority が定義されていません",
                fields.get("authority"), is(notNullValue()));
    }

    /**
     * void setNumber(int) のテストメソッド。
     * インスタンス変数 number に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetNumber() throws Exception {
        anUserAccount.setNumber(100);

        Field number = (Field) fields.get("number");
        assertThat("ユーザアカウント番号が正しく設定されていません", number.getInt(anUserAccount), is(100));
    }

    /**
     * int getNumber() のテストメソッド。
     * インスタンス変数 number に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    @Test
    public void testGetNumber() throws Exception {
        Field number = (Field) fields.get("number");
        number.setInt(anUserAccount, 100);
        assertThat("ユーザアカウント番号が正しく取得できません", anUserAccount.getNumber(), is(100));
    }

    /**
     * void setLoginName(String) のテストメソッド。
     * インスタンス変数 loginName に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetLoginName() throws Exception {
        anUserAccount.setLoginName("endoumame");

        Field loginName = (Field) fields.get("loginName");
        assertThat("ログイン名が正しく設定されていません", (String) loginName.get(anUserAccount), is(equalTo("endoumame")));
    }

    /**
     * String getLoginName() のテストメソッド。
     * インスタンス変数 loginName に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    @Test
    public void testGetLoginName() throws Exception {
        Field loginName = (Field) fields.get("loginName");
        loginName.set(anUserAccount, "edamame");
        assertThat("ログイン名が正しく取得できません", anUserAccount.getLoginName(), is(equalTo("edamame")));
    }

    /**
     * void setPassword(String) のテストメソッド。
     * インスタンス変数 password に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetPassword() throws Exception {
        anUserAccount.setPassword("endoumame");
        Field password = (Field) fields.get("password");
        assertThat("パスワードが正しく設定されていません", (String) password.get(anUserAccount), is(equalTo("endoumame")));
    }

    /**
     * String getPassword() のテストメソッド。
     * インスタンス変数 password に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    @Test
    public void testGetPassword() throws Exception {
        Field password = (Field) fields.get("password");
        password.set(anUserAccount, "edamame");
        assertThat("パスワードが正しく取得できません", anUserAccount.getPassword(), is(equalTo("edamame")));
    }

    /**
     * void setRealName(String) のテストメソッド。
     * インスタンス変数 realName に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetRealName() throws Exception {
        anUserAccount.setRealName("えんどう豆");
        Field realName = (Field) fields.get("realName");
        assertThat("ユーザの実名が正しく設定されていません", (String) realName.get(anUserAccount), is(equalTo("えんどう豆")));
    }

    /**
     * String getRealName() のテストメソッド。
     * インスタンス変数 realName に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    @Test
    public void testGetRealName() throws Exception {
        Field realName = (Field) fields.get("realName");
        realName.set(anUserAccount, "えだ豆");
        assertThat("ユーザの実名が正しく取得できません", anUserAccount.getRealName(), is(equalTo("えだ豆")));
    }

    /**
     * void setExtensionNumber(String) のテストメソッド。
     * インスタンス変数 extensionNumber に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetExtensionNumber() throws Exception {
        anUserAccount.setExtensionNumber("4649");
        Field extensionNumber = (Field) fields.get("extensionNumber");
        assertThat("内線番号が正しく設定されていません", (String) extensionNumber.get(anUserAccount), is(equalTo("4649")));
    }

    /**
     * String getExtensionNumber() のテストメソッド。
     * インスタンス変数 extensionNumber に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    @Test
    public void testGetExtensionNumber() throws Exception {
        Field extensionNumber = (Field) fields.get("extensionNumber");
        extensionNumber.set(anUserAccount, "5963");
        assertThat("内線番号が正しく取得できません", anUserAccount.getExtensionNumber(), is(equalTo("5963")));
    }

    /**
     * void setDivisionName(String) のテストメソッド。
     * インスタンス変数 divisionName に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetDivisionName() throws Exception {
        anUserAccount.setDivisionName("経理部");
        Field divisionName = (Field) fields.get("divisionName");
        assertThat("部署名が正しく設定されていません", (String) divisionName.get(anUserAccount), is(equalTo("経理部")));
    }

    /**
     * String getDivisionName() のテストメソッド。
     * インスタンス変数 divisionName に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    @Test
    public void testGetDivisionName() throws Exception {
        Field divisionName = (Field) fields.get("divisionName");
        divisionName.set(anUserAccount, "捜査一課");
        assertThat("部署名が正しく取得できません", anUserAccount.getDivisionName(), is(equalTo("捜査一課")));
    }

    /**
     * void setAuthority(int) のテストメソッド。
     * インスタンス変数 authority に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetAuthority() throws Exception {
        anUserAccount.setAuthority(1);
        Field authority = (Field) fields.get("authority");
        assertThat("権限が正しく設定されていません", authority.getInt(anUserAccount), is(1));
    }

    /**
     * int getAuthority() のテストメソッド。
     * インスタンス変数 authority に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    @Test
    public void testGetAuthority() throws Exception {
        Field authority = (Field) fields.get("authority");
        authority.setInt(anUserAccount, 0);
        assertThat("権限が正しく取得できません", anUserAccount.getAuthority(), is(0));
    }
}
