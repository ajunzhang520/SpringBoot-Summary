
package com.fiberhome.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.fiberhome.endpoint package. 
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

    private final static QName _MethodOfTestResponse_QNAME = new QName("http://endpoint.fiberhome.com/", "methodOfTestResponse");
    private final static QName _MethodOfTest_QNAME = new QName("http://endpoint.fiberhome.com/", "methodOfTest");
    private final static QName _Exception_QNAME = new QName("http://endpoint.fiberhome.com/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.fiberhome.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MethodOfTestResponse }
     * 
     */
    public MethodOfTestResponse createMethodOfTestResponse() {
        return new MethodOfTestResponse();
    }

    /**
     * Create an instance of {@link MethodOfTest }
     * 
     */
    public MethodOfTest createMethodOfTest() {
        return new MethodOfTest();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link ResultValue }
     * 
     */
    public ResultValue createResultValue() {
        return new ResultValue();
    }

    /**
     * Create an instance of {@link RequestValue }
     * 
     */
    public RequestValue createRequestValue() {
        return new RequestValue();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MethodOfTestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.fiberhome.com/", name = "methodOfTestResponse")
    public JAXBElement<MethodOfTestResponse> createMethodOfTestResponse(MethodOfTestResponse value) {
        return new JAXBElement<MethodOfTestResponse>(_MethodOfTestResponse_QNAME, MethodOfTestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MethodOfTest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.fiberhome.com/", name = "methodOfTest")
    public JAXBElement<MethodOfTest> createMethodOfTest(MethodOfTest value) {
        return new JAXBElement<MethodOfTest>(_MethodOfTest_QNAME, MethodOfTest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.fiberhome.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
