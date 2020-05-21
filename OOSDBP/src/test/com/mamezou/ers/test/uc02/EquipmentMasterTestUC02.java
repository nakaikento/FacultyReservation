/*
 * EquipmentMasterTestUC02.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.mamezou.ers.business.Equipment;
import com.mamezou.ers.business.EquipmentMaster;
import com.mamezou.ers.dataaccess.EquipmentDataAccessor;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC02で実装する範囲のEquipmentMasterクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class EquipmentMasterTestUC02 extends BeanTestCase {

    private EquipmentMaster master = new EquipmentMaster();

    @Override
    protected Class<?> getBeanClass() {
        return EquipmentMaster.class;
    }

    /**
     * 施設一覧の取得が正しく行われていることをテストする.<br>
     * 施設が4件登録されている場合(データアクセス層から1件以上のデータが返された場合)
     */
    @Test
    public void testGetAllEquipments() throws Exception {
        // データアクセス層のスタブを用意する
        EquipmentDataAccessor edaMock = new EquipmentDataAccessor() {
            @Override
            public List<String[]> getAllEquipmentData() throws DataIOException {
                // テスト用のダミーデータを返す
                return Arrays.asList(
                        new String[] {"1", "大会議室", "40", "2110"}
                        , new String[] {"2", "第1会議室", "10", "2111"}
                        , new String[] {"3", "ミーティングスペースA", "4", "4210"}
                        , new String[] {"4", "ミーティングスペースB", "6", "4211"}
                        );
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "equipmentDataAccessor", edaMock);

        // テストの期待値を用意する
        Equipment[] expected = {
                new Equipment(1, "大会議室", 40, "2110")
                , new Equipment(2, "第1会議室", 10, "2111")
                , new Equipment(3, "ミーティングスペースA", 4, "4210")
                , new Equipment(4, "ミーティングスペースB", 6, "4211")
        };

        // 実測値を取得する
        List<Equipment> actual = master.getAllEquipments();


        // テストを実施する(期待値と実測値の比較)
        assertThat("実測値を取得できません", actual, is(notNullValue()));

        assertThat("取得された施設数が期待値と異なります", actual.size(), is(expected.length));

        for (int i=0; i < actual.size(); i++) {
            assertThat("インスタンスの内容が異なります. index=" + i, actual.get(i), is(equalTo(expected[i])));
        }
    }

    /**
     * 施設一覧の取得が正しく行われていることをテストする.<br>
     * 
     * データアクセス層から0件のデータが返された(施設が未登録である)場合
     */
    @Test
    public void testGetAllEquipments_NoData() throws Exception {
        // データアクセス層のスタブを用意する
        EquipmentDataAccessor edaMock = new EquipmentDataAccessor() {
            @Override
            public List<String[]> getAllEquipmentData() throws DataIOException {
                // テスト用のダミーデータを返す
                return Collections.<String[]>emptyList();
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "equipmentDataAccessor", edaMock);

        // 実測値を取得する
        List<Equipment> actual = master.getAllEquipments();

        // テストを実施する(期待値と実測値の比較)
        assertThat("実測値を取得できません", actual, is(notNullValue()));
        assertThat("取得された施設数が期待値と異なります", actual.size(), is(0));
    }

    /**
     * 登録済の施設の施設番号を指定すると、対応するEquipmentインスタンスが取得できることをテストする.<br>
     * 
     * 施設番号  = 1
     */
    @Test
    public void testFindEquipmentByEquipmentNumber() throws Exception {
        // データアクセス層のスタブを用意する
        EquipmentDataAccessor edaMock = new EquipmentDataAccessor() {
            @Override
            public String[] findEquipment(int targetEquipmentNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat("データアクセス層のメソッドに渡された引数が正しくありません.", targetEquipmentNumber, is(1));
                
                // テスト用のダミーデータを返す
                return new String[] {"1", "大会議室", "40", "2110"};
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "equipmentDataAccessor", edaMock);

        // 期待値を準備する(テスト用ダミーデータをEquipmentに変換したもの)
        Equipment expected = new Equipment(1, "大会議室", 40, "2110");

        // 実測値を取得する
        Equipment actual = master.findEquipment(1);

        // 期待値と実測値が、その属性を含めて等しいことを確認する。
        assertThat("施設インスタンスの内容が異なります.", actual, is(equalTo(expected)));
    }

    /*
     * 未登録の施設の施設番号を指定すると、null が取得されることをテストする.<br>
     * 
     * 施設番号  = 903
     */
    @Test
    public void testFindNullEquipmentByEquipmentNumber() throws Exception {
        // データアクセス層のスタブを用意する
        EquipmentDataAccessor edaMock = new EquipmentDataAccessor() {
            @Override
            public String[] findEquipment(int targetEquipmentNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                assertThat("データアクセス層のメソッドに渡された引数が正しくありません.", targetEquipmentNumber, is(903));
                
                // テスト用のダミーデータを返す
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "equipmentDataAccessor", edaMock);

        // 実測値を取得する(存在しない施設番号を指定した場合)
        Equipment actual = master.findEquipment(903);

        // 実測値が 期待値null であることを確認する。
        assertThat("存在しないはずの施設が取得されました", actual, is(nullValue()));
    }
}
