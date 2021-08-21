
/*******************************************************************************
*
* @date   : 25-mar-2010, 11:08:58
* @file   : SCMDataAccessObject
* @author : Antonio Gonzalez Torres
* Contents:
*
* Purpose :
*
*
*******************************************************************************/

package scm;



import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;


public class SVNDataAccessObject extends SCMFactory{

    private SVNRepository	repository	=	null;
    private String username = "";
    private String password = "";
    private String pathAbsoluteCloning;
    
    public String getAbsoluteTempPath() {
		return pathAbsoluteCloning;
	}
    
    @Override
    public void setupConnection(String server, String username, String password) {
            setupLibrary();
            try {
            	this.username = username;
            	this.password = password;
            	SVNURL		repositoryURL 	= SVNURL.parseURIEncoded(server);
            	SVNURL 		repoURL 		= SVNURL.parseURIEncoded( "file://localhost/testRep" );

            	repository = SVNRepositoryFactory.create(repositoryURL);
            	
            	ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
            	
            	
            	
            	repository.setAuthenticationManager(authManager);
            	
//                File directory = new File(path); 
//                ISVNWorkspace workspace = SVNWorkspaceManager.createWorkspace("file", directory.getAbsolutePath());
            	
            	
            } catch (SVNException ex) {
                    Logger.getLogger(SVNDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            }finally{}
    }

    private static void setupLibrary() {
            DAVRepositoryFactory.setup();
            SVNRepositoryFactoryImpl.setup();
            FSRepositoryFactory.setup();
    }
   
    public SCM getSCM() {
                return SCMSVN.getInstance(repository, username, password);
    }
}
