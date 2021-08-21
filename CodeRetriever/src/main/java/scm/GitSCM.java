package scm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.CheckoutConflictException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import scm.elem.ChangedFile;
import scm.elem.FileLinesChange;
import scm.elem.ISCMDirectory;
import scm.elem.ISCMFile;
import scm.elem.ISCMFileSystemElement;
import scm.elem.LogEntry;

public class GitSCM implements SCM {
	
    private static 	GitSCM       	uniqueInstance   	=  	null;
    private static 	Git		repository         	=  	null;	
	
	public GitSCM(Git git) {
		GitSCM.repository = git;
		
	}
	
	public void checkoutCommitId(String commitId) throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException {
		repository.checkout().setName(commitId).call();
	}

	@Override
	public ArrayList<String> retrieveRevisionsID() {
		// TODO Auto-generated method stub
		
		ArrayList<String> revisionsID = new ArrayList<String>();
		try {
			//research if git log can be call with a time filter to sort.
			Iterable<RevCommit> revs =  repository.log().call();
			for (RevCommit rev : revs) {
				revisionsID.add(rev.getId().name());
			}
		} catch (NoHeadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return revisionsID;
	}


	
	
	private LogEntry mappingMetadata(RevCommit rev,String[] previousCommitName) {
		LogEntry metadata = new LogEntry();	
		metadata.setAuthorEmail(rev.getAuthorIdent().getEmailAddress());
		metadata.setAuthorName(rev.getAuthorIdent().getName());
		metadata.setCommitId(rev.getId().getName());
		metadata.setCommitMessage(rev.getFullMessage());
		metadata.setCommitTime(new Date(rev.getCommitTime()));
		metadata.setCommitTimeZone(rev.getAuthorIdent().getTimeZone());
		metadata.setPreviousCommitId(previousCommitName != null && previousCommitName.length != 0 ? previousCommitName[0] : null);
		return metadata;
		
	}
	
	private FileLinesChange.Type mapType(org.eclipse.jgit.diff.Edit.Type t){
		if(org.eclipse.jgit.diff.Edit.Type.INSERT == t) return FileLinesChange.Type.INSERT;
		else if(org.eclipse.jgit.diff.Edit.Type.REPLACE == t) return FileLinesChange.Type.REPLACE;
		else if(org.eclipse.jgit.diff.Edit.Type.DELETE == t) return FileLinesChange.Type.REMOVE;
		else return FileLinesChange.Type.EMPTY;
		
		
	}
	
	
	private void getDifference(RevCommit prev, RevCommit rev, ArrayList<ChangedFile> filesChanged,ArrayList<String> pathChanged) {
		try( DiffFormatter diffFormatter = new DiffFormatter( DisabledOutputStream.INSTANCE ) ) {
			diffFormatter.setRepository( repository.getRepository() );
			List<DiffEntry> diffEntries = diffFormatter.scan( prev.getId(), rev.getId());
			ChangedFile fc;
			for(int i = 0 ; i < diffEntries.size(); i++) {
				
				
				FileHeader fileHeader = diffFormatter.toFileHeader( diffEntries.get( i ) );
				fc = new ChangedFile(fileHeader.getOldPath(),fileHeader.getNewPath());
				
				//fileHeader.DEV_NULL; for new files or file deletions
				
				for(org.eclipse.jgit.diff.Edit e : fileHeader.toEditList())
					//add version A and B for support
					fc.addChange(e.getBeginA(), e.getEndB(), e.getLengthB(), mapType(e.getType()));
				if (!fileHeader.getOldPath().equals(fileHeader.getNewPath()))
					pathChanged.add(fileHeader.getOldPath());
				filesChanged.add(fc);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	

	@Override
	public HashMap<String, LogEntry> retrieveCommits(String... params) {
		// TODO Auto-generated method stub
		
		HashMap<String, LogEntry> metadata = new HashMap<>();
		try{
			Iterable<RevCommit> logs = null;
			try {
				logs = repository.log().all().call();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String [] previousCommit;
			for (RevCommit rev : logs) {
				int previousCount = rev.getParentCount();
				previousCommit = (previousCount == 0)? null:new String[previousCount];
				//Here exist the risk of the files was changed in more than one parent, in other words, a merge conflict
				ArrayList<ChangedFile> filesChanged = new ArrayList<>();
				ArrayList<String> pathChanged = new ArrayList<>();
				
				//Exists another risk, multiple files was changed here
				// On merge there is a problem, how we will save the information in database?
				// Only we will make the changes in files which are being mergin
				// -------------------------------------x-------------x-------o------->
				//                                  \--------x---x-------x----/				
				for(RevCommit prev : rev.getParents()) {
					previousCount--;
					getDifference(prev,rev, filesChanged,pathChanged);
					previousCommit[previousCount] = prev.getId().getName();
				}
				LogEntry gcm= mappingMetadata(rev, previousCommit);
				gcm.setChangedPaths(pathChanged);
				gcm.setChangedFiles(filesChanged);
				
				//set the branch name...
				printGitCommitMetadata(gcm);
				//System.out.println(repository.getRepository().getDirectory().getPath());
				
				
				metadata.put(rev.getId().getName(), gcm);
			}
		} catch (NoHeadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (RevisionSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 		
		
		return metadata;
	}
	
	
	public void printGitCommitMetadata(LogEntry icm) {
		System.out.println("==================Commit==================");
		System.out.println("Commit ID: " + icm.getCommitId());
		System.out.println("Commit Msg: " + icm.getCommitMessage());
		System.out.println("Commit author name: " + icm.getAuthorName());
		System.out.println("Commit author email: " + icm.getAuthorEmail());
		System.out.println("Commit Timestamp: " + icm.getCommitTime().getTime());
		System.out.println("Commit TimeZone: " + icm.getCommitTimeZone().getID());
		System.out.println("Commit BranchName: " + icm.getBranchName());
		
		if(icm.getPreviousCommitId() != null)
			System.out.println("Commit previous Commit: " + icm.getPreviousCommitId());
		if(icm.getChangedFiles() != null && !icm.getChangedFiles().isEmpty())
		for(ChangedFile fc : icm.getChangedFiles()) {
			System.out.println("\t-Changed file (old): " + fc.getFilePathOld() + " (new) " + fc.getFilePath());
			for(FileLinesChange flc : fc.getFileChanges()) {
				System.out.println("\t\t+From: " + flc.getFrom() + " to " + flc.getTo() + " Changed Lines: " + flc.getChangedLines());
			} 
		}
		System.out.println("==========================================");
		System.out.println("");
	}
	
	public static GitSCM getInstance(Git repository){
        if(uniqueInstance == null){
                        uniqueInstance = new GitSCM(repository);
        }
        return uniqueInstance;
    }
	
	private class EqualFileNameFilter implements FilenameFilter{
		
		private String filename;
		public EqualFileNameFilter(String fileName) {
			// TODO Auto-generated constructor stub
			filename = fileName;
		}
		
		@Override
		public boolean accept(File file, String filter) {
			// TODO Auto-generated method stub
			return file.getPath().equals(filename);
		}
		
	}

	@Override
	public ByteArrayOutputStream getFile(String file, String revision) {
		try{
			org.eclipse.jgit.lib.Repository repo = repository.getRepository();
			ObjectId CommitId = repo.resolve(revision);
			// now we have to get the commit
			RevWalk revWalk = new RevWalk(repo);
			RevCommit commit = revWalk.parseCommit(CommitId);
			// and using commit's tree find the path
			RevTree tree = commit.getTree();
			TreeWalk treeWalk = new TreeWalk(repo);
			treeWalk.addTree(tree);
			treeWalk.setRecursive(true);
			treeWalk.setFilter(PathFilter.create(file));
			if (!treeWalk.next()) {
			  return null;
			}
			ObjectId objectId = treeWalk.getObjectId(0);
			ObjectLoader loader = repo.open(objectId);

			
			byte b[] = loader.getBytes();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.write(b);
			baos.close();
			return baos;
			
						
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;

	}
	
	
	

	@Override
	public ArrayList<String> fetchHighLevelRepositoryLayout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection retrieveProjectStructure(String... params) {
		// TODO Auto-generated method stub
		Collection<ISCMFileSystemElement> projectStructure = new ArrayList<>();
		
		
		String branch = (params != null && params.length > 0)? params[0]: "master";
		
		try {
			String currentbranchID = repository.getRepository().getFullBranch();
			repository.checkout().setName(branch).call();
			File root = repository.getRepository().getWorkTree();
			for (File f : root.listFiles())
				projectStructure.add(SCMRevTree(f));
			repository.checkout().setName(currentbranchID).call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return projectStructure;
		
		

	}


	private ISCMFileSystemElement SCMRevTree(File input){
		ISCMFileSystemElement output = (input.isFile()) ? ISCMFile.build(input.getPath(), input.getName()) : ISCMDirectory.build(input.getPath(), input.getName());
		ISCMDirectory dir = null;
		//Not to include .git directory
		if (input.isDirectory() && !input.getName().equals(".git")){
			dir = (ISCMDirectory) output; 
			for(File f: input.listFiles()) {
				dir.addChild(SCMRevTree(f));
			}
		}
		return output;
	}
	
	
	
	
	@Override
	public Hashtable<String, String> retrieveRevisionsDiff(String startRevision, String endRevision) {
		// TODO Auto-generated method stub
		//repository.diff().
		
		
		
		ObjectId previous = ObjectId.fromString( startRevision);
		ObjectId current = ObjectId.fromString( endRevision);
		RevCommit commitFrom = null, commitTo = null;
		RevWalk revWalk = new RevWalk( repository.getRepository() );
		try {
			commitFrom = revWalk.parseCommit( previous );
			commitTo = revWalk.parseCommit( current );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		revWalk.close();
		
		ObjectReader reader = repository.getRepository().newObjectReader();
		CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
		try {
			System.out.println("gitdiff");
			oldTreeIter.reset( reader, commitFrom.getTree() );
			CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
			newTreeIter.reset( reader, commitTo.getTree() );
			DiffFormatter diffFormatter = new DiffFormatter( System.out );
			diffFormatter.setRepository( repository.getRepository() );
			List<DiffEntry> entries = diffFormatter.scan( newTreeIter, oldTreeIter );
			diffFormatter.format( entries );
			diffFormatter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	
	//error catching isn't supported
	@Override
	public String getPreviousPath(String path, String revision) {
		// TODO Auto-generated method stub
		

		ObjectId current = ObjectId.fromString( revision);
		RevCommit prev = null, rev = null;
		RevWalk revWalk = new RevWalk( repository.getRepository() );
		try {
			rev = revWalk.parseCommit( current );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String prevRevision = rev.getParents()[0].getId().getName();
		
		ObjectId previous = ObjectId.fromString( prevRevision);
		try {
			prev = revWalk.parseCommit( previous );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		revWalk.close();		
		if (prev == null || rev == null) return null;
		
		return previousPathHelper(prev,rev,path);
	}
	
	
	private String previousPathHelper(RevCommit prev,RevCommit rev, String pathToAnalize){
		try( DiffFormatter diffFormatter = new DiffFormatter( DisabledOutputStream.INSTANCE ) ) {
			diffFormatter.setRepository( repository.getRepository() );
			List<DiffEntry> diffEntries = diffFormatter.scan( prev.getId(), rev.getId());
			for(int i = 0 ; i < diffEntries.size(); i++) {
				FileHeader fileHeader = diffFormatter.toFileHeader( diffEntries.get( i ) );
				if (fileHeader.getNewPath().compareTo(pathToAnalize) == 0){
					return (fileHeader.getOldPath().compareTo(FileHeader.DEV_NULL) == 0) ? null : fileHeader.getOldPath();
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}