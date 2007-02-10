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
package org.kuali.module.gl.bo;

import org.kuali.core.bo.KualiCodeBase;

/**
 * 
 * @version $Id: OriginEntrySource.java,v 1.8.4.3 2007-02-10 11:37:32 j2eemgr Exp $
 * 
 */

public class OriginEntrySource extends KualiCodeBase {
    public static final String BACKUP = "BACK";
    public static final String COLLECTOR = "COLL";
    public static final String GENERATE_BY_EDOC = "EDOC";
    public static final String EXTERNAL = "EXT";
    public static final String GL_CORRECTION_PROCESS_EDOC = "GLCP";
    public static final String ICR_TRANSACTIONS = "ICR";
    public static final String ICR_POSTER_ERROR = "ICRE";
    public static final String ICR_POSTER_VALID = "ICRV";
    public static final String MAIN_POSTER_ERROR = "MPE";
    public static final String MAIN_POSTER_VALID = "MPV";
    public static final String REVERSAL_POSTER_ERROR = "RPE";
    public static final String REVERSAL_POSTER_VALID = "RPV";
    public static final String SCRUBBER_ERROR = "SCE";
    public static final String SCRUBBER_VALID = "SCV";
    public static final String SCRUBBER_EXPIRED = "SCX";
    public static final String YEAR_END_BEGINNING_BALANCE = "YEBB";
    public static final String YEAR_END_BEGINNING_BALANCE_PRIOR_YEAR = "YEBC";
    public static final String YEAR_END_CLOSE_NOMINAL_BALANCES = "YECN";
    public static final String YEAR_END_ENCUMBRANCE_CLOSING = "YEEC";
    public static final String YEAR_END_ORG_REVERSION = "YEOR";

    // Code base has all the fields we need
}
