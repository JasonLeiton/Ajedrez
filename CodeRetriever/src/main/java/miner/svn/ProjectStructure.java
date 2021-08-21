package miner.svn;


import scm.SCM;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNNodeKind;



public class ProjectStructure{

//    private     DAOFactory  daoFactory =    DAOFactory.getInstance(DAOFactory.MYSQL);

//    public void storePackage(String repository) throws InstantiationException{
//        try {
//            DBMetrics   dbMetrics   =   new DBMetrics();
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            Connection  connection  =   DriverManager.getConnection("jdbc:mysql://localhost/softwarerepository", "root", "Chifero1");
//            String      sPackage    =
//            inserPackages(Connection connection, DBMetrics dbMetrics, int projectId, String sPackage, int parent, int children, int percentage, int revision)
//            int cFiles = ProjectFile.insertFiles(connection, dbMetrics, projectId, fileName, file, dir, revision);
//        } catch (SQLException ex) {
//            Logger.getLogger(ProjectStructure.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(ProjectStructure.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ProjectStructure.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void readRepositoryStructure(String strProject, SCM scmSVNDAO){
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

                         listEntries("", strProject, scmSVNDAO);
                    } // end if
                } // end if
    }
    
private void listEntries(String path, String project, SCM scmSVNDAO){
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
            Collection<SVNDirEntry>     entries1    =   scmSVNDAO.retrieveProjectStructure(path);
            Iterator<SVNDirEntry>        iterator1  =   entries1.iterator();

            while(iterator1.hasNext()){
                    SVNDirEntry entry = iterator1.next();
                    String  fileName   =   projectPath + entry.getName();
                    File    file         =   new File(fileName);
                    if((entry.getKind() == SVNNodeKind.FILE) && (fileName.endsWith(".java"))) {
                        try {
                            file.createNewFile();
                        } catch (IOException ex) {
                            Logger.getLogger(ProjectStructure.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else if(entry.getKind() == SVNNodeKind.DIR){
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
                                Collection<SVNDirEntry>      entries2   =   scmSVNDAO.retrieveProjectStructure(path1);
                                Iterator<SVNDirEntry>        iterator2   =   entries2.iterator();
                                while(iterator2.hasNext()){
                                            SVNDirEntry entry = iterator2.next();
                                            String  fileName    = null;
                                            if(!os.contains("Mac")){
                                                fileName  =   projectPath + path1 + "/" + entry.getName();
                                            }else if(os.contains("Mac")){
                                                        fileName  =   projectPath + path1 + "/" + entry.getName();
                                            }

                                            File    file        =   new File(fileName);
                                            if((entry.getKind() == SVNNodeKind.FILE) && (fileName.contains(".java"))) {
                                                    file.createNewFile();
                                            }else if(entry.getKind() == SVNNodeKind.DIR){
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