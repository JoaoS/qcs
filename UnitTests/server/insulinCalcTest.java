package server;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joaosubtil on 04/05/16.
 */
public class insulinCalcTest {

    @Test
    public void testMealtimeInsulinDose() throws Exception {

        insulinCalc s = new insulinCalc();
        System.out.println(s.mealtimeInsulinDose(1,1,1,1,1));


    }

    @Test
    public void testBackgroundInsulinDose() throws Exception {

    }

    @Test
    public void testPersonalSensitivityToInsulin() throws Exception {

    }
}