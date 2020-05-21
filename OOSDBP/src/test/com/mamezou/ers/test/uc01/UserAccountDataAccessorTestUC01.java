/*
 * UserAccountDataAccessorTestUC01.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.mamezou.ers.dataaccess.UserAccountDataAccessor;
import com.mamezou.ers.test.common.CsvFilePathConsts;
import com.mamezou.ers.test.common.CsvTestCase;

/**
 * UC01で実装する範囲のUserAccountDataAccessorクラスのテストクラス.
 * 
 * テスト用CSVファイルを読み込みます.
 * 
 * @author Mamezou Co., Ltd.
 */
public class UserAccountDataAccessorTestUC01 extends CsvTestCase {

    /** テスト対象クラスのインスタンス */
    private UserAccountDataAccessor userAccountDataAccessor
                                        = new UserAccountDataAccessor(getCsvFilePath());

    /*
     * テスト用のCSVファイルのパスを取得する.
     * @see com.mamezou.ers.test.common.CsvTestCase#getCsvFilePath()
     */
    @Override
    protected String getCsvFilePath() {
        return CsvFilePathConsts.USER_ACCOUNT;
    }

    /**
     * UC01で実装
     * 存在するIDとパスワードの組み合わせを指定すると正しくCSVファイルからデータを取得できることをテストする
     */
    @Test
    public void testFindUserAccountByIdAndPassword() throws Exception {
        String loginName = "soramame";
        String password = "soramame";
        String[] expected = { "1", "soramame", "soramame", "そらまめ", "4101", "教育事業部", "1" };
        String[] actual = userAccountDataAccessor.findUserAccount(loginName, password);
        assertThat("正しいデータが取得できません", actual, is(equalTo(expected)));
    }

    /**
     * UC01で実装
     * 存在しないIDとパスワードの組み合わせを指定するとNullを返すことをテストする
     */
    @Test
    public void testFindNullUserAccountByIdAndPassword() throws Exception {
        String loginName = "soramame";
        String password = "azuki";
        String[] actual = userAccountDataAccessor.findUserAccount(loginName, password);
        assertThat("存在しないはずのデータを読み込みました", actual, is(nullValue()));
    }
}
