/*
 * Copyright 2003-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// $Id: StreamResult.java,v 1.1 2006-08-08 23:20:43 dbeutel Exp $

package javax.xml.transform.stream;

import java.lang.reflect.Method;
import javax.xml.transform.Result;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.net.MalformedURLException;

/**
 * <p>Acts as an holder for a transformation result,
 * which may be XML, plain Text, HTML, or some other form of markup.</p>
 *
 * @author <a href="Jeff.Suttor@Sun.com">Jeff Suttor</a>
 */
public class StreamResult implements Result {

    /** If {@link javax.xml.transform.TransformerFactory#getFeature}
     * returns true when passed this value as an argument,
     * the Transformer supports Result output of this type.
     */
    public static final String FEATURE =
        "http://javax.xml.transform.stream.StreamResult/feature";

    /**
     * Zero-argument default constructor.
     */
    public StreamResult() {
    }

    /**
     * Construct a StreamResult from a byte stream.  Normally,
     * a stream should be used rather than a reader, so that
     * the transformer may use instructions contained in the
     * transformation instructions to control the encoding.
     *
     * @param outputStream A valid OutputStream reference.
     */
    public StreamResult(OutputStream outputStream) {
        setOutputStream(outputStream);
    }

    /**
     * Construct a StreamResult from a character stream.  Normally,
     * a stream should be used rather than a reader, so that
     * the transformer may use instructions contained in the
     * transformation instructions to control the encoding.  However,
     * there are times when it is useful to write to a character
     * stream, such as when using a StringWriter.
     *
     * @param writer  A valid Writer reference.
     */
    public StreamResult(Writer writer) {
        setWriter(writer);
    }

    /**
     * Construct a StreamResult from a URL.
     *
     * @param systemId Must be a String that conforms to the URI syntax.
     */
    public StreamResult(String systemId) {
        this.systemId = systemId;
    }

    /**
     * Construct a StreamResult from a File.
     *
     * @param f Must a non-null File reference.
     */
    public StreamResult(File f) {
        setSystemId(f);
    }

    /**
     * Set the ByteStream that is to be written to.  Normally,
     * a stream should be used rather than a reader, so that
     * the transformer may use instructions contained in the
     * transformation instructions to control the encoding.
     *
     * @param outputStream A valid OutputStream reference.
     */
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Get the byte stream that was set with setOutputStream.
     *
     * @return The byte stream that was set with setOutputStream, or null
     * if setOutputStream or the ByteStream constructor was not called.
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Set the writer that is to receive the result.  Normally,
     * a stream should be used rather than a writer, so that
     * the transformer may use instructions contained in the
     * transformation instructions to control the encoding.  However,
     * there are times when it is useful to write to a writer,
     * such as when using a StringWriter.
     *
     * @param writer  A valid Writer reference.
     */
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    /**
     * Get the character stream that was set with setWriter.
     *
     * @return The character stream that was set with setWriter, or null
     * if setWriter or the Writer constructor was not called.
     */
    public Writer getWriter() {
        return writer;
    }

    /**
     * Set the systemID that may be used in association
     * with the byte or character stream, or, if neither is set, use
     * this value as a writeable URI (probably a file name).
     *
     * @param systemId The system identifier as a URI string.
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * <p>Set the system ID from a <code>File</code> reference.</p>
     * 
     * <p>Note the use of {@link File#toURI()} and {@link File#toURL()}.
     * <code>toURI()</code> is prefered and used if possible.
     * To allow JAXP 1.3 to run on J2SE 1.3, <code>toURL()</code>
     * is used if a {@link NoSuchMethodException} is thrown by the attempt
     * to use <code>toURI()</code>.</p>
     *
     * @param f Must a non-null File reference.
     */
    public void setSystemId(File f) {
    	
    	try {
    		// use reflection to call f.toURI().toString().
    		// Avoid compile time dependency on J2SE 1.4.
    		Method toURIMethod = f.getClass().getMethod("toURI", (Class[]) null);
    		Object uri = toURIMethod.invoke(f, (Object[]) null);
    		Method toStringMethod = uri.getClass().getMethod("toString", (Class[]) null);
    		this.systemId = (String)toStringMethod.invoke(uri, (Object[]) null);
    	} 
    	catch (Exception exception)
	{
    		// running on J2SE 1.3?
    		try {
        		this.systemId = f.toURL().toString();
    		} catch (MalformedURLException malformedURLException) {
    			this.systemId = null;
    			throw new RuntimeException(
    					"javax.xml.transform.stream.StreamResult#setSystemId(File f) with MalformedURLException: "
    					+ malformedURLException.toString()
    			);
    		}
        }
    }

    /**
     * Get the system identifier that was set with setSystemId.
     *
     * @return The system identifier that was set with setSystemId, or null
     * if setSystemId was not called.
     */
    public String getSystemId() {
        return systemId;
    }

    //////////////////////////////////////////////////////////////////////
    // Internal state.
    //////////////////////////////////////////////////////////////////////

    /**
     * The systemID that may be used in association
     * with the byte or character stream, or, if neither is set, use
     * this value as a writeable URI (probably a file name).
     */
    private String systemId;

    /**
     * The byte stream that is to be written to.
     */
    private OutputStream outputStream;

    /**
     * The character stream that is to be written to.
     */
    private Writer writer;
}
