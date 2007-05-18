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

import java.util.Iterator;
import java.util.List;

import org.kuali.core.web.ui.Column;
import org.kuali.module.gl.bo.CorrectionChangeGroup;
import org.kuali.module.gl.bo.OriginEntry;
import org.kuali.module.gl.document.CorrectionDocument;
import org.kuali.module.gl.util.CorrectionDocumentUtils;

public interface CorrectionDocumentService {
    public final static String CORRECTION_TYPE_MANUAL = "M";
    public final static String CORRECTION_TYPE_CRITERIA = "C";

    public final static String SYSTEM_DATABASE = "D";
    public final static String SYSTEM_UPLOAD = "U";
    
    /**
     * When passed into {@link #retrievePersistedInputOriginEntries(CorrectionDocument, int)} and
     * {@link #retrievePersistedOutputOriginEntries(CorrectionDocument, int)} as the int parameter, this will signify
     * that there is no abort threshold (i.e. the methods should return all of the persisted rows, regardless
     * of number of rows.
     */
    public final static int UNLIMITED_ABORT_THRESHOLD = CorrectionDocumentUtils.RECORD_COUNT_FUNCTIONALITY_LIMIT_IS_UNLIMITED;

    public CorrectionChangeGroup findByDocumentNumberAndCorrectionChangeGroupNumber(String docId, int i);

    public List findByDocumentHeaderIdAndCorrectionGroupNumber(String docId, int i);

    public List findByDocumentNumberAndCorrectionGroupNumber(String docId, int i);

    public CorrectionDocument findByCorrectionDocumentHeaderId(String docId);
    
    public List<Column> getTableRenderColumnMetadata(String docId);
    
    /**
     * This method persists an Iterator of input origin entries for a document that is in the initiated or saved state
     * 
     * @param document an initiated or saved document
     * @param entries
     */
    public void persistInputOriginEntriesForInitiatedOrSavedDocument(CorrectionDocument document, Iterator<OriginEntry> entries);
    
    public void removePersistedInputOriginEntries(CorrectionDocument document);
    
    /**
     * Retrieves input origin entries that have been persisted for this document
     * @param document the document
     * @param abortThreshold if the file exceeds this number of rows, then null is returned.  
     * {@link UNLIMITED_ABORT_THRESHOLD} signifies that there is no limit 
     * @return the list, or null if there are too many origin entries
     * @throws RuntimeException several reasons, primarily relating to underlying persistence layer problems
     */
    public List<OriginEntry> retrievePersistedInputOriginEntries(CorrectionDocument document, int abortThreshold);
    
    /**
     * Returns true if the system is storing input origin entries for this class.  Note that this does not mean that there's at least one input origin
     * entry record persisted for this document, but merely returns true if and only if the underlying persistence mechanism has a record of this document's
     * origin entries.  See the docs for the implementations of this method for more implementation specific details.
     *  
     * @param document
     * @return
     */
    public boolean areInputOriginEntriesPersisted(CorrectionDocument document);
    
    /**
     * This method persists an Iterator of input origin entries for a document that is in the initiated or saved state
     * 
     * @param document an initiated or saved document
     * @param entries
     */
    public void persistOutputOriginEntriesForInitiatedOrSavedDocument(CorrectionDocument document, Iterator<OriginEntry> entries);
    
    public void removePersistedOutputOriginEntries(CorrectionDocument document);
    
    /**
     * Retrieves output origin entries that have been persisted for this document
     * @param document the document
     * @param abortThreshold if the file exceeds this number of rows, then null is returned.  
     * {@link UNLIMITED_ABORT_THRESHOLD} signifies that there is no limit 
     * @return the list, or null if there are too many origin entries
     * @throws RuntimeException several reasons, primarily relating to underlying persistence layer problems 
     */
    public List<OriginEntry> retrievePersistedOutputOriginEntries(CorrectionDocument document, int abortThreshold);
    
    /**
     * Returns true if the system is storing output origin entries for this class.  Note that this does not mean that there's at least one output origin
     * entry record persisted for this document, but merely returns true if and only if the underlying persistence mechanism has a record of this document's
     * origin entries.  See the docs for the implementations of this method for more implementation specific details.
     *  
     * @param document
     * @return
     */
    public boolean areOutputOriginEntriesPersisted(CorrectionDocument document);
}
