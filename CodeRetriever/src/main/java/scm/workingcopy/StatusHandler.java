/*
 * ====================================================================
 * Copyright (c) 2004-2008 TMate Software Ltd.  All rights reserved.
 *
 * This software is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at http://svnkit.com/license.html
 * If newer versions of this license are posted there, you may use a
 * newer version instead, at your option.
 * ====================================================================
 */
package scm.workingcopy;

import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusType;

public class StatusHandler{
    
	private SVNStatus status;
    
    public StatusHandler(SVNStatus status) {
        this.status = status;
    }
    
    public String getContentsStatus() {
        SVNStatusType contentsStatus = status.getContentsStatus();
        String pathChangeType = " ";
        // The contents of the file have been Modified.
        if (contentsStatus == SVNStatusType.STATUS_MODIFIED) {
            pathChangeType = "M";
        // Changes received from the server during an  update  overlap  with  local changes the user has in his working copy.             
        } else if (contentsStatus == SVNStatusType.STATUS_CONFLICTED) {
            pathChangeType = "C";
        // The file, directory or symbolic link item has been scheduled for Deletion from the repository.
        } else if (contentsStatus == SVNStatusType.STATUS_DELETED) {
            pathChangeType = "D";
        // The file, directory or symbolic link item has been scheduled for Addition to the repository.
        } else if (contentsStatus == SVNStatusType.STATUS_ADDED) {
            pathChangeType = "A";
        // The file, directory or symbolic link item is not  under  version control.
        } else if (contentsStatus == SVNStatusType.STATUS_UNVERSIONED) {
            pathChangeType = "?";
        // The item is unversioned, but is used by an eXternals definition.
        } else if (contentsStatus == SVNStatusType.STATUS_EXTERNAL) {
            pathChangeType = "X";
        // The file, directory or symbolic link item is not under version control, and is configured 
        // to be Ignored during 'add',  'import'and 'status' operations. 
        } else if (contentsStatus == SVNStatusType.STATUS_IGNORED) {
            pathChangeType = "I";
        } else if (contentsStatus == SVNStatusType.STATUS_MISSING || contentsStatus == SVNStatusType.STATUS_INCOMPLETE) {
         // The file, directory or  symbolic  link  item  is  under  version control but is missing or somehow incomplete. 
            pathChangeType = "!";
        } else if (contentsStatus == SVNStatusType.STATUS_OBSTRUCTED) {
        // The file, directory or symbolic link item is in  the  repository as one kind of object, but what's actually in 
        // the user's working copy is some other kind. 
            pathChangeType = "~";
        } else if (contentsStatus == SVNStatusType.STATUS_REPLACED) {
        // The file, directory or symbolic link item was  Replaced  in  the user's working copy; that is, the item was 
        // deleted,  and  a  new item with the same name was added (within  a  single  revision). 
            pathChangeType = "R";
            // The item was not modified (normal).
        } else if (contentsStatus == SVNStatusType.STATUS_NONE || contentsStatus == SVNStatusType.STATUS_NORMAL) {
            pathChangeType = " ";
        }
        return pathChangeType;
    }

    // the local item is out of date    
    public boolean hasItemChanged() {
    	boolean remoteChangeType = false;
        if(status.getRemotePropertiesStatus() != SVNStatusType.STATUS_NONE || status.getRemoteContentsStatus() != SVNStatusType.STATUS_NONE) {
            remoteChangeType = true;
        }
        return remoteChangeType;
    }
    
    public boolean hasPathChanged() {
    	return status.isSwitched();
    }
    
    public long getWorkingRevision() {
    	return status.getRevision().getNumber();
    }
    
    public long getCommittedRevision() {
    	return status.getCommittedRevision().getNumber();
    }
}