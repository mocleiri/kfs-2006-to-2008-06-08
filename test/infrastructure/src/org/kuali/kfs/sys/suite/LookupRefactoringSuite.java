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
package org.kuali.test.suite;

import junit.framework.Test;

public class LookupRefactoringSuite extends AnnotationTestSuite.Superclass {
    public static Test suite() throws Exception {
        return new LookupRefactoringSuite().getSuite();
    }

    /**
     * This nested class is the suite of all test methods not in the enclosing suite class. IDEs or Ant can run this nested class as
     * JUnit tests.
     */
    /*
     * public static class Not { public static Test suite() throws Exception { return new
     * LookupRefactoringSuite().getNegativeSuite(); } }
     */
}