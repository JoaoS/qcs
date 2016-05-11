package server;

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
    private int[] physicalActivitySamples;
    private int[] bloodSugarDropSamples;

    InsulinCalc calculator;
    private int result;

    public TaskPersonalSensivityToInsulin(){

        calculator = new InsulinCalc();
    }

    @Override
    public Integer call() throws Exception {

        setResult(calculator.personalSensitivityToInsulin(getPhysicalActivityLevel(),getPhysicalActivitySamples(),getBloodSugarDropSamples()));
        return 0;
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
