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
package org.kuali.module.ar.bo;

import org.kuali.core.bo.user.KualiModuleUserBase;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.module.chart.bo.ChartUser;

public class ArUser extends KualiModuleUserBase {

    public static final String MODULE_ID = "ar";
    
    public ArUser( UniversalUser user ) {
        super( MODULE_ID, user );
    }
    
    /**
     * Returns the active flag from the embedded ChartUser object.
     * 
     * @see org.kuali.core.bo.user.KualiModuleUser#isActive()
     */
    public boolean isActive() {
        if ( getUniversalUser() == null ) return false;
        String activeStatus = getUniversalUser().getModuleProperties( ChartUser.MODULE_ID ).get( KFSPropertyConstants.ACTIVE ); 
        return activeStatus != null && activeStatus.equals( "Y" );
    }
    
    @Override
    public boolean isModified(UniversalUser oldRecord, UniversalUser newRecord) {
        boolean isThisModuleUserForNewRecordActive = "Y".equals(newRecord.getModuleProperties( ChartUser.MODULE_ID ).get( KFSPropertyConstants.ACTIVE ));
        return super.isModified(oldRecord, newRecord) || (oldRecord == null && isThisModuleUserForNewRecordActive) ||
                (oldRecord != null && oldRecord.getModuleUser(MODULE_ID).isActive() != isThisModuleUserForNewRecordActive);
    }
    
    public void setActive( boolean active ) {
        throw new UnsupportedOperationException( "setActive is not supported on " + this.getClass().getSimpleName() + " objects" );
    }
}
