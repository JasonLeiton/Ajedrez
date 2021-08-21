
/*******************************************************************************
*
* @date   : 25-mar-2010, 17:48:37
* @file   : SCMSVNDAO
* @author : Antonio Gonzalez Torres
* Contents:
*
* Purpose :
*
*
*******************************************************************************/

package scm;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNProperty;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.SVNRevisionProperty;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.internal.wc.admin.SVNLogImpl;
import org.tmatesoft.svn.core.io.ISVNFileRevisionHandler;
import org.tmatesoft.svn.core.io.SVNFileRevision;
import org.tmatesoft.svn.core.io.SVNLocationEntry;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.diff.SVNDiffWindow;
import org.tmatesoft.svn.core.wc.ISVNInfoHandler;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import dao.scmdao.svn.ChangedPaths;
import dao.scmdao.svn.CheckoutRevision;
import dao.scmdao.svn.SVNChangedFiles;
import scm.elem.ChangedFile;
import scm.elem.LogEntry;
import scm.workingcopy.InfoHandler;
import scm.workingcopy.StatusHandler;


public class SCMSVN implements SCM{
    private static 	SCMSVN        			uniqueInstance   	=  	null;
    private static 	SVNRepository			repository         	=  	null;
    private 		SVNChangedFiles			svnChangedFiles 	=   new SVNChangedFiles();
    private 		CheckoutRevision		checkoutRevision  	=   new CheckoutRevision();
    private 		ChangedPaths			svnChangedPaths		=   new ChangedPaths();
    private String username = "";
    private String password = "";    
    // *****************************
    private 		HashMap<String, Long> 	previousRevisions = new HashMap<String, Long>();
    private 		HashMap<String, ArrayList<String>> pathRevisions = new HashMap<String, ArrayList<String>>();
    //****************************
    @SuppressWarnings("static-access")
    public SCMSVN(SVNRepository repository, String username, String password){
            this.repository  =  repository;
            this.username = username;
            this.password = password;
    }
    
    //https://wiki.svnkit.com/Printing_Out_Repository_History    
	//https://wiki.svnkit.com/Printing_Out_File_Contents
	//https://wiki.svnkit.com/Managing_A_Working_Copy
    @SuppressWarnings({ "unchecked", "finally" })
    public HashMap<String, LogEntry> retrieveCommits(String... params) {
            long            endRevision     =   -1;
            long            startRevision    =   0;
            Collection<SVNLogEntry> logEntries = null;
            HashMap<String, LogEntry> mapLogEntries = new HashMap<String, LogEntry>();
            try {
                logEntries = repository.log(new String[]{""}, null, startRevision, endRevision, true, true);
    			File file = new File("C:\\Users\\agtor\\eclipse-workspace\\jEdit");
    			showStatus(file);
    			
//                Iterator<SVNLogEntry> iterator = logEntries.iterator();
//                while(iterator.hasNext()) {
//                	SVNLogEntry entry = iterator.next();
//                	LogEntry log = new LogEntry();
//                	log.setCommitId(""+ entry.getRevision());
//                	// log.setCommitTimeZone(); Not available in SVN
//                	log.setCommitTime(entry.getDate());
//                	log.setAuthorName(entry.getAuthor());
//                	log.setAuthorEmail("");
//                	log.setCommitMessage(entry.getMessage());
//                	//System.out.println(entry.getRevision() + " " + entry.getDate() + " " + entry.getAuthor());
//                	if (entry.getChangedPaths().size( ) > 0 ) {
//                		
//                		Iterator<SVNLogEntryPath> iter = entry.getChangedPaths().values().iterator();
//                	    ArrayList<String>	changedFilesList = new ArrayList<String>();
//                	    ArrayList<ChangedFile> changedFiles = new ArrayList<ChangedFile>();
//                	    while(iter.hasNext()) {
//                	    	SVNLogEntryPath entryPath = iter.next();
//                	    	SVNNodeKind nodeKind = entryPath.getKind();
//                	    	if (nodeKind == SVNNodeKind.FILE) {
//	                			String strPath = entryPath.getPath();
//	                			setFileRevisions(strPath, "" + entry.getRevision());
//	                			String[] output = getBranchType(entryPath);
//		                		log.setTypeBranch(output[0]);
//	                			log.setBranchName(output[1]);
//	                			if (previousRevisions.containsKey(strPath)) {
//	                				long previousRevision = previousRevisions.get(strPath);
//	                			}
//	                			//ByteArrayOutputStream byteArrayOutputStream = getFile(strPath, "" + entry.getRevision());
//	                			File file = new File("C:\\Users\\agtor\\eclipse-workspace\\jEdit");
////	                			try(OutputStream outputStream = new FileOutputStream(file)) {
////	                			    byteArrayOutputStream.writeTo(outputStream);
////	                			}
//	                			showStatus(file, entry.getRevision());
////	                			previousRevisions.put(strPath, entry.getRevision());
////	                			changedFilesList.add(strPath);
//                	    	}
//                		}
//                		log.setChangedPaths(changedFilesList);
//                	}
//                	mapLogEntries.put("" + entry.getRevision(), log);
//                }
                
            } catch (SVNException svne) {
                        System.out.println("error while collecting log information for '" + repository.getLocation() + "': " + svne.getMessage());
            }finally {
            	return mapLogEntries;
            }
    }

