package com.thoughtworks.acceptance;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDuplicateReferenceTest extends AbstractAcceptanceTest {

    protected void setUp() throws Exception {
        super.setUp();
        xstream.alias("thing", Thing.class);
    }

    public void testReferencesAreWorking() {

        Thing sameThing = new Thing("hello");
        Thing anotherThing = new Thing("hello");

        List list = new ArrayList();
        list.add(sameThing);
        list.add(sameThing);
        list.add(anotherThing);

        String xml = xstream.toXML(list);
        List result = (List) xstream.fromXML(xml);

        assertEquals(list, result);
    }

    public void testReferencesAreTheSameObjectWhenDeserialized() {

        Thing sameThing = new Thing("hello");
        Thing anotherThing = new Thing("hello");

        List list = new ArrayList();
        list.add(sameThing);
        list.add(sameThing);
        list.add(anotherThing);

        String xml = xstream.toXML(list);
        List result = (List) xstream.fromXML(xml);

        Thing t0 = (Thing) result.get(0);
        Thing t1 = (Thing) result.get(1);
        Thing t2 = (Thing) result.get(2);

        t0.field = "bye";

        assertEquals("bye", t0.field);
        assertEquals("bye", t1.field);
        assertEquals("hello", t2.field);

    }

    public static class Thing extends StandardObject {
        public String field;

        public Thing() {
        }

        public Thing(String field) {
            this.field = field;
        }
    }

    public static class MultRef {
        public Object s1 = new Object();
        public Object s2 = s1;
    }

    public void testMultipleReferencesToObjectsWithNoChildren() {
        MultRef in = new MultRef();
        assertSame(in.s1, in.s2);

        String xml = xstream.toXML(in);
        MultRef out = (MultRef) xstream.fromXML(xml);

        assertSame(out.s1, out.s2);
    }

    public void testReferencesNotUsedForSimpleImmutableValueTypes() {
        MultRef in = new MultRef();
        in.s1 = new Integer(4);
        in.s2 = in.s1;

        String xml = xstream.toXML(in);
        MultRef out = (MultRef) xstream.fromXML(xml);

        assertEquals(out.s1, out.s2);
        assertNotSame(out.s1, out.s2);
    }

    public void testReferencesUsedForSimpleMutableValueTypes() {
        MultRef in = new MultRef();
        in.s1 = new StringBuffer("hi");
        in.s2 = in.s1;

        String xml = xstream.toXML(in);
        MultRef out = (MultRef) xstream.fromXML(xml);

        StringBuffer buffer = (StringBuffer) out.s2;
        buffer.append("bye");

        assertEquals("hibye", out.s1.toString());
        assertSame(out.s1, out.s2);
    }

}
