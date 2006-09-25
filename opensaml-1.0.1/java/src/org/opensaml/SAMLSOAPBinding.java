/* 
 * The OpenSAML License, Version 1. 
 * Copyright (c) 2002 
 * University Corporation for Advanced Internet Development, Inc. 
 * All rights reserved
 * 
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution, if any, must include 
 * the following acknowledgment: "This product includes software developed by 
 * the University Corporation for Advanced Internet Development 
 * <http://www.ucaid.edu>Internet2 Project. Alternately, this acknowledegement 
 * may appear in the software itself, if and wherever such third-party 
 * acknowledgments normally appear.
 * 
 * Neither the name of OpenSAML nor the names of its contributors, nor 
 * Internet2, nor the University Corporation for Advanced Internet Development, 
 * Inc., nor UCAID may be used to endorse or promote products derived from this 
 * software without specific prior written permission. For written permission, 
 * please contact opensaml@opensaml.org
 * 
 * Products derived from this software may not be called OpenSAML, Internet2, 
 * UCAID, or the University Corporation for Advanced Internet Development, nor 
 * may OpenSAML appear in their name, without prior written permission of the 
 * University Corporation for Advanced Internet Development.
 * 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND WITH ALL FAULTS. ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE, AND NON-INFRINGEMENT ARE DISCLAIMED AND THE ENTIRE RISK 
 * OF SATISFACTORY QUALITY, PERFORMANCE, ACCURACY, AND EFFORT IS WITH LICENSEE. 
 * IN NO EVENT SHALL THE COPYRIGHT OWNER, CONTRIBUTORS OR THE UNIVERSITY 
 * CORPORATION FOR ADVANCED INTERNET DEVELOPMENT, INC. BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
package org.opensaml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


/**
 *  Base class for policy-specific SOAP over HTTP binding implementations
 *
 *      Scott Cantor
 * @created    November 25, 2001
 */
public class SAMLSOAPBinding implements SAMLBinding
{
	private static Logger log = Logger.getLogger(SAMLSOAPBinding.class.getName());
	
	/** OpenSAML configuration */
	protected SAMLConfig config = SAMLConfig.instance();
		
    /**  Defeault constructor for a SAMLSOAPBinding object */
    public SAMLSOAPBinding() { }

