package com.thoughtworks.xstream.converters.basic;

import java.math.BigDecimal;

/**
 * Converts a java.math.BigDecimal to a String, retaining
 * its precision.
 *
 * @author Joe Walnes
 */
public class BigDecimalConverter extends AbstractBasicConverter {

    public boolean canConvert(Class type) {
        return type.equals(BigDecimal.class);
    }

    protected Object fromString(String str) {
        return new BigDecimal(str);
    }

}
