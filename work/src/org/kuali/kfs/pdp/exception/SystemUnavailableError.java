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
/*
 * Created on Jul 21, 2004
 *
 */
package org.kuali.module.pdp.exception;

public class SystemUnavailableError extends PdpError {

    public SystemUnavailableError() {
        super();
    }

    public SystemUnavailableError(String message) {
        super(message);
    }

    public SystemUnavailableError(String message, Throwable arg1) {
        super(message, arg1);
    }

    public SystemUnavailableError(Throwable arg0) {
        super(arg0);
    }
}
