/*****************************************************************************************
 * Source File: Token.java
 ****************************************************************************************/
package net.ruready.common.parser.core.tokens;

import net.ruready.common.rl.CommonNames;
import net.ruready.common.util.HashCodeUtil;

/**
 * A token represents a logical chunk of a string. For example, a typical tokenizer would
 * break the string <code>"1.23 <= 12.3"</code> into three tokens: the number 1.23, a
 * less-than-or-equal symbol, and the number 12.3. A token is a receptacle, and relies on
 * a tokenizer to decide precisely how to divide a string into tokens. ...
 * <p>
 * -------------------------------------------------------------------------<br>
 * (c) 2006-2007 Continuing Education, University of Utah<br>
 * All copyrights reserved. U.S. Patent Pending DOCKET NO. 00846 25702.PROV
 * <p>
 * This file is part of the RUReady Program software.<br>
 * Contact: Nava L. Livne <code>&lt;nlivne@aoce.utah.edu&gt;</code><br>
 * Academic Outreach and Continuing Education (AOCE)<br>
 * 1901 East South Campus Dr., Room 2197-E<br>
 * University of Utah, Salt Lake City, UT 84112-9359<br>
 * U.S.A.<br>
 * Day Phone: 1-801-587-5835, Fax: 1-801-585-5414<br>
 * <br>
 * Please contact these numbers immediately if you receive this file without permission
 * from the authors. Thank you.<br>
 * -------------------------------------------------------------------------
 * 
 * @author Steven J. Metsker Protected by U.S. Provisional Patent U-4003, February 2006
 * @version 1.0 Copyright (c) 1999 Steven J. Metsker. All Rights Reserved. Steve Metsker
 *          makes no representations or warranties about the fitness of this software for
 *          any particular purpose, including the implied warranty of merchantability.
 * @author Oren E. Livne <code>&lt;olivne@aoce.utah.edu&gt;</code>
 * @version Sep 8, 2007
 */
public class Token
{
	// ========================= CONSTANTS =================================

	/**
	 * A constant indicating that the end of the stream has been read.
	 */
	public static final TokenType TT_EOF = new TokenType("eof");

	/**
	 * A constant indicating that there are no more tokens
	 */
	public static final Token EOF = new Token(TT_EOF, CommonNames.MISC.EMPTY_STRING, 0);

	/**
	 * A constant indicating that a tokenizer state did not return a token.
	 */
	public static final TokenType TT_NULL = new TokenType("null");

	/**
	 * A constant indicating that another token needs to be obtained because a tokenizer
	 * state didn't return one.
	 */
	public static final Token NULL = new Token(TT_NULL, CommonNames.MISC.EMPTY_STRING, 0);

	/**
	 * A constant indicating that a token is a number, like 3.14
	 */
	public static final TokenType TT_NUMBER = new TokenType("number");

	/**
	 * A constant indicating a token is a word, like "cat"
	 */
	public static final TokenType TT_WORD = new TokenType("word");

	/**
	 * A constant indicating that a token is a symbol like "<=".
	 */
	public static final TokenType TT_SYMBOL = new TokenType("symbol");

	/**
	 * A constant indicating that a token is a quoted string, like "Launch Mi".
	 */
	public static final TokenType TT_QUOTED = new TokenType("quoted");

	/**
	 * A constant indicating that a token is a my-quoted string, like "Launch Mi".
	 */
	public static final TokenType TT_MY_QUOTED = new TokenType("my_quoted");

	// ========================= FIELDS ====================================

	protected TokenType ttype;

	protected String sval;

	protected double nval;

	// ========================= CONSTRUCTORS ==============================

	/**
	 * Constructs a token from the given char.
	 * 
	 * @param char
	 *            the char
	 * @return a token constructed from the given char
	 */
	public Token(char c)
	{
		this(TT_SYMBOL, new String(new char[]
		{
			c
		}), 0);
	}

	/**
	 * Constructs a token from the given number.
	 * 
	 * @param double
	 *            the number
	 * @return a token constructed from the given number
	 */
	public Token(double nval)
	{
		this(TT_NUMBER, CommonNames.MISC.EMPTY_STRING, nval);
	}

	/**
	 * Constructs a token from the given string.
	 * 
	 * @param String
	 *            the string
	 * @return a token constructed from the given string
	 */
	public Token(String sval)
	{
		this(TT_WORD, sval, 0);
	}