   public void setChangedFileData(String path, long revision) {
		ChangedFile changedFile = new ChangedFile();
		ByteArrayOutputStream baos = getFile(path, "" + revision); 
		changedFile.setFile(baos);
		
   }
    
    public String[] getBranchType(SVNLogEntryPath entryPath){
    	String[] output = new String[2];
    	SVNNodeKind nodeKind = entryPath.getKind();
    	String strPath = entryPath.getPath();
    	if (nodeKind == SVNNodeKind.FILE) {
    		String[] elems = null;
    		if(strPath.contains("branches")){
    			output[0] = "branches";
    			elems = strPath.split("branches");
    		}
    		if (strPath.contains("tags")) {
    			output[0] = "tags";
    			elems = strPath.split("tags");
    		}
    		if (strPath.contains("trunk")){
    			output[0] = "trunk";
    		}
    		String strBranch = getBranch(elems);
    		if (!strBranch.equals("")) {
    			output[1] = strBranch;
    		}else {
    			output[1] = "";
    		}
    		
    	}
    	return output;
    }
    
    @SuppressWarnings("finally")
	public String getBranch(String[] elems) {
    	String branch = "";
    	try {
	    	if(elems.length > 2) {
	    		String[] e = elems[1].split("[/]+");
	    		if(e.length > 2) {
	    			branch = e[1];
	    		}
	    	}
    	}catch(Exception e){
    		
    	}finally {
    		return branch;	
    	}
    }
    
	@Override
	public ByteArrayOutputStream getFile(String filePath, String revision) {
    	SVNProperties properties = new SVNProperties();
    	ByteArrayOutputStream baos = new ByteArrayOutputStream( );
    	try {
    		if (filePath.endsWith(".java")) {
        		repository.getFile(filePath , Long.parseLong(revision), properties , baos );
    		}        	   
		} catch (SVNException e) {
			
		}finally {
			return baos;
		}
    	
    }

    public void setFileRevisions(String path, String revision) {
    	ArrayList<String> revs = null;
    	if (pathRevisions.containsKey(path)){
    		revs = pathRevisions.get(path);
    	}else{
    		revs = new ArrayList<String>();
    	}
		revs.add(revision);
		pathRevisions.put(path, revs);    	
    	
    }
    
    public void getDiffLines(String filePath) {
//		try {
//		//System.out.println(filePath);
//            long            endRevision     =   -1;
//            long            startRevision    =   0;
//			Collection<SVNFileRevision> revisions = null;
//    		repository.getFileRevisions(filePath, revisions, startRevision, revision);
//    		SVNFileRevision[] revs = (SVNFileRevision[])revisions.toArray();
//    		System.out.println(filePath + " " + revisions.size() + " " + revs[revisions.size() - 1] + " " + revs[revisions.size() - 2]);
//		}catch(org.tmatesoft.svn.core.SVNException e) {
//			
//		}
    }
    

//	Collection<SVNLocationEntry> locations = (Collection<SVNLocationEntry>) repository.getLocations(currentPath, (Collection<SVNLocationEntry>) null, sv.getRevision(), new long[] {revisionId, sv.getRevision() - 1});                	
//	String previousPath = svnChangedPaths.getPreviousPath(repository, currentPath, revision);
    
//	getPreviousPath(currentPath, long revision)
    
    
    public ArrayList<String> checkoutCommit(String revision){
        return checkoutRevision.fetchRepository(repository, Long.parseLong(revision));
       
    }
    
    @SuppressWarnings("unchecked")
    public Collection<SVNDirEntry> retrieveProjectStructure(String... params) {
                Collection<SVNDirEntry>      entries = null;

                return entries;
      }

    public ArrayList<String> fetchHighLevelRepositoryLayout(){
                return checkoutRevision.fetchHighLevelRepositoryLayout(repository);
    }

