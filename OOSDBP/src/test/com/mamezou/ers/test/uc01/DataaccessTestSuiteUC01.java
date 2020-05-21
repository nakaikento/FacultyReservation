/*
 * DataaccessTestSuiteUC01.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.uc01;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * UC01で実装する範囲のdataaccessパッケージのテストスイート
 */
@RunWith(Suite.class)
@SuiteClasses({
	UserAccountDataAccessorTestUC01.class
})
public class DataaccessTestSuiteUC01 {
}
