package scm.workingcopy;

/*
 * ====================================================================
 * Copyright (c) 2004-2011 TMate Software Ltd.  All rights reserved.
 *
 * This software is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at http://svnkit.com/license.html
 * If newer versions of this license are posted there, you may use a
 * newer version instead, at your option.
 * ====================================================================
 */

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Iterator;

import org.tmatesoft.svn.core.SVNCancelException;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;
import org.tmatesoft.svn.core.replicator.ISVNReplicationHandler;
import org.tmatesoft.svn.core.replicator.SVNRepositoryReplicator;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/*
 * This example shows how you can synchronize two repositories
 * with the help of SVNKit. The program accepts 
 * two args: url of the source repository and the filesystem path 
 * of the target repository.
 * If they're not provided the program uses default locations.
 * 
 * If no args are provided both repositories are first created, then 
 * the source one is populated with some data up to revision 4. And 
 * lastly the source repository is replicated to the target one 
 * (all those 4 revisions), so that when the program exits you have two repositories with the 
 * same data.
 * 
 * Below you can see a program layout: 
 * 
   r1 by 'me' at Wed Apr 26 02:10:14 NOVST 2006
   r2 by 'me' at Wed Apr 26 02:10:14 NOVST 2006
   r3 by 'me' at Wed Apr 26 02:10:15 NOVST 2006
   r4 by 'me' at Wed Apr 26 02:10:15 NOVST 2006
   
   The latest source revision: 4
   
   'file:///G:/tgtRepository' repository tree:

   /dirA (author: 'me'; revision: 4; date: Wed Apr 26 02:10:15 NOVST 2006)
   /dirA/dirB (author: 'me'; revision: 4; date: Wed Apr 26 02:10:15 NOVST 2006)
   /dirA/dirB/fileB.txt (author: 'me'; revision: 4; date: Wed Apr 26 02:10:15 NOVST 2006)
   /dirA/fileA.txt (author: 'me'; revision: 2; date: Wed Apr 26 02:10:14 NOVST 2006)
   
   Committed revision 1
   Committed revision 2
   Committed revision 3
   Committed revision 4

   Number of replicated revisions: 4
   
   'file:///G:/tgtRepository' repository tree:

   /dirA (author: 'me'; revision: 4; date: Wed Apr 26 02:10:15 NOVST 2006)
   /dirA/dirB (author: 'me'; revision: 4; date: Wed Apr 26 02:10:15 NOVST 2006)
   /dirA/dirB/fileB.txt (author: 'me'; revision: 4; date: Wed Apr 26 02:10:15 NOVST 2006)
   /dirA/fileA.txt (author: 'me'; revision: 2; date: Wed Apr 26 02:10:14 NOVST 2006)
 */
public class Replicate {

