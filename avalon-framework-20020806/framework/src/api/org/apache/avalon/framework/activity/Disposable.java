/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software License
 * version 1.1, a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.apache.avalon.framework.activity;

/**
 * The Disposable interface is used when components need to
 * deallocate and dispose resources prior to their destruction.
 *
 *  <a href="mailto:fede@apache.org">Federico Barbieri</a>
 *  <a href="mailto:pier@apache.org">Pierpaolo Fumagalli</a>
 *  <a href="mailto:stefano@apache.org">Stefano Mazzocchi</a>
 *  <a href="mailto:peter at apache.org">Peter Donald</a>
 *  <a href="mailto:bloritsch@apache.org">Berin Loritsch</a>
 */
public interface Disposable
{
    /**
     * The dispose operation is called at the end of a components lifecycle.
     * This method will be called after Startable.stop() method (if implemented
     * by component). Components use this method to release and destroy any
     * resources that the Component owns.
     */
    void dispose();
}
