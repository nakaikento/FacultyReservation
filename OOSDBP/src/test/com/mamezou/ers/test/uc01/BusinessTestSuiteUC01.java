/*
 * BusinessTestSuiteUC01.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc01;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.mamezou.ers.business.UserAccountTest;

/**
 * UC01で実装する範囲のbusinessパッケージのテストスイート
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	UserAccountTest.class
	, UserAccountMasterTestUC01.class
	, EquipmentReservationSystemTestUC01.class
})
public class BusinessTestSuiteUC01 {
}
