/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.module.kra.budget.web.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.Constants;
import org.kuali.KeyConstants;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.core.workflow.service.KualiWorkflowInfo;
import org.kuali.module.kra.budget.bo.BudgetAdHocOrg;
import org.kuali.module.kra.budget.bo.BudgetAdHocPermission;
import org.kuali.module.kra.budget.web.struts.form.BudgetForm;

import edu.iu.uis.eden.clientapp.WorkflowInfo;
import edu.iu.uis.eden.clientapp.vo.DocumentContentVO;
import edu.iu.uis.eden.clientapp.vo.DocumentDetailVO;
import edu.iu.uis.eden.clientapp.vo.ReportCriteriaVO;
import edu.iu.uis.eden.clientapp.vo.RouteNodeInstanceVO;
import edu.iu.uis.eden.clientapp.vo.WorkflowAttributeDefinitionVO;
import edu.iu.uis.eden.clientapp.vo.WorkflowIdVO;
import edu.iu.uis.eden.routetemplate.RuleRoutingAttribute;

/**
 * This class handles Actions for Research Administration permissions page.
 * 
 * @author KRA (kualidev@oncourse.iu.edu)
 */
public class BudgetPermissionsAction extends BudgetAction {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BudgetPermissionsAction.class);


    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        BudgetForm budgetForm = (BudgetForm) form;

        if (budgetForm.getNewAdHocPermission().getPersonUniversalIdentifier() == null) {
            // Add page error.
            GlobalVariables.getErrorMap().putError("newAdHocPermission", KeyConstants.ERROR_NO_PERSON_SELECTED, new String[] {});
        }
        else {
            BudgetAdHocPermission newAdHocPermission = budgetForm.getNewAdHocPermission();
            newAdHocPermission.setPersonAddedTimestamp(SpringServiceLocator.getDateTimeService().getCurrentTimestamp());
            newAdHocPermission.setAddedByPerson(SpringServiceLocator.getWebAuthenticationService().getNetworkId(request));
            budgetForm.getBudgetDocument().getBudget().getAdHocPermissions().add(newAdHocPermission);
            budgetForm.setNewAdHocPermission(new BudgetAdHocPermission());
            
            budgetForm.getBudgetDocument().getDocumentHeader().getWorkflowDocument().addAttributeDefinition(new WorkflowAttributeDefinitionVO("blah"));
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    public ActionForward addOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        BudgetForm budgetForm = (BudgetForm) form;

        if (budgetForm.getNewAdHocOrg().getFiscalCampusCode() == null) {
            // Add page error.
            GlobalVariables.getErrorMap().putError("newAdHocOrg", KeyConstants.ERROR_NO_ORG_SELECTED, new String[] {});
        }
        else {
            BudgetAdHocOrg newAdHocOrg = budgetForm.getNewAdHocOrg();
            newAdHocOrg.setPersonAddedTimestamp(SpringServiceLocator.getDateTimeService().getCurrentTimestamp());
            newAdHocOrg.setAddedByPerson(SpringServiceLocator.getWebAuthenticationService().getNetworkId(request));
            budgetForm.getBudgetDocument().getBudget().getAdHocOrgs().add(newAdHocOrg);
            budgetForm.setNewAdHocOrg(new BudgetAdHocOrg());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    } 


    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ((BudgetForm) form).getBudgetDocument().getBudget().getAdHocPermissions().remove(getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    public ActionForward deleteOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ((BudgetForm) form).getBudgetDocument().getBudget().getAdHocOrgs().remove(getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        BudgetForm budgetForm = (BudgetForm) form;

        List adHocPermissions = budgetForm.getBudgetDocument().getBudget().getAdHocPermissions();
        List adHocOrgs = budgetForm.getBudgetDocument().getBudget().getAdHocOrgs();
        
        this.load(mapping, budgetForm, request, response);

        budgetForm.getBudgetDocument().getBudget().setAdHocPermissions(adHocPermissions);
        budgetForm.getBudgetDocument().getBudget().setAdHocOrgs(adHocOrgs);

        return super.save(mapping, budgetForm, request, response);
    }
    
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        BudgetForm budgetForm = (BudgetForm) form;
        
        budgetForm.setNewAdHocPermission(new BudgetAdHocPermission());
        budgetForm.setNewAdHocOrg(new BudgetAdHocOrg());
        
      budgetForm.getBudgetDocument().populateDocumentForRouting();
      WorkflowAttributeDefinitionVO[] test = budgetForm.getBudgetDocument().getDocumentHeader().getWorkflowDocument().getAttributeDefinitions();
      String test2 = budgetForm.getBudgetDocument().getDocumentHeader().getWorkflowDocument().getAttributeContent();
      String[] test3 = budgetForm.getBudgetDocument().getDocumentHeader().getWorkflowDocument().getNodeNames();
      DocumentContentVO test4 = budgetForm.getBudgetDocument().getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocumentContent();
      RuleRoutingAttribute attribute = new RuleRoutingAttribute("KualiBudgetDocument");
      List rows1 = attribute.getRoutingDataRows();
      List rows2 = attribute.getRuleRows();
      List rows3 = attribute.getRuleExtensionValues();
      
      KualiWorkflowInfo service = SpringServiceLocator.getWorkflowInfoService();
      WorkflowInfo info = new WorkflowInfo();
      boolean result = info.isUserAuthenticatedByRouteLog(budgetForm.getBudgetDocument().getDocumentHeader().getWorkflowDocument().getRouteHeaderId(),
              new WorkflowIdVO("JGERHART"), true);
      RouteNodeInstanceVO[] stuff = info.getDocumentRouteNodeInstances(budgetForm.getBudgetDocument().getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
      DocumentDetailVO detail = info.routingReport(new ReportCriteriaVO(budgetForm.getBudgetDocument().getDocumentHeader().getWorkflowDocument().getRouteHeaderId()));
        
        return super.reload(mapping, budgetForm, request, response);
    }
}
