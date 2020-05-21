package com.mamezou.ers.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.test.uc01.UserAccountMasterTestUC01;
import com.mamezou.ers.test.uc02.UserAccountMasterTestUC02;
import com.mamezou.ers.test.uc05.UserAccountMasterTestUC05;

@RunWith(Suite.class)
@SuiteClasses({
    UserAccountMasterTestUC01.class
    , UserAccountMasterTestUC02.class
    , UserAccountMasterTestUC05.class
})
public class UserAccountMasterTestAll {

}
