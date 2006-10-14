/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/gl/batch/service/PosterService.java,v $
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
package org.kuali.module.gl.service;

/**
 * 
 * 
 */
public interface PosterService {
    public static int MODE_ENTRIES = 1;
    public static int MODE_REVERSAL = 2;
    public static int MODE_ICR = 3;

    /**
     * Post scrubbed GL entries to GL tables.
     */
    public void postMainEntries();

    /**
     * Post reversal GL entries to GL tables.
     */
    public void postReversalEntries();

    /**
     * Post ICR GL entries to GL tables.
     */
    public void postIcrEntries();

    /**
     * Generate ICR GL entries.
     * 
     */
    public void generateIcrTransactions();
}
