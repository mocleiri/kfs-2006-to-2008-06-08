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

import java.io.InputStream;

import org.w3c.dom.*;

/**
 *  Abstract class for a SAML Subject Statement
 *
 * @author     Scott Cantor
 * @created    March 25, 2002
 */
public abstract class SAMLSubjectQuery extends SAMLQuery implements Cloneable
{
    protected SAMLSubject subject = null;

    /**
     *  Default constructor
     */
    public SAMLSubjectQuery() {
    }

    /**
     *  Builds a subject query out of its component parts
     *
     * @param  subject            Subject of query
     * @exception  SAMLException  Raised if a statement cannot be constructed
     *      from the supplied information
     */
    public SAMLSubjectQuery(SAMLSubject subject) throws SAMLException {
        this.subject = subject;
    }

    /**
     *  Reconstructs a subject query from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLSubjectQuery(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a subject query from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLSubjectQuery(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }

    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        // Extract subject.
        subject = new SAMLSubject(XML.getFirstChildElement(e));
    }

    /**
     *  Gets the query subject
     *
     * @return    The query subject
     */
    public SAMLSubject getSubject() {
        return subject;
    }

    /**
     *  Sets the query subject
     *
     * @param subject    The query subject
     * @exception SAMLException     Raised if the subject is invalid
     */
    public void setSubject(SAMLSubject subject) throws SAMLException {
        if (subject != null) {
            if (root != null) {
                root.replaceChild(subject.toDOM(root.getOwnerDocument()), this.subject.root);
            }
            this.subject = subject;
        }
        else
            throw new IllegalArgumentException("subject cannot be null");
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (subject == null)
            throw new MalformedException(SAMLException.RESPONDER, "SubjectQuery invalid, requires subject");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLSubjectQuery dup=(SAMLSubjectQuery)super.clone();
        
        // Clone the embedded objects.
        dup.subject = (SAMLSubject)subject.clone();
        
        return dup;
    }
}

