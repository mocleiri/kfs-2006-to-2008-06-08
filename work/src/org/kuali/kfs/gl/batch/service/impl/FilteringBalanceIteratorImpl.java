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
package org.kuali.module.gl.batch.closing.year.service.impl;

import java.util.Iterator;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.iterators.FilterIterator;
import org.kuali.module.gl.batch.closing.year.service.BalancePredicate;
import org.kuali.module.gl.batch.closing.year.service.FilteringBalanceIterator;
import org.kuali.module.gl.bo.Balance;

public class FilteringBalanceIteratorImpl implements FilteringBalanceIterator {
    private FilterIterator filteredBalances;
    private Iterator<Balance> balancesSource;
    private BalancePredicate balancePredicate;
    private boolean initialized = false;

    public boolean hasNext() {
        if (!initialized) {
            initialize();
        }
        return filteredBalances.hasNext();
    }

    public Balance next() {
        if (!initialized) {
            initialize();
        }
        return (Balance)filteredBalances.next();
    }

    public void remove() {
        if (!initialized) {
            initialize();
        }
        filteredBalances.remove();
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Balance> iterator() {
        return this;
    }
    
    private void initialize() {
        if (!initialized) {
            filteredBalances = new FilterIterator(balancesSource, new Predicate() {
                public boolean evaluate(Object obj) {
                    return balancePredicate.select((Balance)obj);
                }
            });
            initialized = true;
        }
    }

    /**
     * Sets the balancePredicate attribute value.
     * @param balancePredicate The balancePredicate to set.
     */
    public void setBalancePredicate(BalancePredicate balancePredicate) {
        this.balancePredicate = balancePredicate;
    }

    /**
     * Sets the balancesSource attribute value.
     * @param balancesSource The balancesSource to set.
     */
    public void setBalancesSource(Iterator<Balance> balancesSource) {
        this.balancesSource = balancesSource;
    }

}