    public static void main(String[] args) {
        String srcPath = "srcRepository";
        String tgtPath = "tgtRepository";
        String srcUrl = null;
        setupLibrary();

        if (args != null) {
            srcUrl = (args.length >= 1) ? args[0] : srcUrl;
            tgtPath = (args.length >= 2) ? args[1] : tgtPath;
        }

        SVNURL srcURL = null;
        SVNURL tgtURL = null;
        SVNRepository srcRepository = null;
        SVNRepository tgtRepository = null;
        boolean createSrcRepos = false;
        boolean populateSrcRepos = false;
        try {
            if (srcUrl != null) {
                srcURL = SVNURL.parseURIDecoded(srcUrl);
                if ("file".equals(srcURL.getProtocol())) {
                    File srcReposDir = new File(srcURL.getPath());
                    if (!srcReposDir.exists()) {
                        createSrcRepos = true;
                        populateSrcRepos = true;
                        srcPath = srcURL.getPath();
                    } else {
                        srcRepository = SVNRepositoryFactory.create(srcURL);
                        if (srcRepository.getLatestRevision() == 0) {
                            populateSrcRepos = true;
                        }
                    }
                }
            } else {
                createSrcRepos = true;
                populateSrcRepos = true;
            }

            if (createSrcRepos) {
                srcURL = SVNRepositoryFactory.createLocalRepository(new File(srcPath), false, false);
            }
            
            tgtURL = SVNRepositoryFactory.createLocalRepository(new File(tgtPath), true, false);
            
            srcRepository = SVNRepositoryFactory.create(srcURL);
            tgtRepository = SVNRepositoryFactory.create(tgtURL);
        } catch (SVNException svne) {
            System.err.println("Can not create a repository: " + svne.getErrorMessage().getFullMessage());
            System.exit(1);
        }

        srcRepository.setAuthenticationManager(SVNWCUtil.createDefaultAuthenticationManager());
        tgtRepository.setAuthenticationManager(SVNWCUtil.createDefaultAuthenticationManager());
        
        try{
            if (populateSrcRepos) {
                populateSourceRepository(srcRepository);
            }
            System.out.println();
            long srcLatestRevision = srcRepository.getLatestRevision();
            System.out.println("The latest source revision: " + srcLatestRevision);
            System.out.println();
            System.out.println("'" + srcURL + "' repository tree:");
            System.out.println();
            /*
             * Using the DisplayRepositoryTree example to show the source 
             * repository tree in the latest revision. 
             */
           // DisplayRepositoryTree.listEntries(srcRepository, "");
            System.out.println();
        }catch(SVNException svne){
            System.err.println("An error occurred while accessing source repository: " + svne.getErrorMessage().getFullMessage());
            System.exit(1);
        }

        try{
            /*
             * First let's try the standard replay way.
             */
            long replicatedRevisions = 0;
            try {
                initializeRepository(srcRepository, tgtRepository);
                replicatedRevisions = synchronizeRepository(srcRepository, tgtRepository);
            } catch (SVNException svne) {
                if (svne.getErrorMessage().getErrorCode() != SVNErrorCode.RA_NOT_IMPLEMENTED) {
                    throw svne;
                }

                tgtRepository = SVNRepositoryFactory.create(tgtURL);
                replicatedRevisions = replicateRepository(srcRepository, tgtRepository);
            }
            
            System.out.println();
            System.out.println("Number of replicated revisions: " + replicatedRevisions);
            System.out.println();
            System.out.println("'" + tgtURL + "' repository tree:");
            System.out.println();
            /*
             * Shows the tree of the target repository in the latest revision.
             */
           // DisplayRepositoryTree.listEntries(tgtRepository, "");
        }catch(SVNException svne){
            System.err.println("An error occurred while accessing source repository: " + svne.getErrorMessage().getFullMessage());
            System.exit(1);
        }
        
    }
    private static void initializeRepository(SVNRepository fromRepos, SVNRepository toRepos) throws SVNException {
        copyRevisionProperties(fromRepos, toRepos, 0);
    }
    private static void copyRevisionProperties(SVNRepository fromRepository, SVNRepository toRepository, long revision) throws SVNException {
        SVNProperties revProps = fromRepository.getRevisionProperties(revision, null);
        for (Iterator propNames = revProps.nameSet().iterator(); propNames.hasNext();) {
            String propName = (String) propNames.next();
            SVNPropertyValue propValue = revProps.getSVNPropertyValue(propName);
            toRepository.setRevisionPropertyValue(revision, propName, propValue);
        }
    }
    private static long synchronizeRepository(SVNRepository fromRepos, SVNRepository toRepos) throws SVNException {
        long lastMergedRevision = 0;
        long fromLatestRevision = fromRepos.getLatestRevision();
        long count = 0;
        for (long currentRev = lastMergedRevision + 1; currentRev <= fromLatestRevision; currentRev++) {
            ISVNEditor commitEditor = toRepos.getCommitEditor("", null);
            fromRepos.replay(0, currentRev, true, commitEditor);
            SVNCommitInfo info = commitEditor.closeEdit();
                
            if (info.getNewRevision() != currentRev) {
                System.err.println("Commit created rev " + info.getNewRevision() + " but should have created " + currentRev);
                System.exit(1);
            }
            System.out.println("Committed revision " + info.getNewRevision());
            copyRevisionProperties(fromRepos, toRepos, currentRev);
            count++;
        }
        return count;
    }

