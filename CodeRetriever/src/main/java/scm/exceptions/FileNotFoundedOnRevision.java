package scm.exceptions;

import java.io.FileNotFoundException;

public class FileNotFoundedOnRevision extends FileNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2064450447856253026L;
	private String revision;
	
	/**
	 * FileNotFoundedOnRevision. Is the constructor method and sets the revision of the filename didn't find.
	 * @param revision the revision of the version controlled project.
	 */
	public FileNotFoundedOnRevision(String revision) {
		// TODO Auto-generated constructor stub
		this.revision = revision;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage() + ", at Revision " + revision;
	}

}
