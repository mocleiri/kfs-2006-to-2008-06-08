/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.module.financial.rule.event;

import org.kuali.core.document.Document;
import org.kuali.kfs.rule.event.AttributedDocumentEventBase;
import org.kuali.module.financial.bo.Check;

/**
 * An event which is fired when a member of the Cash Receipt family of documents deletes a check. 
 */
public class AttributedDeleteCheckEvent extends AttributedDocumentEventBase {
    private final Check check;
    
    /**
     * Initializes fields common to all subclasses
     * 
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param check
     */
    public AttributedDeleteCheckEvent(String description, String errorPathPrefix, Document document, Check check) {
        super(description, errorPathPrefix, document);

        this.check = check;
    }
    
    /**
     * @see org.kuali.core.rule.event.CheckEvent#getCheck()
     */
    public Check getCheck() {
        return check;
    }
}