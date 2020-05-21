package com.mamezou.ers.dataaccess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.test.uc02.ReservationDataAccessorTestUC02;
import com.mamezou.ers.test.uc03.ReservationDataAccessorTestUC03;


@RunWith(Suite.class)
@SuiteClasses({
    ReservationDataAccessorTestUC02.class
    , ReservationDataAccessorTestUC03.class
})
public class ReservationDataAccessorTestAll {

}
