/*
 * EquipmentTest.java
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

import com.mamezou.ers.business.Equipment;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * Equipmentクラスのテストクラス.
 *
 * Equipmentクラスはあらかじめ提供されるクラスです.
 *
 * @author Mamezou Co., Ltd.
 */
public class EquipmentTest extends BeanTestCase {

    /** テスト対象となる Equipmentクラスのインスタンス */
    private Equipment anEquipment;

    @Override
    protected Class<Equipment> getBeanClass() {
        return Equipment.class;
    }

    /**
     * 各テストケースメソッドが呼び出される前に毎回実行される準備処理。
     */
    @Before
    public void setUp() {
        anEquipment = new Equipment();
    }

    /**
     * テスト対象のEquipmentクラスに、施設番号を表すインスタンス変数 number が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_number() throws Exception {
        assertThat("施設番号を表すインスタンス変数 number が定義されていません", fields.get("number"), is(notNullValue()));
    }

    /**
     * テスト対象のEquipmentクラスに、施設名を表すインスタンス変数 name が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_name() throws Exception {
        assertThat("施設名を表すインスタンス変数 name が定義されていません", fields.get("name"), is(notNullValue()));
    }

    /**
     * テスト対象のEquipmentクラスに、定員数を表すインスタンス変数 capacity が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_capacity() throws Exception {
        assertThat("定員数を表すインスタンス変数 capacity が定義されていません", fields.get("capacity"), is(notNullValue()));
    }

    /**
     * テスト対象のEquipmentクラスに、内線番号を表すインスタンス変数 extensionNumber が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_() throws Exception {
        assertThat("内線番号を表すインスタンス変数 extensionNumber が定義されていません",
                fields.get("extensionNumber"), is(notNullValue()));
    }

    /**
     * setNumber(int) のテストメソッド。
     * インスタンス変数 number に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetNumber() throws Exception {
        anEquipment.setNumber(100);
        Field number = (Field) fields.get("number");
        assertThat("施設番号が正しく設定されていません", number.getInt(anEquipment), is(100));
    }

    @Test
    public void testGetNumber() throws Exception {
        Field number = (Field) fields.get("number");
        number.setInt(anEquipment, 100);
        assertThat("施設番号が正しく取得できません", anEquipment.getNumber(), is(100));
    }

    @Test
    public void testSetName() throws Exception {
        anEquipment.setName("第100会議室");
        Field name = (Field) fields.get("name");
        assertThat("施設名が正しく設定されていません", (String) name.get(anEquipment), is(equalTo("第100会議室")));
    }

    @Test
    public void testGetName() throws Exception {
        Field name = (Field) fields.get("name");
        name.set(anEquipment, "第100会議室");
        assertThat("施設名が正しく取得できません", anEquipment.getName(), is(equalTo("第100会議室")));
    }

    @Test
    public void testSetCapacity() throws Exception {
        anEquipment.setCapacity(15);
        Field capacity = (Field) fields.get("capacity");
        assertThat("定員数が正しく設定されていません", capacity.getInt(anEquipment), is(15));
    }

    @Test
    public void testGetCapacity() throws Exception {
        Field capacity = (Field) fields.get("capacity");
        capacity.setInt(anEquipment, 30);
        assertThat("定員数が正しく取得できません", anEquipment.getCapacity(), is(30));
    }

    @Test
    public void testSetExtensionNumber() throws Exception {
        anEquipment.setExtensionNumber("5963");
        Field extensionNumber = (Field) fields.get("extensionNumber");
        assertThat("内線番号が正しく設定されていません", (String) extensionNumber.get(anEquipment), is("5963"));
    }

    @Test
    public void testGetExtensionNumber() throws Exception {
        Field extensionNumber = (Field) fields.get("extensionNumber");
        extensionNumber.set(anEquipment, "4649");
        assertThat("内線番号が正しく取得できません", anEquipment.getExtensionNumber(), is("4649"));
    }
}
