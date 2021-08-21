/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.scmdao.svn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.util.SVNPathUtil;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNDiffStatusHandler;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNDiffStatus;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatusClient;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import scm.SVNDataAccessObject;

/**
 *
 * @author agtorres
 */
public class Prueba {


     final List modifiedFiles = new LinkedList();
     final List addedFiles = new LinkedList();
     final List deletedFiles = new LinkedList();
     SVNRevision rN = SVNRevision.create(5000);
     SVNRevision rM = SVNRevision.create(5200);

     final String tag1Path = "/tags/1.0.0/";
     final String tag2Path = "/tags/20050131/";
   private SVNRepository                    repository         =    null;


       private static void setupLibrary() {
            DAVRepositoryFactory.setup();
            SVNRepositoryFactoryImpl.setup();
            FSRepositoryFactory.setup();
    }

    public void setupConnection(String server, String user, String password) {
            setupLibrary();
            try {
                    SVNURL       repositoryURL = SVNURL.parseURIEncoded(server);
                    repository = SVNRepositoryFactory.create(repositoryURL);
                    ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(user, password);
                    repository.setAuthenticationManager(authManager);
            } catch (SVNException ex) {
                    Logger.getLogger(SVNDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            }finally{}
    }

    public Prueba(){
                    setupConnection("http://svn.svnkit.com/repos/svnkit/trunk/", "", "");
                 SVNClientManager clientManager = SVNClientManager.newInstance();

     SVNWCClient wcClient = clientManager.getWCClient();
     SVNStatusClient statusClient = clientManager.getStatusClient();
     SVNDiffClient diffClient = clientManager.getDiffClient();
     SVNLogClient logClient = clientManager.getLogClient();
        try {

            diffClient.doDiffStatus(SVNURL.parseURIEncoded("http://svn.svnkit.com/repos/svnkit" + tag1Path), rN, SVNURL.parseURIEncoded("http://svn.svnkit.com/repos/svnkit" + tag2Path), rM, SVNDepth.INFINITY, true, new ISVNDiffStatusHandler() {
                public void handleDiffStatus(SVNDiffStatus diffStatus) {
                    if (diffStatus.getKind() == SVNNodeKind.FILE) {
                        SVNStatusType status = diffStatus.getModificationType();
                        String path = diffStatus.getPath();
                        if (status == SVNStatusType.STATUS_ADDED) {
                            System.out.println("Added: " + path);
                            addedFiles.add(SVNPathUtil.append("/trunk", path));
                        } else if (status == SVNStatusType.STATUS_DELETED) {
                            System.out.println("Deleted: " + path);
                            deletedFiles.add(SVNPathUtil.append("/trunk", path));
                        } else if (status == SVNStatusType.STATUS_MODIFIED) {
                            System.out.println("Modified: " + path);
                            modifiedFiles.add(SVNPathUtil.append("/trunk", path));
                        }
                    }
                }
            });
        } catch (SVNException ex) {
            
        }

        final long[] startRev = { -1 };
        final long[] endRev = { -1 };
        try {
            logClient.doLog(SVNURL.parseURIEncoded("http://svn.svnkit.com/repos/svnkit/tags"), null, SVNRevision.UNDEFINED, SVNRevision.HEAD, SVNRevision.create(0), true, true, false, 0, null, new ISVNLogEntryHandler() {

                public void handleLogEntry(SVNLogEntry logEntry) {
                    Map changedPaths = logEntry.getChangedPaths();
                    for (Iterator changedPathsIter = changedPaths.keySet().iterator(); changedPathsIter.hasNext();) {
                        String changedPath = (String) changedPathsIter.next();
                        SVNLogEntryPath logEntryPath = (SVNLogEntryPath) changedPaths.get(changedPath);
                        if (logEntryPath.getType() == SVNLogEntryPath.TYPE_ADDED && logEntryPath.getCopyPath() != null) {
                            if (changedPath.equals(tag1Path)) {
                                startRev[0] = logEntry.getRevision();
                            } else if (changedPath.equals(tag2Path)) {
                                endRev[0] = logEntry.getRevision();
                            }
                        }
                    }
                }
            });
        } catch (SVNException ex) {
            
        }

        //find out what files in what revisions and by whom were deleted
        //(String[]) deletedFiles.toArray(new String[deletedFiles.size()])
        final Map deletedEntries = new HashMap();
        try {
            logClient.doLog(SVNURL.parseURIEncoded("http://svn.svnkit.com/repos/svnkit/trunk"), null, SVNRevision.UNDEFINED, SVNRevision.create(endRev[0]), SVNRevision.create(startRev[0]), false, true, false, 0, null, new ISVNLogEntryHandler() {

                public void handleLogEntry(SVNLogEntry logEntry) {
                    Map changedPaths = logEntry.getChangedPaths();
                    for (Iterator changedPathsIter = changedPaths.keySet().iterator(); changedPathsIter.hasNext();) {
                        String changedPath = (String) changedPathsIter.next();
                        SVNLogEntryPath logEntryPath = (SVNLogEntryPath) changedPaths.get(changedPath);
                        if (deletedFiles.contains(changedPath) && logEntryPath.getType() == SVNLogEntryPath.TYPE_DELETED) {
                            changedPath = SVNPathUtil.removeHead(changedPath);
                            deletedEntries.put(changedPath, logEntry);
                        }
                    }
                }
            });
        } catch (SVNException ex) {
            
        }

         for (Iterator deletedFilesIter = deletedEntries.keySet().iterator(); deletedFilesIter.hasNext();) {
             String deletedPath = (String) deletedFilesIter.next();
             SVNLogEntry logEntry = (SVNLogEntry) deletedEntries.get(deletedPath);
             System.out.println("D " + deletedPath + ", author: " + logEntry.getAuthor() + ", revision: " + logEntry.getRevision() + ", comment: " + logEntry.getMessage());
         }
     }
    public static void main(String[] args){
        Prueba prueba = new Prueba();
    }
}


//Although it contains hardcoded path segments the idea must be clear to
//you, I hope. What it does is first of all collects the difference
//between two tags. As tags are not modified since they are created all
//modifications should always happen in their copy source path, in my case
//it is the trunk path, so I just hardcoded prepending of "/trunk" to each
//path received from an SVNDiffStatus object (a path provided by an
//SVNDiffStatus object is always relative to the operation target) to get
//the absolute repository path of items I'm interested in.
//
//Then I find out in what revisions my tags were created by copying from
//the trunk location. I do that with the help of the log operation
//(SVNLogClient.doLog()). Further those revisions are used in the next
//doLog() call as a start and end revision markers between which I'm
//looking for revisions where my deleted paths were actually deleted. So,
//that's it. In a similar manner you can find out what revisions you added
//files were added in.
//
//But regarding modified paths - there could be plenty of revisions where
//the paths were modified, so you can handle each one separately. 
//SVNDiffClient.doDiffStatus() gives you only a kind of a snapshot, it
//cannot give you all revisions where a single file was changed. It only
//says - yes, this file has been modified, but what number of times? That
//question is answered by SVNLogClient.doLog().