/*
 * ReservationDataAccessorTestUC02.java
 * Copyright (C) 2005- Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.dataaccess.ReservationDataAccessor;
import com.mamezou.ers.test.common.CsvFilePathConsts;
import com.mamezou.ers.test.common.CsvTestCase;

/**
 * UC02で実装する範囲のReservationDataAccessorクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class ReservationDataAccessorTestUC02 extends CsvTestCase {

    /** テスト対象となるReservationDataAccessorクラスのインスタンス */
    private ReservationDataAccessor reservationDataAccessor;

    /** テストメソッドで利用する日付フォーマッタ */
    private DateFormat df;

    @Override
    protected String getCsvFilePath() {
        return CsvFilePathConsts.RESERVATION;
    }

    @Before
    public void setUp() {
        reservationDataAccessor  = new ReservationDataAccessor(getCsvFilePath());
        df = DateFormat.getDateInstance();
    }

    /*
     * 存在する予約の施設番号と利用日を指定すると正しくCSVからデータが取得できることをテストする
     */
    @Test
    public void testFindReservationByEquipmentAndStartDate() throws Exception {
        LocalDate startDate = null;
        int equipmentNumber = 0;
        startDate = LocalDate.of(2005, 4, 1);
        equipmentNumber = 3;

        List<String[]> actual = reservationDataAccessor.findReservation(equipmentNumber, startDate);
        assertThat("戻り値のリストがnullです", actual, is(notNullValue()));
        assertThat("1件検索されるはずでしたが件数が異なります", actual.size(), is(1));

        startDate = LocalDate.of(2004, 4, 1);
        actual = reservationDataAccessor.findReservation(equipmentNumber, startDate);
        assertThat("2件検索されるはずでしたが件数が異なります", actual.size(), is(2));
    }

    /*
     * 予約の入っていない施設番号と利用日の組み合わせを指定すると空のリストが取得できることをテストする
     */
    @Test
    public void testFindEmptyReservationByEquipmentAndStartDate()
            throws Exception {
        LocalDate startDate = null;
        int equipmentNumber = 0;
        startDate = LocalDate.of(9999, 4, 1);
        equipmentNumber = 1;

        List<String[]> actual = reservationDataAccessor.findReservation(equipmentNumber, startDate);
        assertThat("戻り値のリストがnullです", actual, is(notNullValue()));
        assertThat("9999/4/1の予定として空のリストが返ってきませんでした", actual.size(), is(0));

        startDate = LocalDate.of(2005, 4, 1);
        equipmentNumber = 999;
        actual = reservationDataAccessor.findReservation(equipmentNumber, startDate);
        assertThat("戻り値のリストがnullです", actual, is(notNullValue()));
        assertThat("999番の施設の予定として空のリストが返ってきませんでした", actual.size(), is(0));
    }
}
