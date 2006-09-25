package antlr.collections;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: Stack.java,v 1.2 2006-09-25 14:26:15 bnelson Exp $
 */

import java.util.NoSuchElementException;

/** A simple stack definition; restrictive in that you cannot
 * access arbitrary stack elements.
 *
 *  Terence Parr
 * <a href=http://www.MageLang.com>MageLang Institute</a>
 */
public interface Stack {
    public int height();

    public Object pop() throws NoSuchElementException;

    public void push(Object o);

    public Object top() throws NoSuchElementException;
}
