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
    public List<Future<Integer>> futureList = new ArrayList<>();
    private ArrayList<Integer> output = new ArrayList<Integer>();
    private int nPools = 3;

    public Controller(String incomingServiceURI){
        setServiceURI(incomingServiceURI);
    }

    //for testing
    public Controller() {
    }

    public void caller() throws Exception {


        Future<Integer> result;

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nPools);


        List<Integer> activitySamples= new ArrayList<>();
        List<Integer> bloodSamples= new ArrayList<>();

        switch (getServiceURI()) {
            /*Meal Time Insulin Dose*/
            case "mealtimeInsulinDose":
                TaskMealtimeInsulinDose taskMealtimeInsulinDose = new TaskMealtimeInsulinDose();

                taskMealtimeInsulinDose.setTaskCarbohydrateAmount(this.getCarbohydrateAmount());
                taskMealtimeInsulinDose.setTaskCarbohydrateToInsulinRatio(this.getCarbohydrateToInsulinRatio());
                taskMealtimeInsulinDose.setTaskPreMealBloodSugar(this.getPreMealBloodSugar());
                taskMealtimeInsulinDose.setTaskPersonalSensitivity(this.getPersonalSensitivity());

                TaskURL1 taskMealTimeInsulinDoseURL1 = new TaskURL1(getServiceURI(),taskMealtimeInsulinDose,null,null);
                TaskURL2 taskMealTimeInsulinDoseURL2  = new TaskURL2(getServiceURI(),taskMealtimeInsulinDose,null,null);

                List<Callable<Integer>> pool = new ArrayList<>();
                pool.add(taskMealtimeInsulinDose);
                pool.add(taskMealTimeInsulinDoseURL1);
                pool.add(taskMealTimeInsulinDoseURL2);

                futureList = executor.invokeAll(pool, 4, TimeUnit.SECONDS);

                for (Future<Integer> future : futureList) {
                    try {

                        if (!future.isCancelled())
                            output.add(future.get());

                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                        System.out.println("Interrupted Exception");
                        //Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
                        //e.printStackTrace();
                        System.out.println("Execution Exception");
                        //Thread.currentThread().interrupt();
                    }
                }

                for(int i=0;i<output.size();i++){
                    System.out.println("RESULT "+i+":"+output.get(i));
                }

                executor.shutdown();
                break;
            /*Backgroung Insulin Dose*/
            case "backgroundInsulinDose":

                TaskBackgroundInsulinDose taskBackgroundInsulinDose= new TaskBackgroundInsulinDose();
                taskBackgroundInsulinDose.setBodyWeight(this.getBodyWeight());

                TaskURL1 taskBackgroungInsulinDoseURL1 = new TaskURL1(getServiceURI(),null,taskBackgroundInsulinDose,null);
                TaskURL2 taskBackgroungInsulinDoseURL2 = new TaskURL2(getServiceURI(),null,taskBackgroundInsulinDose,null);

                pool = new ArrayList<>();
                pool.add(taskBackgroundInsulinDose);
                pool.add(taskBackgroungInsulinDoseURL1);
                pool.add(taskBackgroungInsulinDoseURL2);

                futureList = executor.invokeAll(pool, 4, TimeUnit.SECONDS);

                for (Future<Integer> future : futureList) {
                    try {

                        if (!future.isCancelled())
                            output.add(future.get());

                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                        System.out.println("Interrupted Exception");
                        //Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
                        //e.printStackTrace();
                        System.out.println("Execution Exception");
                        //Thread.currentThread().interrupt();
                    }
                }

                for(int i=0;i<output.size();i++){
                    System.out.println("RESULT "+i+":"+output.get(i));
                }

                executor.shutdown();
                break;

            /*Personal Sensivity To Insulin*/
            case "personalSensitivityToInsulin":

                TaskPersonalSensivityToInsulin taskPersonalSensivityToInsulin = new TaskPersonalSensivityToInsulin();
                taskPersonalSensivityToInsulin.setPhysicalActivityLevel(this.getPhysicalActivityLevel());
                taskPersonalSensivityToInsulin.setPhysicalActivitySamples(this.getPhysicalActivitySamples());
                taskPersonalSensivityToInsulin.setBloodSugarDropSamples(this.getBloodSugarDropSamples());

                TaskURL1 taskPersonalSensivityToInsulinURL1 = new TaskURL1(getServiceURI(),null,null,taskPersonalSensivityToInsulin);
                TaskURL2 taskPersonalSensivityToInsulinURL2 = new TaskURL2(getServiceURI(),null,null,taskPersonalSensivityToInsulin);

                pool = new ArrayList<>();
                pool.add(taskPersonalSensivityToInsulin);
                pool.add(taskPersonalSensivityToInsulinURL1);
                pool.add(taskPersonalSensivityToInsulinURL2);

                futureList = executor.invokeAll(pool, 4, TimeUnit.SECONDS);

                for (Future<Integer> future : futureList) {
                    try {

                        if (!future.isCancelled())
                            output.add(future.get());

                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                        System.out.println("Interrupted Exception");
                        //Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
                        //e.printStackTrace();
                        System.out.println("Execution Exception");
                        //Thread.currentThread().interrupt();
                    }
                }

                for(int i=0;i<output.size();i++){
                    System.out.println("RESULT "+i+":"+output.get(i));
                }
                executor.shutdown();
                break;
        }

    }

    /**
     * @param values arraylist with values, if a certain service times-out the corresponding index shall bear the -1 value
     * @return the value or -1 if it cannot reach a conclusion
     *
     */
    public int  voterMechanism(ArrayList<Integer> values){

        //mediana
        Collections.sort(values);
        //correct the -1
        int timeouts=Collections.frequency(values,-1);

        if (timeouts<=1){

            //voter system
            int leftDistance=values.get(1)-values.get(0);
            int rightDistance=values.get(2)-values.get(1);

            if (leftDistance <= 1 || rightDistance <=1) {
                return values.get(1);
            }

        }

        //else
        return -1;

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

    public ArrayList<Integer> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<Integer> output) {
        this.output = output;
    }
}

