package dao.scmdao.svn;

/*
 * ====================================================================
 * Copyright (c) 2004-2009 TMate Software Ltd.  All rights reserved.
 *
 * This software is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at http://svnkit.com/license.html.
 * If newer versions of this license are posted there, you may use a
 * newer version instead, at your option.
 * ====================================================================
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.io.ISVNReporter;
import org.tmatesoft.svn.core.io.ISVNReporterBaton;
import org.tmatesoft.svn.core.io.SVNRepository;


public class CheckoutRevision {

        private PropFetchingEditor editor = new PropFetchingEditor();

    /**
     * Demonstration of recursive fetching properties from a repository tree using only low-level 
     * SVNKit API. This example fetches properties from the SVNKit's project repository which is publicly 
     * readable. So, you may copy-paste this example and run it as is.
     * @param repos
     * @param revision
     * @return arrayFiles
     */
    @SuppressWarnings("finally")
	public ArrayList<String> fetchRepository(SVNRepository repos, final long revision){
                ArrayList<String> arrayFiles        = new ArrayList<String>();
                try {
                    ISVNReporterBaton reporter = new ISVNReporterBaton() {
                        public void report(ISVNReporter reporter) throws SVNException {
                            reporter.setPath("", null, revision, SVNDepth.INFINITY, true);
                            reporter.finishReport();
                        }
                    };
                    repos.status(revision, null, SVNDepth.FILES, reporter, editor);
                    Map<String, Map<String, SVNPropertyValue>> fileProps = editor.getFilesToProps();
                    for (Iterator<String> filePathsIter = fileProps.keySet().iterator(); filePathsIter.hasNext();) {
                                String  path        =   filePathsIter.next();
                                System.out.println("CheckoutRevision: " + path);
                                if(path.endsWith(".java")) {
                                            System.out.println("CheckoutRevision: " + path);
                                            arrayFiles.add(path);

                                }
                    }
                } catch (SVNException svne) {
                                System.out.println(svne.getErrorMessage().getFullMessage());
                }finally{
                    return arrayFiles;
                }
    }

    @SuppressWarnings(value = { "unchecked", "finally" })
    public ArrayList<String> fetchHighLevelRepositoryLayout(SVNRepository repos){
                ArrayList<String> 	arrayBranches   = new ArrayList<String>();
                Stack<String> 		theStack 		= new Stack<String>();
                boolean				firstTime		= true;
                try {
                    // Retrieves the first level of the repository: branches, tags, trunk
                    Collection<SVNDirEntry> entries = repos.getDir("", repos.getLatestRevision(), null, (Collection<SVNDirEntry>) null);
                    Iterator<SVNDirEntry> iterator1 = entries.iterator();
                    while (iterator1.hasNext()) {
                        SVNDirEntry entry = iterator1.next();
                        // push the directories "branches, tags, and trunk"
                        if (entry.getKind() == SVNNodeKind.DIR) {
                        		String branch = entry.getName();
                        		if(branch.contains("branches") || branch.contains("tags") || branch.contains("trunk")){
                        				theStack.push(entry.getName());
                        		}
                        }
                    }
                    // Retrieves the second level of the respository: name of branches, tags and the trunk itself
                    while( !theStack.isEmpty()){
                                // pop the first level of the hierarchy
                                String  				path       	=   theStack.pop();
                                Collection<SVNDirEntry> entries2    =   repos.getDir(path, repos.getLatestRevision(), null,(Collection<SVNDirEntry>) null);
                                Iterator<SVNDirEntry>   iterator2	=   entries2.iterator();
                                // Iterates through the second level elements
                                while(iterator2.hasNext()){
                                            SVNDirEntry entry = iterator2.next();
                                            if((entry.getKind() == SVNNodeKind.DIR) && (!path.equals("trunk"))){
                                                    arrayBranches.add("/" + path + "/" + entry.getName());
                                            }else if (path.equals("trunk") && firstTime){
                                            			arrayBranches.add("/" + path);
                                                        firstTime =  false;
                                            }// end if
                                } // end while
                    } // end while
                } catch (SVNException ex) {
                                Logger.getLogger(CheckoutRevision.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    return arrayBranches;
                }

    }
}