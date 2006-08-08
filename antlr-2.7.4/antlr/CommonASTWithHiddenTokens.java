package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: CommonASTWithHiddenTokens.java,v 1.1 2006-08-08 23:16:56 dbeutel Exp $
 */

/** A CommonAST whose initialization copies hidden token
 *  information from the Token used to create a node.
 */
public class CommonASTWithHiddenTokens extends CommonAST {
    protected CommonHiddenStreamToken hiddenBefore, hiddenAfter; // references to hidden tokens

    public CommonASTWithHiddenTokens() {
        super();
    }

    public CommonASTWithHiddenTokens(Token tok) {
        super(tok);
    }

    public CommonHiddenStreamToken getHiddenAfter() {
        return hiddenAfter;
    }

    public CommonHiddenStreamToken getHiddenBefore() {
        return hiddenBefore;
    }

    public void initialize(Token tok) {
        CommonHiddenStreamToken t = (CommonHiddenStreamToken)tok;
        super.initialize(t);
        hiddenBefore = t.getHiddenBefore();
        hiddenAfter = t.getHiddenAfter();
    }
}
