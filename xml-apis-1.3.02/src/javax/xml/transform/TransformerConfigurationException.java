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

// $Id: TransformerConfigurationException.java,v 1.1 2006-08-08 23:20:43 dbeutel Exp $

package javax.xml.transform;

/**
 * Indicates a serious configuration error.
 */
public class TransformerConfigurationException extends TransformerException {

    /**
     * Create a new <code>TransformerConfigurationException</code> with no
     * detail mesage.
     */
    public TransformerConfigurationException() {
        super("Configuration Error");
    }

    /**
     * Create a new <code>TransformerConfigurationException</code> with
     * the <code>String </code> specified as an error message.
     *
     * @param msg The error message for the exception.
     */
    public TransformerConfigurationException(String msg) {
        super(msg);
    }

    /**
     * Create a new <code>TransformerConfigurationException</code> with a
     * given <code>Exception</code> base cause of the error.
     *
     * @param e The exception to be encapsulated in a
     * TransformerConfigurationException.
     */
    public TransformerConfigurationException(Throwable e) {
        super(e);
    }

    /**
     * Create a new <code>TransformerConfigurationException</code> with the
     * given <code>Exception</code> base cause and detail message.
     *
     * @param e The exception to be encapsulated in a
     *      TransformerConfigurationException
     * @param msg The detail message.
     */
    public TransformerConfigurationException(String msg, Throwable e) {
        super(msg, e);
    }

    /**
     * Create a new TransformerConfigurationException from a message and a Locator.
     *
     * <p>This constructor is especially useful when an application is
     * creating its own exception from within a DocumentHandler
     * callback.</p>
     *
     * @param message The error or warning message.
     * @param locator The locator object for the error or warning.
     */
    public TransformerConfigurationException(String message,
                                             SourceLocator locator) {
        super(message, locator);
    }

    /**
     * Wrap an existing exception in a TransformerConfigurationException.
     *
     * @param message The error or warning message, or null to
     *                use the message from the embedded exception.
     * @param locator The locator object for the error or warning.
     * @param e Any exception.
     */
    public TransformerConfigurationException(String message,
                                             SourceLocator locator,
                                             Throwable e) {
        super(message, locator, e);
    }
}
