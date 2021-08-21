package scm.workingcopy;

import java.io.BufferedOutputStream;

/*
 * ====================================================================
 * Copyright (c) 2004-2011 TMate Software Ltd.  All rights reserved.
 *
 * This software is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at http://svnkit.com/license.html.
 * If newer versions of this license are posted there, you may use a
 * newer version instead, at your option.
 * ====================================================================
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.tmatesoft.svn.core.ISVNDirEntryHandler;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.wc.admin.SVNAdminClient;


/**
 * This examples demonstrate how you can run WORKING:HEAD diff.
 * 
 * @version 1.3
 * @author  TMate Software Ltd.
 */
public class DiffWCToRepository {
    
    /**
     * Pass the absolute path of the base directory where all example data will be created in 
     * arg[0]. The sample will create:
     *  
     *  - arg[0]/exampleRepository - repository with some test data
     *  - arg[0]/exampleWC         - working copy checked out from exampleRepository
     */
    
    public static void main (String[] args) {
        //initialize SVNKit to work through file:/// protocol
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();
        try {
            //first create a repository and fill it with data
        	
            String path = "C:/Users/agtor/Dropbox/Universidades/TEC/Research/AVIB/sprint1/Maleku/jEdit";
            SVNURL srcURL = SVNURL.fromFile(new File(path));
            SVNRepository srcRepository = SVNRepositoryFactory.create(srcURL);
            //if ("file".equals(srcURL.getProtocol())) {
            	System.out.println("file1: " + srcURL.getPath());
                File srcReposDir = new File(srcURL.getPath());
                //if (srcReposDir.exists()) {
                	ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager("", ""); 
                	srcRepository.setAuthenticationManager(authManager);
                	ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
                	SVNClientManager clientManager = SVNClientManager.newInstance(options, authManager);
                	SVNDiffClient diffClient = clientManager.getDiffClient();
                	SVNLogClient svnLogClient = new SVNLogClient(authManager,options); 
                  		HashMap<String, File> p = new HashMap<String, File>();
                  		//	doList(java.io.File path, SVNRevision pegRevision, SVNRevision revision, boolean fetchLocks, boolean recursive, ISVNDirEntryHandler handler)   
                        svnLogClient.doList(new File(path),
                        		SVNRevision.WORKING, 
                        		SVNRevision.HEAD, 
                                true,
                                true,
                                new ISVNDirEntryHandler(){ 
                                   public void handleDirEntry(SVNDirEntry entry){ 
                                      if ((entry.getKind() == SVNNodeKind.FILE) && (entry.getPath().contains(".java"))){
                                    	  System.out.println("Path: " + path + "/" + entry.getPath());
                                    	  p.put(path, new File(path + "/" + entry.getPath()));
                                         } // End of if 
                                       } // End of handleDirEntry method 
                                   } // End of ISVNDirEntryHandler 
                                );
                        int counter = 0;
                        //File[] paths = new File[p.size()];
                        Iterator<File> files = p.values().iterator();
                        Iterator<String> paths = p.keySet().iterator();
                        while(files.hasNext()) {
                        	log(svnLogClient, new File[] {files.next()}, diffClient, paths.next());
                        	//paths[counter++] = iterator.next(); 
                        }
                  	  	
	        } catch (SVNException svne) {
	            System.out.println(svne.getErrorMessage());
	        } catch(Exception e){ 
	            e.printStackTrace(); 
	    } // End of try  
    }
    
    public static void log(SVNLogClient svnLogClient, File[] paths, SVNDiffClient diffClient, String path) {
    	//System.out.println(paths[0].getPath());
    	try {
			svnLogClient.doLog(paths,
			     SVNRevision.create(0),
			     SVNRevision.WORKING,
			     false, 
			     true, 
			     100000L,
			     new ISVNLogEntryHandler(){ 
							public void handleLogEntry(SVNLogEntry entry){ 
							  System.out.println(entry.getAuthor() + " " + entry.getRevision()); 
							  Iterator<SVNLogEntryPath> iter = entry.getChangedPaths().values().iterator();

							  while(iter.hasNext()) {
		                	    	SVNLogEntryPath entryPath = iter.next();
		                	    	SVNNodeKind nodeKind = entryPath.getKind();
		                	    	if ((nodeKind == SVNNodeKind.FILE) && entryPath.getPath().contains(".java")) {
			                			String strPath = entryPath.getPath();
							            System.out.println("strPath: " + path.replace("/jEdit", "") + strPath);
							            //setSources(SvnTarget source1, SvnTarget source2) 
							            try {
							            	ArrayList<String>	changedFilesList = new ArrayList<String>();
							            	// Requires Subversive SVN Team Provider - https://sliksvn.com/download/
							            	
											diffClient.doDiff(new File(path.replace("/jEdit", "") + strPath), SVNRevision.create(0), SVNRevision.create(0), SVNRevision.WORKING, SVNDepth.INFINITY, true, System.out, changedFilesList);
											Iterator<String> iterator = changedFilesList.iterator();
											for(String value = iterator.next(); iterator.hasNext();) {
												System.out.println(value);
											}
										} catch (SVNException e) {
											//e.printStackTrace();
										}							  
		                	    	}
							  }
							} // End of handleDirEntry method 
			 	} // End of ISVNDirEntryHandler
			);
		} catch (SVNException e) {
			e.printStackTrace();
		}	
    }    
//    public void test() {
//    	HashMap<String, String> update=new HashMap<String, String>(); 
//    	DAVRepositoryFactory.setup(); 
//    	ISVNAuthenticationManager  authManager = SVNWCUtil.createDefaultAuthenticationManager("",""); 
//    	ISVNOptions options = SVNWCUtil.createDefaultOptions(true); 
//    	SVNLogClient svnLogClient = new SVNLogClient(authManager,options); 
//
//    	try{ 
//    		String svnCheckoutDir="c:/wc"; 
//    				svnLogClient.doList(new File(svnCheckoutDir), 
//                        SVNRevision.WORKING, 
//                        SVNRevision.WORKING, 
//                        false, 
//                        true, 
//                        new ISVNDirEntryHandler(){ 
//                           public void handleDirEntry(SVNDirEntry entry) throws SVNException{ 
//                              if (entry.getKind() == SVNNodeKind.FILE){ 
//                            	  Calendar cal = Calendar.getInstance(); 
//                            	  cal.setTimeInMillis(entry.getDate().getTime()); 
//                            	  GregorianCalendar gCal = new GregorianCalendar(); 
//                            	  gCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)); 
//                            	  System.out.println("url"+entry.getURL()); 
//                            	  update.put(entry.getName(), new CVSFile(svnCheckoutDir+"/"+entry.getRelativePath(),gCal, new Long(entry.getRevision()).toString())); 
//                              } // End of if 
//                           } // End of handleDirEntry method 
//                           } // End of ISVNDirEntryHandler 
//                        ); 
//       } catch(Exception e){ 
//              e.printStackTrace(); 
//      } // End of try 
//   
//    for(Iterator iterator = update.entrySet (  ) .iterator (  ) ; iterator.hasNext (  ) ; )   {   
//        Map.Entry entry =  ( Map.Entry )  iterator.next (  ) ; 
//        String key =  ( String ) entry.getKey (  ) ; 
//        CVSFile value =  ( CVSFile ) entry.getValue (  ) ; 
//        System.out.print("**key"+ key); 
//        System.out.println("value :"+ value.getPath()); 
//        System.out.println("value :"+ value.getVersion()); 
//     } 
//return update;   
//    }
    
}