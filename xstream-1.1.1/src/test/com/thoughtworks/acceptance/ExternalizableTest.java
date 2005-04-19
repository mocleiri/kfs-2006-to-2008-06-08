package com.thoughtworks.acceptance;

import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

public class ExternalizableTest extends AbstractAcceptanceTest {

    public static class SomethingExternalizable extends StandardObject implements Externalizable {

        private String first;
        private String last;

        public SomethingExternalizable() {
        }

        public SomethingExternalizable(String first, String last) {
            this.first = first;
            this.last = last;
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(first.length());
            out.writeObject(first + last);
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            int offset = in.readInt();
            String full = (String) in.readObject();
            first = full.substring(0, offset);
            last = full.substring(offset);
        }
    }

    public void testExternalizable() {
        xstream.alias("something", SomethingExternalizable.class);
        
        SomethingExternalizable in = new SomethingExternalizable("Joe", "Walnes");

        String expected = ""
                + "<something>\n"
                + "  <int>3</int>\n"
                + "  <string>JoeWalnes</string>\n"
                + "</something>";

        assertBothWays(in, expected);
    }
}
