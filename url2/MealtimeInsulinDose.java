
package url2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mealtimeInsulinDose complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mealtimeInsulinDose">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carbohydrateAmount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="carbohydrateToInsulinRatio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="preMealBloodSugar" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="targetBloodSugar" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="personalSensitivity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mealtimeInsulinDose", propOrder = {
    "carbohydrateAmount",
    "carbohydrateToInsulinRatio",
    "preMealBloodSugar",
    "targetBloodSugar",
    "personalSensitivity"
})
public class MealtimeInsulinDose {

    protected int carbohydrateAmount;
    protected int carbohydrateToInsulinRatio;
    protected int preMealBloodSugar;
    protected int targetBloodSugar;
    protected int personalSensitivity;

    /**
     * Gets the value of the carbohydrateAmount property.
     * 
     */
    public int getCarbohydrateAmount() {
        return carbohydrateAmount;
    }

    /**
     * Sets the value of the carbohydrateAmount property.
     * 
     */
    public void setCarbohydrateAmount(int value) {
        this.carbohydrateAmount = value;
    }

    /**
     * Gets the value of the carbohydrateToInsulinRatio property.
     * 
     */
    public int getCarbohydrateToInsulinRatio() {
        return carbohydrateToInsulinRatio;
    }

    /**
     * Sets the value of the carbohydrateToInsulinRatio property.
     * 
     */
    public void setCarbohydrateToInsulinRatio(int value) {
        this.carbohydrateToInsulinRatio = value;
    }

    /**
     * Gets the value of the preMealBloodSugar property.
     * 
     */
    public int getPreMealBloodSugar() {
        return preMealBloodSugar;
    }

    /**
     * Sets the value of the preMealBloodSugar property.
     * 
     */
    public void setPreMealBloodSugar(int value) {
        this.preMealBloodSugar = value;
    }

    /**
     * Gets the value of the targetBloodSugar property.
     * 
     */
    public int getTargetBloodSugar() {
        return targetBloodSugar;
    }

    /**
     * Sets the value of the targetBloodSugar property.
     * 
     */
    public void setTargetBloodSugar(int value) {
        this.targetBloodSugar = value;
    }

    /**
     * Gets the value of the personalSensitivity property.
     * 
     */
    public int getPersonalSensitivity() {
        return personalSensitivity;
    }

    /**
     * Sets the value of the personalSensitivity property.
     * 
     */
    public void setPersonalSensitivity(int value) {
        this.personalSensitivity = value;
    }

}
