
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



import java.net.URISyntaxException;

import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.httpclient.Credentials;
import com.microsoft.tfs.core.httpclient.UsernamePasswordCredentials;

public class TFSDataAccessObject extends SCMFactory{

	TFSTeamProjectCollection tpc;

	private String pathAbsoluteCloning;
    
    public String getAbsoluteTempPath() {
		return pathAbsoluteCloning;
	}
    @Override
    public void setupConnection(String server, String user, String password) {
    	
    	Credentials credentials=new UsernamePasswordCredentials(user,password);
    	java.net.URI url;
		try {
			url = new  java.net.URI(server);
	    	tpc = new TFSTeamProjectCollection(url, credentials);			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

   
    public SCM getSCM() {
                return null;
    }
}
