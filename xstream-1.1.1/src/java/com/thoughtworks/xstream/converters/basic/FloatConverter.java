package com.thoughtworks.xstream.converters.basic;

/**
 * Converts a float primitive or java.lang.Float wrapper to
 * a String.
 *
 * @author Joe Walnes
 */
public class FloatConverter extends AbstractBasicConverter {

    public boolean canConvert(Class type) {
        return type.equals(float.class) || type.equals(Float.class);
    }

    protected Object fromString(String str) {
        return Float.valueOf(str);
    }

}
