package org.opensaml;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import org.apache.xml.security.signature.XMLSignature;

import junit.framework.TestCase;

/**
 * @author Scott Cantor
 *
 */
public class POSTProfileTest extends TestCase
{
    private String path = "data/org/opensaml/test.jks";
    private String alias = "mykey";
    private char[] password = "opensaml".toCharArray();
    private KeyStore ks = null;
    
    /**
     * Constructor for POSTProfileTest.
     * @param arg0
     */
    public POSTProfileTest(String arg0)
    {
        super(arg0);
    }

    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(POSTProfileTest.class);
    }

    /**
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(path), password);
    }

    /**
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testPOSTProfile() throws Exception
    {
        SAMLResponse r = SAMLPOSTProfile.prepare(
            "www.opensaml.org",
            "www.opensaml.org",
            Collections.singleton("http://www.opensaml.org"),
            "foo",
            "foo",
            null,
            "127.0.0.1",
            "foo",
            new Date(),
            Collections.singleton(
            		new SAMLAuthorityBinding(SAMLBinding.SAML_SOAP_HTTPS,
                            "http://www.opensaml.org",
                            new QName(XML.SAMLP_NS,"AttributeQuery")
                            )
                    )
            );
        assertNotNull("No SAMLResponse was generated.",r);
        Iterator i = r.getAssertions();
        ((SAMLAssertion)i.next()).sign(
            XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1,
            ks.getKey(alias,password),
            Arrays.asList(ks.getCertificateChain(alias))
            );
        r.sign(
            XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1,
            ks.getKey(alias,password),
            Arrays.asList(ks.getCertificateChain(alias))
            );
        assertTrue("SAMLResponse is not signed.",r.isSigned());
        System.err.println("================ Generated Response ===============");
        r.toStream(System.err);
        System.err.println();
        
        r.verify(ks.getCertificate(alias));
        
        SAMLResponse r2 = SAMLPOSTProfile.accept(r.toBase64(), "www.opensaml.org", 60, true);
        assertTrue("SAMLResponse is not signed.",r2.isSigned());
        SAMLPOSTProfile.getSSOAssertion(r2,Collections.singleton("http://www.opensaml.org")).verify(ks.getCertificate(alias));
        r2.verify(ks.getCertificate(alias));
        System.err.println("================ Verified Response ===============");
        r2.toStream(System.err);
        System.err.println();
    }
}
