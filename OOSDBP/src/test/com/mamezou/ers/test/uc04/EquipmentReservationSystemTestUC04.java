/*
 * EquipmentReservationSystemTestUC04.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.business.Equipment;
import com.mamezou.ers.business.EquipmentMaster;
import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.exceptions.DataValueException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC04で実装する範囲のEquipmentReservationSystemクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class EquipmentReservationSystemTestUC04 extends BeanTestCase {

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
     * 施設を新規に追加登録するテスト(登録成功).<br>
     * <p>
     * 期待する結果 = 施設番号が発行され、登録した情報を持つ施設インスタンスが返される.
     * <p>
     */
    @Test
    public void testAddEquipment() throws Exception {
        // EquipmentMasterのスタブを用意する
        EquipmentMaster emMock = new EquipmentMaster() {
            @Override
            public Equipment findEquipment(String targetEquipmentName) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentMaster#findEquipment(String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設名", targetEquipmentName),
                        targetEquipmentName, is("教室A"));
                return null;
            }

            @Override
            public Equipment addEquipment(String newEquipmentName, int newCapacity, String newExtensionNumber)
                    throws DataFormatException, DataIOException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentMaster#addEquipment(String, int, String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設名", newEquipmentName),
                        newEquipmentName, is("教室A"));
                assertThat(String.format(msg, "定員数", newCapacity),
                        newCapacity, is(22));
                assertThat(String.format(msg, "内線番号", newExtensionNumber),
                        newExtensionNumber, is("9696"));
                
                return new Equipment(5, "教室A", 22, "9696");
            }
        };
        
        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "equipmentMaster", emMock);
        
        // 期待値を準備する
        Equipment expected = new Equipment(5, "教室A", 22, "9696");

        // テスト対象のメソッドを実行し、実測値を取得する
        Equipment actual = system.addEquipment("教室A", 22, "9696");

        // 期待値と実測値が、その属性を含めて等しいことを確認する。
        assertThat("施設インスタンスの内容が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * 施設を新規に追加登録するテスト(登録失敗:登録したい施設名と同名の施設が登録済である).<br>
     * <p>
     * 期待する結果 = 例外 {@link DataValueException} が発生する.
     * <p>
     */
    @Test
    public void testAddEquipment_AlreadyExists() throws Exception {
        // EquipmentMasterのスタブを用意する
        EquipmentMaster emMock = new EquipmentMaster() {
            @Override
            public Equipment findEquipment(String targetEquipmentName) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentMaster#findEquipment(String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設名", targetEquipmentName),
                        targetEquipmentName, is("第1会議室"));
                return new Equipment();
            }

            @Override
            public Equipment addEquipment(String newEquipmentName, int newCapacity, String newExtensionNumber)
                    throws DataFormatException, DataIOException {
                fail("呼び出されてはいけないメソッドが呼び出されました.");
                return null;
            }
        };
        
        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "equipmentMaster", emMock);

        // テスト対象のメソッドを実行する
        try {
            system.addEquipment("第1会議室", 22, "9696");
            fail("例外DataValueExceptionが発生しませんでした.");
        } catch (DataValueException e) {
            // 期待値と実測値が等しいことを確認する。
            assertThat("例外のエラーメッセージが異なります.", e.getMessage(), is("入力した施設は既に登録されています。"));
        }
    }
}
