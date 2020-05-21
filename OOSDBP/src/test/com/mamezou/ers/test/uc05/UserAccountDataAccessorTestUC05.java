/*
 * UserAccountDataAccessorTestUC05.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.dataaccess.UserAccountDataAccessor;
import com.mamezou.ers.test.common.CsvFilePathConsts;
import com.mamezou.ers.test.common.CsvTestCase;

/**
 * UC05で実装する範囲のUserAccountDataAccessorクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class UserAccountDataAccessorTestUC05 extends CsvTestCase {

    /** テスト対象となるUserAccountDataAccessorクラスのインスタンス(への参照) */
    private UserAccountDataAccessor userAccountDataAccessor;

    @Before
    public void setUp() {
        userAccountDataAccessor = new UserAccountDataAccessor(getCsvFilePath());
    }

    @Override
    protected String getCsvFilePath() {
        return CsvFilePathConsts.USER_ACCOUNT;
    }

    /*
     * 存在するIDを指定すると正しくCSVファイルからデータを取得できることをテストする
     */
    @Test
    public void testFindUserAccountById() throws Exception {
        // 期待値を準備する
        String[] expected = { "1", "soramame", "soramame", "そらまめ", "4101", "教育事業部", "1" };

        // 実測値を取得する
        // (ID="soramame")
        String[] actual = userAccountDataAccessor.findUserAccount("soramame");

        assertThat("正しいデータが取得できません", actual, is(equalTo(expected)));
    }

    /*
     * 存在しないIDを指定するとNullを返すことをテストする
     */
    @Test
    public void testFindUserAccountById_NoData() throws Exception {
        // 実測値を取得する
        // (ID="daizu")
        String[] actual = userAccountDataAccessor.findUserAccount("daizu");
        assertThat("存在しないはずのデータを読み込みました", actual, is(nullValue()));
    }

    /*
     * 利用者データを正しくCSVファイルに追加できることをテストする
     */
    @Test
    public void testAddUserAccount() throws Exception {
        // 追加するデータを準備する
        String[] addData = { "4", "endo-mame", "endo-mame", "えんどうまめ", "9190", "教育事業部", "1" };

        // テスト対象のメソッドを実行する
        userAccountDataAccessor.addUserAccount(addData);

        // CSVファイルの内容を全件読み込む
        List<String[]> records = getAllRecords();

        // CSVファイルの行数(=利用者データ件数)が 期待値と等しいことを確認する
        // (変更前は4件なので、5が期待値となる)
        assertThat("データ件数が異なります", records.size(), is(5));

        // 最後の1行の内容が、追加メソッド実行時に指定した内容と同じであることを確認する
        String[] lastRecord = records.get(records.size() - 1);
        assertThat("正しくデータが追加できません", lastRecord, is(equalTo(addData)));
    }

    /*
     * 利用者番号を正しく取得できることをテストする
     */
    @Test
    public void testGetNextSeq() throws Exception {
        int actual = userAccountDataAccessor.getNextSeq();
        assertThat("シーケンス番号が正しくありません", actual, is(5));
    }
}
