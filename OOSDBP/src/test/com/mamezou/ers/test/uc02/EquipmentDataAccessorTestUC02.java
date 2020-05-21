/*
 * EquipmentDataAccessorTestUC02.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc02;

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
 * UC02で実装する範囲のEquipmentDataAccessorクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class EquipmentDataAccessorTestUC02 extends CsvTestCase {

    private EquipmentDataAccessor equipmentDataAccessor;

    @Override
    protected String getCsvFilePath() {
        return CsvFilePathConsts.EQUIPMENT;
    }

    @Before
    public void setUp() {
        equipmentDataAccessor = new EquipmentDataAccessor(getCsvFilePath());
    }

    /* UC02で実装
     * CSVから施設データ一覧の取得が正しく行われていることをテストする
     */
    @Test
    public void testGetAllEquipmentData() throws Exception {
        String[][] expect = { { "1", "大会議室", "40", "2110" },
                { "2", "第1会議室", "10", "2111" },
                { "3", "ミーティングスペースA", "4", "4210" },
                { "4", "ミーティングスペースB", "6", "4211" } };

        List<String[]> dataList = equipmentDataAccessor.getAllEquipmentData();

        assertThat("データ件数が期待値と異なります", dataList.size(), is(4));

        for (int i = 0; i < dataList.size(); i++) {
            String[] record = dataList.get(i);
            assertThat((i + 1) + "行目のデータが異なります", record, is(equalTo(expect[i])));
        }
    }

    /* UC02で実装
     * 存在する施設番号を指定すると正しくCVSからデータが取得できることをテストする
     */
    @Test
    public void testFindEquipmentByEquipmentNumber() throws Exception {
        String[] expected = { "3", "ミーティングスペースA", "4", "4210" };

        //正常系
        String[] actual = equipmentDataAccessor.findEquipment(3);
        assertThat("指定した施設が見つかりません", actual, is(equalTo(expected)));
    }

    /* UC02で実装
     * 存在しない施設番号を指定するとNullを返すことをテストする
     */
    @Test
    public void testFindNullEquipmentByEquipmentNumber() throws Exception {
        //異常系
        String[] actual = equipmentDataAccessor.findEquipment(100);
        assertThat("存在しないはずのデータが存在します", actual, is(nullValue()));
    }
}
