package com.mamezou.ers.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mamezou.ers.test.uc01.EquipmentReservationSystemTestUC01;
import com.mamezou.ers.test.uc02.EquipmentReservationSystemTestUC02;
import com.mamezou.ers.test.uc03.EquipmentReservationSystemTestUC03;
import com.mamezou.ers.test.uc04.EquipmentReservationSystemTestUC04;
import com.mamezou.ers.test.uc05.EquipmentReservationSystemTestUC05;

@RunWith(Suite.class)
@SuiteClasses({
    EquipmentReservationSystemTestUC01.class
    , EquipmentReservationSystemTestUC02.class
    , EquipmentReservationSystemTestUC03.class
    , EquipmentReservationSystemTestUC04.class
    , EquipmentReservationSystemTestUC05.class
})
public class EquipmentReservationSystemTestAll {

}
