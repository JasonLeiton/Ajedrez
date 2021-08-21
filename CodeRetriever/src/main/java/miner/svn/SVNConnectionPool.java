/*******************************************************************************
 *
 * @date   : 29-mar-2010, 9:38:55
 * @file   : SVNConnectionPool
 * @author : Antonio Gonzalez Torres
 * Contents:
 *
 * Purpose :
 *
 *
 *******************************************************************************/

package miner.svn;

import java.util.Hashtable;

import scm.SCMFactory;

public class SVNConnectionPool extends Hashtable<String, SCMFactory> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SVNConnectionPool uniqueInstance = null;

	public SCMFactory getSCMDAOFactory(String strProject) {
		SCMFactory svnDAOFactory = null;
		if (containsKey(strProject)) {
			svnDAOFactory = get(strProject);
		}
		return svnDAOFactory;
	}

	public void createSCMDAOFactory(String strServer, String strProject, String strUser, String strPassword) {
		SCMFactory svnDAOFactory = null;
		if (!containsKey(strProject)) {
			svnDAOFactory = SCMFactory.getInstance(SCMFactory.SVN);
			svnDAOFactory.setupConnection(strServer, strUser, strPassword);
			put(strProject, svnDAOFactory);
			
		} // end if
	}

	public static SVNConnectionPool getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new SVNConnectionPool();
		}
		return uniqueInstance;
	}
}