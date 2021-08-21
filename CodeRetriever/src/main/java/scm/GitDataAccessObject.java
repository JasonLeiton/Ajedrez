
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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.jgit.api.FetchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.dfs.DfsRepositoryDescription;
import org.eclipse.jgit.internal.storage.dfs.InMemoryRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.TagOpt;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;




public class GitDataAccessObject extends SCMFactory{
	
	/*localPath es el directiorio local del repositorio, remotePath es la direccion electronica donde se esta alojando dicho repositorio*/
	private String localPath, remotePath, pathAbsoluteCloning;
	/*Objecto que guarda el repositorio local en memoria, haciendo uso de la biblioteca jgit*/
	private Repository localRepo;
	/**Objeto que administra el objeto localRepo, @see localRepo*/
	private Git git;
	
	UsernamePasswordCredentialsProvider userCredentials;

	
	public String getAbsoluteTempPath() {
		return pathAbsoluteCloning;
	}

	public void setPathAbsoluteCloning(String pathAbsoluteCloning) {
		this.pathAbsoluteCloning = pathAbsoluteCloning;
	}

	private File createTempFile(){
		File temp = null;
		try {
			temp = File.createTempFile("temp", "");
			this.pathAbsoluteCloning=temp.getAbsolutePath(); // it is for getting the path of the PC
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return null;
		}		
		if(!(temp.delete())){
	        try {
				throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	    }
	    if(!(temp.mkdir())){
	        try {
				throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
			} catch (IOException e) {e.printStackTrace();return null;}
	    }
	    
	    return temp;
	    
	}
	
	@Override
	public void setupConnection(String server, String user, String password){
		File temp = null;
		this.remotePath = server;
		temp = this.createTempFile();
		this.remotePath = server;
		try {
			git = Git.cloneRepository().setURI(server).setDirectory(temp).call();
			
		} catch (GitAPIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		userCredentials = new UsernamePasswordCredentialsProvider(user, password);
		try {
			//git = Git.init().setDirectory(new File("repo")).call();
			StoredConfig sc = git.getRepository().getConfig();
			sc.setString("remote", "origin", "url", server);
			sc.save();

			//git.pull().setCredentialsProvider(userCredentials).call();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	}
	
	public void setupConnection3(String server, String user, String password){
		InMemoryRepository repo = new InMemoryRepository(new DfsRepositoryDescription());
		git =  null;
		File temp = null;
		this.remotePath = server;
		repo.setGitwebDescription(server);
		try {
			temp = File.createTempFile("temp", "");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}		
		if(!(temp.delete())){
	        try {
				throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    if(!(temp.mkdir())){
	        try {
				throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
			} catch (IOException e) {e.printStackTrace();}
	    }
		git = new Git(repo);
		
		RemoteAddCommand rac = git.remoteAdd();
		rac.setName("origin");
		try {
			rac.setUri(new URIish(server));
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rac.call();
		} catch (GitAPIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		TagOpt to = TagOpt.AUTO_FOLLOW;
		FetchCommand pull = git.fetch().setRemote("origin").setCredentialsProvider(userCredentials);
		//PullCommand pull = git.pull().setRemote("origin").setRemoteBranchName("master");

		StoredConfig config = git.getRepository().getConfig();
		config.setString("remote", "origin", "url", server);
		try {
			config.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FetchResult result = pull.call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
			Iterable<RevCommit> rvcommit = null;
			try {
				rvcommit = git.log().all().call();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("rvcommit lista:");
			System.out.println(rvcommit.iterator().hasNext());
			System.out.println("end rvcommit");
			for (RevCommit revCommit : rvcommit) {
				System.out.println(revCommit.getId());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
	
	
    //@Override
    public void setupConnection2(String server, String user, String password) {
		userCredentials = new UsernamePasswordCredentialsProvider(user, password);
		this.remotePath = server;
		InMemoryRepository repo = new InMemoryRepository(new DfsRepositoryDescription("remoteRepo"));
		repo.setGitwebDescription(server);
		
		git = new Git(repo);
		
		
		
		try {
			//git = Git.init().setDirectory(new File("repo")).call();
			StoredConfig sc = git.getRepository().getConfig();
			sc.setString("remote", "origin", "url", server);
			sc.save();
			
			File rep = new File("repo/");
			
			
			git = Git.cloneRepository().setURI(server).setDirectory(rep).call();
			
			
			//git.pull().setCredentialsProvider(userCredentials).call();
		} catch (GitAPIException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
    public SCM getSCM() {
        return GitSCM.getInstance(git);
	}


}
