package server;

import java.util.*;
import java.util.concurrent.*;


/**
 * Created by danielamaral on 10/05/16.
 */
public class Controller {

    private String serviceURI;
    private int CarbohydrateAmount;
    private int CarbohydrateToInsulinRatio;
    private int PreMealBloodSugar;
    private int TargetBloodSugar;
    private int PersonalSensitivity;
    private int BodyWeight;
    private int PhysicalActivityLevel;
    private int[] PhysicalActivitySamples;
    private int[] BloodSugarDropSamples;
    private int result;

    public Controller(String incomingServiceURI){
        setServiceURI(incomingServiceURI);
    }

    public void caller() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future;


    /**
    *The voter has to be implemented here, maybe use the 3 urls and then implement the voter
    *
    *
    *
    */
        switch (getServiceURI()){

            /*Meal Time Insulin Dose*/
            case "mealtimeInsulinDose":
                TaskMealtimeInsulinDose taskMealtimeInsulinDose = new TaskMealtimeInsulinDose();

                future = executor.submit(taskMealtimeInsulinDose);

                taskMealtimeInsulinDose.setTaskCarbohydrateAmount(this.getCarbohydrateAmount());
                taskMealtimeInsulinDose.setTaskCarbohydrateToInsulinRatio(this.getCarbohydrateToInsulinRatio());
                taskMealtimeInsulinDose.setTaskPreMealBloodSugar(this.getPreMealBloodSugar());
                taskMealtimeInsulinDose.setTaskTargetBloodSugar(this.getTargetBloodSugar());
                taskMealtimeInsulinDose.setTaskPersonalSensitivity(this.getPersonalSensitivity());


                try {
                    System.out.println("Started..");
                    System.out.println(future.get(4, TimeUnit.SECONDS));
                    setResult(taskMealtimeInsulinDose.getResult());






                    System.out.println("Finished!");
                } catch (TimeoutException e) {
                    future.cancel(true);
                    System.out.println("Timeout Exception!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                executor.shutdownNow();
                break;

            /*Backgroung Insulin Dose*/
            case "backgroundInsulinDose":

                TaskBackgroundInsulinDose taskBackgroundInsulinDose = new TaskBackgroundInsulinDose();
                future = executor.submit(taskBackgroundInsulinDose);
                taskBackgroundInsulinDose.setBodyWeight(this.getBodyWeight());


                try {
                    System.out.println("Started..");

                    future.get(4, TimeUnit.SECONDS);

                    setResult(taskBackgroundInsulinDose.getResult());

                  /*
                  future = executor.submit(interface_1.backgroundInsulinDose(50));


                    url2.InsulinDoseCalculator_Service servico2 = new url2.InsulinDoseCalculator_Service();
                    url2.InsulinDoseCalculator interface_2 = servico2.getInsulinDoseCalculatorPort();
                    interface_2.backgroundInsulinDose(50);
                    */

                    System.out.println("Finished!");
                } catch (TimeoutException e) {
                    future.cancel(true);
                    System.out.println("Timeout Exception!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                executor.shutdownNow();
                break;

            /*Personal Sensivity To Insulin*/
            case "personalSensitivityToInsulin":

                TaskPersonalSensivityToInsulin taskPersonalSensivityToInsulin= new TaskPersonalSensivityToInsulin();
                future = executor.submit(taskPersonalSensivityToInsulin);
                taskPersonalSensivityToInsulin.setPhysicalActivityLevel(this.getPhysicalActivityLevel());
                taskPersonalSensivityToInsulin.setPhysicalActivitySamples(this.getPhysicalActivitySamples());
                taskPersonalSensivityToInsulin.setBloodSugarDropSamples(this.getBloodSugarDropSamples());

                //TEST
                url2.InsulinDoseCalculator_Service servico2 = new url2.InsulinDoseCalculator_Service();
               url2.InsulinDoseCalculator interface_2 = servico2.getInsulinDoseCalculatorPort();

                List<Integer> activitySamples= new ArrayList<>();
                int[] level1=this.getPhysicalActivitySamples();
                System.out.println("list"+level1.toString());
                for (int i=0;i< level1.length;i++){
                    activitySamples.add(level1[i]);
                    System.out.println(activitySamples.get(i));

                }
                List<Integer> bloodSamples= new ArrayList<>();
                int[] level2=this.getBloodSugarDropSamples();
                for (int i=0;i< level2.length;i++){
                    bloodSamples.add(level2[i]);
                    System.out.println(bloodSamples.get(i));
                }

                System.out.println("Foreign value= "+interface_2.personalSensitivityToInsulin(this.getPhysicalActivityLevel(),activitySamples, bloodSamples ));


                try {
                    System.out.println("Started..");
                    System.out.println(future.get(4, TimeUnit.SECONDS));
                    setResult(taskPersonalSensivityToInsulin.getResult());
                    System.out.println("Finished!");
                } catch (TimeoutException e) {
                    future.cancel(true);
                    System.out.println("Timeout Exception!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                executor.shutdownNow();
                break;

        }


    }


    public int getCarbohydrateAmount() {
        return CarbohydrateAmount;
    }

    public void setCarbohydrateAmount(int carbohydrateAmount) {
        CarbohydrateAmount = carbohydrateAmount;
    }

    public int getCarbohydrateToInsulinRatio() {
        return CarbohydrateToInsulinRatio;
    }

    public void setCarbohydrateToInsulinRatio(int carbohydrateToInsulinRatio) {
        CarbohydrateToInsulinRatio = carbohydrateToInsulinRatio;
    }

    public int getPreMealBloodSugar() {
        return PreMealBloodSugar;
    }

    public void setPreMealBloodSugar(int preMealBloodSugar) {
        PreMealBloodSugar = preMealBloodSugar;
    }

    public int getTargetBloodSugar() {
        return TargetBloodSugar;
    }

    public void setTargetBloodSugar(int targetBloodSugar) {
        TargetBloodSugar = targetBloodSugar;
    }

    public int getPersonalSensitivity() {
        return PersonalSensitivity;
    }

    public void setPersonalSensitivity(int personalSensitivity) {
        PersonalSensitivity = personalSensitivity;
    }

    public int getBodyWeight() {
        return BodyWeight;
    }

    public void setBodyWeight(int bodyWeight) {
        BodyWeight = bodyWeight;
    }

    public int getPhysicalActivityLevel() {
        return PhysicalActivityLevel;
    }

    public void setPhysicalActivityLevel(int physicalActivityLevel) {
        PhysicalActivityLevel = physicalActivityLevel;
    }

    public int[] getPhysicalActivitySamples() {
        return PhysicalActivitySamples;
    }

    public void setPhysicalActivitySamples(int[] physicalActivitySamples) {
        PhysicalActivitySamples = physicalActivitySamples;
    }

    public int[] getBloodSugarDropSamples() {
        return BloodSugarDropSamples;
    }

    public void setBloodSugarDropSamples(int[] bloodSugarDropSamples) {
        BloodSugarDropSamples = bloodSugarDropSamples;
    }

    public String getServiceURI() {
        return serviceURI;
    }

    public void setServiceURI(String serviceURI) {
        this.serviceURI = serviceURI;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

