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
package org.kuali.module.purap.maintenance;

import org.kuali.core.maintenance.KualiMaintainableImpl;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.purap.bo.PurchaseOrderContractLanguage;

public class PurchaseOrderContractLanguageMaintainableImpl extends KualiMaintainableImpl {

    @Override
    public void prepareForSave() {
        // TODO Auto-generated method stub
        super.prepareForSave();
    }

    @Override
    public void saveBusinessObject() {
        // TODO Auto-generated method stub
        super.saveBusinessObject();
    }
    
    @Override
    public String getLockingRepresentation() {
        // TODO Auto-generated method stub
        return super.getLockingRepresentation();
    }
    
    @Override
    public void processAfterRetrieve() {
        // TODO Auto-generated method stub
        super.processAfterRetrieve();
    }

    @Override
    public void processAfterCopy() {
        PurchaseOrderContractLanguage pocl = (PurchaseOrderContractLanguage)super.getBusinessObject();
        pocl.setContractLanguageCreateDate(SpringServiceLocator.getDateTimeService().getCurrentSqlDate());
    }
}