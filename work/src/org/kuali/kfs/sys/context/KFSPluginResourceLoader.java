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
package org.kuali.kfs.context;

import javax.xml.namespace.QName;

import org.kuali.core.workflow.KFSResourceLoader;
import org.kuali.rice.resourceloader.BaseResourceLoader;
import org.kuali.rice.resourceloader.ResourceLoader;

public class KFSPluginResourceLoader extends BaseResourceLoader {

    public KFSPluginResourceLoader() {
        super(new QName("KFSPluginResourceLoader"));
    }

    @Override
    public void start() throws Exception {
        SpringContext.initializePluginApplicationContext();
        ResourceLoader kfsResourceLoader = SpringContext.getBean(KFSResourceLoader.class);
        if (kfsResourceLoader == null) {
            throw new RuntimeException("Could not locate the KFSResourceLoader");
        }
        addResourceLoader(kfsResourceLoader);
        super.start();
    }

    @Override
    public void stop() throws Exception {
        SpringContext.close();
        super.stop();
    }





}
