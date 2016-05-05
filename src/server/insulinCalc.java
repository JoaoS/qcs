package server;
/**
 * Created by joaosubtil on 04/05/16.
 */
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;


@WebService()
public class insulinCalc implements InsulinDoseCalculator {

    public insulinCalc() {
    }

    @Override  @WebMethod
    public int mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity) {

    try {
        double carbohydrate_dose,high_blood_sugar_dose;

        if(targetBloodSugar > preMealBloodSugar){
            return 0;
        }

        else{

            carbohydrate_dose = carbohydrateAmount / carbohydrateToInsulinRatio /personalSensitivity * 50;

            high_blood_sugar_dose = (preMealBloodSugar - targetBloodSugar) / personalSensitivity;

        }
        int last= (int)Math.round(carbohydrate_dose+high_blood_sugar_dose);

        return last;

    }catch (Exception e){
        return -1;
    }

    }

    @WebMethod
    @Override
    public int backgroundInsulinDose(int bodyWeight) {
        return 0;
    }

    @WebMethod @Override
    public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples) {
        return 0;
    }


    public static void main(String[] argv) {
        Object implementor = new insulinCalc ();
        String address = "http://localhost:9000/insulinCalc";
        Endpoint.publish(address, implementor);
    }
}
