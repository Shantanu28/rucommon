/*****************************************************************************************
 * Source File: MinimalEnvironment.java
 ****************************************************************************************/
package net.ruready.common.rl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A common initialization of testing classes that use the minimal resource locator. This
 * is the same as the stand alone environment, without database access.
 * <p>
 * -------------------------------------------------------------------------<br>
 * (c) 2006-2007 Continuing Education, University of Utah<br>
 * All copyrights reserved. U.S. Patent Pending DOCKET NO. 00846 25702.PROV
 * <p>
 * This file is part of the RUReady Program software.<br>
 * Contact: Nava L. Livne <code>&lt;nlivne@aoce.utah.edu&gt;</code><br>
 * Academic Outreach and Continuing Education (AOCE)<br>
 * 1901 East South Campus Dr., Room 2197-E<br>
 * University of Utah, Salt Lake City, UT 84112<br>
 * -------------------------------------------------------------------------
 * 
 * @author Oren E. Livne <code>&lt;olivne@aoce.utah.edu&gt;</code>
 * @version Aug 30, 2007
 */
public class MinimalEnvironment extends DefaultEnvironment
{

	// ========================= CONSTANTS =================================

	/**
	 * A logger that helps identify this class' printouts.
	 */
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(MinimalEnvironment.class);

	// ========================= FIELDS ====================================

	// ========================= CONSTRUCTORS ==============================

	/**
	 * Construct a stand-alone setup object. Resource locator is not yet initialized. Call
	 * <code>setUp()</code> to initialize it.
	 */
	public MinimalEnvironment()
	{

	}

	// ========================= IMPLEMENTATION: Environment ===============

	/**
	 * Set up: configure and initialize the Hibernate EIS layer (designed to bypass DAO
	 * factory and get sessions directly from the session factory).
	 */
	synchronized public void setUp()
	{
		// Initialize the resource locator
		resourceLocator = MinimalResourceLocator.getInstance();
	}

	// ========================= METHODS ===================================

	// ========================= GETTERS & SETTERS =========================

}
