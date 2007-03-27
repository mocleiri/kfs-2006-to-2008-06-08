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
package org.kuali.event;

import org.kuali.bo.Document;

import org.kuali.service.Policy;
import org.kuali.service.PolicyException;

/**
 * Event created from activity of a <code>{@link Policy}</code> evaluating a 
 * <code>{@link Document}</code>
 * 
 * @author Leo Przybylski
 * @see Policy
 * @see PolicyException
 */
public class PolicyEvent implements KualiEvent {
    private String _trace;
    
    /**
     * Creates a PolicyEvent from a <code>{@link PolicyException}</code>.
     *
     * @param pe <code>{@link PolicyException}</code> to create an event from.
     */
    public PolicyEvent(PolicyException pe) {
        
    }

    /**
     * @see org.kuali.event.KualiEvent#getTrace()
     */ 
    public String getTrace() {
        return _trace;
    }
}
