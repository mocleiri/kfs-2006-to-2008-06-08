package com.thoughtworks.xstream.converters.reflection;

import sun.misc.Unsafe;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

/**
 * Instantiates a new object on the Sun JVM by bypassing the constructor (meaning code in the constructor
 * will never be executed and parameters do not have to be known). This is the same method used by the internals of
 * standard Java serialization, but relies on internal Sun code that may not be present on all JVMs.
 *
 * @author Joe Walnes
 * @author Brian Slesinsky
 */
public class Sun14ReflectionProvider extends PureJavaReflectionProvider {

    private final ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
    private Unsafe cachedUnsafe;
    private final Map constructorCache = Collections.synchronizedMap(new HashMap());

    private Unsafe getUnsafe() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        if (cachedUnsafe != null) {
            return cachedUnsafe;
        }
        Class objectStreamClass = Class.forName("java.io.ObjectStreamClass$FieldReflector");
        Field unsafeField = objectStreamClass.getDeclaredField("unsafe");
        unsafeField.setAccessible(true);
        cachedUnsafe = (Unsafe) unsafeField.get(null);
        return cachedUnsafe;
    }

    public Object newInstance(Class type) {
        try {
            Constructor customConstructor = getMungedConstructor(type);
            Object newValue = customConstructor.newInstance(new Object[0]);
            return newValue;
        } catch (NoSuchMethodException e) {
            throw new ObjectAccessException("Cannot construct " + type.getName(), e);
        } catch (SecurityException e) {
            throw new ObjectAccessException("Cannot construct " + type.getName(), e);
        } catch (InstantiationException e) {
            throw new ObjectAccessException("Cannot construct " + type.getName(), e);
        } catch (IllegalAccessException e) {
            throw new ObjectAccessException("Cannot construct " + type.getName(), e);
        } catch (IllegalArgumentException e) {
            throw new ObjectAccessException("Cannot construct " + type.getName(), e);
        } catch (InvocationTargetException e) {
            throw new ObjectAccessException("Cannot construct " + type.getName(), e);
        }
    }

    private Constructor getMungedConstructor(Class type) throws NoSuchMethodException {
        if (!constructorCache.containsKey(type)) {
            Constructor javaLangObjectConstructor = Object.class.getDeclaredConstructor(new Class[0]);
            Constructor customConstructor = reflectionFactory.newConstructorForSerialization(type, javaLangObjectConstructor);
            constructorCache.put(type, customConstructor);
        }
        return (Constructor) constructorCache.get(type);
    }

    public void writeField(Object object, String fieldName, Object value, Class definedIn) {
        write(fieldDictionary.field(object.getClass(), fieldName, definedIn), object, value);
    }

    private void write(Field field, Object object, Object value) {
        try {
            Unsafe unsafe = getUnsafe();
            long offset = unsafe.objectFieldOffset(field);
            Class type = field.getType();
            if (type.isPrimitive()) {
                if (type.equals(Integer.TYPE)) {
                    unsafe.putInt(object, offset, ((Integer) value).intValue());
                } else if (type.equals(Long.TYPE)) {
                    unsafe.putLong(object, offset, ((Long) value).longValue());
                } else if (type.equals(Short.TYPE)) {
                    unsafe.putShort(object, offset, ((Short) value).shortValue());
                } else if (type.equals(Character.TYPE)) {
                    unsafe.putChar(object, offset, ((Character) value).charValue());
                } else if (type.equals(Byte.TYPE)) {
                    unsafe.putByte(object, offset, ((Byte) value).byteValue());
                } else if (type.equals(Float.TYPE)) {
                    unsafe.putFloat(object, offset, ((Float) value).floatValue());
                } else if (type.equals(Double.TYPE)) {
                    unsafe.putDouble(object, offset, ((Double) value).doubleValue());
                } else if (type.equals(Boolean.TYPE)) {
                    unsafe.putBoolean(object, offset, ((Boolean) value).booleanValue());
                } else {
                    throw new ObjectAccessException("Could not set field " +
                            object.getClass() + "." + field.getName() +
                            ": Unknown type " + type);
                }
            } else {
                unsafe.putObject(object, offset, value);
            }

        } catch (IllegalArgumentException e) {
            throw new ObjectAccessException("Could not set field " + object.getClass() + "." + field.getName(), e);
        } catch (IllegalAccessException e) {
            throw new ObjectAccessException("Could not set field " + object.getClass() + "." + field.getName(), e);
        } catch (NoSuchFieldException e) {
            throw new ObjectAccessException("Could not set field " + object.getClass() + "." + field.getName(), e);
        } catch (ClassNotFoundException e) {
            throw new ObjectAccessException("Could not set field " + object.getClass() + "." + field.getName(), e);
        }
    }

    protected void validateFieldAccess(Field field) {
        // (overriden) don't mind final fields.
    }
}
