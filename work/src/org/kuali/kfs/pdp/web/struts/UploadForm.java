/*
 * Copyright 2007 The Kuali Foundation.
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
/*
 * Created on Jul 9, 2004
 *
 */
package org.kuali.module.pdp.form.upload;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * @author jsissom
 */
public class UploadForm extends ActionForm {
    private FormFile file;

    public UploadForm() {
        super();
    }

    /**
     * Retrieve a representation of the file the user has uploaded
     */
    public FormFile getFile() {
        return file;
    }

    /**
     * Set a representation of the file the user has uploaded
     */
    public void setFile(FormFile file) {
        this.file = file;
    }
}