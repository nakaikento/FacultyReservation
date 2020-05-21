/*
 * DataaccessTestSuiteUC05.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc05;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.test.uc01.UserAccountDataAccessorTestUC01;
import com.mamezou.ers.test.uc02.EquipmentDataAccessorTestUC02;
import com.mamezou.ers.test.uc02.ReservationDataAccessorTestUC02;
import com.mamezou.ers.test.uc02.UserAccountDataAccessorTestUC02;
import com.mamezou.ers.test.uc03.ReservationDataAccessorTestUC03;
import com.mamezou.ers.test.uc04.EquipmentDataAccessorTestUC04;

/**
 * UC05までに実装する範囲のdataaccessパッケージのテストスイート
 */
@RunWith(Suite.class)
@SuiteClasses({
    EquipmentDataAccessorTestUC02.class
    , EquipmentDataAccessorTestUC04.class
    , ReservationDataAccessorTestUC02.class
    , ReservationDataAccessorTestUC03.class
    , UserAccountDataAccessorTestUC01.class
    , UserAccountDataAccessorTestUC02.class
    , UserAccountDataAccessorTestUC05.class     // 追加
})
public class DataaccessTestSuiteUC05 {
}
