package com.mamezou.ers.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.test.uc02.EquipmentMasterTestUC02;
import com.mamezou.ers.test.uc04.EquipmentMasterTestUC04;

@RunWith(Suite.class)
@SuiteClasses({
    EquipmentMasterTestUC02.class
    , EquipmentMasterTestUC04.class
})
public class EquipmentMasterTestAll {
}
