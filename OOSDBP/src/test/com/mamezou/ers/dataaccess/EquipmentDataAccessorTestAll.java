package com.mamezou.ers.dataaccess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.test.uc02.EquipmentDataAccessorTestUC02;
import com.mamezou.ers.test.uc04.EquipmentDataAccessorTestUC04;

@RunWith(Suite.class)
@SuiteClasses({
    EquipmentDataAccessorTestUC02.class
    , EquipmentDataAccessorTestUC04.class
})
public class EquipmentDataAccessorTestAll {
}
