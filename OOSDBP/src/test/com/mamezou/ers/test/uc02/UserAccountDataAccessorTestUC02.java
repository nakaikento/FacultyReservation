/*
 * UserAccountDataAccessorTestUC02.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc02;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.mamezou.ers.dataaccess.UserAccountDataAccessor;
import com.mamezou.ers.test.common.CsvFilePathConsts;
import com.mamezou.ers.test.common.CsvTestCase;

/**
 * UC02で実装する範囲のUserAccountDataAccessorクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class UserAccountDataAccessorTestUC02 extends CsvTestCase {

    private UserAccountDataAccessor userAccountDataAccessor
                    = new UserAccountDataAccessor(getCsvFilePath());

    @Override
    protected String getCsvFilePath() {
        return CsvFilePathConsts.USER_ACCOUNT;
    }

    /* UC02で実装
     * 存在する利用者番号を指定すると正しくCVSからデータを取得できることをテストする
     */
    @Test
    public void testFindUserAccountByAccountNumber() throws Exception {
        int userAccountNumber = 1;
        String[] expected = { "1", "soramame", "soramame", "そらまめ", "4101",
                "教育事業部", "1" };
        String[] actual = userAccountDataAccessor.findUserAccount(userAccountNumber);
        assertThat("正しいデータが取得できません", actual, is(equalTo(expected)));
    }

    /* UC02で実装
     * 存在しない利用者番号を指定するとNullを返すことをテストする
     */
    @Test
    public void testFindNullUserAccountByAccountNumber() throws Exception {
        int userAccountNumber = 999;
        String[] actual = userAccountDataAccessor.findUserAccount(userAccountNumber);
        assertThat("存在しないはずのデータを読み込みました", actual, is(nullValue()));
    }
}
