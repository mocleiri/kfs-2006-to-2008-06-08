/*
 * Copyright 2006-2007 The Kuali Foundation.
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
package org.kuali.module.purap.web.struts.form;

import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.module.purap.document.AssignContractManagerDocument;

/**
 * Struts Action Form for Contract Manager Assignment Document. 
 */
public class AssignContractManagerForm extends KualiTransactionalDocumentFormBase {

    /**
     * Constructs a AssignContractManagerForm instance 
     */
    public AssignContractManagerForm() {
        super();
        setDocument(new AssignContractManagerDocument());
    }

}