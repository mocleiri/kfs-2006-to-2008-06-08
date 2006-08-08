package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: DefaultFileLineFormatter.java,v 1.1 2006-08-08 23:16:56 dbeutel Exp $
 */

public class DefaultFileLineFormatter extends FileLineFormatter {
    public String getFormatString(String fileName, int line, int column) {
        StringBuffer buf = new StringBuffer();

        if (fileName != null)
            buf.append(fileName + ":");

        if (line != -1) {
            if (fileName == null)
                buf.append("line ");

            buf.append(line);

            if (column != -1)
                buf.append(":" + column);

            buf.append(":");
        }

        buf.append(" ");

        return buf.toString();
    }
}
