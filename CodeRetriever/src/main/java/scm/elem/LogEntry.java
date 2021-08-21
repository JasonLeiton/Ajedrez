package scm.elem;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class LogEntry {

	private String commitId = "";	
	private TimeZone commitTimeZone;
	private Date commitTime;
	private String authorName= "";
	private String authorEmail = "";
	private String commitMessage = "";
	
	private String previousCommitId = "";
	private String typeBranch = "";
	private String branchName = "";
	
	private ArrayList<ChangedFile> changedFiles = new ArrayList<ChangedFile>();
	private ArrayList<String> changedPaths = new ArrayList<String>();
	
	public LogEntry() {}
	
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	public String getCommitId() {
		return commitId;
	}
	//SVN no lo tiene
	public void setCommitTimeZone(TimeZone commitTimeZone) {
		this.commitTimeZone = commitTimeZone;
	}
	
    public TimeZone getCommitTimeZone() {
		return commitTimeZone;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
    
	public Date getCommitTime() {
		return commitTime;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}	
	
	public String getAuthorName() {
		return authorName;
	}	
	// SVN no lo tiene
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}

	public String getCommitMessage() {
		return commitMessage;
	}	

	//Falta
	public void setChangedFiles(ArrayList<ChangedFile> changedFiles) {
		this.changedFiles = changedFiles;
	}

	public ArrayList<ChangedFile> getChangedFiles() {
		return changedFiles;
	}
	
	public void setChangedPaths(ArrayList<String> changedPaths) {
		this.changedPaths = changedPaths;
	}
	
	public ArrayList<String> getChangedPaths() {
		return changedPaths;
	}	
	  
	public String getPreviousCommitId() {
		return this.previousCommitId;
	}
		
	public void setPreviousCommitId(String previousCommitId) {
		this.previousCommitId = previousCommitId;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}	
	
	public String getBranchName() {
		return this.branchName;
	}

	public String getTypeBranch() {
		return typeBranch;
	}

	public void setTypeBranch(String typeBranch) {
		this.typeBranch = typeBranch;
	}
	
	public static void main(String[] args) {
		System.out.println("le");
	}
	
}
