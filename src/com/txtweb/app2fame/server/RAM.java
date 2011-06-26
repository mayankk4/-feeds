package com.txtweb.app2fame.server;

/**	Class RAM 
 * 	@version 1.0
 * 	@author Mayank Kandpal
 */


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class RAM {
	private static final PersistenceManagerFactory pmfInstance =
		JDOHelper.getPersistenceManagerFactory("transactions-optional");

	private RAM() {}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
