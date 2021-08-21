package miner.svn;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import scm.SCM;
import scm.elem.ISCMFileSystemElement;

public class ProjectStructureGit extends IProjectStructure{

	public ProjectStructureGit(String[] fileExtensionFilter) {
		super(fileExtensionFilter);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void listEntries(String path, String project, SCM scm) {
		// TODO Auto-generated method stub
		boolean            retry           =   true;
        Stack<String>    theStack1     =   new Stack<String>();
        String              projectPath  =   "";
        String              os              =   System.getProperty("os.name");
        if(!os.contains("Mac")){
                projectPath        =   "C:/Repository/" + project + "/";
        }else if(os.contains("Mac")){
                    projectPath        =   "/Repository/" + project + "/";
        }

        @SuppressWarnings("unchecked")
        Collection<ISCMFileSystemElement>     entries1    =   scm.retrieveProjectStructure(path);
        Iterator<ISCMFileSystemElement>        iterator1  =   entries1.iterator();

        while(iterator1.hasNext()){
        	ISCMFileSystemElement entry = iterator1.next();
                String  fileName   =   projectPath + entry.getName();
                File    file         =   new File(fileName);
                if((entry.isFile()) && appropiatedFileExtension(fileName)) {
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        Logger.getLogger(ProjectStructure.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(entry.isDirectory()){
                            theStack1.push(entry.getName());
                            file.mkdirs();
                }
        } // end while

        while( !theStack1.isEmpty()){
                retry   =   true;
                String  path1       =   theStack1.pop();
                while(retry){
                        try{
                            @SuppressWarnings("unchecked")
                            Collection<ISCMFileSystemElement>      entries2   =   scm.retrieveProjectStructure(path1);
                            Iterator<ISCMFileSystemElement>        iterator2   =   entries2.iterator();
                            while(iterator2.hasNext()){
                            	ISCMFileSystemElement entry = iterator2.next();
                                        String  fileName    = null;
                                        if(!os.contains("Mac")){
                                            fileName  =   projectPath + path1 + "/" + entry.getName();
                                        }else if(os.contains("Mac")){
                                                    fileName  =   projectPath + path1 + "/" + entry.getName();
                                        }

                                        File    file        =   new File(fileName);
                                        if(entry.isFile() && (appropiatedFileExtension(fileName))) {
                                                file.createNewFile();
                                        }else if(entry.isDirectory()){
                                                    theStack1.push(path1 + "/" + entry.getName());
                                                    file.mkdirs();
                                        }
                            } // end while
                            retry   =   false;
                        }catch (IOException ex) {
                                    retry   =   true;
                                    Logger.getLogger(ProjectStructure.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
        } // end while
        cleanDirectory(new File(projectPath));
		
		
	}
	
	
    private void cleanDirectory(File root){
        Stack<String> theStack = new Stack<String>();
        theStack.push(root.getAbsolutePath());
        while( !theStack.isEmpty()){
                File file = new File(theStack.pop());
                if(file.isDirectory()){
                        File[] files = file.listFiles();
                        if((files.length > 0)){
                            for(int i = 0; i < files.length; i++){
                                    theStack.push(files[i].getAbsolutePath());
                            }
                        }else{
                            file.delete();
                        }
                }
        } // end while
 }

}