	/**
	 * Constructs a token of the indicated type and associated string or numeric values.
	 * 
	 * @param MathToken
	 *            the type of the token, typically one of the constants this class defines
	 * @param string
	 *            the string value of the token, typically null except for WORD and QUOTED
	 *            tokens
	 * @param double
	 *            the numeric value of the token, typically 0 except for NUMBER tokens
	 * @return a token
	 */
	public Token(TokenType ttype, String sval, double nval)
	{
		this.ttype = ttype;
		this.sval = sval;
		this.nval = nval;
	}

	// ========================= IMPLEMENTATION: Object ====================

	/**
	 * Return a textual description of this object.
	 * 
	 * @return a textual description of this object
	 */
	@Override
	public String toString()
	{
		if (ttype == TT_EOF)
		{
			return "EOF";
		}
		return value().toString();
	}

	/**
	 * Must be overridden when <code>equals()</code> is.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		int result = HashCodeUtil.SEED;

		// Mandatory fields used in equals()
		result = HashCodeUtil.hash(result, ttype);
		result = HashCodeUtil.hash(result, sval);

		// Optional fields that are likely to be different for different
		// instances

		return result;
	}

	/**
	 * Returns true if the supplied object is an equivalent token.
	 * 
	 * @param obj
	 *            the object to compare
	 * @return true, if the supplied object is of the same type and value
	 */
	@Override
	public boolean equals(Object obj)
	{
		// Standard checks to ensure the adherence to the general contract of
		// the equals() method
		if (this == obj)
		{
			return true;
		}
		if ((obj == null) || (obj.getClass() != this.getClass()))
		{
			return false;
		}
		// Cast to friendlier version
		Token t = (Token) obj;

		if (ttype != t.ttype)
		{
			return false;
		}
		if (ttype == TT_NUMBER)
		{
			return nval == t.nval;
		}
		if (sval == null || t.sval == null)
		{
			return false;
		}
		return sval.equals(t.sval);
	}

	// ========================= METHODS ===================================

	/**
	 * Returns true if the supplied object is an equivalent token, given mellowness about
	 * case in strings and characters.
	 * 
	 * @param obj
	 *            the object to compare
	 * @return true, if the supplied object is of the same type and value. This method
	 *         disregards case when comparing the string value of tokens.
	 */
	public boolean equalsIgnoreCase(Object obj)
	{
		// Standard checks to ensure the adherence to the general contract of
		// the equals() method
		if (this == obj)
		{
			return true;
		}
		if ((obj == null) || (obj.getClass() != this.getClass()))
		{
			return false;
		}
		// Cast to friendlier version
		Token t = (Token) obj;

		if (ttype != t.ttype)
		{
			return false;
		}
		if (ttype == TT_NUMBER)
		{
			return nval == t.nval;
		}
		if (sval == null || t.sval == null)
		{
			return false;
		}
		return sval.equalsIgnoreCase(t.sval);
	}

	/**
	 * Returns true if this token is a number.
	 * 
	 * @return true, if this token is a number
	 */
	public boolean isNumber()
	{
		return ttype == TT_NUMBER;
	}

	/**
	 * Returns true if this token is a quoted string.
	 * 
	 * @return true, if this token is a quoted string
	 */
	public boolean isQuotedString()
	{
		return ttype == TT_QUOTED;
	}

	/**
	 * Returns true if this token is a quoted string.
	 * 
	 * @return true, if this token is a quoted string
	 */
	public boolean isMyQuotedString()
	{
		return ttype == TT_MY_QUOTED;
	}

	/**
	 * Returns true if this token is a symbol.
	 * 
	 * @return true, if this token is a symbol
	 */
	public boolean isSymbol()
	{
		return ttype == TT_SYMBOL;
	}

	/**
	 * Returns true if this token is a word.
	 * 
	 * @return true, if this token is a word.
	 */
	public boolean isWord()
	{
		return ttype == TT_WORD;
	}

	/**
	 * Returns the numeric value of this token.
	 * 
	 * @return the numeric value of this token
	 */
	public double nval()
	{
		return nval;
	}

	/**
	 * Returns the string value of this token.
	 * 
	 * @return the string value of this token
	 */
	public String sval()
	{
		return sval;
	}

	/**
	 * Returns the type of this token.
	 * 
	 * @return the type of this token, typically one of the constants this class defines
	 */
	public TokenType ttype()
	{
		return ttype;
	}

	/**
	 * Returns an object that represents the value of this token.
	 * 
	 * @return an object that represents the value of this token
	 */
	public Object value()
	{
		if (ttype == TT_NUMBER)
		{
			return new Double(nval);
		}
		if (ttype == TT_EOF)
		{
			return EOF;
		}
		if (sval != null)
		{
			return sval;
		}
		return ttype;
	}

	/**
	 * @return Returns the ttype.
	 */
	public TokenType getTtype()
	{
		return ttype;
	}

}
