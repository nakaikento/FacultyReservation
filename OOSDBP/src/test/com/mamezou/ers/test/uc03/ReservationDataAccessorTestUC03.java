/*
 * ReservationDataAccessorTestUC03.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.mamezou.ers.dataaccess.ReservationDataAccessor;
import com.mamezou.ers.test.common.CsvFilePathConsts;
import com.mamezou.ers.test.common.CsvTestCase;

/**
 * UC03で実装する範囲のReservationDataAccessorクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class ReservationDataAccessorTestUC03 extends CsvTestCase {

    private ReservationDataAccessor reservationDataAccessor
                    = new ReservationDataAccessor(getCsvFilePath());

    @Override
    protected String getCsvFilePath() {
        return CsvFilePathConsts.RESERVATION;
    }

    /*
     * 存在する予約の施設番号と利用の開始/終了日時を指定すると正しくCSVからデータが
     * 取得できることをテストする。
     * ただし、利用の開始/終了日時についてはまったく同一でなくても時間帯が重複していれば
     * よしとする。
     */
    @Test
    public void testFindReservationByEquipmentAndStartAndEnd_01() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2004, 4, 1, 11, 0); // 2004/04/01 11:00
        LocalDateTime endDateTime = LocalDateTime.of(2004, 4, 1, 14, 0); // 2004/04/01 14:00
        /* 指定開始時間が既存予約時間帯に含まれるケース */
        //完全に含まれる
        String[] expected = { "1", "2004/04/01 10:00", "2004/04/01 12:00", "お誕生日会", "3", "1" };

        String[] actual = reservationDataAccessor.findReservation(3, startDateTime, endDateTime);
        assertThat("データが異なります1", actual, is(equalTo(expected)));
    }

    /*
     * 存在する予約の施設番号と利用の開始/終了日時を指定すると正しくCSVからデータが
     * 取得できることをテストする。
     * ただし、利用の開始/終了日時についてはまったく同一でなくても時間帯が重複していれば
     * よしとする。
     */
    @Test
    public void testFindReservationByEquipmentAndStartAndEnd_02() throws Exception {
        String[] expected = { "1", "2004/04/01 10:00", "2004/04/01 12:00", "お誕生日会", "3", "1" };

        //指定終了時間が既存予約時間帯に含まれるケース
        LocalDateTime startDateTime = LocalDateTime.of(2004, 4, 1, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2004, 4, 1, 11, 0);

        String[] actual = reservationDataAccessor.findReservation(3, startDateTime, endDateTime);

        assertThat("データが異なります2", actual, is(equalTo(expected)));
    }

    /*
     * 存在する予約の施設番号と利用の開始/終了日時を指定すると正しくCSVからデータが
     * 取得できることをテストする。
     * ただし、利用の開始/終了日時についてはまったく同一でなくても時間帯が重複していれば
     * よしとする。
     */
    @Test
    public void testFindReservationByEquipmentAndStartAndEnd_03() throws Exception {
        String[] expected = { "1", "2004/04/01 10:00", "2004/04/01 12:00", "お誕生日会", "3", "1" };

        //指定時間帯が既存の予約時間帯を包含するケース
        LocalDateTime startDateTime = LocalDateTime.of(2004, 4, 1, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2004, 4, 1, 14, 0);

        String[] actual = reservationDataAccessor.findReservation(3, startDateTime, endDateTime);

        assertThat("データが異なります3", actual, is(equalTo(expected)));
    }

    /*
     * 存在する予約の施設番号と利用の開始/終了日時を指定すると正しくCSVからデータが
     * 取得できることをテストする。
     * ただし、利用の開始/終了日時についてはまったく同一でなくても時間帯が重複していれば
     * よしとする。
     */
    @Test
    public void testFindReservationByEquipmentAndStartAndEnd_04() throws Exception {
        String[] expected = { "1", "2004/04/01 10:00", "2004/04/01 12:00", "お誕生日会", "3", "1" };

        //指定時間帯が既存の予約時間帯に包含されるケース
        LocalDateTime startDateTime = LocalDateTime.of(2004, 4, 1, 10, 30);
        LocalDateTime endDateTime = LocalDateTime.of(2004, 4, 1, 11, 30);

        String[] actual = reservationDataAccessor.findReservation(3, startDateTime, endDateTime);

        assertThat("データが異なります4", actual, is(equalTo(expected)));
    }

    /*
     * 存在しない予約の施設番号と利用の開始/終了日時を指定するとNullが返ることをテストする
     */
    @Test
    public void testFindNullReservationByEquipmentAndStartAndEnd() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(9999, 4, 1, 13, 00);
        LocalDateTime endDateTime = LocalDateTime.of(9999, 4, 1, 15, 30);
        String[] actual = reservationDataAccessor.findReservation(0, startDateTime, endDateTime);

        assertThat("データが異なります", actual, is(nullValue()));
    }

    /*
     * 存在する予約番号を指定すると正しくCSVからデータが取得できることをテストする
     */
    @Test
    public void testFindReservationByReservationNumber() throws Exception {
        int reservationNumber = 1;
        String[] expected = { "1", "2004/04/01 10:00", "2004/04/01 12:00", "お誕生日会", "3", "1" };
        String[] actual = reservationDataAccessor.findReservation(reservationNumber);
        assertThat("予約情報が異なります", actual, is(equalTo(expected)));
    }

    /*
     * 存在しない予約番号を指定するとNullが返ることをテストする
     */
    @Test
    public void testFindNullReservationByReservationNumber() throws Exception {
        int reservationNumber = 9999;
        String[] actual = reservationDataAccessor.findReservation(reservationNumber);
        assertThat("存在しないはずのデータが取得されました", actual, is(nullValue()));
    }

    /*
     * 予約番号が正しく取得できることをテストする
     */
    @Test
    public void testGetNextSeq() throws Exception {
        int actual = reservationDataAccessor.getNextSeq();
        assertThat("シーケンス番号が正しくありません", actual, is(4));
    }

    /*
     * 予約データが正しくCSVに登録されることをテストする
     */
    @Test
    public void testAddReservation() throws Exception {
        String[] addData = { "4", "2005/04/18 10:00", "2005/05/16 20:00", "Java言語研修", "3", "1" };
        reservationDataAccessor.addReservation(addData);

        List<String[]> records = getAllRecords();
        String[] lastRecord = (String[]) records.get(records.size() - 1);
        assertThat("正しいデータで追加されませんでした", lastRecord, is(equalTo(addData)));
    }
}
