package com.mamezou.ers.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.test.uc02.ReservationMasterTestUC02;
import com.mamezou.ers.test.uc03.ReservationMasterTestUC03;

@RunWith(Suite.class)
@SuiteClasses({
     ReservationMasterTestUC02.class
    , ReservationMasterTestUC03.class
})
public class ReservationMasterTestAll {
}
