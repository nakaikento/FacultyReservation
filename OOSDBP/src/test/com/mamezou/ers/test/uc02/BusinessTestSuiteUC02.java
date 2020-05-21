/*
 * BusinessTestSuite02.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.business.EquipmentTest;
import com.mamezou.ers.business.ReservationTest;
import com.mamezou.ers.business.UserAccountTest;
import com.mamezou.ers.test.uc01.EquipmentReservationSystemTestUC01;
import com.mamezou.ers.test.uc01.UserAccountMasterTestUC01;

/**
 * UC02までに実装する範囲のbusinessパッケージのテストスイート
 */
@RunWith(Suite.class)
@SuiteClasses({
    EquipmentTest.class                         // 追加
    , ReservationTest.class
    , UserAccountTest.class
    , EquipmentMasterTestUC02.class             // 追加
    , ReservationMasterTestUC02.class           // 追加
    , UserAccountMasterTestUC01.class
    , UserAccountMasterTestUC02.class           // 追加
    , EquipmentReservationSystemTestUC01.class
    , EquipmentReservationSystemTestUC02.class  // 追加
})
public class BusinessTestSuiteUC02 {
}