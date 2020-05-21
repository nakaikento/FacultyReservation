/*
 * ReservationTest.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.business;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.test.common.BeanTestCase;

/**
 * Reservationクラスのテストクラス.
 *
 * Reservationクラスはあらかじめ提供されるクラスです.
 *
 * @author Mamezou Co., Ltd.
 */
public class ReservationTest extends BeanTestCase {

    /** テスト対象クラス Reservation のインスタンス */
    private Reservation aReservation;

    @Override
    protected Class<Reservation> getBeanClass() {
        return Reservation.class;
    }

    /**
     * 各テストケースメソッドが呼び出される前に毎回実行される準備処理。
     */
    @Before
    public void setUp() {
        aReservation = new Reservation();
    }

    /**
     * テスト対象のReservationクラスに、予約番号を表すインスタンス変数 number が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_number() {
        assertThat("予約番号を表すインスタンス変数 number が定義されていません",
                fields.get("number"), is(notNullValue()));
    }

    /**
     * テスト対象のReservationクラスに、使用開始日時を表すインスタンス変数 startDateTime が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_startDateTime() {
        assertThat("使用開始日時を表すインスタンス変数 startDateTime が定義されていません",
                fields.get("startDateTime"), is(notNullValue()));
    }

    /**
     * テスト対象のReservationクラスに、使用終了日時を表すインスタンス変数 endDateTime が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_endDateTime() {
        assertThat("使用終了日時を表すインスタンス変数 endDateTime が定義されていません",
                fields.get("endDateTime"), is(notNullValue()));
    }

    /**
     * テスト対象のReservationクラスに、使用目的を表すインスタンス変数 purpose が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_purpose() {
        assertThat("使用目的を表すインスタンス変数 purpose が定義されていません",
                fields.get("purpose"), is(notNullValue()));
    }

    /**
     * テスト対象のReservationクラスに、施設番号を表すインスタンス変数 equipmentNumber が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_equipmentNumber() {
        assertThat("施設番号を表すインスタンス変数 equipmentNumber が定義されていません",
                fields.get("equipmentNumber"), is(notNullValue()));
    }

    /**
     * テスト対象のReservationクラスに、ユーザアカウント番号を表すインスタンス変数 userAccountNumber が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_userAccountNumber() {
        assertThat("ユーザアカウント番号を表すインスタンス変数 userAccountNumber が定義されていません",
                fields.get("userAccountNumber"), is(notNullValue()));
    }

    /**
     * テスト対象のReservationクラスに、予約した施設を表すインスタンス変数 equipment が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_equipment() {
        assertThat("予約した施設を表すインスタンス変数 equipment が定義されていません",
                fields.get("equipment"), is(notNullValue()));
    }

    /**
     * テスト対象のReservationクラスに、予約したユーザを表すインスタンス変数 reserveUser が
     * 定義されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testDefineInstanceVariable_reserveUser() {
        assertThat("予約したユーザを表すインスタンス変数 reserveUser が定義されていません",
                fields.get("reserveUser"), is(notNullValue()));
    }

    /**
     * void setStartDateTime(LocalDateTime) のテストメソッド。
     * インスタンス変数 startDateTime に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetStartDateTime() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        aReservation.setStartDateTime(now);
        Field startDateTime = (Field) fields.get("startDateTime");
        assertThat("使用開始日時が正しく設定されていません"
                , (LocalDateTime) startDateTime.get(aReservation)
                , is(equalTo(now)));
    }

    /**
     * LocalDateTime getStartDateTime() のテストメソッド。
     * インスタンス変数 startDateTime に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    public void testGetStartDateTime() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Field startDateTime = (Field) fields.get("startDateTime");
        startDateTime.set(aReservation, now);
        assertThat("使用開始日時が正しく取得できません", aReservation.getStartDateTime(), is(equalTo(now)));
    }

    /**
     * void setEndDateTime(LocalDateTime) のテストメソッド。
     * インスタンス変数 endDateTime に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetEndDateTime() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        aReservation.setEndDateTime(now);
        Field endDateTime = (Field) fields.get("endDateTime");
        assertThat("使用終了日時が正しく設定されていません", (LocalDateTime) endDateTime.get(aReservation), is(equalTo(now)));
    }

    /**
     * LocalDateTime getEndDateTime() のテストメソッド。
     * インスタンス変数 endDateTime に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    public void testGetEndDateTime() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Field endDateTime = (Field) fields.get("endDateTime");
        endDateTime.set(aReservation, now);
        assertThat("使用終了日時が正しく取得できません", aReservation.getEndDateTime(), is(equalTo(now)));
    }

    /**
     * void setPurpose(String) のテストメソッド。
     * インスタンス変数 purpose に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetPurpose() throws Exception {
        aReservation.setPurpose("プロジェクト進捗会議");
        Field purpose = (Field) fields.get("purpose");
        assertThat("使用目的が正しく設定されていません", (String) purpose.get(aReservation), is(equalTo("プロジェクト進捗会議")));
    }

    /**
     * String getPurpose() のテストメソッド。
     * インスタンス変数 purpose に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    public void testGetPurpose() throws Exception {
        Field purpose = (Field) fields.get("purpose");
        purpose.set(aReservation, "チームミーティング");
        assertThat("使用目的が正しく取得できません", aReservation.getPurpose(), is(equalTo("チームミーティング進捗会議")));
    }

    /**
     * void setEquipmentNumber(int) のテストメソッド。
     * インスタンス変数 equipmentNumber に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetEquipmentNumber() throws Exception {
        aReservation.setEquipmentNumber(100);
        Field equipmentNumber = (Field) fields.get("equipmentNumber");
        assertThat("施設番号が正しく設定されていません", equipmentNumber.getInt(aReservation), is(100));
    }

    /**
     * int getEquipmentNumber() のテストメソッド。
     * インスタンス変数 equipmentNumber に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    public void testGetEquipmentNumber() throws Exception {
        Field equipmentNumber = (Field) fields.get("equipmentNumber");
        equipmentNumber.setInt(aReservation, 100);
        assertThat("施設番号が正しく取得できません", aReservation.getEquipmentNumber(), is(100));
    }

    /**
     * void setUserAccountNumber(int) のテストメソッド。
     * インスタンス変数 userAccountNumber に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetUserAccountNumber() throws Exception {
        aReservation.setUserAccountNumber(100);
        Field userAccountNumber = (Field) fields.get("userAccountNumber");
        assertThat("ユーザアカウント番号が正しく設定されていません", userAccountNumber.getInt(aReservation), is(100));
    }

    /**
     * int getUserAccountNumber() のテストメソッド。
     * インスタンス変数 userAccountNumber に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    public void testGetUserAccountNumber() throws Exception {
        Field userAccountNumber = (Field) fields.get("userAccountNumber");
        userAccountNumber.setInt(aReservation, 100);
        assertThat("ユーザアカウント番号が正しく取得できません", aReservation.getUserAccountNumber(), is(100));
    }

    /**
     * void setEquipment(Equipment) のテストメソッド。
     * インスタンス変数 equipment に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetEquipment() throws Exception {
        Equipment aEquipment = new Equipment();
        aReservation.setEquipment(aEquipment);
        Field equipment = (Field) fields.get("equipment");
        assertThat("予約した施設が正しく設定されていません", (Equipment) equipment.get(aReservation), is(equalTo(aEquipment)));
    }

    /**
     * Equipment getEquipment() のテストメソッド。
     * インスタンス変数 equipment に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    public void testGetEquipment() throws Exception {
        Field equipment = (Field) fields.get("equipment");
        Equipment aEquipment = new Equipment();
        equipment.set(aReservation, aEquipment);
        assertThat("予約した施設が正しく取得できません", aReservation.getEquipment(), is(equalTo(aEquipment)));
    }

    /**
     * void setUserAccount(UserAccount) のテストメソッド。
     * インスタンス変数 reserveUser に、引数で指定した値が
     * 代入されていることを確認する。
     * @throws Exception
     */
    @Test
    public void testSetUserAccount() throws Exception {
        UserAccount anUserAccount = new UserAccount();
        aReservation.setUserAccount(anUserAccount);
        Field reserveUser = (Field) fields.get("reserveUser");
        assertThat("予約したユーザが正しく設定されていません", (UserAccount) reserveUser.get(aReservation), is(equalTo(anUserAccount)));
    }

    /**
     * UserAccount getUserAccount() のテストメソッド。
     * インスタンス変数 reserveUser に格納されている値が
     * 戻り値として返されることを確認する。
     * @throws Exception
     */
    public void testGetUserAccount() throws Exception {
        Field reserveUser = (Field) fields.get("reserveUser");
        UserAccount anUserAccount = new UserAccount();
        reserveUser.set(aReservation, anUserAccount);
        assertThat("予約したユーザが正しく取得できません", (UserAccount) aReservation.getUserAccount(), is(equalTo(anUserAccount)));
    }
}
