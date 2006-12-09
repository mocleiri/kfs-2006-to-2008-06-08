/*
 * Copyright 2006 The Kuali Foundation.
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

import java.util.List;

import org.kuali.module.gl.bo.CorrectionChangeGroup;
import org.kuali.module.gl.document.CorrectionDocument;

public interface CorrectionDocumentService {
    public final static String CORRECTION_TYPE_MANUAL = "M";
    public final static String CORRECTION_TYPE_CRITERIA = "C";

    public final static String SYSTEM_DATABASE = "D";
    public final static String SYSTEM_UPLOAD = "U";

    public CorrectionChangeGroup findByDocumentNumberAndCorrectionChangeGroupNumber(String docId, int i);

    public List findByDocumentHeaderIdAndCorrectionGroupNumber(String docId, int i);

    public List findByDocumentNumberAndCorrectionGroupNumber(String docId, int i);

    public CorrectionDocument findByCorrectionDocumentHeaderId(String docId);
}
