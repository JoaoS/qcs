
package url2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for backgroundInsulinDose complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="backgroundInsulinDose">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bodyWeight" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "backgroundInsulinDose", propOrder = {
    "bodyWeight"
})
public class BackgroundInsulinDose {

    protected int bodyWeight;

    /**
     * Gets the value of the bodyWeight property.
     * 
     */
    public int getBodyWeight() {
        return bodyWeight;
    }

    /**
     * Sets the value of the bodyWeight property.
     * 
     */
    public void setBodyWeight(int value) {
        this.bodyWeight = value;
    }

}
