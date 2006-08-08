using System;

namespace antlr
{
	/*ANTLR Translator Generator
	* Project led by Terence Parr at http://www.jGuru.com
	* Software rights: http://www.antlr.org/license.html
	*
	* $Id: TokenStreamRecognitionException.cs,v 1.1 2006-08-08 23:17:07 dbeutel Exp $
	*/
	
	//
	// ANTLR C# Code Generator by Micheal Jordan
	//                            Kunle Odutola       : kunle UNDERSCORE odutola AT hotmail DOT com
	//                            Anthony Oguntimehin
	//
	// With many thanks to Eric V. Smith from the ANTLR list.
	//

	/*
	* Wraps a RecognitionException in a TokenStreamException so you
	* can pass it along.
	*/

	[Serializable]
	public class TokenStreamRecognitionException : TokenStreamException
	{
		public RecognitionException recog;
		
		public TokenStreamRecognitionException(RecognitionException re) :
				base(re.Message)
		{
			this.recog = re;
		}
		
		override public string ToString()
		{
			return recog.ToString();
		}
	}
}