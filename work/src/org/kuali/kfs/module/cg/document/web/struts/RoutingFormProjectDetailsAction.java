/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/module/cg/document/web/struts/RoutingFormProjectDetailsAction.java,v $
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
package org.kuali.module.kra.routingform.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.Constants;
import org.kuali.module.kra.routingform.bo.RoutingFormInstitutionCostShare;
import org.kuali.module.kra.routingform.bo.RoutingFormOtherCostShare;
import org.kuali.module.kra.routingform.bo.RoutingFormSubcontractor;
import org.kuali.module.kra.routingform.web.struts.form.RoutingForm;

public class RoutingFormProjectDetailsAction extends RoutingFormAction {

    public ActionForward insertRoutingFormInstitutionCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RoutingForm routingForm = (RoutingForm) form;
        routingForm.getRoutingFormDocument().addRoutingFormInstitutionCostShare(routingForm.getNewRoutingFormInstitutionCostShare());
        
        // use getters and setters on the form to reinitialize the properties on the form.                
        routingForm.setNewRoutingFormInstitutionCostShare(new RoutingFormInstitutionCostShare());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteRoutingFormInstitutionCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RoutingForm routingForm = (RoutingForm) form;

        // Remove the item from the list.
        int lineToDelete = super.getLineToDelete(request);
        routingForm.getRoutingFormDocument().getRoutingFormInstitutionCostShares().remove(lineToDelete);        
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward insertRoutingFormOtherCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RoutingForm routingForm = (RoutingForm) form;
        routingForm.getRoutingFormDocument().addRoutingFormOtherCostShare(routingForm.getNewRoutingFormOtherCostShare());
        
        // use getters and setters on the form to reinitialize the properties on the form.                
        routingForm.setNewRoutingFormOtherCostShare(new RoutingFormOtherCostShare());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteRoutingFormOtherCostShare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RoutingForm routingForm = (RoutingForm) form;

        // Remove the item from the list.
        int lineToDelete = super.getLineToDelete(request);
        routingForm.getRoutingFormDocument().getRoutingFormOtherCostShares().remove(lineToDelete);        
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward insertRoutingFormSubcontractor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RoutingForm routingForm = (RoutingForm) form;
        routingForm.getRoutingFormDocument().addRoutingFormSubcontractor(routingForm.getNewRoutingFormSubcontractor());
        
        // use getters and setters on the form to reinitialize the properties on the form.                
        routingForm.setNewRoutingFormSubcontractor(new RoutingFormSubcontractor());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteRoutingFormSubcontractor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RoutingForm routingForm = (RoutingForm) form;

        // Remove the item from the list.
        int lineToDelete = super.getLineToDelete(request);
        routingForm.getRoutingFormDocument().getRoutingFormSubcontractors().remove(lineToDelete);        
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

}
