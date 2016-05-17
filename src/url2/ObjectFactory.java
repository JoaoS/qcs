
package url2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the url2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PersonalSensitivityToInsulinResponse_QNAME = new QName("http://server/", "personalSensitivityToInsulinResponse");
    private final static QName _BackgroundInsulinDose_QNAME = new QName("http://server/", "backgroundInsulinDose");
    private final static QName _BackgroundInsulinDoseResponse_QNAME = new QName("http://server/", "backgroundInsulinDoseResponse");
    private final static QName _MealtimeInsulinDose_QNAME = new QName("http://server/", "mealtimeInsulinDose");
    private final static QName _Hello_QNAME = new QName("http://server/", "hello");
    private final static QName _MealtimeInsulinDoseResponse_QNAME = new QName("http://server/", "mealtimeInsulinDoseResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://server/", "helloResponse");
    private final static QName _PersonalSensitivityToInsulin_QNAME = new QName("http://server/", "personalSensitivityToInsulin");
    private final static QName _InsulinCalculator_QNAME = new QName("http://server/", "InsulinCalculator");
    private final static QName _InsulinCalculatorResponse_QNAME = new QName("http://server/", "InsulinCalculatorResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: url2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsulinCalculatorResponse }
     * 
     */
    public InsulinCalculatorResponse createInsulinCalculatorResponse() {
        return new InsulinCalculatorResponse();
    }

    /**
     * Create an instance of {@link InsulinCalculator_Type }
     * 
     */
    public InsulinCalculator_Type createInsulinCalculator_Type() {
        return new InsulinCalculator_Type();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link PersonalSensitivityToInsulin }
     * 
     */
    public PersonalSensitivityToInsulin createPersonalSensitivityToInsulin() {
        return new PersonalSensitivityToInsulin();
    }

    /**
     * Create an instance of {@link MealtimeInsulinDoseResponse }
     * 
     */
    public MealtimeInsulinDoseResponse createMealtimeInsulinDoseResponse() {
        return new MealtimeInsulinDoseResponse();
    }

    /**
     * Create an instance of {@link MealtimeInsulinDose }
     * 
     */
    public MealtimeInsulinDose createMealtimeInsulinDose() {
        return new MealtimeInsulinDose();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link BackgroundInsulinDose }
     * 
     */
    public BackgroundInsulinDose createBackgroundInsulinDose() {
        return new BackgroundInsulinDose();
    }

    /**
     * Create an instance of {@link BackgroundInsulinDoseResponse }
     * 
     */
    public BackgroundInsulinDoseResponse createBackgroundInsulinDoseResponse() {
        return new BackgroundInsulinDoseResponse();
    }

    /**
     * Create an instance of {@link PersonalSensitivityToInsulinResponse }
     * 
     */
    public PersonalSensitivityToInsulinResponse createPersonalSensitivityToInsulinResponse() {
        return new PersonalSensitivityToInsulinResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonalSensitivityToInsulinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "personalSensitivityToInsulinResponse")
    public JAXBElement<PersonalSensitivityToInsulinResponse> createPersonalSensitivityToInsulinResponse(PersonalSensitivityToInsulinResponse value) {
        return new JAXBElement<PersonalSensitivityToInsulinResponse>(_PersonalSensitivityToInsulinResponse_QNAME, PersonalSensitivityToInsulinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BackgroundInsulinDose }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "backgroundInsulinDose")
    public JAXBElement<BackgroundInsulinDose> createBackgroundInsulinDose(BackgroundInsulinDose value) {
        return new JAXBElement<BackgroundInsulinDose>(_BackgroundInsulinDose_QNAME, BackgroundInsulinDose.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BackgroundInsulinDoseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "backgroundInsulinDoseResponse")
    public JAXBElement<BackgroundInsulinDoseResponse> createBackgroundInsulinDoseResponse(BackgroundInsulinDoseResponse value) {
        return new JAXBElement<BackgroundInsulinDoseResponse>(_BackgroundInsulinDoseResponse_QNAME, BackgroundInsulinDoseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MealtimeInsulinDose }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "mealtimeInsulinDose")
    public JAXBElement<MealtimeInsulinDose> createMealtimeInsulinDose(MealtimeInsulinDose value) {
        return new JAXBElement<MealtimeInsulinDose>(_MealtimeInsulinDose_QNAME, MealtimeInsulinDose.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MealtimeInsulinDoseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "mealtimeInsulinDoseResponse")
    public JAXBElement<MealtimeInsulinDoseResponse> createMealtimeInsulinDoseResponse(MealtimeInsulinDoseResponse value) {
        return new JAXBElement<MealtimeInsulinDoseResponse>(_MealtimeInsulinDoseResponse_QNAME, MealtimeInsulinDoseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonalSensitivityToInsulin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "personalSensitivityToInsulin")
    public JAXBElement<PersonalSensitivityToInsulin> createPersonalSensitivityToInsulin(PersonalSensitivityToInsulin value) {
        return new JAXBElement<PersonalSensitivityToInsulin>(_PersonalSensitivityToInsulin_QNAME, PersonalSensitivityToInsulin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsulinCalculator_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "InsulinCalculator")
    public JAXBElement<InsulinCalculator_Type> createInsulinCalculator(InsulinCalculator_Type value) {
        return new JAXBElement<InsulinCalculator_Type>(_InsulinCalculator_QNAME, InsulinCalculator_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsulinCalculatorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "InsulinCalculatorResponse")
    public JAXBElement<InsulinCalculatorResponse> createInsulinCalculatorResponse(InsulinCalculatorResponse value) {
        return new JAXBElement<InsulinCalculatorResponse>(_InsulinCalculatorResponse_QNAME, InsulinCalculatorResponse.class, null, value);
    }

}
