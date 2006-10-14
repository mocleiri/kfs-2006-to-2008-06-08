/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/fp/document/web/struts/PreEncumbranceForm.java,v $
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
package org.kuali.module.financial.web.struts.form;

import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.module.financial.document.PreEncumbranceDocument;

/**
 * This class is the Struts specific form object that works in conjunction with the pojo utilities to build the UI.
 * 
 * 
 * @version $Id: PreEncumbranceForm.java,v 1.3.8.3 2006-10-14 02:29:29 jbmorris Exp $
 */
public class PreEncumbranceForm extends KualiTransactionalDocumentFormBase {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a PreEncumbranceForm.java.
     */
    public PreEncumbranceForm() {
        super();
        setDocument(new PreEncumbranceDocument());
    }

    /**
     * @return Returns the preEncumbranceDocument.
     */
    public PreEncumbranceDocument getPreEncumbranceDocument() {
        return (PreEncumbranceDocument) getDocument();
    }

    /**
     * @param preEncumbranceDocument The preEncumbranceDocument to set.
     */
    public void setPreEncumbranceDocument(PreEncumbranceDocument preEncumbranceDocument) {
        setDocument(preEncumbranceDocument);
    }

    /**
     * This method returns the reversal date in the format MMM d, yyyy.
     * 
     * @return String
     */
    public String getFormattedReversalDate() {
        return formatReversalDate(getPreEncumbranceDocument().getReversalDate());
    }
}
