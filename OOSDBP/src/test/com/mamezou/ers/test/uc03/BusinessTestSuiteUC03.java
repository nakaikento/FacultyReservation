/*
 * BusinessTestSuiteUC03.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc03;

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

/**
 * UC03までに実装する範囲のbusinessパッケージのテストスイート
 */
@RunWith(Suite.class)
@SuiteClasses({
    EquipmentTest.class
    , ReservationTest.class
    , UserAccountTest.class
    , EquipmentMasterTestUC02.class
    , ReservationMasterTestUC02.class
    , ReservationMasterTestUC03.class    // 追加
    , UserAccountMasterTestUC01.class
    , UserAccountMasterTestUC02.class
    , EquipmentReservationSystemTestUC01.class
    , EquipmentReservationSystemTestUC02.class
    , EquipmentReservationSystemTestUC03.class  //追加
})
public class BusinessTestSuiteUC03 {
}
