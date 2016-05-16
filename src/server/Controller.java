package server;

import java.util.ArrayList;
import java.util.List;
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
    private ArrayList<Integer> result= new ArrayList<>();

    public Controller(String incomingServiceURI){
        setServiceURI(incomingServiceURI);
    }

    public void caller() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executor1 = Executors.newSingleThreadExecutor();
        ExecutorService executor2 = Executors.newSingleThreadExecutor();

        Future<Integer> future;
        Future<Integer> future1;
        Future<Integer> future2;


        List<Integer> activitySamples= new ArrayList<>();
        List<Integer> bloodSamples= new ArrayList<>();


        switch (getServiceURI()){

            /*Meal Time Insulin Dose*/
            case "mealtimeInsulinDose":
                TaskMealtimeInsulinDose taskMealtimeInsulinDose = new TaskMealtimeInsulinDose();
                TaskURL1 taskMealtimeInsulinDoseURL1 = new TaskURL1();
                TaskURL2 taskMealtimeInsulinDoseURL2 = new TaskURL2();

                taskMealtimeInsulinDose.setTaskCarbohydrateAmount(this.getCarbohydrateAmount());
                taskMealtimeInsulinDose.setTaskCarbohydrateToInsulinRatio(this.getCarbohydrateToInsulinRatio());
                taskMealtimeInsulinDose.setTaskPreMealBloodSugar(this.getPreMealBloodSugar());
                taskMealtimeInsulinDose.setTaskTargetBloodSugar(this.getTargetBloodSugar());
                taskMealtimeInsulinDose.setTaskPersonalSensitivity(this.getPersonalSensitivity());

                future = executor.submit(taskMealtimeInsulinDose);
                future1 = executor1.submit(taskMealtimeInsulinDoseURL1);
                future2 = executor2.submit(taskMealtimeInsulinDoseURL2);

                try {
                    System.out.println("Started..");
                    System.out.println(future.get(4,TimeUnit.SECONDS));
                    System.out.println(future1.get(4, TimeUnit.SECONDS));
                    System.out.println(future2.get(4, TimeUnit.SECONDS));

                    result.add(taskMealtimeInsulinDose.getResult());
                    result.add(taskMealtimeInsulinDoseURL1.getResult());
                    result.add((taskMealtimeInsulinDoseURL2.getResult()));

                    System.out.println("Finished!");
                } catch (TimeoutException e) {
                    future.cancel(true);
                    future1.cancel(true);
                    future2.cancel(true);
                    System.out.println("Timeout Exception!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                executor.shutdownNow();
                executor1.shutdown();
                executor2.shutdown();
                break;

            /*Backgroung Insulin Dose*/
            case "backgroundInsulinDose":
                TaskBackgroundInsulinDose taskBackgroundInsulinDose = new TaskBackgroundInsulinDose();
                TaskURL1 taskBackgroundInsulinDoseURL1 = new TaskURL1();
                TaskURL2 taskBackgroundInsulinDoseURL2 = new TaskURL2();

                taskBackgroundInsulinDose.setBodyWeight(this.getBodyWeight());

                future = executor.submit(taskBackgroundInsulinDose);
                future1 = executor1.submit(taskBackgroundInsulinDoseURL1);
                future2 = executor2.submit(taskBackgroundInsulinDoseURL2);

                try {
                    System.out.println("Started..");

                    future.get(4, TimeUnit.SECONDS);
                    future1.get(4, TimeUnit.SECONDS);
                    future2.get(4, TimeUnit.SECONDS);

                    result.add(taskBackgroundInsulinDose.getResult());
                    result.add(taskBackgroundInsulinDoseURL1.getResult());
                    result.add(taskBackgroundInsulinDoseURL2.getResult());

                    System.out.println("Finished!");
                } catch (TimeoutException e) {
                    future.cancel(true);
                    future1.cancel(true);
                    future2.cancel(true);
                    System.out.println("Timeout Exception!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                executor.shutdownNow();
                executor1.shutdown();
                executor2.shutdown();
                break;

            /*Personal Sensivity To Insulin*/
            case "personalSensitivityToInsulin":

                TaskPersonalSensivityToInsulin taskPersonalSensivityToInsulin= new TaskPersonalSensivityToInsulin();
                TaskURL1 taskPersonalSensivityToInsulinURL1 = new TaskURL1();
                TaskURL2 taskPersonalSensivityToInsulinURL2 = new TaskURL2();

                intArrayToIntList(getPhysicalActivitySamples(),activitySamples);
                intArrayToIntList(getBloodSugarDropSamples(),bloodSamples);

                taskPersonalSensivityToInsulin.setPhysicalActivityLevel(this.getPhysicalActivityLevel());
                taskPersonalSensivityToInsulin.setPhysicalActivitySamples(activitySamples);
                taskPersonalSensivityToInsulin.setBloodSugarDropSamples(bloodSamples);


                future = executor.submit(taskPersonalSensivityToInsulin);
                future1 = executor1.submit(taskPersonalSensivityToInsulinURL1);
                future2 = executor2.submit(taskPersonalSensivityToInsulinURL2);


                try {
                    System.out.println("Started 1 ...");
                    future.get(4, TimeUnit.SECONDS);//nosso
                    result.add(taskPersonalSensivityToInsulin.getResult());
                    System.out.println("Finished!");
                } catch (TimeoutException e) {
                    future.cancel(true);
                    System.out.println("Timeout Exception!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                try{
                    future1.get(4, TimeUnit.SECONDS);
                    result.add(taskPersonalSensivityToInsulinURL1.getResult());
                }catch(TimeoutException e){
                    future1.cancel(true);
                    System.out.println("Timeout Exception!");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                try{
                    future2.get(4, TimeUnit.SECONDS);
                    result.add(taskPersonalSensivityToInsulinURL2.getResult());
                }catch(TimeoutException e){
                    future2.cancel(true);
                    System.out.println("Timeout Exception!");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                executor.shutdownNow();
                executor1.shutdown();
                executor2.shutdown();
                break;

        }


    }

    /**
     *
     * @return
     * timed out values have to bear the -1 value
     */
    public boolean voterMechanism(ArrayList<Integer> values){


        //todo valerá a a pena devolver dados não binários?
        int value1=values.get(0);
        int value2=values.get(1);
        int value3=values.get(2);


        //all timeouts
        if (value1==-1 && value2==-1 && value3==-1) {
                  //
            System.out.println("All timeouts");
            return false;

        }

        //only one value, two timeouts
       /*
            ::a1 > 0 && a2 <= 0 && a3 <= 0 ->	printf("Reject value 1\n");
            ::a2 > 0 && a1 <= 0 && a3 <= 0 ->   printf("Reject value 2\n");
            ::a3 > 0 && a1 <= 0 && a2 <= 0 ->	printf("Reject value 3\n");
       */

       // if (value1 > 0 && value2 ==-1 && value3==-1)







return true;

    }


    /**
     * Returns true if the value is equal or has a 1 point diference
     *
     * @param value1 value of 1st voter
     * @param value2 value of 2nd voter
     *
     *
     *
     */
    public boolean isEqual(int value1, int value2){

        if (value1 == value2)
            return true;

        if(value1-1==value2 || value1+1==value2)
            return  true;

        else
            return false;

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


    public ArrayList<Integer> getResult() {
        return result;
    }

    public void setResult(ArrayList<Integer> result) {
        this.result = result;
    }

    public void intArrayToIntList(int[] input,List<Integer> output){

        for (int i=0;i< input.length;i++){
            output.add(input[i]);
        }
    }
}

