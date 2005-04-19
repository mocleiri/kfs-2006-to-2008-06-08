package com.thoughtworks.xstream.converters.collections;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

/**
 * Base helper class for converters that need to handle
 * collections of items (arrays, Lists, Maps, etc).
 * <p/>
 * <p>Typically, subclasses of this will converter the outer
 * structure of the collection, loop through the contents and
 * call readItem() or writeItem() for each item.</p>
 *
 * @author Joe Walnes
 */
public abstract class AbstractCollectionConverter implements Converter {

    private final Mapper mapper;

    /**
     * @deprecated As of 1.1.1, use {@link #mapper()}
     */
    protected ClassMapper classMapper;

    /**
     * @deprecated As of 1.1.1, use {@link #mapper()} 
     */
    protected String classAttributeIdentifier;

    public abstract boolean canConvert(Class type);

    /**
     * @deprecated As of 1.1.1, use other constructor.
     */
    public AbstractCollectionConverter(ClassMapper classMapper, String classAttributeIdentifier) {
        // TODO: this classAttributeIdentifer should be optional - most uses of XStream don't need it.
        this.classMapper = classMapper;
        this.classAttributeIdentifier = classAttributeIdentifier;
        this.mapper = classMapper;
    }

    public AbstractCollectionConverter(Mapper mapper) {
        this.mapper = mapper;
    }

    protected Mapper mapper() {
        return mapper;
    }

    public abstract void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context);

    public abstract Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context);

    protected void writeItem(Object item, MarshallingContext context, HierarchicalStreamWriter writer) {
        // PUBLISHED API METHOD! If changing signature, ensure backwards compatability.
        if (item == null) {
            // todo: this is duplicated in TreeMarshaller.start()
            writer.startNode(mapper().serializedClass(ClassMapper.Null.class));
            writer.endNode();
        } else {
            writer.startNode(mapper().serializedClass(item.getClass()));
            context.convertAnother(item);
            writer.endNode();
        }
    }

    protected Object readItem(HierarchicalStreamReader reader, UnmarshallingContext context, Object current) {
        // PUBLISHED API METHOD! If changing signature, ensure backwards compatability.
        String classAttribute = reader.getAttribute(mapper().attributeForImplementationClass());
        Class type;
        if (classAttribute == null) {
            type = mapper().realClass(reader.getNodeName());
        } else {
            type = mapper().realClass(classAttribute);
        }
        return context.convertAnother(current, type);
    }

    protected Object createCollection(Class type) {
        Class defaultType = mapper().defaultImplementationOf(type);
        try {
            return defaultType.newInstance();
        } catch (InstantiationException e) {
            throw new ConversionException("Cannot instantiate " + defaultType.getName(), e);
        } catch (IllegalAccessException e) {
            throw new ConversionException("Cannot instantiate " + defaultType.getName(), e);
        }
    }
}
