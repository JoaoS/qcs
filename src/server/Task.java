package server;

import java.util.ArrayList;
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
    public Integer call() {
        return calculator.mealtimeInsulinDose(getTaskCarbohydrateAmount(), getTaskCarbohydrateToInsulinRatio(), getTaskPreMealBloodSugar(), getTaskTargetBloodSugar(), getTaskPersonalSensitivity());

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
    public Integer call() {
        return calculator.backgroundInsulinDose(getBodyWeight());

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
    private int[] physicalActivitySamples;
    private int[] bloodSugarDropSamples;

    InsulinCalc calculator;
    private int result;

    public TaskPersonalSensivityToInsulin(){

        calculator = new InsulinCalc();
    }

    @Override
    public Integer call() {

        result = calculator.personalSensitivityToInsulin(getPhysicalActivityLevel(),physicalActivitySamples,bloodSugarDropSamples);
        return result;

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

    public int[] getPhysicalActivitySamples() {
        return physicalActivitySamples;
    }

    public void setPhysicalActivitySamples(int[] physicalActivitySamples) {
        this.physicalActivitySamples = physicalActivitySamples;
    }

    public int[] getBloodSugarDropSamples() {
        return bloodSugarDropSamples;
    }

    public void setBloodSugarDropSamples(int[] bloodSugarDropSamples) {
        this.bloodSugarDropSamples = bloodSugarDropSamples;
    }
}

class TaskURL1 implements Callable<Integer> {

    public String task;
    public int result = 0;
    TaskMealtimeInsulinDose taskMTID;
    TaskBackgroundInsulinDose taskBID;
    TaskPersonalSensivityToInsulin taskPSTI;
    url1.InsulinDoseCalculatorService service;
    url1.InsulinDoseCalculator interface_;

    int physicalActivityLevel;
    List<Integer> actSamples = new ArrayList<>();
    List<Integer> blSamples = new ArrayList<>();

    public TaskURL1(String task_,TaskMealtimeInsulinDose taskMTID_,TaskBackgroundInsulinDose taskBID_,TaskPersonalSensivityToInsulin taskPSTI_){
        taskMTID = taskMTID_;
        taskBID = taskBID_;
        taskPSTI = taskPSTI_;
        setTask(task_);
        service = new url1.InsulinDoseCalculatorService();
        interface_ = service.getInsulinDoseCalculatorPort();
    }

    @Override
    public Integer call() throws Exception {
       switch (this.getTask()){
           case "mealtimeInsulinDose":
               result = interface_.mealtimeInsulinDose(taskMTID.getTaskCarbohydrateAmount(), taskMTID.getTaskCarbohydrateToInsulinRatio(), taskMTID.getTaskPreMealBloodSugar(), taskMTID.getTaskTargetBloodSugar(), taskMTID.getTaskPersonalSensitivity());
               break;

           case "backgroundInsulinDose":
               result = interface_.backgroundInsulinDose(taskBID.getBodyWeight());
                break;

           case "personalSensitivityToInsulin":
               physicalActivityLevel = taskPSTI.getPhysicalActivityLevel();
               intArrayToIntList(taskPSTI.getPhysicalActivitySamples(),actSamples);
               intArrayToIntList(taskPSTI.getBloodSugarDropSamples(),blSamples);
               result = interface_.personalSensitivityToInsulin(physicalActivityLevel, actSamples, blSamples);
               break;
       }
        return result;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void intArrayToIntList(int[] input,List<Integer> output){

        for (int i=0;i< input.length;i++){
            output.add(input[i]);
        }
    }
}
class TaskURL2 implements Callable<Integer> {

    public String task;
    public int result = 0;
    TaskMealtimeInsulinDose taskMTID;
    TaskBackgroundInsulinDose taskBID;
    TaskPersonalSensivityToInsulin taskPSTI;
    url2.InsulinCalculator_Service service;
    url2.InsulinCalculator interface_;

    int physicalActivityLevel;
    List<Integer> actSamples = new ArrayList<>();
    List<Integer> blSamples = new ArrayList<>();

    public TaskURL2(String task_,TaskMealtimeInsulinDose taskMTID_,TaskBackgroundInsulinDose taskBID_,TaskPersonalSensivityToInsulin taskPSTI_){
        taskMTID = taskMTID_;
        taskBID = taskBID_;
        taskPSTI = taskPSTI_;
        setTask(task_);
        service = new url2.InsulinCalculator_Service();
        interface_ = service.getInsulinCalculatorPort();
    }

    @Override
    public Integer call() throws Exception {
        switch (this.getTask()){
            case "mealtimeInsulinDose":
                result = interface_.mealtimeInsulinDose(taskMTID.getTaskCarbohydrateAmount(), taskMTID.getTaskCarbohydrateToInsulinRatio(), taskMTID.getTaskPreMealBloodSugar(), taskMTID.getTaskTargetBloodSugar(), taskMTID.getTaskPersonalSensitivity());
                break;

            case "backgroundInsulinDose":
                result = interface_.backgroundInsulinDose(taskBID.getBodyWeight());
                break;

            case "personalSensitivityToInsulin":
                physicalActivityLevel = taskPSTI.getPhysicalActivityLevel();
                intArrayToIntList(taskPSTI.getPhysicalActivitySamples(),actSamples);
                intArrayToIntList(taskPSTI.getBloodSugarDropSamples(),blSamples);
                result = interface_.personalSensitivityToInsulin(physicalActivityLevel, actSamples, blSamples);
                break;
        }
        return result;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void intArrayToIntList(int[] input,List<Integer> output){

        for (int i=0;i< input.length;i++){
            output.add(input[i]);
        }
    }
}