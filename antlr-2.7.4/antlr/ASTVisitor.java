package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: ASTVisitor.java,v 1.1 2006-08-08 23:16:56 dbeutel Exp $
 */

import antlr.collections.AST;

public interface ASTVisitor {
    public void visit(AST node);
}
