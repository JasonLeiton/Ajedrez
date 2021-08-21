
/*******************************************************************************
*
* @date   : 31-mar-2010, 10:15:53
* @file   : ArrowWidget
* @author : Antonio Gonzalez Torres
* Contents:
*
* Purpose :
*
*
*******************************************************************************/

package dao.scmdao.svn;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperty;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.diff.SVNDiffWindow;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.SVNCommitInfo;

public class PropFetchingEditor implements ISVNEditor {
        private Stack<String>                                         myDirectoriesStack    = new Stack<String>();
        private Map<String, Map<String, SVNPropertyValue>>  myDirProps              = new HashMap<String, Map<String, SVNPropertyValue>>();
        private Map<String, Map<String, SVNPropertyValue>>  myFileProps             = new HashMap<String, Map<String, SVNPropertyValue>>();
        
        public void abortEdit() throws SVNException {}
        public void absentDir(String path) throws SVNException {}
        public void absentFile(String path) throws SVNException {}
        public void addFile(String path, String copyFromPath, long copyFromRevision) throws SVNException {}
        public void closeFile(String path, String textChecksum) throws SVNException {}
        public void deleteEntry(String path, long revision) throws SVNException {}
        public void openFile(String path, long revision) throws SVNException {}
        public void targetRevision(long revision) throws SVNException {}
        public void applyTextDelta(String path, String baseChecksum) throws SVNException {}
        public void textDeltaEnd(String path) throws SVNException {}

        public SVNCommitInfo closeEdit() throws SVNException { return null; }

        public OutputStream textDeltaChunk(String path, SVNDiffWindow diffWindow) throws SVNException {
            return null;
        }

        public void addDir(String path, String copyFromPath, long copyFromRevision) throws SVNException {
            String absouluteDirPath = "/" + path;
            myDirectoriesStack.push(absouluteDirPath);
        }

    @SuppressWarnings("unchecked")
        public void changeDirProperty(String name, SVNPropertyValue value) throws SVNException {
            //filter out svn:entry and svn:wc properties since we are interested in regular properties only
            if (!SVNProperty.isRegularProperty(name)) {
                return;
            }

            String                                  currentDirPath  = myDirectoriesStack.peek();
            Map<String, SVNPropertyValue>   props              = myDirProps.get(currentDirPath);
            if (props == null) {
                props = new HashMap<String, SVNPropertyValue>();
                myDirProps.put(currentDirPath, props);
            }
            props.put(name, value);
        }

        public void changeFileProperty(String path, String propertyName, SVNPropertyValue propertyValue) throws SVNException {
                //filter out svn:entry and svn:wc properties since we are interested in regular properties only
                if (!SVNProperty.isRegularProperty(propertyName)) {
                    return;
                }

                String absolutePath = "/" + path;
                Map<String, SVNPropertyValue> props =  myFileProps.get(absolutePath);
                if (props == null) {
                    props = new HashMap<String, SVNPropertyValue>();
                    myFileProps.put(absolutePath, props);
                }
                props.put(propertyName, propertyValue);
        }

        public void closeDir() throws SVNException {
            myDirectoriesStack.pop();
        }

    @SuppressWarnings("unchecked")
        public void openDir(String path, long revision) throws SVNException {
            String absoluteDirPath = "/" + path;
            myDirectoriesStack.push(absoluteDirPath);
        }

    @SuppressWarnings("unchecked")
        public void openRoot(long revision) throws SVNException {
            String absoluteDirPath = "/";  
            myDirectoriesStack.push(absoluteDirPath);
        }

        public Map<String, Map<String, SVNPropertyValue>> getDirsToProps() {
                return myDirProps;
        }
        
        public Map<String, Map<String, SVNPropertyValue>> getFilesToProps() {
                return myFileProps;
        }
    }