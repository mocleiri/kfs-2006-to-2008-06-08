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
package org.kuali.kfs.web;

import java.util.regex.Pattern;

/**
 * A utility class which holds functions that can be used as JSP functions.
 */
public class WebUtilities {
    /**
     * Converts a property name so that it is correct for the purposes of populating a business object
     * in the maintenance framework - basically by changing "document.oldMaintainableObject.businessObject" to 
     * "document.oldMaintainableObject" and doing a similar operation for "document.newMaintainableObject.businessObject"
     * @param propertyName the property name to fix
     * @return the corrected version of the property name
     */
    public static String renamePropertyForMaintenanceFramework(String propertyName) {
        if (propertyName == null) {
            return null;
        }
        Pattern oldMaintainablePattern = Pattern.compile("^document\\.oldMaintainableObject\\.businessObject\\.");
        if (oldMaintainablePattern.matcher(propertyName).find()) {
            return propertyName.replaceFirst("^document\\.oldMaintainableObject\\.businessObject\\.", "document.oldMaintainableObject.");
        }
        Pattern newMaintainablePattern = Pattern.compile("^document\\.newMaintainableObject\\.businessObject\\.");
        if (newMaintainablePattern.matcher(propertyName).find()) {
            return propertyName.replaceFirst("^document\\.newMaintainableObject\\.businessObject\\.", "document.newMaintainableObject.");
        }
        return propertyName;
    }
}
