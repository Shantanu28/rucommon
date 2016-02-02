/*******************************************************************************
 * Source File: LineID.java
 ******************************************************************************/
package net.ruready.common.junit.assembler;

import net.ruready.common.parser.core.assembler.AssemblerIdentifier;

/**
 * A factory to instantiate assembler types for the parametric evaluation
 * parser. This is the only public class in the assembler package, and serves as
 * the package's proxy.
 * 
 * @author Nava L. Livne <i>&lt;nlivne@aoce.utah.edu&gt;</i> Academic Outreach and
 *         Continuing Education (AOCE) 1901 East South Campus Dr., Room 2197-E
 *         University of Utah, Salt Lake City, UT 84112
 * @author Oren E. Livne <i>&lt;olivne@aoce.utah.edu&gt;</i> AOCE, Room 2197-E,
 *         University of Utah University of Utah, Salt Lake City, UT 84112 (c) 
 *         2006-07 Continuing Education , University of Utah . All copyrights
 *         reserved. U.S. Patent Pending DOCKET NO. 00846 25702.PROV
 * @version Jan 31, 2007
 */
public enum LineReaderAssemblerID implements AssemblerIdentifier
{
	// ========================= ENUMERATED TYPES ==========================

	/**
	 * Appends a token to the target string.
	 */
	APPEND_TOKEN
	{

	},

	/**
	 * Set a parameter value in the target.
	 */
	PARAM_SET
	{

	},

	/**
	 * Set the type of line read in the target.
	 */
	SET_LINE_ID
	{

	};

	// ========================= FIELDS ====================================

	// ========================= IMPLEMENTATION: Identifier ===================

	/**
	 * Return the object's type. This would normally be the object's
	 * <code>getClass().getSimpleName()</code>, but objects might be wrapped
	 * by Hibernate to return unexpected names. As a rule, the type string
	 * should not contain spaces and special characters.
	 */
	public String getType()
	{
		// The enumerated type's name satisfies all the type criteria, so use
		// it.
		return name();
	}

	// ========================= METHODS ===================================

}
