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

import org.kuali.service.Policy;

/**
 * Simple, starter implementation of an event subsystem. Right now, only 
 * <code>{@link PolicyEvent}</code> objects need to be handled because the only 
 * system being handled right now is for <code>{@link Policy}</code> objects.<br/>
 * <br/>
 * A <code>{@link PolicyEvent}</code> may be handled any way restricted by the 
 * imagination just as a <code>{@link Policy}</code> is implemented. Evaluation of 
 * a <code>{@link Policy}</code> may generate a <code>{@link PolicyEvent}</code>. 
 * Since a <code>{@link Policy}</code> is arbitrarily implemented, it is possible 
 * that the <code>{@link PolicyEvent}</code> may also be arbitrary. There may be 
 * none at all.<br/>
 * <br/>
 * It is possible to log events in a database or even into a serialized persistent 
 * backing store or just a log file.
 * 
 * @author Leo Przybylski
 * @see Policy
 * @see PolicyEvent
 */
public class EventManager {
    public EventManager() {
    }

    /**
     * Reports a <code>{@link PolicyEvent}</code>. Right now it's just going to spew 
     * whatever is in the trace to the STDERR handle.
     *
     * @param event <code>{@link PolicyEvent}</code> instance to report
     * @see PolicyEvent
     * @see Policy
     */
    public static void reportPolicyEvent(PolicyEvent event) {
        // Some reporting
    }
}
