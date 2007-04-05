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
package org.kuali.module.vendor.util;

import org.apache.commons.lang.StringUtils;

public class VendorUtils {
    
    public static final char LEFT_COLLECTION_SEPERATOR='[';
    public static final char RIGHT_COLLECTION_SEPERATOR=']';
    public static final char FIELD_SEPERATOR='.';

    /**
     * This method builds up a string and a position like so abc, 1 becomes abc[1] it is used
     * for fields that require operations on collections.
     * @param full
     * @param collections
     * @param pos
     * @return
     */
    public static String assembleWithPosition(String full, String[] collections, int[] positions) {
        
        if(collections.length != positions.length) {
            throw new IllegalArgumentException();
        }
        
        String[] parts = StringUtils.split(full,FIELD_SEPERATOR);
        
        for (int j = 0; j<parts.length; j++) {
            for (int i = 0; i < collections.length; i++) {
                if(StringUtils.equals(parts[j],collections[i])) {
                   parts[j] = collections[i]+LEFT_COLLECTION_SEPERATOR+positions[i]+RIGHT_COLLECTION_SEPERATOR;
                   break;
                }

            }
        }
        
        return StringUtils.join(parts,FIELD_SEPERATOR);
    }
    /**
     * 
     * This method is a helper to call assembleWithPosition(String full, String[] collections, int[] positions) when
     * only one collection
     * @param full
     * @param collection
     * @param position
     * @return
     */
    public static String assembleWithPosition(String full, String collection, int position) {
        String[] collections = {collection};
        int[] positions = {position};
        return assembleWithPosition(full,collections,positions);
    }
}
