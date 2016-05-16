package server;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by danielamaral on 10/05/16.
 */
class TaskMealtimeInsulinDose implements Callable<Integer> {


    private int TaskCarbohydrateAmount;
    private int TaskCarbohydrateToInsulinRatio;
    private int TaskPreMealBloodSugar;
    private int TaskTargetBloodSugar;
    private int TaskPersonalSensitivity;
    InsulinCalc calculator;
    private int result;

    public TaskMealtimeInsulinDose (){

        calculator = new InsulinCalc();
    }

    @Override
    public Integer call() throws Exception {
        setResult(calculator.mealtimeInsulinDose(getTaskCarbohydrateAmount(), getTaskCarbohydrateToInsulinRatio(), getTaskPreMealBloodSugar(), getTaskTargetBloodSugar(), getTaskPersonalSensitivity()));
        return 0;
    }


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getTaskCarbohydrateAmount() {
        return TaskCarbohydrateAmount;
    }

    public void setTaskCarbohydrateAmount(int taskCarbohydrateAmount) {
        TaskCarbohydrateAmount = taskCarbohydrateAmount;
    }

    public int getTaskCarbohydrateToInsulinRatio() {
        return TaskCarbohydrateToInsulinRatio;
    }

    public void setTaskCarbohydrateToInsulinRatio(int taskCarbohydrateToInsulinRatio) {
        TaskCarbohydrateToInsulinRatio = taskCarbohydrateToInsulinRatio;
    }

    public int getTaskPreMealBloodSugar() {
        return TaskPreMealBloodSugar;
    }

    public void setTaskPreMealBloodSugar(int taskPreMealBloodSugar) {
        TaskPreMealBloodSugar = taskPreMealBloodSugar;
    }

    public int getTaskTargetBloodSugar() {
        return TaskTargetBloodSugar;
    }

    public void setTaskTargetBloodSugar(int taskTargetBloodSugar) {
        TaskTargetBloodSugar = taskTargetBloodSugar;
    }

    public int getTaskPersonalSensitivity() {
        return TaskPersonalSensitivity;
    }

    public void setTaskPersonalSensitivity(int taskPersonalSensitivity) {
        TaskPersonalSensitivity = taskPersonalSensitivity;
    }
}


class TaskBackgroundInsulinDose implements Callable<Integer> {


    private int bodyWeight;

    InsulinCalc calculator;
    private int result;

    public TaskBackgroundInsulinDose(){

        calculator = new InsulinCalc();
    }

    @Override
    public Integer call() throws Exception {
        setResult(calculator.backgroundInsulinDose(getBodyWeight()));
        return 0;
    }


