package miner.svn;


import java.io.File;

import scm.SCM;

public abstract class IProjectStructure {
	
	/*.java, .py, cpp etc, etc*/
	protected String[] fileExtensionFilter;
	
	public IProjectStructure(String [] fileExtensionFilter){
		this.fileExtensionFilter = fileExtensionFilter;
	}
	
	public void readRepositoryStructure(String strProject, SCM scm){
        String      name = null;
        String os   =   System.getProperty("os.name");
        if(!os.contains("Mac")){
            name        =   "C:/" + "Repository";
        }else if(os.contains("Mac")){
                    name        =   "/Repository";
        }
        File        f           =   new File(name);
        if(f.isDirectory()){
            if(!(f.list().length > 0)){

                 listEntries("", strProject, scm);
            } // end if
        } // end if
	}
	
	public boolean appropiatedFileExtension(String filename){
		
		for(String fileExtension:fileExtensionFilter){
			if (filename.endsWith("." + fileExtension)) return true;
		}
		
		return false;
		
	}
	
	abstract protected void listEntries(String path, String project, SCM scmSVNDAO);
}
