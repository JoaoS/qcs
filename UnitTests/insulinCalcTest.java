import org.junit.Test;
import server.InsulinCalc;
import server.Controller;

import java.util.ArrayList;

/**
 * Created by joaosubtil on 04/05/16.
 */

public class insulinCalcTest {

    @Test
    public void testMealtimeInsulinDose() throws Exception {
        /*
            method mealtimeInsulinDose(...)

            input: 60, 12, 200, 100, 25 ---> expected output: 14

            input: 95, 10, 100, 120, 50 ---> expected output: 0

            input: 120, 14, 170, 100, 60 ---> expected output: 8
        */

        InsulinCalc s = new InsulinCalc();
        System.out.println("Teste1="+s.mealtimeInsulinDose( 60, 12, 200, 100, 25));

        System.out.println("Teste2="+s.mealtimeInsulinDose(  95, 10, 100, 120, 50));

        System.out.println("Teste3="+s.mealtimeInsulinDose(  120, 14, 170, 100, 60));


    }

    @Test/*Works ok*/
    public void testBackgroundInsulinDose() throws Exception {

            InsulinCalc s = new InsulinCalc();
            System.out.println(s.backgroundInsulinDose(79));

    }

    @Test/*fails last test*/
    public void testPersonalSensitivityToInsulin() throws Exception {
    /*
    *   input: 5, {0, 10}, {50, 50} ---> expected output: 50

        input: 6, {2, 8}, {32, 83} ---> expected output: 66

        input: 0, {1, 3, 10}, {33, 43, 70} ---> expected output: 30

        input: 4, {1, 6, 8, 9}, {32, 61, 91, 88} ---> expected output: 53
        */
        InsulinCalc s = new InsulinCalc();
        int array1 [] ={0,10};
        int array2 [] ={50,50};
        System.out.println("Teste1="+s.personalSensitivityToInsulin(5,array1,array2));

        int array3 [] ={2,8};
        int array4 [] ={32,83};
        System.out.println("Teste2="+s.personalSensitivityToInsulin(6,array3,array4));

        int array5 [] ={1,3,10};
        int array6 [] ={33,43,70};
        System.out.println("Teste3="+s.personalSensitivityToInsulin(0,array5,array6));

        int array7 [] ={1,6,8,9};
        int array8 [] ={32,61,91,88};
        System.out.println("Teste4="+s.personalSensitivityToInsulin(4,array7,array8)); //todo, falha este teste

    }


    @Test
    public void testController() throws Exception {

        Controller n = new Controller();
        //3 value arraylist

        ArrayList<Integer> s=new ArrayList<>();
        s.add(2);
        s.add(1);
        s.add(3);
        System.out.println("result= "+n.voterMechanism(s));


    }
}









