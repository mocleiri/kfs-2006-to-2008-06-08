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

import java.util.Collection;

import org.w3c.dom.Element;

/**
 *  Indicates an error at a level somewhere above core processing rules,
 *  generally involving 
 *
 * @author     Scott Cantor
 * @created    January 3, 2003
 */
public class TrustException extends InvalidCryptoException implements Cloneable
{
    /**
     *  Creates a new TrustException
     *
     * @param  e    The root of a DOM tree
     * @exception  SAMLException   Raised if an exception occurs while constructing
     *                              the object.
     */
    protected TrustException(Element e)
        throws SAMLException
    {
        super(e);
    }

    /**
     *  Creates a new TrustException
     *
     * @param  msg    The detail message
     */
    public TrustException(String msg)
    {
        super(msg);
    }

    /**
     *  Creates a new TrustException
     *
     * @param  msg    The detail message
     * @param  e      The exception to be wrapped in a TrustException
     */
    public TrustException(String msg, Exception e)
    {
        super(msg,e);
    }

    /**
     *  Creates a new TrustException
     *
     * @param  codes  A collection of QNames
     * @param  msg    The detail message
     */
    public TrustException(Collection codes, String msg)
    {
        super(codes,msg);
    }

    /**
     *  Creates a new TrustException wrapping an existing exception <p>
     *
     *  The existing exception will be embedded in the new one, and its message
     *  will become the default message for the TrustException.</p>
     *
     * @param  codes  A collection of QNames
     * @param  e      The exception to be wrapped in a TrustException
     */
    public TrustException(Collection codes, Exception e)
    {
        super(codes,e);
    }

    /**
     *  Creates a new TrustException from an existing exception. <p>
     *
     *  The existing exception will be embedded in the new one, but the new
     *  exception will have its own message.</p>
     *
     * @param  codes  A collection of QNames
     * @param  msg    The detail message
     * @param  e      The exception to be wrapped in a TrustException
     */
    public TrustException(Collection codes, String msg, Exception e)
    {
        super(codes,msg,e);
    }

    /**
     *  Creates a new TrustException
     *
     * @param  code   A status code
     * @param  msg    The detail message
     */
    public TrustException(QName code, String msg)
    {
        super(code,msg);
    }

    /**
     *  Creates a new TrustException wrapping an existing exception <p>
     *
     *  The existing exception will be embedded in the new one, and its message
     *  will become the default message for the TrustException.</p>
     *
     * @param  code   A status code
     * @param  e      The exception to be wrapped in a TrustException
     */
    public TrustException(QName code, Exception e)
    {
        super(code,e);
    }

    /**
     *  Creates a new TrustException from an existing exception. <p>
     *
     *  The existing exception will be embedded in the new one, but the new
     *  exception will have its own message.</p>
     *
     * @param  code   A status code
     * @param  msg    The detail message
     * @param  e      The exception to be wrapped in a TrustException
     */
    public TrustException(QName code, String msg, Exception e)
    {
        super(code,msg,e);
    }
}

