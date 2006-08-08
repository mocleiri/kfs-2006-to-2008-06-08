package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: TokenStream.java,v 1.1 2006-08-08 23:16:57 dbeutel Exp $
 */

public interface TokenStream {
    public Token nextToken() throws TokenStreamException;
}
