/*
 * Copyright 2005-2006 The Kuali Foundation.
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
package org.kuali.module.gl.dao;

import java.util.Collection;

import org.kuali.module.gl.bo.OriginEntrySource;

/**
 *  
 * @version $Id: OriginEntrySourceDao.java,v 1.2.8.4 2007-02-10 11:37:33 j2eemgr Exp $
 * 
 */

public interface OriginEntrySourceDao {
    public Collection findAll();

    public OriginEntrySource findBySourceCode(String code);
}
