/*
 * Copyright 2005-2007 The Kuali Foundation.
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
package org.kuali.module.chart.service;

import org.kuali.module.chart.bo.ProjectCode;

/**
 * This interface defines methods that a ProjectCode Service must provide.
 */
public interface ProjectCodeService {
    /**
     * Retrieves a ProjectCode object based on primary key.
     * 
     * @param projectCode - Project Code
     * @return ProjectCode
     */
    public ProjectCode getByPrimaryId(String projectCode);

    /**
     * Retrieves an ProjectCode object based on the project name.
     * 
     * @param name - Project name
     * @return ProjectCode
     */
    public ProjectCode getByName(String name);

}