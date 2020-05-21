package com.mamezou.ers.dataaccess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.test.uc01.UserAccountDataAccessorTestUC01;
import com.mamezou.ers.test.uc02.UserAccountDataAccessorTestUC02;
import com.mamezou.ers.test.uc05.UserAccountDataAccessorTestUC05;


@RunWith(Suite.class)
@SuiteClasses({
	UserAccountDataAccessorTestUC01.class
	, UserAccountDataAccessorTestUC02.class
	, UserAccountDataAccessorTestUC05.class
})
public class UserAccountDataAccessorTestAll {
}
