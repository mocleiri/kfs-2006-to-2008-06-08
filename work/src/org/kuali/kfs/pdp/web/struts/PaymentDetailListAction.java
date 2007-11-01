/*
 * Copyright 2007 The Kuali Foundation.
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
/*
 * Created on Oct 5, 2004
 *
 */
package org.kuali.module.pdp.action.paymentdetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.pdp.action.BaseAction;
import org.kuali.module.pdp.bo.PaymentDetail;
import org.kuali.module.pdp.service.PaymentGroupService;
import org.kuali.module.pdp.service.SecurityRecord;
import org.kuali.module.pdp.utilities.GeneralUtilities;


/**
 * @author delyea
 */
public class PaymentDetailListAction extends BaseAction {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PaymentDetailListAction.class);
    private PaymentGroupService paymentGroupService;

    public PaymentDetailListAction() {
        setPaymentGroupService(SpringContext.getBean(PaymentGroupService.class));
    }

    protected boolean isAuthorized(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        SecurityRecord sr = getSecurityRecord(request);
        return sr.isLimitedViewRole() || sr.isViewAllRole() || sr.isViewIdRole() || sr.isViewBankRole();
    }

    protected ActionForward executeLogic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        PaymentDetail pd = (PaymentDetail) request.getSession().getAttribute("PaymentDetail");
        String listType = request.getParameter("listType");

        if (pd == null) {
            // Handle Session expiration
            LOG.info("executeLogic() Payment Detail object 'pd' is null");
            return mapping.findForward("pdp_session_timeout");
        }
        else if (GeneralUtilities.isStringEmpty(listType)) {
            // Invalid call to Action
            return mapping.findForward("pdp_system_error");
        }
        else {
            if ((!("disbursement".equals(listType))) && (!("group".equals(listType)))) {
                // Invalid List Type
                return mapping.findForward("pdp_system_error");
            }
        }
        return mapping.findForward("display");
    }

    public void setPaymentGroupService(PaymentGroupService p) {
        paymentGroupService = p;
    }

}
