/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/test/infrastructure/src/org/kuali/kfs/sys/suite/FiveMinuteSuite.java,v $
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
package org.kuali.test.suite;

import java.io.IOException;

import junit.framework.TestSuite;

/**
 * This is a plain old JUnit suite of suites that can be run within a reasonable amount of time.
 * IDEs or Ant can run this class as JUnit tests.
 */
public class FiveMinuteSuite {
    public static TestSuite suite()
        throws IOException
    {
        TestSuite suite = new TestSuite(FiveMinuteSuite.class.getName());
        suite.addTest(CrossSectionSuite.suite());
        suite.addTest(OftenUsefulSuite.suite());
        suite.addTest(WithTestSpringContextSuite.Not.suite());
        return suite;
    }
}