    public int getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(int bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

class TaskPersonalSensivityToInsulin implements Callable<Integer> {


    private int physicalActivityLevel;
    private List<Integer> physicalActivitySamples;
    private List<Integer> bloodSugarDropSamples;

    InsulinCalc calculator;
    private int result;

    public TaskPersonalSensivityToInsulin(){

        calculator = new InsulinCalc();
    }

    @Override
    public Integer call() throws Exception {

        int[] activitySamples = new int[2];
        intListToArray(getPhysicalActivitySamples(),activitySamples);

        int[] bloodSamples = new int[2];
        intListToArray(getBloodSugarDropSamples(),bloodSamples);

        setResult(calculator.personalSensitivityToInsulin(getPhysicalActivityLevel(),activitySamples,bloodSamples));
        return 0;
    }

    public void intListToArray(List<Integer> input,int[] output){

        for (int i=0;i< input.size();i++){
            output[i] = input.get(i);
        }
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getPhysicalActivityLevel() {
        return physicalActivityLevel;
    }

    public void setPhysicalActivityLevel(int physicalActivityLevel) {
        this.physicalActivityLevel = physicalActivityLevel;
    }

    public List<Integer> getPhysicalActivitySamples() {
        return physicalActivitySamples;
    }

    public void setPhysicalActivitySamples(List<Integer> physicalActivitySamples) {
        this.physicalActivitySamples = physicalActivitySamples;
    }

    public List<Integer> getBloodSugarDropSamples() {
        return bloodSugarDropSamples;
    }

    public void setBloodSugarDropSamples(List<Integer> bloodSugarDropSamples) {
        this.bloodSugarDropSamples = bloodSugarDropSamples;
    }
}

class TaskURL1 implements Callable<Integer> {

    public String task;
    private int result;

    url1.InsulinDoseCalculatorService servico1;
    url1.InsulinDoseCalculator interface_1;



    public TaskURL1(){
        servico1 = new url1.InsulinDoseCalculatorService();
        interface_1 = servico1.getInsulinDoseCalculatorPort();
    }
    @Override
    public Integer call() throws Exception {
       switch (task){
           case "mealtimeInsulinDose":
               TaskMealtimeInsulinDose taskMealTimeInsulinDose = new TaskMealtimeInsulinDose();
               setResult(interface_1.mealtimeInsulinDose(taskMealTimeInsulinDose.getTaskCarbohydrateAmount(), taskMealTimeInsulinDose.getTaskCarbohydrateToInsulinRatio(), taskMealTimeInsulinDose.getTaskPreMealBloodSugar(), taskMealTimeInsulinDose.getTaskTargetBloodSugar(), taskMealTimeInsulinDose.getTaskPersonalSensitivity()));
            break;

           case "backgroundInsulinDose":
               TaskBackgroundInsulinDose taskBackgroundInsulinDose = new TaskBackgroundInsulinDose();
               setResult(interface_1.backgroundInsulinDose(taskBackgroundInsulinDose.getBodyWeight()));
               break;

           case "personalSensitivityToInsulin":
               TaskPersonalSensivityToInsulin taskPersonalSensivityToInsulin = new TaskPersonalSensivityToInsulin();
               setResult(interface_1.personalSensitivityToInsulin(taskPersonalSensivityToInsulin.getPhysicalActivityLevel(),  taskPersonalSensivityToInsulin.getPhysicalActivitySamples(), taskPersonalSensivityToInsulin.getBloodSugarDropSamples()));
       }
        return 0;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }


}

class TaskURL2 implements Callable<Integer> {

    public String task;
    private int result;

    url1.InsulinDoseCalculatorService servico2;
    url1.InsulinDoseCalculator interface_2;

    public TaskURL2(){
        servico2 = new url1.InsulinDoseCalculatorService();
        interface_2 = servico2.getInsulinDoseCalculatorPort();
    }
    @Override
    public Integer call() throws Exception {
        switch (task){
            case "mealtimeInsulinDose":
                TaskMealtimeInsulinDose taskMealTimeInsulinDose = new TaskMealtimeInsulinDose();
                setResult(interface_2.mealtimeInsulinDose(taskMealTimeInsulinDose.getTaskCarbohydrateAmount(), taskMealTimeInsulinDose.getTaskCarbohydrateToInsulinRatio(), taskMealTimeInsulinDose.getTaskPreMealBloodSugar(), taskMealTimeInsulinDose.getTaskTargetBloodSugar(), taskMealTimeInsulinDose.getTaskPersonalSensitivity()));
                break;

            case "backgroundInsulinDose":
                TaskBackgroundInsulinDose taskBackgroundInsulinDose = new TaskBackgroundInsulinDose();
                setResult(interface_2.backgroundInsulinDose(taskBackgroundInsulinDose.getBodyWeight()));
                break;

            case "personalSensitivityToInsulin":
                TaskPersonalSensivityToInsulin taskPersonalSensivityToInsulin = new TaskPersonalSensivityToInsulin();
                setResult(interface_2.personalSensitivityToInsulin(taskPersonalSensivityToInsulin.getPhysicalActivityLevel(),  taskPersonalSensivityToInsulin.getPhysicalActivitySamples(), taskPersonalSensivityToInsulin.getBloodSugarDropSamples()));
        }
        return 0;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }


}
