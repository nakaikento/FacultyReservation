/*
 * DataaccessTestSuite.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.test.uc01.UserAccountDataAccessorTestUC01;

/**
 * UC02までに実装する範囲のdataaccessパッケージのテストスイート
 */
@RunWith(Suite.class)
@SuiteClasses({
    EquipmentDataAccessorTestUC02.class
    , ReservationDataAccessorTestUC02.class
    , UserAccountDataAccessorTestUC01.class
    , UserAccountDataAccessorTestUC02.class
})
public class DataaccessTestSuiteUC02 {
}
