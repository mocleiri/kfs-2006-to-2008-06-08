/*
 * Created on Apr 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.kuali.bo;

import org.kuali.exceptions.IllegalObjectStateException;

/**
 * @author aapotts
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface PendingEntryGenerator {

    /**
     * @param document
     */
    public void generateEntries() throws IllegalObjectStateException;

    public String getSufficientFundsObjectCode(AccountingLineBase accountingLine) throws Exception;

}