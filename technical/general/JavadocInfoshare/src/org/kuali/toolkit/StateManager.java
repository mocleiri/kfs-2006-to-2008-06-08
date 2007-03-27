/* By Leo Przybylski for the University of Arizona Summer 2005
 *
 * The Educational Community License
 *
 * This Educational Community License (the "License") applies to any
 * original work of authorship (the "Original Work") whose owner (the
 * "Licensor") has placed the following notice immediately following the
 * copyright notice for the Original Work:
 *
 * Copyright (c) 2005 Leo Przybylski
 *
 * Licensed under the Educational Community License version 1.0
 *
 * This Original Work, including software, source code, documents, or
 * other related items, is being provided by the copyright holder(s)
 * subject to the terms of the Educational Community License. By
 * obtaining, using and/or copying this Original Work, you agree that you
 * have read, understand, and will comply with the following terms and
 * conditions of the Educational Community License:
 *
 * Permission to use, copy, modify, merge, publish, distribute, and
 * sublicense this Original Work and its documentation, with or without
 * modification, for any purpose, and without fee or royalty to the
 * copyright holder(s) is hereby granted, provided that you include the
 * following on ALL copies of the Original Work or portions thereof,
 * including modifications or derivatives, that you make:
 * 
 * ¥ The full text of the Educational Community License in a location
 * viewable to users of the redistributed or derivative work.
 *
 * ¥ Any pre-existing intellectual property disclaimers, notices, or terms
 * and conditions.
 *
 * ¥ Notice of any changes or modifications to the Original Work,
 * including the date the changes were made.
 *
 * ¥ Any modifications of the Original Work must be distributed in such a
 * manner as to avoid any confusion with the Original Work of the
 * copyright holders.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * The name and trademarks of copyright holder(s) may NOT be used in
 * advertising or publicity pertaining to the Original or Derivative Works
 * without specific, written prior permission. Title to copyright in the
 * Original Work and any associated documentation will at all times remain
 * with the copyright holders.
 */
package org.kuali.toolkit;

/**
 * Abstraction for maintainin persistent state. Also discovers the desired 
 * <code>{@link StateManager} implementation. The current preferred method is to 
 * use <code>{@link StateManager#getStateClass()}</code> to lookup a System 
 * property with the class name of the <code>{@link StateManager}</code> 
 * implementation to use.
 *
 * @author Leo Przybylski
 */
public abstract class StateManager {
    public static String STATE_CLASS = "kuali.map.class";

    /**
     * All implementations of <code>{@link StateManager}</code> need to implement 
     * this to be complete because we all need to gain access to the state somehow.
     *
     * @return Object
     */
    public abstract Object getState();

    /**
     * Use System property to discover which <code>{@link StateManager}</code> to use.
     *
     * @return String
     * @throws ClassNotFoundException
     */
    private static Class getStateClass() throws ClassNotFoundException {
        return Class.forName(STATE_CLASS);
    }
    
    /**
     * Create a new <code>{@link StateManager}</code> instance for the desired 
     * <code>{@link StateManager}</code>
     * 
     * @return StateManger
     */
    public static StateManager newInstance() {
        return getStateClass().newInstance();
    }
}