	@Override
    public Hashtable<String, String> retrieveRevisionsDiff(String startRevision, String endRevision){
               return svnChangedFiles.export(repository, Long.parseLong(startRevision), Long.parseLong(endRevision));
    }
	@Override
    public String getPreviousPath(String path, String revision){
                return svnChangedPaths.getPreviousPath(repository, path, Long.parseLong(revision));
    }
    
	@Override
	public ArrayList<String> retrieveRevisionsID() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @SuppressWarnings("deprecation")
	private void showStatus(File wcPath){
    	try {
        /*
         * StatusHandler displays status information for each entry in the console (in the 
         * manner of the native Subversion command line client)
         */
    		
        DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);

        SVNClientManager ourClientManager = SVNClientManager.newInstance(SVNWCUtil.createDefaultOptions(true), repository.getAuthenticationManager());
//        if (ourClientManager == null){
//        	System.out.println("Null");
//        }else{
//        	System.out.println("Not null");
//        }

               
        //Invokes handler to return information about path in revision.
        //.doInfo(SVNURL.fromFile(wcPath), SVNRevision.UNDEFINED, SVNRevision.create(revision), true, new ISVNInfoHandler() { 
        //.doInfo(wcPath , SVNRevision.create(revision), true, new ISVNInfoHandler() {
        
        java.util.Collection<java.lang.String> changeLists = new ArrayList<java.lang.String>();
//        doInfo(file, SVNRevision.UNDEFINED, SVNRevision.WORKING, SVNDepth.getInfinityOrEmptyDepth(true), null, new ISVNInfoHandler() {
        ourClientManager.getWCClient().doInfo(wcPath, SVNRevision.UNDEFINED, SVNRevision.WORKING, SVNDepth.getInfinityOrEmptyDepth(true), changeLists, new ISVNInfoHandler() { 
                @Override        	
        	public void handleInfo(SVNInfo info) {
            System.out.println("-----------------INFO-----------------");
            System.out.println("Local Path: " + info.getFile().getPath());
            System.out.println("URL: " + info.getURL());

            if (info.isRemote() && info.getRepositoryRootURL() != null) {
                System.out.println("Repository Root URL: " + info.getRepositoryRootURL());
            }
            if(info.getRepositoryUUID() != null){
                System.out.println("Repository UUID: " + info.getRepositoryUUID());
            }
            System.out.println("Revision: " + info.getRevision().getNumber());
            System.out.println("Node Kind: " + info.getKind().toString());
            if(!info.isRemote()){
                System.out.println("Schedule: " + (info.getSchedule() != null ? info.getSchedule() : "normal"));
            }
            System.out.println("Last Changed Author: " + info.getAuthor());
            System.out.println("Last Changed Revision: " + info.getCommittedRevision().getNumber());
            System.out.println("Last Changed Date: " + info.getCommittedDate());
            if (info.getPropTime() != null) {
                System.out.println("Properties Last Updated: " + info.getPropTime());
            }
            if (info.getKind() == SVNNodeKind.FILE && info.getChecksum() != null) {
                if (info.getTextTime() != null) {
                    System.out.println("Text Last Updated: " + info.getTextTime());
                }
                System.out.println("Checksum: " + info.getChecksum());
            }
            if (info.getLock() != null) {
                if (info.getLock().getID() != null) {
                    System.out.println("Lock Token: " + info.getLock().getID());
                }
                System.out.println("Lock Owner: " + info.getLock().getOwner());
                System.out.println("Lock Created: " + info.getLock().getCreationDate());
                if (info.getLock().getExpirationDate() != null) {
                    System.out.println("Lock Expires: " + info.getLock().getExpirationDate());
                }
                if (info.getLock().getComment() != null) {
                    System.out.println("Lock Comment: " + info.getLock().getComment());
                }
            }
        }
    });
//        SVNStatusClient statusClient = ourClientManager.getStatusClient();
        System.out.println(changeLists.size());
//        SVNStatus status = statusClient.doStatus(wcPath, true);
//        StatusHandler statusHandler = new StatusHandler(status);
//        
//        SVNWCClient svnClient = ourClientManager.getWCClient();
//        SVNInfo info = svnClient.doInfo(wcPath, SVNRevision.COMMITTED);
//        
//        InfoHandler infoHandler = new InfoHandler();
//        infoHandler.handleInfo(info);
    	}catch(SVNException e) {}
    }	

	public static SCMSVN getInstance(SVNRepository repository, String username, String password){
        if(uniqueInstance == null){
                        uniqueInstance = new SCMSVN(repository, username, password);
        }
        return uniqueInstance;
    }
}