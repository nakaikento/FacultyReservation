/*
 * AdminEquipmentUI.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.ui;

// com.mamezou.ers.businessパッケージのインポート
// EquipmentReservationSystem, UserAccount
import com.mamezou.ers.business.Equipment;
import com.mamezou.ers.business.EquipmentReservationSystem;
import com.mamezou.ers.business.UserAccount;

// com.mamezou.ers.exceptionsパッケージのインポート
//    DataFormatException, DataIOException, DataValueException
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;
import com.mamezou.ers.exceptions.DataValueException;

// com.mamezou.ers.ui.utilityパッケージのインポート
//    AppValidator, UIUtility
import com.mamezou.ers.ui.utility.AppValidator;
import com.mamezou.ers.ui.utility.UIUtility;

/**
 * 施設登録画面
 * @author Mamezou
 */
public class AdminEquipmentUI {
    /** 施設予約システムクラスのインスタンスへの参照 */
    private EquipmentReservationSystem equipReservationSystem;

    /**
     * コンストラクタです。
     * @param system EquipmentReservationSystem
     */
    public AdminEquipmentUI(EquipmentReservationSystem system) {
        equipReservationSystem = system;
    }

    /**
     * 施設を登録する。<br>
     * 施設登録画面を表示し、入力された施設情報で施設を登録する。
     * @param adminUser ログイン中の管理者を表すユーザアカウント
     */
    public void addEquipment(UserAccount adminUser) {
/* UC04で実装します
*/
    }

    /**
     * 施設登録完了画面を表示する
     * @param adminUser 登録を行った管理者のユーザアカウント
     * @param newEquipment 新規に登録された施設
     */
/* UC04で実装します
    private void showEquipmentInfo(UserAccount adminUser, Equipment newEquipment) {
    }
*/
}
