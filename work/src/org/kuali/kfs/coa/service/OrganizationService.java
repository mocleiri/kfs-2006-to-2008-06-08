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
package org.kuali.module.chart.service;

import java.util.List;

import org.kuali.module.chart.bo.Org;

/**
 * This interface defines methods that an Org Service must provide.
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
*/
public interface OrganizationService {
    
    /**
     * This method retrieves an organization instance by its composite primary keys (parameters 
     * passed in).
     * @param chartOfAccountsCode
     * @param organizationCode
     * @return An Org instance.
     */
    public Org getByPrimaryId(String chartOfAccountsCode, String organizationCode);
    
    /**
     * Saves an Org object instance.
     * @param organization
     */
    public void save(Org organization);
    
    /**
     * 
     * Retrieves a List of Accounts that are active, and are tied to this Org.
     * 
     * If there are no Accounts that meet this criteria, an empty list will 
     * be returned.
     * 
     * @param chartOfAccountsCode - chartCode for the Org you want Accounts for
     * @param organizationCode - orgCode for the Org you want Accounts for
     * @return - A List of Accounts that are active, and tied to this Org
     * 
     */
    public List getActiveAccountsByOrg(String chartOfAccountsCode, String organizationCode);
    
    /**
     * 
     * Retrieves a List of Orgs that are active, and that ReportTo this Org
     * 
     * If there are no Orgs that meet this criteria, an empty list will 
     * be returned.
     * 
     * @param chartOfAccountsCode - chartCode for the Org you want Child Orgs for
     * @param organizationCode - orgCode for the Org you want Child Orgs for
     * @return - A List of Orgs that are active, and report to this Org
     * 
     */
    public List getActiveChildOrgs(String chartOfAccountsCode, String organizationCode);
    
}