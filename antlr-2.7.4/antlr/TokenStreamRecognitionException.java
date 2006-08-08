package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: TokenStreamRecognitionException.java,v 1.1 2006-08-08 23:16:57 dbeutel Exp $
 */

/**
 * Wraps a RecognitionException in a TokenStreamException so you
 * can pass it along.
 */
public class TokenStreamRecognitionException extends TokenStreamException {
    public RecognitionException recog;

    public TokenStreamRecognitionException(RecognitionException re) {
        super(re.getMessage());
        this.recog = re;
    }

    public String toString() {
        return recog.toString();
    }
}
