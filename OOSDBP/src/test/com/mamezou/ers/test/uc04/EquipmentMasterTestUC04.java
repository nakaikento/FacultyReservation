/*
 * EquipmentMasterTestUC04.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.business.Equipment;
import com.mamezou.ers.business.EquipmentMaster;
import com.mamezou.ers.dataaccess.EquipmentDataAccessor;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC04で実装する範囲のEquipmentMasterクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class EquipmentMasterTestUC04 extends BeanTestCase {

    private EquipmentMaster master;

    @Before
    public void setUp() {
        master = new EquipmentMaster();
    }

    @Override
    protected Class<?> getBeanClass() {
        return EquipmentMaster.class;
    }

    /**
     * 検索条件に該当する施設が存在する場合のテスト.<br>
     * <p>
     * 検索条件 = {施設名=大会議室}
     * </p>
     * <p>
     * 期待する結果 = 1件の施設が取得できる.
     * <p>
     */
     @Test
    public void testFindEquipmentByName() throws Exception {
        // データアクセス層のスタブを用意する
        EquipmentDataAccessor edaMock = new EquipmentDataAccessor() {
            @Override
            public String[] findEquipment(String targetEquipmentName) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentDataAccessor#findEquipment(String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設名", targetEquipmentName), targetEquipmentName, is("大会議室"));
                
                // テスト用のダミーデータを返す
                return new String[] {"1", "大会議室", "40", "2110"};
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "equipmentDataAccessor", edaMock);

        // 期待値を準備する
        Equipment expected = new Equipment(1, "大会議室", 40, "2110");

        // テスト対象のメソッドを実行し、実測値を取得する
        Equipment actual = master.findEquipment("大会議室");

        // 期待値と実測値が、その属性を含めて等しいことを確認する。
        assertThat("施設インスタンスの内容が異なります.", actual, is(equalTo(expected)));
    }

     /**
      * 検索条件に該当する施設が存在しない場合のテスト.<br>
      * <p>
      * 検索条件 = {施設名=鉄子の部屋}
      * </p>
      * <p>
      * 期待する結果 = null が返される
      * <p>
      */
    @Test
    public void testFindEquipmentByName_NoData() throws Exception {
        // データアクセス層のスタブを用意する
        EquipmentDataAccessor edaMock = new EquipmentDataAccessor() {
            @Override
            public String[] findEquipment(String targetEquipmentName) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentDataAccessor#findEquipment(String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設名", targetEquipmentName), targetEquipmentName, is("鉄子の部屋"));
                
                // テスト用のダミーデータを返す
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "equipmentDataAccessor", edaMock);

        // テスト対象のメソッドを実行し、実測値を取得する
        Equipment actual = master.findEquipment("鉄子の部屋");

        // 期待値と実測値が等しいことを確認する。
        assertThat("存在しないはずの施設が取得されました", actual, is(nullValue()));
    }

    /**
     * 施設を新規に追加登録するテスト.<br>
     * <p>
     * 期待する結果 = 施設番号が発行され、登録した情報を持つ施設インスタンスが返される.
     * <p>
     */
    @Test
    public void testAddEquipment() throws Exception {
        // データアクセス層のスタブを用意する
        EquipmentDataAccessor edaMock = new EquipmentDataAccessor() {
            @Override
            public int getNextSeq() throws DataIOException {
                return 5;
            }

            @Override
            public void addEquipment(String[] equipmentData) throws DataIOException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentDataAccessor#addEquipment(String[])に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号(index=0)", equipmentData[0]), equipmentData[0], is("5"));
                assertThat(String.format(msg, "施設名(index=1)", equipmentData[1]), equipmentData[1], is( "教室A"));
                assertThat(String.format(msg, "定員数(index=2)", equipmentData[2]), equipmentData[2], is("22"));
                assertThat(String.format(msg, "内線番号(index=3)", equipmentData[3]), equipmentData[3], is("9696"));
            }

            @Override
            public String[] findEquipment(int targetEquipmentNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentDataAccessor#findEquipment(int)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber), targetEquipmentNumber, is(5));
                // テスト用のダミーデータを返す
                return new String[] {"5", "教室A", "22", "9696"};
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "equipmentDataAccessor", edaMock);

        // 期待値を準備する
        Equipment expected = new Equipment(5, "教室A", 22, "9696");

        // テスト対象のメソッドを実行し、実測値を取得する
        Equipment actual = master.addEquipment("教室A", 22, "9696");

        // 期待値と実測値が、その属性を含めて等しいことを確認する。
        assertThat("施設インスタンスの内容が異なります.", actual, is(equalTo(expected)));
    }
}
