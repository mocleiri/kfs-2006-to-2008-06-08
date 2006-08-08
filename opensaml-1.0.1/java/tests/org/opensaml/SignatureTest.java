package org.opensaml;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.security.KeyStore;

import org.apache.xml.security.signature.XMLSignature;

import junit.framework.TestCase;

/**
 * @author Scott Cantor
 *
 */
public class SignatureTest extends TestCase
{
    private String path = "data/org/opensaml/test.jks";
    private String alias = "mykey";
    private char[] password = "opensaml".toCharArray();
    private KeyStore ks = null;
    private String xmlpath = "data/org/opensaml/assertion.xml";
    private int count = 1;
    
    /**
     * Constructor for SignatureTest.
     * @param arg0
     */
    public SignatureTest(String arg0)
    {
        super(arg0);
    }

    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(SignatureTest.class);
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

    public void testSignatureLoop() throws Exception
    {
        theTest t = new theTest();
        long total = 0;
    	for (int i=0; i<count; i++) {
    		long elapsed = System.currentTimeMillis();
            t.testSignature();
            total = total + System.currentTimeMillis() - elapsed;
        }
        System.err.println("Avg Time (ms): " + (total / count));
    }
    
    private class theTest {
        private void testSignature() throws Exception
        {
            SAMLAssertion a = new SAMLAssertion(new FileInputStream(xmlpath));
            assertNotNull("No unsigned SAMLAssertion was generated.",a);
            a.sign(XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1,ks.getKey(alias,password),null);
            String dump=a.toString();
            //System.err.println(dump);
            SAMLAssertion a2 = new SAMLAssertion(new ByteArrayInputStream(dump.getBytes()));
            assertNotNull("No signed SAMLAssertion was generated",a2);
            a.verify(ks.getCertificate(alias).getPublicKey());
        }
    }
}
