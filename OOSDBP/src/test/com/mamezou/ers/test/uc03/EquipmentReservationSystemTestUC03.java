/*
 * EquipmentReservationSystemTestUC03.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc03;

import static com.mamezou.commons.utility.DateUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.business.Equipment;
import com.mamezou.ers.business.EquipmentMaster;
import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.business.Reservation;
import com.mamezou.ers.business.ReservationMaster;
import com.mamezou.ers.business.UserAccount;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.exceptions.DataValueException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC03で実装する範囲のEquipmentReservationSystemクラスのテストクラス
 * @author Mamezou Co., Ltd.
 */
public class EquipmentReservationSystemTestUC03 extends BeanTestCase {
    /** テスト対象となる EquipmentReservationSystemクラスのインスタンス(への参照) */
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
     * 予約を新規に追加登録するテスト(登録成功).<br>
     * <p>
     * 期待する結果 = 予約番号が発行され、登録した情報を持つ予約インスタンスが返される.
     * <p>
     */
    @Test
    public void testAddReservation() throws Exception {
        // EquipmentMasterのスタブを用意する
        EquipmentMaster emMock = new EquipmentMaster() {
            @Override
            public Equipment findEquipment(int targetEquipmentNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentMaster#findEquipment(int)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber),
                        targetEquipmentNumber, is(2));

                return new Equipment(2, "第1会議室", 10, "2111");
            }
        };

        ReservationMaster rmMock = new ReservationMaster() {
            @Override
            public Reservation findReservation(int targetEquipmentNumber, LocalDateTime targetStartDateTime,
                    LocalDateTime targetEndDateTime) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "ReservationMaster#findReservation(int, Date, Date)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber),
                        targetEquipmentNumber, is(2));
                assertThat(String.format(msg, "開始日時", convertDateTimeToString(targetStartDateTime)),
                        targetStartDateTime, is(LocalDateTime.of(2005, 4, 18, 9, 0)));
                assertThat(String.format(msg, "終了日時", convertDateTimeToString(targetEndDateTime)),
                        targetEndDateTime, is(LocalDateTime.of(2005, 5, 16, 18, 0)));
                // テストデータとしてnullを返す(重複する登録済予約なし)
                return null;
            }

            @Override
            public Reservation addReservation(int userAccountNumber, int equipmentNumber, LocalDateTime startDateTime,
                    LocalDateTime endDateTime, String purpose) throws DataFormatException, DataIOException {
                // 引数が正しく渡っていることを確認する
                String msg = "ReservationMaster#addReservation(int, int, Date, Date, String)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "ユーザアカウント番号", userAccountNumber),
                        userAccountNumber, is(1));
                assertThat(String.format(msg, "施設番号", equipmentNumber),
                        equipmentNumber, is(2));
                assertThat(String.format(msg, "開始日時", convertDateTimeToString(startDateTime)),
                        startDateTime, is(LocalDateTime.of(2005, 4, 18, 9, 0)));
                assertThat(String.format(msg, "終了日時", convertDateTimeToString(endDateTime)),
                        endDateTime, is(LocalDateTime.of(2005, 5, 16, 18, 0)));
                assertThat(String.format(msg, "利用目的", purpose),
                        purpose, is("新人研修"));

                return  new Reservation(4, startDateTime, endDateTime, "新人研修", 2, 1);
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "equipmentMaster", emMock);
        setMockObject(system, "reservationMaster", rmMock);

        // 期待値を準備する
        UserAccount user = new UserAccount(1, "soramame", "soramame", "そらまめ", "4041", "教育部", 1);
        Equipment equipment = new Equipment(2, "第1会議室", 10, "2111");
        LocalDateTime startDateTime = LocalDateTime.of(2005, 4, 18, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2005, 5, 16, 18, 0);

        Reservation expected = new Reservation(4, startDateTime, endDateTime, "新人研修", 2, 1);
        expected.setEquipment(equipment);
        expected.setUserAccount(user);

        // テスト対象のメソッドを実行し、実測値を取得する
        Reservation actual = system.addReservation(2, startDateTime, endDateTime, "新人研修", user);

        // 実測値と期待値が等しいことを確認する
        assertThat("予約インスタンスの内容が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * 予約を新規に追加登録するテスト(登録失敗：未登録の施設を指定した).<br>
     * <p>
     * 期待する結果 = 例外 {@link DataValueException} が発生する.
     * <p>
     */
    @Test
    public void testAddReservation_NotExistEquipment() throws Exception {
        // EquipmentMasterのスタブを用意する
        EquipmentMaster emMock = new EquipmentMaster() {
            @Override
            public Equipment findEquipment(int targetEquipmentNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentMaster#findEquipment(int)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber),
                        targetEquipmentNumber, is(10000));

                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "equipmentMaster", emMock);

        UserAccount user = new UserAccount(1, "soramame", "soramame", "そらまめ", "4041", "教育部", 1);

        LocalDateTime startDateTime = LocalDateTime.of(2005, 4, 18, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2005, 5, 16, 18, 0);

        try {
            system.addReservation(10000, startDateTime, endDateTime, "新人研修", user);
            fail("例外DataValueExceptionが発生しませんでした.");
        } catch (DataValueException e) {
            assertThat("例外のエラーメッセージが異なります", e.getMessage(), is("該当する施設番号は存在しません。"));
        }
    }

    /**
     * 予約を新規に追加登録するテスト(登録失敗：指定時間帯と重複する予約がある).<br>
     * <p>
     * 期待する結果 = 例外 {@link DataValueException} が発生する.
     * <p>
     */
    @Test
    public void testAddReservation_AlreadyReserved() throws Exception {
        // EquipmentMasterのスタブを用意する
        EquipmentMaster emMock = new EquipmentMaster() {
            @Override
            public Equipment findEquipment(int targetEquipmentNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentMaster#findEquipment(int)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber),
                        targetEquipmentNumber, is(3));

                return new Equipment(3, "ミーティングスペースA", 4, "4210");
            }
        };

        ReservationMaster rmMock = new ReservationMaster() {
            @Override
            public Reservation findReservation(int targetEquipmentNumber, LocalDateTime targetStartDateTime,
                    LocalDateTime targetEndDateTime) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "ReservationMaster#findReservation(int, Date, Date)に渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber),
                        targetEquipmentNumber, is(3));
                assertThat(String.format(msg, "開始日時", convertDateTimeToString(targetStartDateTime)),
                        targetStartDateTime, is(LocalDateTime.of(2004, 4, 1, 16, 0)));
                assertThat(String.format(msg, "終了日時", convertDateTimeToString(targetEndDateTime)),
                        targetEndDateTime, is(LocalDateTime.of(2005, 4, 1, 18, 0)));

                // テストデータとしてnull以外を返す(重複する登録済予約あり)
                return new Reservation();
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "equipmentMaster", emMock);
        setMockObject(system, "reservationMaster", rmMock);

        UserAccount user = new UserAccount(1, "soramame", "soramame", "そらまめ", "4041", "教育部", 1);
        LocalDateTime startDateTime = LocalDateTime.of(2004, 4, 1, 16, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2005, 4, 1, 18, 0);

        try {
            system.addReservation(3, startDateTime, endDateTime, "新人研修", user);
        } catch (DataValueException e) {
            assertThat("例外のエラーメッセージが異なります", e.getMessage(), is("既に予約済みです。"));
        }
    }
}
