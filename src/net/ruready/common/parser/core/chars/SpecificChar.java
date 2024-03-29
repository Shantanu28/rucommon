/*****************************************************************************************
 * Source File: SpecificChar.java
 ****************************************************************************************/
package net.ruready.common.parser.core.chars;

import java.util.List;

import net.ruready.common.parser.core.manager.Parser;
import net.ruready.common.parser.core.manager.Terminal;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved. Steve Metsker makes no
 * representations or warranties about the fitness of this software for any particular
 * purpose, including the implied warranty of merchantability.
 */

/**
 * A SpecificChar matches a specified character from a character assembly.
 * 
 * @author Steven J. Metsker Protected by U.S. Provisional Patent U-4003, February 2006
 * @version 1.0
 */
public class SpecificChar extends Terminal
{

	/**
	 * the character to match
	 */
	protected Character character;

	/**
	 * Constructs a SpecificChar to match the specified char.
	 * 
	 * @param char
	 *            the character to match
	 * @return a SpecificChar to match a Character constructed from the specified char.
	 */
	public SpecificChar(char c)
	{
		this(new Character(c));
	}

	/**
	 * Constructs a SpecificChar to match the specified character.
	 * 
	 * @param character
	 *            the character to match
	 * @return a SpecificChar to match the specified character
	 */
	public SpecificChar(Character character)
	{
		this.character = character;
	}

	/**
	 * Returns true if an assembly's next element is equal to the character this object
	 * was constructed with.
	 * 
	 * @param object
	 *            an element from an assembly
	 * @return true, if an assembly's next element is equal to the character this object
	 *         was constructed with
	 */
	@Override
	public boolean qualifies(Object o)
	{
		Character c = (Character) o;
		return c.charValue() == character.charValue();
	}

	/**
	 * Returns a textual description of this parser.
	 * 
	 * @param visited
	 *            a list of parsers already printed in this description
	 * @return a textual description of this parser
	 * @see Parser#toString()
	 */
	@Override
	public String unvisitedString(List<Parser> visited)
	{
		return character.toString();
	}
}
