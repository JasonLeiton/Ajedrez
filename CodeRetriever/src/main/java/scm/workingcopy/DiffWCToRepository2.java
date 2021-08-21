package scm.workingcopy;

/*
 * ====================================================================
 * Copyright (c) 2004-2008 TMate Software Ltd.  All rights reserved.
 *
 * This software is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at http://svnkit.com/license.html.
 * If newer versions of this license are posted there, you may use a
 * newer version instead, at your option.
 * ====================================================================
 */

import java.io.File;
import java.io.IOException;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.admin.SVNAdminClient;
/**
 * This examples demonstrate how you can run WORKING:HEAD diff.
 *
 * @version 1.2.0
 * @author  TMate Software Ltd.
 */
public class DiffWCToRepository2 {

    /**
     * Pass the absolute path of the base directory where all example data will be created in
     * arg[0]. The sample will create:
     *
     *  - arg[0]/exampleRepository - repository with some test data
     *  - arg[0]/exampleWC         - working copy checked out from exampleRepository
     */
    public static void main (String[] args) {
        //initialize SVNKit to work through file:/// protocol
        SamplesUtility.initializeFSFSprotocol();

        File baseDirectory = new File("C:\\Users\\agtor\\Dropbox\\Universidades\\TEC\\Research\\AVIB\\Sprint1\\");
        File reposRoot = new File(baseDirectory, "exampleRepository");
        File wcRoot = new File(baseDirectory, "exampleWC");

        try {
            //1. first create a repository and fill it with data
            SVNAdminClient adminClient = SVNClientManager.newInstance().getAdminClient();
            adminClient.doCreateRepository(reposRoot, null, true, false, false, false);
            
            //2. populate the repository with the greek tree in a single transaction
            SVNCommitInfo info = SamplesUtility.createRepositoryTree(reposRoot);
            
            SVNURL reposURL = SVNURL.fromFile(wcRoot);
            
            SVNRepository repos = SVNRepositoryFactory.create(reposURL);
            
            ISVNEditor commitEditor = repos.getCommitEditor("initializing the repository with a greek tree", null, false, null, null);
            SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
            commitEditor.openRoot(SVNRepository.INVALID_REVISION);
            
            //pring out the commit information
            System.out.println(info);

            //3. checkout the entire repository tree
           // SVNURL reposURL = SVNURL.fromFile(reposRoot);
            SamplesUtility.checkOutWorkingCopy(reposURL, wcRoot);
            
            SVNUpdateClient updateClient = SVNClientManager.newInstance().getUpdateClient();
           // updateClient.doCheckout(url, wcRoot, SVNRevision.UNDEFINED, SVNRevision.HEAD, SVNDepth.INFINITY, false);

            
            
            

            //4. now make some changes to the working copy
            SamplesUtility.writeToFile(new File(wcRoot, "iota"), "New text appended to 'iota'", true);
            SamplesUtility.writeToFile(new File(wcRoot, "A/mu"), "New text in 'mu'", false);

            SVNClientManager clientManager = SVNClientManager.newInstance();
            SVNWCClient wcClient = SVNClientManager.newInstance().getWCClient();
            //set some property on a working copy directory
            wcClient.doSetProperty(new File(wcRoot, "A/B"), "spam", SVNPropertyValue.create("egg"), false, SVNDepth.EMPTY, null, null);

            //5. now run diff the working copy against the repository
            SVNDiffClient diffClient = clientManager.getDiffClient();
            /*
             * This corresponds to 'svn diff -rHEAD'.
             */
            diffClient.doDiff(wcRoot, SVNRevision.UNDEFINED, SVNRevision.WORKING, SVNRevision.HEAD,
                    SVNDepth.INFINITY, true, System.out, null);
            
            
            
        } catch (SVNException svne) {
            System.out.println(svne.getErrorMessage());
            System.exit(1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }

}