    private static void populateSourceRepository(SVNRepository srcRepository) throws SVNException {
        String dirA = "dirA";
        String dirB = "dirA/dirB";
        String fileA = "dirA/fileA.txt";
        String fileB = "dirA/dirB/fileB.txt";
        byte[] fileAContents = "This is file fileA.txt".getBytes();
        byte[] fileBContents = "This is file fileB.txt".getBytes();
        SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
        long revision = -1;
        SVNCommitInfo info = null;
        String checksum = null;
        
        /*
         * First commit "/dirA".
         */
        ISVNEditor editor = srcRepository.getCommitEditor("adding " + dirA, null);
        editor.openRoot(-1);
        editor.addDir(dirA, null, -1);
        editor.closeDir();
        editor.closeDir();
        info = editor.closeEdit();
        System.out.println(info);
        revision = info.getNewRevision();
        
        /*
         * Then commit "/dirA/fileA.txt".
         */
        editor = srcRepository.getCommitEditor("adding " + fileA, null);
        editor.openRoot(-1);
        editor.openDir(dirA, revision);
        editor.addFile(fileA, null, -1);
        editor.applyTextDelta(fileA, null);
        checksum = deltaGenerator.sendDelta(fileA, new ByteArrayInputStream(fileAContents), editor, true);
        editor.closeFile(fileA, checksum);
        editor.closeDir();
        editor.closeDir();
        info = editor.closeEdit();
        System.out.println(info);
        revision = info.getNewRevision();

        /*
         * Then commit "/dirA/dirB".
         */
        editor = srcRepository.getCommitEditor("adding " + dirB, null);
        editor.openRoot(-1);
        editor.openDir(dirA, revision);
        editor.addDir(dirB, null, -1);
        editor.closeDir();
        editor.closeDir();
        editor.closeDir();
        info = editor.closeEdit();
        System.out.println(info);
        revision = info.getNewRevision();
        
        /*
         * Then commit "/dirA/dirB/fileB.txt".
         */
        editor = srcRepository.getCommitEditor("adding " + fileB, null);
        editor.openRoot(-1);
        editor.openDir(dirA, revision);
        editor.openDir(dirB, revision);
        editor.addFile(fileB, null, -1);
        editor.applyTextDelta(fileB, null);
        checksum = deltaGenerator.sendDelta(fileB, new ByteArrayInputStream(fileBContents), editor, true);
        editor.closeFile(fileB, checksum);
        editor.closeDir();
        editor.closeDir();
        editor.closeDir();
        info = editor.closeEdit();
        System.out.println(info);
    }

    /*
     * This routine makes synchronization between two repositories
     * using SVNKit's own feature - replicator.
     */
    private static long replicateRepository(SVNRepository srcRepository, SVNRepository tgtRepository) throws SVNException {
        long latestRevision = srcRepository.getLatestRevision();
        SVNRepositoryReplicator replicator = SVNRepositoryReplicator.newInstance();
        replicator.setReplicationHandler(new ISVNReplicationHandler() {

            public void revisionReplicated(SVNRepositoryReplicator source, SVNCommitInfo commitInfo) throws SVNException {
                System.out.println("Committed revision " + commitInfo.getNewRevision());
            }
            
            public void revisionReplicating(SVNRepositoryReplicator source, SVNLogEntry logEntry) throws SVNException {
            }
            
            public void checkCancelled() throws SVNCancelException {
            }
        });
        
        return replicator.replicateRepository(srcRepository, tgtRepository, 1, latestRevision);
    }
    
    private static void setupLibrary() {
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();
    }

}