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

        int output=0;
        try {
            output = (int) (Math.round((0.55 * bodyWeight) / 0.5));
        }catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return output;
    }

    @WebMethod @Override
    public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples) {
        float alpha = 0;
        float beta = 0;
        int i = 0;

        try {
            if (physicalActivitySamples.length == bloodSugarDropSamples.length && physicalActivitySamples.length >= 2 && physicalActivitySamples.length <= 10 && bloodSugarDropSamples.length >= 2 && bloodSugarDropSamples.length <= 10) {
                SimpleRegression regression = new SimpleRegression(true);
                for (i = 0; i < physicalActivitySamples.length; i++) {
                    regression.addData(physicalActivitySamples[i], bloodSugarDropSamples[i]);
                }
                alpha = (float) regression.getIntercept();
                beta = (float) regression.getSlope();

            } else {
                System.out.println("It's not possible calculate personal sensitivity");
            }
        }catch (Exception e){
            System.out.println("Exception:" + e);
        }
        return Math.round(alpha + beta * physicalActivityLevel);
    }


    public static void main(String[] argv) {
        Object implementor = new insulinCalc ();
        String address = "http://localhost:9000/insulinCalc";
        Endpoint.publish(address, implementor);
    }
}
