/*
 * BusinessTestSuiteUC05.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc05;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.business.EquipmentTest;
import com.mamezou.ers.business.ReservationTest;
import com.mamezou.ers.business.UserAccountTest;
import com.mamezou.ers.test.uc01.EquipmentReservationSystemTestUC01;
import com.mamezou.ers.test.uc01.UserAccountMasterTestUC01;
import com.mamezou.ers.test.uc02.EquipmentMasterTestUC02;
import com.mamezou.ers.test.uc02.EquipmentReservationSystemTestUC02;
import com.mamezou.ers.test.uc02.ReservationMasterTestUC02;
import com.mamezou.ers.test.uc02.UserAccountMasterTestUC02;
import com.mamezou.ers.test.uc03.EquipmentReservationSystemTestUC03;
import com.mamezou.ers.test.uc03.ReservationMasterTestUC03;
import com.mamezou.ers.test.uc04.EquipmentMasterTestUC04;
import com.mamezou.ers.test.uc04.EquipmentReservationSystemTestUC04;

/**
 * UC05までに実装する範囲のbusinessパッケージのテストスイート
 */
@RunWith(Suite.class)
@SuiteClasses({
    EquipmentTest.class
    , ReservationTest.class
    , UserAccountTest.class
    , EquipmentMasterTestUC02.class
    , EquipmentMasterTestUC04.class
    , ReservationMasterTestUC02.class
    , ReservationMasterTestUC03.class
    , UserAccountMasterTestUC01.class
    , UserAccountMasterTestUC02.class
    , UserAccountMasterTestUC05.class           // 追加
    , EquipmentReservationSystemTestUC01.class
    , EquipmentReservationSystemTestUC02.class
    , EquipmentReservationSystemTestUC03.class
    , EquipmentReservationSystemTestUC04.class
    , EquipmentReservationSystemTestUC05.class  // 追加
})
public class BusinessTestSuiteUC05 {
}
