/*****************************************************************************************
 * Source File: PrettyParser.java
 ****************************************************************************************/
package net.ruready.common.parser.core.pretty;

import java.util.ArrayList;
import java.util.List;

import net.ruready.common.parser.core.entity.Assembly;
import net.ruready.common.parser.core.entity.Matches;
import net.ruready.common.parser.core.manager.Parser;

/**
 * This class accepts a <code>Parser</code> object in its constructor. The constructor
 * sets each assembler in the parser composite to be one of the "pretty" assemblers in
 * this package. These assemblers build a tree of nodes from the
 * <code>ComponentNode</code> hierarchy that is also in this package. The resulting tree
 * effectively records the order in which the parse proceeds. Printing the tree results in
 * a "pretty print" or a standard formatting of the parse. The tree indents composite
 * nodes (sequences, alternations and repetitions) and prints terminals as they appeared
 * in the input.
 * 
 * @author Steven J. Metsker
 * @version 1.0
 */
public class PrettyParser
{
	protected Parser parser;

	boolean showLabels = false;

	/**
	 * Construct a pretty parser whose assemblers create a "pretty print" of input text.
	 */
	public PrettyParser(Parser parser)
	{
		this.parser = parser;
		parser.accept(new PrettyVisitor());
	}

	/*
	 * Return a collection of complete parses of the given assembly.
	 */
	protected Matches completeMatches(Assembly inAssembly)
	{
		Matches inState = new Matches();
		inState.addAssembly(inAssembly);
		Matches outState = parser.matchAndAssemble(inState);
		Matches outComplete = new Matches();
		for (Assembly a : outState.getAssemblies())
		{
			if (!a.hasMoreElements())
			{
				outComplete.addAssembly(a);
			}
		}
		return outComplete;
	}

	/**
	 * Returns true if this <code>PrettyParser</code> object will show labels for
	 * composite nodes.
	 * 
	 * @return true if this <code>PrettyParser</code> object will show labels for
	 *         composite nodes.
	 */
	public boolean getShowLabels()
	{
		return showLabels;
	}

	/**
	 * Returns a collection of strings that show the order of a parse of the given
	 * assembly.
	 * 
	 * @param Assembly
	 *            the assembly to parse
	 * @return a collection of strings that show the order of a parse of the given
	 *         assembly
	 */
	public List<String> parseTrees(Assembly inAssembly)
	{
		Matches outAssemblies = this.completeMatches(inAssembly);
		List<String> outStrings = new ArrayList<String>();
		for (Assembly a : outAssemblies.getAssemblies())
		{
			ComponentNode node = (ComponentNode) a.pop();
			outStrings.add(treeString(node));
		}
		return outStrings;
	}

	/**
	 * Sets the boolean that determines if this <code>PrettyParser
	 * </code> object will
	 * show labels for composite nodes.
	 * 
	 * @param boolean
	 *            the boolean that determines if this <code>PrettyParser</code> object
	 *            will show labels for composite nodes.
	 */
	public void setShowLabels(boolean showLabels)
	{
		this.showLabels = showLabels;
	}

	/**
	 * Return a string representation of a parse tree. The tree is a component node that
	 * typically contains a hierarchy of other nodes. Show labels for composite nodes if
	 * we have been instructed to do so.
	 */
	protected String treeString(ComponentNode node)
	{
		if (showLabels)
		{
			return node.toString();
		}
		else
		{
			return node.toStringWithoutLabels();
		}
	}
}
