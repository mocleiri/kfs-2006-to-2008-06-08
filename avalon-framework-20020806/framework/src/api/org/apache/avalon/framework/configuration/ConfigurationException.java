/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software License
 * version 1.1, a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.apache.avalon.framework.configuration;

import org.apache.avalon.framework.CascadingException;

/**
 * Thrown when a <code>Configurable</code> component cannot be configured
 * properly, or if a value cannot be retrieved properly.
 *
 *  <a href="mailto:fede@apache.org">Federico Barbieri</a>
 *  <a href="mailto:stefano@apache.org">Stefano Mazzocchi</a>
 *  <a href="mailto:fumagalli@exoffice.com">Pierpaolo Fumagalli</a>
 */
public class ConfigurationException
    extends CascadingException
{
    /**
     * Construct a new <code>ConfigurationException</code> instance.
     *
     * @param message The detail message for this exception.
     */
    public ConfigurationException( final String message )
    {
        this( message, null );
    }

    /**
     * Construct a new <code>ConfigurationException</code> instance.
     *
     * @param message The detail message for this exception.
     * @param throwable the root cause of the exception
     */
    public ConfigurationException( final String message, final Throwable throwable )
    {
        super( message, throwable );
    }
}
