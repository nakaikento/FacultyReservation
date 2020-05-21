/*
 * EquipmentReservationSystemTestUC02.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mamezou.ers.business.Equipment;
import com.mamezou.ers.business.EquipmentMaster;
import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.business.Reservation;
import com.mamezou.ers.business.ReservationMaster;
import com.mamezou.ers.business.UserAccount;
import com.mamezou.ers.business.UserAccountMaster;
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.exceptions.DataValueException;
import com.mamezou.ers.test.common.BeanTestCase;

/**
 * UC02で実装する範囲のEquipmentReservationSystemクラスのテストクラス.<br>
 *
 * @author Mamezou Co., Ltd.
 */
public class EquipmentReservationSystemTestUC02 extends BeanTestCase {
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
     * 施設一覧の取得が正しく行われていることをテストする
     */
    @Test
    public void testGetAllEquipments() throws Exception {
        // EquipmentMasterのスタブを用意する
        EquipmentMaster emMock = new EquipmentMaster() {
            @Override
            public List<Equipment> getAllEquipments() throws DataIOException, DataFormatException {
                // テスト用のダミーデータを返す
                return Arrays.asList(
                        new Equipment(1, "大会議室", 40, "2110")
                        , new Equipment(2, "第1会議室", 10, "2111")
                        , new Equipment(3, "ミーティングスペースA", 4, "4210")
                        , new Equipment(4, "ミーティングスペースB", 6, "4211")
                        );
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "equipmentMaster", emMock);

        // 期待値を設定する
        Equipment[] expected = {
                new Equipment(1, "大会議室", 40, "2110")
                , new Equipment(2, "第1会議室", 10, "2111")
                , new Equipment(3, "ミーティングスペースA", 4, "4210")
                , new Equipment(4, "ミーティングスペースB", 6, "4211")
        };

        // 実測値を取得する
        List<Equipment> actual = system.getAllEquipments();

        // 実測値と期待値が等しいことを確認する
        assertThat("戻り値のリストがnullです", actual, is(notNullValue()));
        assertThat("要素数が異なります", actual.size(), is(4));
        for (int i = 0; i < actual.size(); i++) {
            assertThat("施設インスタンスの内容が異なります.index=" + i, actual.get(i), is(equalTo(expected[i])));
        }
    }

    /**
     * 検索条件の施設番号と日付に合致する予約が存在する場合のテスト.<br>
     * <p>
     * 検索条件 = {施設番号=3, 日付=2004-04-01}
     * </p>
     * <p>
     * 期待する結果 = 3件の予約が取得でき、個々の予約には対応する施設と予約者が設定されている.
     * <p>
     */
    @Test
    public void testFindReservation() throws Exception {
        // ReservationMasterのスタブを用意する
        ReservationMaster rmMock = new ReservationMaster() {
            @Override
            public List<Reservation> findReservation(int targetEquipmentNumber, LocalDate targetDate)
                    throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "ReservationMaster#findReservation(int, LocalDate)メソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber), targetEquipmentNumber, is(3));
                assertThat(String.format(msg, "日付", targetDate), targetDate, is(LocalDate.of(2004, 4, 1)));

                // テスト用のダミーデータを返却する
                return Arrays.asList(
                        new Reservation(1, LocalDateTime.of(2004, 4, 1, 10, 0), LocalDateTime.of(2004, 4, 1, 12, 0), "お誕生日会", 3, 1)
                        , new Reservation(2, LocalDateTime.of(2004, 4, 1, 16, 0), LocalDateTime.of(2004, 4, 1, 18, 0), "OOADレビュー", 3, 2)
                        , new Reservation(3, LocalDateTime.of(2005, 4, 1, 10, 0), LocalDateTime.of(2005, 4, 1, 12, 0), "お誕生日会", 3, 1)
                        );
            }
        };

        // EquipmentMasterのスタブを用意する
        EquipmentMaster emMock = new EquipmentMaster() {
            @Override
            public Equipment findEquipment(int targetEquipmentNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentMaster#findEquipment(int)メソッドに渡された引数が正しくありません.[%d]";
                assertThat(String.format(msg, targetEquipmentNumber), targetEquipmentNumber, is(3));

                // テスト用のダミーデータを返却する
                return new Equipment(3, "ミーティングスペースA", 4, "4210");
            }
        };

        // UserAccountMasterのスタブを用意する
        UserAccountMaster umMock = new UserAccountMaster() {
            @Override
            public UserAccount findUserAccount(int targetUserAccountNumber) throws DataIOException, DataFormatException {

                if (targetUserAccountNumber == 1) {
                    return new UserAccount(1, "soramame", "soramame", "そらまめ", "4101", "教育事業部", 1);
                } else if (targetUserAccountNumber == 2) {
                    return new UserAccount(2, "yudemame", "yudemame", "ゆでまめ", "4002", "技術部", 1);
                } else {
                    // 引数が正しく渡っていることを確認する
                    String msg = "UserAccountMaster#findUserAccount(int)メソッドに渡された引数が正しくありません.[%d]";
                    fail(String.format(msg, targetUserAccountNumber));
                    return null;
                }
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "reservationMaster", rmMock);
        setMockObject(system, "equipmentMaster", emMock);
        setMockObject(system, "userAccountMaster", umMock);

        // 期待値を用意する
        Reservation[] expected = {
                new Reservation(1, LocalDateTime.of(2004, 4, 1, 10, 0), LocalDateTime.of(2004, 4, 1, 12, 0), "お誕生日会", 3, 1)
                , new Reservation(2, LocalDateTime.of(2004, 4, 1, 16, 0), LocalDateTime.of(2004, 4, 1, 18, 0), "OOADレビュー", 3, 2)
                , new Reservation(3, LocalDateTime.of(2005, 4, 1, 10, 0), LocalDateTime.of(2005, 4, 1, 12, 0), "お誕生日会", 3, 1)
        };
        Equipment expectedEq =  new Equipment(3, "ミーティングスペースA", 4, "4210");
        UserAccount expectedUA1 =  new UserAccount(1, "soramame", "soramame", "そらまめ", "4101", "教育事業部", 1);
        UserAccount[] expectedUA = {
                expectedUA1
                , new UserAccount(2, "yudemame", "yudemame", "ゆでまめ", "4002", "技術部", 1)
                , expectedUA1
        };

        for (int i=0; i<expected.length; i++) {
            expected[i].setEquipment(expectedEq);
            expected[i].setUserAccount(expectedUA[i]);
        }


        // 実測値を取得する(存在する施設番号、開始日を指定した場合)
        List<Reservation> actual = system.findReservation(3, LocalDate.of(2004, 4, 1));

        // 実測値と期待値が等しいことを確認する
        assertThat("戻り値のリストがnullです", actual, is(notNullValue()));
        assertThat("要素数が異なります", actual.size(), is(3));
        for (int i = 0; i < actual.size(); i++) {
            assertThat("予約インスタンスの内容が異なります. index=" + i, actual.get(i), is(equalTo(expected[i])));
        }
    }

    /**
     * 検索条件の施設番号と日付に合致する予約が存在しない場合のテスト.<br>
     * <p>
     * 検索条件 = {施設番号=903, 日付=2004-04-01}<br>
     * (テストデータに2004/4/1開始の予約はあるが、それは施設番号が903ではない)
     * </p>
     * <p>
     * 期待する結果 = 例外 {@link DataValueException} が発生し、エラーメッセージとして
     * 「該当する予約がありません」が設定されていること.
     * <p>
     */
    @Test
    public void testFindReservation_NoData() throws Exception {
        // ReservationMasterのスタブを用意する
        ReservationMaster rmMock = new ReservationMaster() {
            @Override
            public List<Reservation> findReservation(int targetEquipmentNumber, LocalDate targetDate)
                    throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "ReservationMaster#findReservation(int, LocalDate)メソッドに渡された引数が正しくありません.[%s=%s]";
                assertThat(String.format(msg, "施設番号", targetEquipmentNumber), targetEquipmentNumber, is(903));
                assertThat(String.format(msg, "日付", targetDate), targetDate, is(LocalDate.of(2004, 4, 10)));

                // テストデータとして空のリストを返す
                return Collections.<Reservation>emptyList();
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "reservationMaster", rmMock);

        // 実測値を取得する(該当する予約が存在しない、施設番号と開始日を指定した場合)
        try {
            system.findReservation(903, LocalDate.of(2004, 4, 10));
        } catch (DataValueException e) {
            String expected = "該当する予約がありません。";
            assertThat("エラーメッセージが異なります.", e.getMessage(), is(expected));
        }
    }

    /**
     * 検索条件のユーザアカウント番号に該当するユーザが存在する場合のテスト.<br>
     * <p>
     * 検索条件 = {ユーザアカウント番号=1}<br>
     * </p>
     * <p>
     * 期待する結果 = 1件のユーザアカウントが取得できること.
     * <p>
     */
    @Test
    public void testFindUserAccountByAccountNumber() throws Exception {
        // UserAccountMasterのスタブを用意する
        UserAccountMaster umMock = new UserAccountMaster() {
            @Override
            public UserAccount findUserAccount(int targetUserAccountNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "UserAccountMaster#findUserAccount(int)メソッドに渡された引数が正しくありません.[%d]";
                assertThat(String.format(msg, targetUserAccountNumber), targetUserAccountNumber, is(1));

                // テスト用のダミーデータを返却する
                return new UserAccount(1, "soramame", "soramame", "そらまめ", "4101", "教育事業部", 1);
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "userAccountMaster", umMock);

        // 期待値を設定する
        UserAccount expected = new UserAccount(1, "soramame", "soramame", "そらまめ", "4101", "教育事業部", 1);

        // テスト対象メソッドを呼び出して、実測値を取得する
        UserAccount actual = system.findUserAccount(1);

        // 実測値が期待値と等しいことを確認する
        assertThat("存在するはずのユーザアカウントが取得できません", actual, is(notNullValue()));
        assertThat("ユーザアカウント情報が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * 検索条件のユーザアカウント番号に該当するユーザが存在しない場合のテスト.<br>
     * <p>
     * 検索条件 = {ユーザアカウント番号=555}<br>
     * </p>
     * <p>
     * 期待する結果 = null が返されること.
     * <p>
     */
    @Test
    public void testFindUserAccountByAccountNumber_NoData() throws Exception {
        // UserAccountMasterのスタブを用意する
        UserAccountMaster umMock = new UserAccountMaster() {
            @Override
            public UserAccount findUserAccount(int targetUserAccountNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "UserAccountMaster#findUserAccount(int)メソッドに渡された引数が正しくありません.[%d]";
                assertThat(String.format(msg, targetUserAccountNumber), targetUserAccountNumber, is(555));

                // テスト用のダミーデータを返却する
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "userAccountMaster", umMock);

        // テスト対象メソッドを呼び出して、実測値を取得する
        UserAccount actual = system.findUserAccount(555);

        // 実測値が期待値と等しいことを確認する
        assertThat("存在しないはずのユーザアカウントが取得されました", actual, is(nullValue()));
    }

    /**
     * 検索条件の施設番号に該当する施設が存在する場合のテスト.<br>
     * <p>
     * 検索条件 = {施設番号=1}
     * </p>
     * <p>
     * 期待する結果 = 1件の施設が取得できる
     * <p>
     */
    @Test
    public void testFindEquipment() throws Exception {
        // EquipmentMasterのスタブを用意する
        EquipmentMaster emMock = new EquipmentMaster() {
            @Override
            public Equipment findEquipment(int targetEquipmentNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentMaster#findEquipment(int)メソッドに渡された引数が正しくありません.[%d]";
                assertThat(String.format(msg, targetEquipmentNumber), targetEquipmentNumber, is(1));

                // テスト用のダミーデータを返却する
                return new Equipment(1, "大会議室", 40, "2110");
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "equipmentMaster", emMock);

        // 期待値を設定する
        Equipment expected = new Equipment(1, "大会議室", 40, "2110");

        // テスト対象メソッドを呼び出して、実測値を取得する
        Equipment actual = system.findEquipment(1);

        // 実測値が期待値と等しいことを確認する
        assertThat("存在するはずの施設が取得できません", actual, is(notNullValue()));
        assertThat("施設インスタンスの内容が異なります.", actual, is(equalTo(expected)));
    }

    /**
     * 検索条件の施設番号に該当する施設が存在しない場合のテスト.<br>
     * <p>
     * 検索条件 = {施設番号=903}
     * </p>
     * <p>
     * 期待する結果 = null が返されること
     * <p>
     */
    @Test
    public void testFindEquipment_NoData() throws Exception {
        // EquipmentMasterのスタブを用意する
        EquipmentMaster emMock = new EquipmentMaster() {
            @Override
            public Equipment findEquipment(int targetEquipmentNumber) throws DataIOException, DataFormatException {
                // 引数が正しく渡っていることを確認する
                String msg = "EquipmentMaster#findEquipment(int)メソッドに渡された引数が正しくありません.[%d]";
                assertThat(String.format(msg, targetEquipmentNumber), targetEquipmentNumber, is(903));

                // テスト用のダミーデータを返却する
                return null;
            }
        };

        // スタブをテスト対象インスタンスに設定する
        setMockObject(system, "equipmentMaster", emMock);

        // テスト対象メソッドを呼び出して、実測値を取得する
        Equipment actual = system.findEquipment(903);

        // 実測値が期待値と等しいことを確認する
        assertThat("存在しないはずの施設が取得されました", actual, is(nullValue()));
    }
}
