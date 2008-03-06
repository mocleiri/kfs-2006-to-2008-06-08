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
package org.kuali.module.effort.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kfs.KFSConstants;
import org.kuali.module.effort.document.EffortCertificationDocument;
import org.kuali.module.effort.rule.event.LoadDetailLineEvent;
import org.kuali.module.effort.web.struts.form.CertificationRecreateForm;

public class CertificationRecreateAction extends EffortCertificationAction {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CertificationRecreateAction.class);

    /**
     * load the detail lines with the given information
     */
    public ActionForward loadDetailLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CertificationRecreateForm recreateForm = (CertificationRecreateForm) form;
        EffortCertificationDocument effortCertificationDocument = recreateForm.getEffortCertificationDocument();
        recreateForm.forceInputAsUpperCase();

        if (recreateForm.validateImporingFieldValues()) {
            boolean isRulePassed = this.invokeRules(new LoadDetailLineEvent("", "", effortCertificationDocument));
        }

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }
}