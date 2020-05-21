/*
 * ReservationMasterTest02.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc02;

import static com.mamezou.commons.utility.DateUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.mamezou.ers.business.Reservation;
import com.mamezou.ers.business.ReservationMaster;
import com.mamezou.ers.dataaccess.ReservationDataAccessor;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC02で実装する範囲のReservationMasterクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class ReservationMasterTestUC02 extends BeanTestCase {

    private ReservationMaster master = new ReservationMaster();

    @Override
    protected Class<?> getBeanClass() {
        return ReservationMaster.class;
    }

    /*
     * 存在する施設番号と開始日を指定すると正しくデータが取得できることをテストする
     */
    @Test
    public void testFindReservationByEquipmentAndStartDate() throws Exception {
        // データアクセス層のスタブを用意する
        ReservationDataAccessor rdaMock = new ReservationDataAccessor() {
            @Override
            public List<String[]> findReservation(int targetEquipmentNumber, LocalDate targetStartDate)
                    throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "データアクセス層のメソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber)
                        , targetEquipmentNumber, is(3));
                assertThat(String.format(msg, "日付", targetStartDate)
                        , targetStartDate, is(LocalDate.of(2004, 4, 1)));

                // ダミーの戻り値を返す
                return Arrays.asList(
                          new String[]{"1", "2004/04/01 10:00", "2004/04/01 12:00", "お誕生日会", "3", "1"}
                        , new String[]{"2", "2004/04/01 16:00", "2004/04/01 18:00", "OOADレビュー", "3", "2"}
                        , new String[]{"3", "2005/04/01 10:00", "2005/04/01 12:00", "お誕生日会", "3", "1"}
                        );
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "reservationDataAccessor", rdaMock);

        // 期待値を設定する. (データアクセス層からのデータ(String[])をReservationインスタンスに変換したもの)
        Reservation[] expected = {
                new Reservation(1, LocalDateTime.of(2004, 4, 1, 10, 0), LocalDateTime.of(2004, 4, 1, 12, 0), "お誕生日会", 3, 1)
                , new Reservation(2, LocalDateTime.of(2004, 4, 1, 16, 0), LocalDateTime.of(2004, 4, 1, 18, 0), "OOADレビュー", 3, 2)
                , new Reservation(3, LocalDateTime.of(2005, 4, 1, 10, 0), LocalDateTime.of(2005, 4, 1, 12, 0), "お誕生日会", 3, 1)
        };

        // テスト対象メソッドを実行し、実測値を取得する.
        List<Reservation> actual = master.findReservation(3, LocalDate.of(2004, 4, 1));

        // 実測値と期待値が一致していることを確認する
        assertThat("戻り値のリストがnullです", actual, is(notNullValue()));
        assertThat("要素数が異なります", actual.size(), is(3));

        for (int i = 0; i < actual.size(); i++) {
            assertThat("予約インスタンスの内容が異なります. index=" + i, actual.get(i), is(equalTo(expected[i])));

        }
    }

    /**
     * 該当する予約が存在しない施設番号と開始日を指定すると空のリストが取得できることをテストする.<br>
     * 施設番号 = 903
     * 日付 = 2004/4/1
     */
    @Test
    public void testFindEmptyReservationByEquipmentAndStartDate_NotExistEquipNum() throws Exception {
        // データアクセス層のスタブを用意する
        ReservationDataAccessor rdaMock = new ReservationDataAccessor() {
            @Override
            public List<String[]> findReservation(int targetEquipmentNumber, LocalDate targetStartDate)
                    throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "データアクセス層のメソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber)
                        , targetEquipmentNumber, is(903));
                assertThat(String.format(msg, "日付", targetStartDate)
                        , targetStartDate, is(LocalDate.of(2004, 4, 1)));

                // ダミーの戻り値を返す
                return Collections.<String[]>emptyList();
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "reservationDataAccessor", rdaMock);

        // テスト対象メソッドを実行し、実測値を取得する.
        List<Reservation> actual = master.findReservation(903, convertStringToDate("2004/4/1"));

        // 実測値と期待値が一致していることを確認する
        assertThat("戻り値のリストがnullです", actual, is(notNullValue()));
        assertThat("存在しないはずの予約が取得されました", actual.size(), is(0));
    }

    /**
     * 該当する予約が存在しない施設番号と開始日を指定すると空のリストが取得できることをテストする.<br>
     * 施設番号 = 1
     * 日付 = 2004/7/10
     */
    @Test
    public void testFindEmptyReservationByEquipmentAndStartDate_NotExistStartDate() throws Exception {
        // データアクセス層のスタブを用意する
        ReservationDataAccessor rdaMock = new ReservationDataAccessor() {
            @Override
            public List<String[]> findReservation(int targetEquipmentNumber, LocalDate targetStartDate)
                    throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "データアクセス層のメソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber)
                        , targetEquipmentNumber, is(1));
                assertThat(String.format(msg, "日付", targetStartDate)
                        , targetStartDate, is(LocalDate.of(2004, 7, 10)));

                // ダミーの戻り値を返す
                return Collections.<String[]>emptyList();
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "reservationDataAccessor", rdaMock);

        // テスト対象メソッドを実行し、実測値を取得する.
        List<Reservation> actual = master.findReservation(1, convertStringToDate("2004/7/10"));

        // 実測値と期待値が一致していることを確認する
        assertThat("戻り値のリストがnullです", actual, is(notNullValue()));
        assertThat("存在しないはずの予約が取得されました", actual.size(), is(0));
    }

    /**
     * 該当する予約が存在しない施設番号と開始日を指定すると空のリストが取得できることをテストする.<br>
     * 施設番号 = 903
     * 日付 = 2004/7/10
     */
    @Test
    public void testFindEmptyReservationByEquipmentAndStartDate_NotExistEquipNumAndStartDate() throws Exception {
        // データアクセス層のスタブを用意する
        ReservationDataAccessor rdaMock = new ReservationDataAccessor() {
            @Override
            public List<String[]> findReservation(int targetEquipmentNumber, LocalDate targetStartDate)
                    throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "データアクセス層のメソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber)
                        , targetEquipmentNumber, is(903));
                assertThat(String.format(msg, "日付", targetStartDate)
                        , targetStartDate, is(LocalDate.of(2004, 7, 10)));

                // ダミーの戻り値を返す
                return Collections.<String[]>emptyList();
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(master, "reservationDataAccessor", rdaMock);

        // テスト対象メソッドを実行し、実測値を取得する.
        List<Reservation> actual = master.findReservation(903, convertStringToDate("2004/7/10"));

        // 実測値と期待値が一致していることを確認する
        assertThat("戻り値のリストがnullです", actual, is(notNullValue()));
        assertThat("存在しないはずの予約が取得されました", actual.size(), is(0));
    }
}
