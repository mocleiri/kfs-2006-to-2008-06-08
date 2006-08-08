namespace antlr
{
	/*ANTLR Translator Generator
	* Project led by Terence Parr at http://www.jGuru.com
	* Software rights: http://www.antlr.org/license.html
	*
	* $Id: ANTLRPanicException.cs,v 1.1 2006-08-08 23:17:06 dbeutel Exp $
	*/

	//
	// ANTLR C# Code Generator by Micheal Jordan
	//                            Kunle Odutola       : kunle UNDERSCORE odutola AT hotmail DOT com
	//                            Anthony Oguntimehin
	//
	// With many thanks to Eric V. Smith from the ANTLR list.
	//
	
	using System;
	
	[Serializable]
	public class ANTLRPanicException : ANTLRException 
	{
		public ANTLRPanicException() : base() 
		{
		}

		public ANTLRPanicException(string s) : base(s)
		{
		}

		public ANTLRPanicException(string s, Exception inner) : base(s, inner)
		{
		}
	}
}
