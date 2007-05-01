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
package org.kuali.module.vendor.lookup;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.lookup.AbstractLookupableHelperServiceImpl;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.BeanPropertyComparator;
import org.kuali.kfs.Constants;
import org.kuali.module.vendor.VendorConstants;
import org.kuali.module.vendor.bo.VendorContact;
import org.kuali.module.vendor.bo.VendorContactPhoneNumber;
import org.kuali.module.vendor.service.VendorService;

public class VendorContactLookupableHelperServiceImpl extends AbstractLookupableHelperServiceImpl {

    /**
     * @see org.kuali.core.lookup.Lookupable#getSearchResults(java.util.Map) 
     * 
     * This method overrides the getSearchResults in the super class so that we can do some customization 
     * in our vendor contact lookup. For example, we want to be able to display the first phone number, fax 
     * number and toll free number in the vendor contact.
     */
    @Override
    public List<PersistableBusinessObject> getSearchResults(Map<String, String> fieldValues) {
        boolean unbounded = false;
        super.setBackLocation((String) fieldValues.get(Constants.BACK_LOCATION));
        super.setDocFormKey((String) fieldValues.get(Constants.DOC_FORM_KEY));

        List<PersistableBusinessObject> searchResults = (List) getLookupService().findCollectionBySearchHelper(getBusinessObjectClass(), fieldValues, unbounded);

        // loop through results
        for (PersistableBusinessObject object : searchResults) {
            VendorContact vendorContact = (VendorContact) object;

            for (VendorContactPhoneNumber phoneNumber: vendorContact.getVendorContactPhoneNumbers()) {
                String extension = phoneNumber.getVendorPhoneExtensionNumber();
                if (phoneNumber.getVendorPhoneType().getVendorPhoneTypeCode().equals(VendorConstants.PhoneTypes.PHONE) &&
                    StringUtils.isEmpty(vendorContact.getPhoneNumberForLookup())) {
                    vendorContact.setPhoneNumberForLookup(phoneNumber.getVendorPhoneNumber() + ((StringUtils.isNotEmpty(extension)) ? " x " + extension : null)) ;
                } else if (phoneNumber.getVendorPhoneType().getVendorPhoneTypeCode().equals(VendorConstants.PhoneTypes.FAX) &&
                           StringUtils.isBlank(vendorContact.getFaxForLookup())) {
                    vendorContact.setFaxForLookup(phoneNumber.getVendorPhoneNumber() + ((StringUtils.isNotEmpty(extension)) ? " x " + extension : Constants.EMPTY_STRING));
                } else if (phoneNumber.getVendorPhoneType().getVendorPhoneTypeCode().equals(VendorConstants.PhoneTypes.TOLL_FREE) &&
                           StringUtils.isBlank(vendorContact.getTollFreeForLookup())) {
                    vendorContact.setTollFreeForLookup(phoneNumber.getVendorPhoneNumber() + ((StringUtils.isNotEmpty(extension)) ? " x " + extension : Constants.EMPTY_STRING));
                }
            }
        }

        // sort list if default sort column given
        List<String> defaultSortColumns = getDefaultSortColumns();
        if (defaultSortColumns.size() > 0) {
            Collections.sort(searchResults, new BeanPropertyComparator(getDefaultSortColumns(), true));
        }
        return searchResults;
    }

}