    /**
     *  Used by requester to send a SAML request to an authority, and obtain a
     *  response in return
     *
     * @param  bindingInfo        Defines the binding protocol and the authority
     *      to contact
     * @param  request            SAML request to send
     * @return                    SAML response received from authority
     * @exception  SAMLException  Base class of exceptions that may be thrown
     *      during processing
     */
    public SAMLResponse send(SAMLAuthorityBinding bindingInfo, SAMLRequest request)
        throws SAMLException
    {
        try
        {
            NDC.push("send");

            // Turn the request into a DOM, and use its document for the SOAP nodes.
            Document doc=request.toDOM().getOwnerDocument();
        
            // Build a SOAP envelope and body.
            Element e=doc.createElementNS(XML.SOAP11ENV_NS, "Envelope");
            e.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SOAP11ENV_NS);
            Element body=doc.createElementNS(XML.SOAP11ENV_NS, "Body");
            e.appendChild(body);
        
            // Attach SAML request.
            body.appendChild(request.toDOM());
            
            if (doc.getDocumentElement()==null)
                doc.appendChild(e);
            else
                doc.replaceChild(e, doc.getDocumentElement());

            // Connect to authority.
            log.debug("connecting to SAML authority at " + bindingInfo.getLocation());
            URLConnection conn=new URL(bindingInfo.getLocation()).openConnection();
            conn.setAllowUserInteraction(false);
            conn.setDoOutput(true);
            ((HttpURLConnection)conn).setInstanceFollowRedirects(false);
            ((HttpURLConnection)conn).setRequestMethod("POST");
            ((HttpURLConnection)conn).setRequestProperty("Content-Type","text/xml; charset=UTF-8");
            ((HttpURLConnection)conn).setRequestProperty("SOAPAction","http://www.oasis-open.org/committees/security");
            if (conn instanceof javax.net.ssl.HttpsURLConnection)
            {
                String ks_path=config.getProperty("ssl-keystore");
                if (ks_path != null)
                {
                    String ks_alias=config.getProperty("ssl-alias");
                    String ks_pwd=config.getProperty("ssl-keystore-pwd");
                    String key_pwd=config.getProperty("ssl-key-pwd");
                    KeyStore ks=KeyStore.getInstance("JKS");
                    ks.load(new FileInputStream(ks_path),(ks_pwd!=null) ? ks_pwd.toCharArray() : null);
                    
                    SSLContext ctx=SSLContext.getInstance("TLS");
                    KeyManagerFactory kmf=KeyManagerFactory.getInstance("SunX509");
                    kmf.init(ks,(key_pwd!=null) ? key_pwd.toCharArray() : null);
                    ctx.init(kmf.getKeyManagers(), null, null);
                    ((javax.net.ssl.HttpsURLConnection)conn).setSSLSocketFactory(ctx.getSocketFactory());
                }
            }
            
            Canonicalizer c = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
            conn.connect();
            conn.getOutputStream().write(c.canonicalizeSubtree(e));
            
            String content_type=((HttpURLConnection)conn).getContentType();
            if (!content_type.startsWith("text/xml"))
            {
                log.error("send() received an invalid content type in the response (" + content_type + "), with the following content:");
                BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                log.error(reader.readLine());
                throw new BindingException(SAMLException.RESPONDER,"send() detected an invalid content type in the response: " + content_type);
            }
            
            e=XML.parserPool.parse(conn.getInputStream()).getDocumentElement();
            
            // The root must be a SOAP 1.1 envelope.
            if (!XML.isElementNamed(e,XML.SOAP11ENV_NS,"Envelope"))
                throw new MalformedException(SAMLException.RESPONDER,"SAMLSOAPBinding::send() detected an incompatible or missing SOAP envelope");

            /* Walk the children. If we encounter any headers with mustUnderstand, we have to bail.
               The thinking here is, we're not a "real" SOAP processor, but we have to emulate one that
               understands no headers. For now, assume we're the recipient.
             */
            Element n = XML.getFirstChildElement(e);
            if (XML.isElementNamed(n,XML.SOAP11ENV_NS,"Header"))
            {
                Node header=n.getFirstChild();
                while (header!=null)
                {
                    if (header.getNodeType()==Node.ELEMENT_NODE &&
                        ((Element)header).getAttributeNS(XML.SOAP11ENV_NS,"mustUnderstand")!=null &&
                        ((Element)header).getAttributeNS(XML.SOAP11ENV_NS,"mustUnderstand").equals("1"))
                        throw new SOAPException(SAMLException.RESPONDER,"SAMLSOAPBinding::send() detected a mandatory SOAP header");
                    header=header.getNextSibling();
                }
                n = XML.getNextSiblingElement(n);   // advance to body    
            }
    
            if (n != null)
            {
                // Get the first (and only) child element of the Body.
                n = XML.getFirstChildElement(n);
                if (n != null)
                {
                    // Is it a fault?
                    if (n.getNodeType() == Node.ELEMENT_NODE && XML.isElementNamed((Element)n,XML.SOAP11ENV_NS,"Fault"))
                    {
                        // Find the faultstring element and use it in the message.
                        NodeList nlist = n.getElementsByTagNameNS(null,"faultstring");
                        String msg;
                        if (nlist != null && nlist.getLength() > 0)
                            msg = nlist.item(0).getFirstChild().getNodeValue();
                        else
                            msg = "SAMLSOAPBinding::send() detected a SOAP fault";
                        
                        nlist = n.getElementsByTagNameNS(null,"faultstring");
                        if (nlist != null && nlist.getLength() > 0)
                            throw new SOAPException(QName.getQNameTextNode((Text)nlist.item(0).getFirstChild()),msg);
                        else
                            throw new SOAPException(SOAPException.SERVER,msg);
                    }
    
                    SAMLResponse ret = new SAMLResponse(n);
                    if (!ret.getInResponseTo().equals(request.getId())) {
                    	throw new BindingException("SAMLSOAPBinding.send() unable to match SAML InResponseTo value to request");
                    }
                    return ret;
                }
            }
            throw new SOAPException(SOAPException.SERVER,"SAMLSOAPBinding::send() unable to find a SAML response or fault in SOAP body");
        }
        catch (KeyStoreException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() caught a keystore exception", ex);
        }
        catch (CertificateException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() caught a certificate exception", ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() caught a JCE exception", ex);
        }
        catch (UnrecoverableKeyException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() caught a key exception", ex);
        }
        catch (KeyManagementException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() caught a key mgmt exception", ex);
        }
        catch (MalformedURLException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() detected a malformed URL in the binding provided", ex);
        }
        catch (SAXException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() caught an XML exception while parsing the response", ex);
        }
        catch (InvalidCanonicalizerException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() caught a C14N exception while serializing the request", ex);
        }
        catch (CanonicalizationException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() caught a C14N exception while serializing the request", ex);
        }
        catch (java.io.IOException ex)
        {
            throw new SAMLException("SAMLSOAPBinding.send() caught an I/O exception", ex);
        }
        finally
        {
            NDC.pop();
        }
    }

    /**
     *  Process the receipt of a SAML request<P>
     *
     *  <B>NOTE to implementors:</B> If you want to talk to authorities that use
     *  this implementation as their foundation, do not specify <I>xmlns:xsd</I>
     *  or <I>xmlns:xsi</I> in your SOAP request, or if you do, specify the 2001
     *  schema namespaces. Any valid SOAP 1.1 envelope should validate, just
     *  don't let your toolkit generate the schema prefixes.
     *
     * @param  reqContext         An instance of HttpServletRequest
     * @param  requester          The authenticated name of the requester,
     *      determined from an SSL client certificate, or HTTP authentication
     * @return                    A SAML request
     * @exception  SAMLException  Base class of exceptions that may be thrown
     *      during processing
     * @deprecated
     */
    public SAMLRequest receive(Object reqContext, StringBuffer requester)
        throws SAMLException
    {
		// The client certificate chain should be accessible via an attribute. For now,
		// just pull out the common name from the subject of the first certificate.
		requester.setLength(0);
		HttpServletRequest req = (HttpServletRequest) reqContext;

		X509Certificate[] certarray = (X509Certificate[]) req.getAttribute("javax.servlet.request.X509Certificate");
		if (certarray != null && certarray.length > 0) {
			StringTokenizer tokenizer = new StringTokenizer(certarray[0].getSubjectDN().getName(), ", ");
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				if (token.startsWith("CN=")) {
					requester.append(token.substring(3));
					break;
				}
			}
			log.debug("Requester name: " + requester);
		} else {
			log.debug("No Requester name available.");
		}

		return receive(reqContext);
    }
    
    /**
     *  Process the receipt of a SAML request<P>
     *
     *  <B>NOTE to implementors:</B> If you want to talk to authorities that use
     *  this implementation as their foundation, do not specify <I>xmlns:xsd</I>
     *  or <I>xmlns:xsi</I> in your SOAP request, or if you do, specify the 2001
     *  schema namespaces. Any valid SOAP 1.1 envelope should validate, just
     *  don't let your toolkit generate the schema prefixes.
     *
     * @param  reqContext         An instance of HttpServletRequest
     * @return                    A SAML request
     * @exception  SAMLException  Base class of exceptions that may be thrown
     *      during processing
     */
    public SAMLRequest receive(Object reqContext)
        throws SAMLException
    {
        // This is a basic implementation that should suffice as a starting step
        // for any app-specific extensions, which should invoke super() and
        // then perform any additional checking.

        // The SAML SOAP binding requires that we receieve a SOAP envelope via
        // the POST method as text/xml.
        HttpServletRequest req = (HttpServletRequest)reqContext;
        if (!req.getMethod().equals("POST") || !req.getContentType().startsWith("text/xml"))
            throw new BindingException(SAMLException.REQUESTER, "SAMLSOAPBinding.receive() found a bad HTTP method or content type");

        // The body of the POST contains the XML document to parse as a SOAP envelope.

        /* XXX - This is less than ideal because it assumes the envelope can be validated
           using the 2001/Schema namespace against the unofficial SOAP 1.1 schema. This isn't
           so terrible, except that if a SOAP toolkit used by a SHAR produces an envelope
           that explicitly sets the xsd or xsi namespaces to something older, we're screwed.
           (Apache SOAP parses without validating, so they can handle multiple schema levels.)
         */
        try
        {
            Document doc = XML.parserPool.parse(req.getInputStream());
            Element e = doc.getDocumentElement();

            // The root must be a SOAP 1.1 envelope.
            if (!XML.isElementNamed(e,XML.SOAP11ENV_NS,"Envelope"))
                throw new SOAPException(SOAPException.VERSION, "SAMLSOAPBinding.receive() detected an incompatible or missing SOAP envelope");

            /* Walk the children. If we encounter any headers with mustUnderstand, we have to bail.
               The thinking here is, we're not a "real" SOAP processor, but we have to emulate one that
               understands no headers. For now, assume we're the recipient.
             */
            Node n = e.getFirstChild();
            while (n != null && n.getNodeType() != Node.ELEMENT_NODE)
                n = n.getNextSibling();
            if (XML.isElementNamed((Element)n,XML.SOAP11ENV_NS,"Header"))
            {
                Node header = n.getFirstChild();
                while (header != null)
                {
                    if (header.getNodeType() == Node.ELEMENT_NODE &&
                        ((Element)header).getAttributeNS(XML.SOAP11ENV_NS, "mustUnderstand").equals("1"))
                        throw new SOAPException(SOAPException.MUSTUNDERSTAND, "SAMLSOAPBinding.receive() detected a mandatory SOAP header");
                    header = header.getNextSibling();
                }
                // Advance to the Body element.
                n = n.getNextSibling();
                while (n != null && n.getNodeType() != Node.ELEMENT_NODE)
                    n = n.getNextSibling();
            }

            /* The element after the optional Header is the mandatory Body (the meat). The SAML
               SOAP binding specifies the samlp:Request be immediately inside the body. Until
               we locate a Request (which we know validated), we're still in SOAP land. A SOAP
               envelope without a samlp:Request inside it is treated as a SOAP Client fault.
             */
            if (n != null)
            {
                n = n.getFirstChild();
                while (n != null && n.getNodeType() != Node.ELEMENT_NODE)
                    n = n.getNextSibling();
            }

            return new SAMLRequest((Element)n);
        }
        catch (SAXException e)
        {
            throw new SOAPException(SOAPException.CLIENT, "SAMLSOAPBinding.receive() detected an XML parsing error: " + e.getMessage());
        }
        catch (java.io.IOException e)
        {
            throw new SOAPException(SOAPException.SERVER, "SAMLSOAPBinding.receive() detected an I/O error: " + e.getMessage());
        }
    }

    /**
     *  Return a response to a requester
     *
     * @param  respContext              An instance of HttpServletResponse
     * @param  response                 The SAML response to return (optional)
     * @param  e                        An exception to translate into a SOAP fault
     * @exception  java.io.IOException  Thrown if sending of data fails
     */
    public void respond(Object respContext, SAMLResponse response, SAMLException e)
        throws java.io.IOException
    {
        HttpServletResponse resp=(HttpServletResponse)respContext;

        try
        {
            Document doc = (e==null) ? response.toDOM().getOwnerDocument() : XML.parserPool.newDocument();

            // Build the SOAP envelope and body for the response.
            Element env = doc.createElementNS(XML.SOAP11ENV_NS, "soap:Envelope");
            env.setAttributeNS(XML.XMLNS_NS,"xmlns:soap", XML.SOAP11ENV_NS);
            env.setAttributeNS(XML.XMLNS_NS,"xmlns:xsd", XML.XSD_NS);
            env.setAttributeNS(XML.XMLNS_NS,"xmlns:xsi", XML.XSI_NS);
            if (doc.getDocumentElement()==null)
                doc.appendChild(env);
            else
                doc.replaceChild(env, doc.getDocumentElement());
            Element body = doc.createElementNS(XML.SOAP11ENV_NS, "soap:Body");
            env.appendChild(body);

            // If we're handed an exception, turn it into a SOAP fault.
            if (e != null)
            {
                Element fault = doc.createElementNS(XML.SOAP11ENV_NS, "soap:Fault");
                body.appendChild(fault);
                Element elem = doc.createElementNS(null,"faultcode");
                if (e instanceof SOAPException)
                {
                    Iterator codes = e.getCodes();
                    if (codes.hasNext())
                        elem.appendChild(doc.createTextNode("soap:" + ((QName)codes.next()).getLocalName()));
                    else
                        elem.appendChild(doc.createTextNode("soap:" + SOAPException.SERVER.getLocalName()));
                }
                else
                    elem.appendChild(doc.createTextNode("soap:" + SOAPException.SERVER.getLocalName()));
                fault.appendChild(elem);
                    
                elem = doc.createElementNS(null,"faultstring");
                fault.appendChild(elem).appendChild(doc.createTextNode(e.getMessage()));

                // The completed SOAP fault package constitutes the whole response.
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                Canonicalizer c = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
                resp.getOutputStream().write(c.canonicalizeSubtree(env));
                return;
            }

            // Attach the SAML response.
            body.appendChild(response.toDOM());

            Canonicalizer c = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
            resp.setContentType("text/xml; charset=UTF-8");
            resp.getOutputStream().write(c.canonicalizeSubtree(env));
        }
        catch (InvalidCanonicalizerException ex)
        {
            ex.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "SAMLSOAPBinding.respond() unable to serialize XML document instance");
        }
        catch (CanonicalizationException ex)
        {
            ex.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "SAMLSOAPBinding.respond() unable to serialize XML document instance");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "SAMLSOAPBinding.respond() caught an unexpected exception: " + ex.getClass().getName() + " " + ex.getMessage());
        }
    }
}

