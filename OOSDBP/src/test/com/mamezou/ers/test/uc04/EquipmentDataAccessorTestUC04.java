/*
 * EquipmentDataAccessorTest04.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc04;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.dataaccess.EquipmentDataAccessor;
import com.mamezou.ers.test.common.CsvFilePathConsts;
import com.mamezou.ers.test.common.CsvTestCase;

/**
 * UC04で実装する範囲のEquipmentDataAccessorクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class EquipmentDataAccessorTestUC04 extends CsvTestCase {
    private EquipmentDataAccessor equipmentDataAccessor;

    @Before
    public void setUp() {
        equipmentDataAccessor = new EquipmentDataAccessor(getCsvFilePath());
    }

    @Override
    protected String getCsvFilePath() {
        return CsvFilePathConsts.EQUIPMENT;
    }

    /*
     * 存在する施設名を指定すると正しくCVSからデータが取得できることをテストする
     */
    @Test
    public void testFindEquipmentByName() throws Exception {
        String[] expected = { "2", "第1会議室", "10", "2111" };
        //正常系
        String[] actual = equipmentDataAccessor.findEquipment("第1会議室");
        assertThat("指定した施設が見つかりません", actual, is(equalTo(expected)));
    }

    /*
     * 存在しない施設名を指定するとNullを返すことをテストする
     */
    @Test
    public void testFindNullEquipmentByName() throws Exception {
        //異常系
        String[] actual = equipmentDataAccessor.findEquipment("第100会議室");
        assertThat("存在しないはずのデータが存在します", actual, is(nullValue()));
    }

    /*
     * シーケンス番号が正しく取得できることをテストする
     */
    @Test
    public void testGetNextSeq() throws Exception {
        int actual = equipmentDataAccessor.getNextSeq();
        assertThat("", actual, is(5));
    }

    /*
     * 施設データが正しくCVSに登録できることをテストする
     * @throws Exception 確認のための全レコード取得時に何らかの入出力エラーが発生した場合
     */
    @Test
    public void testAddEquipment() throws Exception {
        // 登録するデータ、兼、期待値 を用意する
        String[] addData = { "100", "応接室", "6", "9999" };

        // テスト対象のメソッドを実行する
        equipmentDataAccessor.addEquipment(addData);

        // CSVファイルの全件を取得し、最後の1件が新規に登録したデータと等しいことを確認する
        List<String[]> records = getAllRecords();
        String[] lastRecord = records.get(records.size() - 1);
        assertThat("正しいデータで追加されませんでした", lastRecord, is(equalTo(addData)));
    }
}
