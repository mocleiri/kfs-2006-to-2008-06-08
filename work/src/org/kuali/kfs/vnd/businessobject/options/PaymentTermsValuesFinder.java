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
package org.kuali.module.vendor.lookup.keyvalues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.KeyValuesService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.vendor.bo.PaymentTermType;

/**
 * Values finder for <code>PaymentTermType</code>. Similar to <code>PaymentTypeValuesFinder</code>, except that the list of
 * <code>KeyValuePair</code>s returned by this class' <code>getKeyValues()</code> method are a code and a description, rather
 * than a description and a description. That method's signature needs to stay the same to satisfy the core code that uses values
 * finders, so we can't simply employ polymorphism in method signatures; we'll use a separate class instead.
 * 
 * @see org.kuali.module.vendor.bo.PaymentTermType
 * @see org.kuali.module.vendor.lookup.keyvalues.PaymentTypeValuesFinder
 */
public class PaymentTermsValuesFinder extends KeyValuesBase {

    /*
     * @see org.kuali.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List getKeyValues() {

        KeyValuesService boService = SpringContext.getBean(KeyValuesService.class);
        Collection codes = boService.findAll(PaymentTermType.class);
        List labels = new ArrayList();
        labels.add(new KeyLabelPair("", ""));
        for (Iterator iter = codes.iterator(); iter.hasNext();) {
            PaymentTermType pt = (PaymentTermType) iter.next();
            labels.add(new KeyLabelPair(pt.getVendorPaymentTermsCode(), pt.getVendorPaymentTermsDescription()));
        }

        return labels;
    }

}