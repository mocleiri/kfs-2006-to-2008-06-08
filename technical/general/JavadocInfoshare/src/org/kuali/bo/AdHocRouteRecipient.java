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
package org.kuali.bo;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.Constants;
import org.kuali.bo.user.AuthenticationUserId;
import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.exceptions.UserNotFoundException;
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationErrorList;

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.WorkgroupVO;
import edu.iu.uis.eden.clientapp.EdenInfo;
import edu.iu.uis.eden.exception.EdenException;
import edu.iu.uis.eden.exception.InvalidWorkgroupException;
import edu.iu.uis.eden.util.CodeTranslator;

/**
 * @author bmcgough
 * 
 * This class will contain a 
 *
 */
public class AdHocRouteRecipient extends BusinessObjectBase {

    private static Map actionRequestCds = CodeTranslator.arLabels;
    public static final Integer PERSON_TYPE = new Integer(0);
    public static final Integer WORKGROUP_TYPE = new Integer(1);
    
    protected Integer type;
    protected String actionRequested;
    protected String id;  //can be networkId or workgroupname
   
    public AdHocRouteRecipient() {
        // set some defaults that can be overridden
        this.actionRequested = EdenConstants.ACTION_REQUEST_APPROVE_REQ;
    }
    
    public String getActionRequested() {
        return actionRequested;
    }
    public void setActionRequested(String actionRequested) {
        this.actionRequested = actionRequested;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    
    public String getActionRequestedValue(){
        String actionRequestedValue = null;
        if(StringUtils.isNotBlank(getActionRequested())) {
            actionRequestedValue = (String) actionRequestCds.get(getActionRequested());
        } 
        return actionRequestedValue;
    }

    
    public void validate(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        this.setValidationNumber(errors.getNextValidationNumber());
        if (PERSON_TYPE.equals(getType())) {
            // validate that they are a user from the user service by looking them up
            try {
                SpringServiceLocator.getKualiUserService().getUser(new AuthenticationUserId(getId()));
            } catch (UserNotFoundException userNotFoundException) {
                errors.addError(getValidationNumber(), "id", "error.adhoc.invalid.person");        
            }
        }
        if (WORKGROUP_TYPE.equals(getType())) {
            // validate that they are a workgroup from the workgroup service by looking them up
            // TODO retrofit this to make sure that it is a valid workgroup before we call in
            EdenInfo info = new EdenInfo(Constants.KUALI_WORKFLOW_APPLICATION_CODE);
            try {
                WorkgroupVO workgroupVo = info.getWorkgroup(getId());
            } catch (InvalidWorkgroupException e) {
                errors.addError(getValidationNumber(), "id", "error.adhoc.invalid.workgroup");
            } catch (EdenException e) {
                errors.addError(getValidationNumber(), "id", "error.adhoc.invalid.workgroup");
            }
        }
        errors.throwErrors();
    }
}
