/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software License
 * version 1.1, a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.apache.avalon.framework.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is a static implementation of a <code>ServiceManager</code>. Allow ineritance
 * and extension so you can generate a tree of <code>ServiceManager</code> each defining
 * Object scope.
 *
 * @author <a href="mailto:fede@apache.org">Federico Barbieri</a>
 * @author <a href="mailto:peter at apache.org">Peter Donald</a>
 * @author <a href="mailto:mcconnell@apache.org">Stephen McConnell</a>
 * @version 1.0
 */
public class DefaultServiceManager
    implements ServiceManager
{
    private final HashMap m_objects = new HashMap();
    private final ServiceManager m_parent;
    private boolean m_readOnly;

    /**
     * Construct <code>ServiceManager</code> with no parent.
     *
     */
    public DefaultServiceManager()
    {
        this( null );
    }

    /**
     * Construct <code>ServiceManager</code> with specified parent.
     *
     * @param parent this <code>ServiceManager</code>'s parent
     */
    public DefaultServiceManager( final ServiceManager parent )
    {
        m_parent = parent;
    }

    /**
     * Retrieve <code>Object</code> by role from <code>ServiceManager</code>.
     *
     * @param role the role
     * @return the <code>Object</code>
     * @throws ServiceException if an error occurs
     */
    public Object lookup( final String role )
        throws ServiceException
    {
        final Object object = m_objects.get( role );

        if( null != object )
        {
            return object;
        }
        else if( null != m_parent )
        {
            return m_parent.lookup( role );
        }
        else
        {
            throw new ServiceException( role, "Unable to provide implementation for " + role, null );
        }
    }

    /**
     * Check to see if a <code>Object</code> exists for a role.
     *
     * @param role  a string identifying the role to check.
     * @return True if the object exists, False if it does not.
     */
    public boolean hasService( final String role )
    {
        boolean objectExists = false;

        try
        {
            this.lookup( role );
            objectExists = true;
        }
        catch( Throwable t )
        {
            // Ignore all throwables--we want a yes or no answer.
        }
        return objectExists;
    }

    /**
     * Place <code>Object</code> into <code>ServiceManager</code>.
     *
     * @param role the object's role
     * @param object an <code>Object</code> value
     */
    public void put( final String role, final Object object )
    {
        checkWriteable();
        m_objects.put( role, object );
    }

    /**
     * Build a human readable representation of this
     * <code>ServiceManager</code>.
     *
     * @return the description of this <code>ServiceManager</code>
     */
    public String toString()
    {
        final StringBuffer buffer = new StringBuffer();
        final Iterator objects = m_objects.keySet().iterator();
        buffer.append( "Services:" );

        while( objects.hasNext() )
        {
            buffer.append( "[" );
            buffer.append( objects.next() );
            buffer.append( "]" );
        }

        return buffer.toString();
    }

    /**
     * Helper method for subclasses to retrieve parent.
     *
     * @return the parent <code>ServiceManager</code>
     */
    protected final ServiceManager getParent()
    {
        return m_parent;
    }

    /**
     * Helper method for subclasses to retrieve object map.
     *
     * @return the object map
     */
    protected final Map getObjectMap()
    {
        return m_objects;
    }

    /**
     * Makes this <code>ServiceManager</code> read-only.
     *
     */
    public void makeReadOnly()
    {
        m_readOnly = true;
    }

    /**
     * Checks if this <code>ServiceManager</code> is writeable.
     *
     * @throws IllegalStateException if this <code>ServiceManager</code> is
     * read-only
     */
    protected final void checkWriteable()
        throws IllegalStateException
    {
        if( m_readOnly )
        {
            throw new IllegalStateException
                ( "ServiceManager is read only and can not be modified" );
        }
    }

    /**
     * Release the <code>Object</code>.
     * @param object The <code>Object</code> to release.
     */
    public void release( Object object )
    {
    }

}
