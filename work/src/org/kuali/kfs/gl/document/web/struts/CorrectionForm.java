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

package org.kuali.module.gl.web.struts.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.core.authorization.TransactionalDocumentActionFlags;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.module.gl.bo.CorrectionChange;
import org.kuali.module.gl.bo.CorrectionChangeGroup;
import org.kuali.module.gl.bo.CorrectionCriteria;
import org.kuali.module.gl.bo.OriginEntry;
import org.kuali.module.gl.document.CorrectionDocument;
import org.kuali.module.gl.service.CorrectionDocumentService;

public class CorrectionForm extends KualiDocumentFormBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CorrectionForm.class);

    private String chooseSystem;
    private String editMethod;
    private Integer inputGroupId;
    private Integer outputGroupId;
    private String inputFileName;
    protected FormFile sourceFile;
    private boolean processInBatch = false;
    private boolean matchCriteriaOnly = false;
    private boolean dataLoadedFlag = false;
    private boolean editableFlag = false;
    private boolean manualEditFlag = false;
    private boolean deleteFileFlag = false;
    private boolean showOutputFlag = false;
    private Collection allEntries;
    private OriginEntry entryForManualEdit;

    private List<GroupHolder> groups;

    public CorrectionForm() {
        super();

        setDocument(new CorrectionDocument());

        // create a blank TransactionalDocumentActionFlags instance, since form-recreation needs it
        setDocumentActionFlags(new TransactionalDocumentActionFlags());

        // These are for the blank rows that are used to add criteria/changes
        groups = new ArrayList<GroupHolder>();

        // Sync up the groups
        syncGroups();

        entryForManualEdit = new OriginEntry();
        entryForManualEdit.setEntryId(0);
    }

    public void syncGroups() {
        int groupCount = getCorrectionDocument().getCorrectionChangeGroup().size();
        getGroupsItem(groupCount);
    }

    public GroupHolder getGroupsItem(int i) {
        while ( i >= groups.size() ) {
            groups.add(new GroupHolder());
        }
        return groups.get(i);
    }

    public void clearForm() {
        chooseSystem = "";
        editMethod = "";
        inputFileName = "";
        inputGroupId = null;
        outputGroupId = null;
        processInBatch = false;
        matchCriteriaOnly = false;
        dataLoadedFlag = false;
        editableFlag = false;
        manualEditFlag = false;
        deleteFileFlag = false;
        showOutputFlag = false;
        allEntries = new ArrayList();

        setDocument(new CorrectionDocument());

        // create a blank TransactionalDocumentActionFlags instance, since form-recreation needs it
        setDocumentActionFlags(new TransactionalDocumentActionFlags());

        // These are for the blank rows that are used to add criteria/changes
        groups = new ArrayList<GroupHolder>();

        // Sync up the groups
        syncGroups();

        entryForManualEdit = new OriginEntry();
        entryForManualEdit.setEntryId(0);
    }

    public Integer getAllEntriesSize() {
        return ( allEntries == null ) ? null : allEntries.size();
    }

    public void copy(CorrectionForm c) {
        chooseSystem = c.chooseSystem;
        editMethod = c.editMethod;
        inputGroupId = c.inputGroupId;
        outputGroupId = c.outputGroupId;
        inputFileName = c.inputFileName;
        matchCriteriaOnly = c.matchCriteriaOnly;
        processInBatch = c.processInBatch;
        dataLoadedFlag = c.dataLoadedFlag;
        editableFlag = c.editableFlag;
        manualEditFlag = c.manualEditFlag;
        deleteFileFlag = c.deleteFileFlag;
        showOutputFlag = c.showOutputFlag;
        allEntries = c.allEntries;
        entryForManualEdit = c.entryForManualEdit;
        groups = c.groups;
        setDocument(c.getDocument());
    }

    public CorrectionDocument getCorrectionDocument() {
        return (CorrectionDocument)getDocument();
    }

    public boolean getShowOutputFlag() {
        return showOutputFlag;
    }

    public void setShowOutputFlag(boolean showOutputFlag) {
        this.showOutputFlag = showOutputFlag;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public Integer getOutputGroupId() {
        return outputGroupId;
    }

    public void setOutputGroupId(Integer outputGroupId) {
        this.outputGroupId = outputGroupId;
    }

    public String getChooseSystem() {
        return chooseSystem;
    }

    public void setChooseSystem(String chooseSystem) {
        this.chooseSystem = chooseSystem;
    }

    public String getEditMethod() {
        return editMethod;
    }

    public void setEditMethod(String editMethod) {
        this.editMethod = editMethod;
    }

    public Integer getInputGroupId() {
        return inputGroupId;
    }

    public void setInputGroupId(Integer g) {
        this.inputGroupId = g;
    }

    public boolean getProcessInBatch() {
        return processInBatch;
    }

    public void setProcessInBatch(boolean processInBatch) {
        this.processInBatch = processInBatch;
    }

    public Collection getAllEntries() {
        return allEntries;
    }

    public void setAllEntries(Collection allEntriesForManualEdit) {
        this.allEntries = allEntriesForManualEdit;
    }

    public OriginEntry getEntryForManualEdit() {
        return entryForManualEdit;
    }

    public void setEntryForManualEdit(OriginEntry entryForManualEdit) {
        this.entryForManualEdit = entryForManualEdit;
    }

    public FormFile getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(FormFile sourceFile) {
        this.sourceFile = sourceFile;
    }

    public boolean getMatchCriteriaOnly() {
        return matchCriteriaOnly;
    }

    public void setMatchCriteriaOnly(boolean matchCriteriaOnly) {
        this.matchCriteriaOnly = matchCriteriaOnly;
    }

    public boolean getDataLoadedFlag() {
        return dataLoadedFlag;
    }

    public void setDataLoadedFlag(boolean dataLoadedFlag) {
        this.dataLoadedFlag = dataLoadedFlag;
    }

    public boolean getDeleteFileFlag() {
        return deleteFileFlag;
    }

    public void setDeleteFileFlag(boolean deleteFileFlag) {
        this.deleteFileFlag = deleteFileFlag;
    }

    public boolean getEditableFlag() {
        return editableFlag;
    }

    public void setEditableFlag(boolean editableFlag) {
        this.editableFlag = editableFlag;
    }

    public boolean getManualEditFlag() {
        return manualEditFlag;
    }

    public void setManualEditFlag(boolean manualEditFlag) {
        this.manualEditFlag = manualEditFlag;
    }
}
