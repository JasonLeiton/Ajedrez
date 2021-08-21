package dao.scmdao.svn;


/*******************************************************************************
*
* @date   : 30-mar-2010, 19:36:17
* @file   : SVNChangedFiles
* @author : Antonio Gonzalez Torres
* Contents:
*
* Purpose : This class exports a hash with the files that have change between
 *          two revision, without including those that have been deleted.
*
*
*******************************************************************************/


import java.util.ArrayList;

import java.util.Hashtable;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.ISVNDiffStatusHandler;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNDiffStatus;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNWCUtil;


public class SVNChangedFiles {

	public Hashtable<String, String> export(SVNRepository repos, long longStartingRevision, long longEndingRevision) {
		Hashtable<String, String>	hashFiles           = new Hashtable<String, String>();
		SVNRevision          		startingRevision   	= SVNRevision.create(longStartingRevision);
		SVNRevision                 endingRevision		= SVNRevision.create(longEndingRevision);
		ArrayList<SVNDiffStatus>   	changes				= list(repos, startingRevision, endingRevision);
		try {
			for (int idx = 0; idx < changes.size(); idx++) {
				SVNDiffStatus 	change = changes.get(idx);
				String			action = change.getModificationType().toString();
				if(!action.equals("deleted")){
					String strFile = change.getPath();
					if(strFile.endsWith(".java")) {
						hashFiles.put(strFile, action);
                   }
				}
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		return hashFiles;
	}

	public ArrayList<SVNDiffStatus> list(SVNRepository repos, SVNRevision startingRevision, SVNRevision endingRevision) {
		try {
			SVNDiffClient             diffClient = new SVNDiffClient(repos.getAuthenticationManager(), SVNWCUtil.createDefaultOptions(true));
			ArrayList<SVNDiffStatus>    changes     = new ArrayList<SVNDiffStatus>();
			ImplISVNDiffStatusHandler   handler     = new ImplISVNDiffStatusHandler(changes);

			diffClient.doDiffStatus(repos.getLocation(), startingRevision,
									repos.getLocation(), endingRevision,
									SVNDepth.INFINITY, false, handler);

			return changes;
		} catch (SVNException e) {
			throw new RuntimeException(e);
		}
	}

	private static class ImplISVNDiffStatusHandler implements ISVNDiffStatusHandler {
		private ArrayList<SVNDiffStatus> changes    =   null;
		public ImplISVNDiffStatusHandler(ArrayList<SVNDiffStatus> changes) {
			this.changes = changes;
		}

		public void handleDiffStatus(SVNDiffStatus status) throws SVNException {
			if (status.getKind() == SVNNodeKind.FILE && (status.getModificationType() == SVNStatusType.STATUS_ADDED || status.getModificationType() == SVNStatusType.STATUS_MODIFIED || status.getModificationType() == SVNStatusType.STATUS_DELETED)) {
				changes.add(status);
			}
		}
	}
}