
/*******************************************************************************
*
* @date   : 12-abr-2010, 14:29:41
* @file   : ChangedPaths
* @author : Antonio Gonzalez Torres
* Contents:
*
* Purpose :
*
*
*******************************************************************************/

package dao.scmdao.svn;

import java.util.Collection;
import java.util.Iterator;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.io.SVNLocationEntry;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.SVNRevision;

public class ChangedPaths{

//    public void getChangedPaths(SVNRepository repos, String path, long currentRevision){
//            try {
//
//                    // get file path as it was at revision using getLocations
//                    // pegRevision is revision which we used in log call.
//                    Collection<SVNLocationEntry> locations =   repos.getLocations(path, (Collection<SVNLocationEntry>) null,
//                                                            3790, new long[] {currentRevision});
//
//                    //List<SVNLocationEntry> locations = repos.getLocationSegments(path, -1, repos.getLatestRevision(), currentRevision);
//
////                    Collection<SVNLocationEntry> locations =   repos.getLocations("", (Collection<SVNLocationEntry>) null,
////                                                            revision, new long[] {repos.});
//
//                    // as we passed long[1] to getLocations there is only
//                    // one SVNLocationEntry in collection:
//                    for(Iterator<SVNLocationEntry> iterator = locations.iterator(); iterator.hasNext(); ){
//                                SVNLocationEntry location = iterator.next();
//                                System.out.println("path at revision " + location.getRevision() + " is: " + location.getPath());
//                    }
//                                // now we may call getFile on correct path and revision:
//                                //repos.getFile(location.getPath(), location.getRevision(), null, System.out);
//
//            } catch (SVNException ex) {
//                            ex.printStackTrace();
//            }
//
//
//    }

    
	public String getPreviousPath(SVNRepository repos, String path, long currentRevision){
            String strPreviousPath = "NoPreviousPath";
            try {
//                Collection<SVNLogEntry> logEntries = repos.log(new String [] {path},
//                                                (Collection<SVNLogEntry>) null, currentRevision, 0, true, false);
    			System.out.println("Action5:" + path.substring(12, path.length()));
            	//String 	filelongId = 	FILESDAO.getDataField(3, path.substring(12, path.length()));
            	//System.out.println("Action6: " + filelongId);
            	//long  	revisionId =	Long.parseLong(filelongId.substring(5, 12));
            	//System.out.println("Revision: " + revisionId + " " + SVNRevision.HEAD.getNumber());
            	
                Collection<SVNLocationEntry> locations = (Collection<SVNLocationEntry>) repos.getLocations(path, (Collection<SVNLocationEntry>) null, currentRevision, new long[] {currentRevision, currentRevision - 1});

                Iterator<SVNLocationEntry>	locs = locations.iterator();
                while(locs.hasNext()){
                	System.out.println(" locs: " + locs.next().getPath());
                }
                SVNLocationEntry location = locations.iterator().next();
                strPreviousPath = location.getPath();
                SVNLogEntry[]  entries= null;
//                if(logEntries.size() > 1){
//	                logEntries.toArray(entries);
//	                
//	                for (Iterator<SVNLogEntry> logs = logEntries.iterator(); logs.hasNext();) {
//	                    SVNLogEntry logEntry = logs.next();
//	                    System.out.println("logEntry1: " + logEntry.toString() + " " + logEntry.getRevision());
//	                }
//	                    //SVNLogEntry logEntry2 = entries[entries.length - 1];
//	                SVNLogEntry logEntry = entries[entries.length - 2];
//	                System.out.println("logEntry2: " + logEntry.toString());
//	                    // get file path as it was at revision using getLocations
//	                    // pegRevision is revision which we used in log call.
//	
//	                    locations =   repos.getLocations(path, (Collection<SVNLocationEntry>) null,
//	                                                            currentRevision, new long[] {logEntry.getRevision()});
//	
//	                    
//	                    
//	                    // as we passed long[1] to getLocations there is only
//	                    // one SVNLocationEntry in collection:
//	                    SVNLocationEntry location = locations.iterator().next();
//	                    strPreviousPath = location.getPath();
//	                    System.out.println(strPreviousPath + " " + logEntry.getRevision());
//	//                    System.out.println("path at revision " + location.getRevision() + " is: " + location.getPath());
//	
//	                                // now we may call getFile on correct path and revision:
//	                                //repos.getFile(location.getPath(), location.getRevision(), null, System.out);
//	//                }
//                }
            } catch (SVNException ex) {
                            ex.printStackTrace();
            }finally{
                 return strPreviousPath;
            }
    }
}
