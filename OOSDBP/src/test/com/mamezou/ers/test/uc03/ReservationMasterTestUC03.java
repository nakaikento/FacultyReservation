/*
 * ReservationMasterTestUC03.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc03;

import static com.mamezou.commons.utility.DateUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.business.Reservation;
import com.mamezou.ers.business.ReservationMaster;
import com.mamezou.ers.dataaccess.ReservationDataAccessor;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC03で実装する範囲のReservationMasterクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class ReservationMasterTestUC03 extends BeanTestCase {

    private ReservationMaster master;

    @Before
    public void setUp() {
        master = new ReservationMaster();
    }

    @Override
    protected Class<?> getBeanClass() {
        return ReservationMaster.class;
    }

    /**
     * 検索条件に適合する予約が存在する場合のテスト.<br>
     * <p>
     * 検索条件 = {施設番号=3, 開始日時=2004/04/01 09:00, 終了日時=2004/04/01 13:00}
     * </p>
     * <p>
     * 期待する結果 = 1件の予約が取得できる.
     * <p>
     */
    @Test
    public void testFindReservationByEquipmentAndStartAndEnd() throws Exception {
        // データアクセス層のスタブを用意する
        ReservationDataAccessor rdaMock = new ReservationDataAccessor() {
            @Override
            public String[] findReservation(int targetEquipmentNumber, LocalDateTime targetStartDateTime, LocalDateTime targetEndDateTime)
                    throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "データアクセス層のメソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber),
                        targetEquipmentNumber, is(3));
                assertThat(String.format(msg, "開始日時", convertDateTimeToString(targetStartDateTime)),
                        targetStartDateTime, is(LocalDateTime.of(2004, 4, 1, 9, 0)));
                assertThat(String.format(msg, "開始日時", convertDateTimeToString(targetEndDateTime)),
                        targetEndDateTime, is(LocalDateTime.of(2004, 4, 1, 13, 0)));

                // テスト用のダミーデータを返す
                return new String[]{"1", "2004/04/01 10:00", "2004/04/01 12:00", "お誕生日会", "3", "1"};
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "reservationDataAccessor", rdaMock);

        // 期待値を準備する
        Reservation expected = new Reservation(1, LocalDateTime.of(2004, 4, 1, 10, 0),
                LocalDateTime.of(2004, 4, 1, 12, 0), "お誕生日会", 3, 1);

        // テスト対象のメソッドを実行し、実測値を取得する
        Reservation actual = master.findReservation(3,
                                        LocalDateTime.of(2004, 4, 1, 9, 0),
                                        LocalDateTime.of(2004, 4, 1, 13, 0));

        // 実測値と期待値が等しいことを確認する
        assertThat("予約インスタンスの内容が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * 検索条件に適合する予約が存在しない場合のテスト.<br>
     * <p>
     * 検索条件 = {施設番号=1, 開始日時=2004/04/01 13:00, 終了日時=2004/04/01 15:00}
     * </p>
     * <p>
     * 期待する結果 = null が返される.
     * <p>
     */
    @Test
    public void testFindReservationByEquipmentAndStartAndEnd_NoData() throws Exception {
        // データアクセス層のスタブを用意する
        ReservationDataAccessor rdaMock = new ReservationDataAccessor() {
            @Override
            public String[] findReservation(int targetEquipmentNumber, LocalDateTime targetStartDateTime, LocalDateTime targetEndDateTime)
                    throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "データアクセス層のメソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber),
                        targetEquipmentNumber, is(1));
                assertThat(String.format(msg, "開始日時", convertDateTimeToString(targetStartDateTime)),
                        targetStartDateTime, is(LocalDateTime.of(2004, 4, 1, 13, 0)));
                assertThat(String.format(msg, "終了日時", convertDateTimeToString(targetEndDateTime)),
                        targetEndDateTime, is(LocalDateTime.of(2004, 4, 1, 15, 0)));

                // テスト用のダミーデータを返す
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "reservationDataAccessor", rdaMock);

        // テスト対象のメソッドを実行し、実測値を取得する
        Reservation actual = master.findReservation(1,
                                        LocalDateTime.of(2004, 4, 1, 13, 0),
                                        LocalDateTime.of(2004, 4, 1, 15, 0));

        // 実測値と期待値が等しいことを確認する
        assertThat("存在しないはずの予約が取得されました", actual, is(nullValue()));
    }

    /**
     * 検索条件に適合する予約が存在する場合のテスト.<br>
     * <p>
     * 検索条件 = {予約番号=1}
     * </p>
     * <p>
     * 期待する結果 = 1件の予約が取得できる.
     * <p>
     */
    @Test
    public void testFindReservationByReservationNumber() throws Exception {
        // データアクセス層のスタブを用意する
        ReservationDataAccessor rdaMock = new ReservationDataAccessor() {
            @Override
            public String[] findReservation(int targetReservationNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "データアクセス層のメソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "予約番号", targetReservationNumber),
                        targetReservationNumber, is(1));
                return new String[]{"1", "2004/04/01 10:00", "2004/04/01 12:00", "お誕生日会", "3", "1"};
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "reservationDataAccessor", rdaMock);

        // 期待値を準備する
        Reservation expected = new Reservation(1, LocalDateTime.of(2004, 4, 1, 10, 0),
                LocalDateTime.of(2004, 4, 1, 12, 0), "お誕生日会", 3, 1);

        // テスト対象のメソッドを実行し、実測値を取得する
        Reservation actual = master.findReservation(1);

        // 実測値と期待値が等しいことを確認する
        assertThat("予約インスタンスの内容が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * 検索条件に適合する予約が存在しない場合のテスト.<br>
     * <p>
     * 検索条件 = {予約番号=1000}
     * </p>
     * <p>
     * 期待する結果 = null が返される.
     * <p>
     */
    @Test
    public void testFindReservationByReservationNumber_NoData() throws Exception {
        // データアクセス層のスタブを用意する
        ReservationDataAccessor rdaMock = new ReservationDataAccessor() {
            @Override
            public String[] findReservation(int targetReservationNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "データアクセス層のメソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "予約番号", targetReservationNumber),
                        targetReservationNumber, is(1000));
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "reservationDataAccessor", rdaMock);

        // テスト対象のメソッドを実行し、実測値を取得する
        Reservation actual = master.findReservation(1000);

        // 実測値と期待値が等しいことを確認する
        assertThat("存在しないはずの予約が取得されました", actual, is(nullValue()));
    }

    /*
     * 予約データが正常に追加できることをテストする
     */
    @Test
    public void testAddReservation() throws Exception {
        // データアクセス層のスタブを用意する
        ReservationDataAccessor rdaMock = new ReservationDataAccessor() {
            @Override
            public void addReservation(String[] newReservationData) throws DataIOException {
                // 引数が正しく渡っていることを確認する
                String msg = "ReservationDataAccessor#addReservation(String[]) に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "予約番号(index=0)", newReservationData[0]),
                        newReservationData[0], is("4"));
                assertThat(String.format(msg, "利用開始日時(index=1)", newReservationData[1]),
                        newReservationData[1], is("2005/04/18 09:00"));
                assertThat(String.format(msg, "利用終了日時(index=2)", newReservationData[2]),
                        newReservationData[2], is("2005/05/16 18:00"));
                assertThat(String.format(msg, "利用目的(index=3)", newReservationData[3]),
                        newReservationData[3], is("新人研修"));
                assertThat(String.format(msg, "施設番号(index=4)", newReservationData[4]),
                        newReservationData[4], is("2"));
                assertThat(String.format(msg, "予約者のユーザアカウント番号(index=5)", newReservationData[5]),
                        newReservationData[5], is("1"));
            }

            @Override
            public int getNextSeq() throws DataIOException {
                return 4;
            }

            @Override
            public String[] findReservation(int targetReservationNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "データアクセス層のメソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "予約番号", targetReservationNumber),
                        targetReservationNumber, is(4));
                return new String[] {"4", "2005/04/18 09:00", "2005/05/16 18:00", "新人研修", "2", "1"};
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "reservationDataAccessor", rdaMock);

        // 期待値を準備する
        Reservation expected = new Reservation(4, LocalDateTime.of(2005, 4, 18, 9, 0),
                LocalDateTime.of(2005, 5, 16, 18, 0), "新人研修", 2, 1);

        // テスト対象のメソッドを実行し、実測値を取得する
        Reservation actual = master.addReservation(1, 2, LocalDateTime.of(2005, 4, 18, 9, 0),
                                            LocalDateTime.of(2005, 5, 16, 18, 0), "新人研修");

        // 実測値と期待値が等しいことを確認する
        assertThat("予約インスタンスの内容が異なります.", actual, is(equalTo(expected)));
    }
}
