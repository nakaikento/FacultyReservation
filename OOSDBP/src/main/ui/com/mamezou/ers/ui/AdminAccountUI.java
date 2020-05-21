/*
 * AdminAccountUI.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.ui;

// com.mamezou.ers.businessパッケージのインポート
// EquipmentReservationSystem, UserAccount
import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.business.UserAccount;

// com.mamezou.ers.exceptionsパッケージのインポート
//    DataFormatException, DataIOException, DataValueException
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.exceptions.DataValueException;

// com.mamezou.ers.ui.utilityパッケージのインポート
//     AppValidator, UIUtility
import com.mamezou.ers.ui.utility.AppValidator;
import com.mamezou.ers.ui.utility.UIUtility;

/**
 * ユーザ登録画面
 * @author Mamezou
 */
public class AdminAccountUI {

    /** 施設予約システムクラスのインスタンスへの参照 */
    private EquipmentReservationSystem equipReservationSystem;

    /**
     * コンストラクタです。
     * @param system EquipmentReservationSystem
     */
    public AdminAccountUI(EquipmentReservationSystem system) {
        equipReservationSystem = system;
    }

    /**
     * ユーザ登録画面を表示し、ユーザアカウントを登録する。<br>
     * 画面から登録したい情報を受け付け、ユーザアカウントとして
     * 登録する。
     * @param adminUser 管理者のユーザアカウントインスタンス
     */
    public void show(UserAccount adminUser) {
/* UC05で実装します。
*/
    }

    /**
     * ユーザ登録完了画面を表示する。
     * @param adminUser 登録を行った管理者のユーザアカウント
     * @param newUserAccount 新しく登録されたユーザアカウント
     */
/* UC05で実装します。
    private void showUserAccountInfo(UserAccount adminUser,
                                        UserAccount newUserAccount) {
    }
*/
}
