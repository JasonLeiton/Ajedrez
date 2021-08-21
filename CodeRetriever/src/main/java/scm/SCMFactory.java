package scm;




public abstract class SCMFactory {
    // List of DAO types supported by the factory
    public static final int SVN     = 1;
    public static final int GIT     = 2;
    public static final int TFS     = 3;

    public static SCMFactory getInstance(int whichFactory) {
            switch (whichFactory) {
                      case SVN: return new SVNDataAccessObject();
                      case GIT: return new GitDataAccessObject();
                      case TFS: return new TFSDataAccessObject();                      	
                      default: return null;
            }
    }
    
    public abstract void setupConnection(String server, String user, String password);
    public     abstract SCM getSCM();
    public abstract String getAbsoluteTempPath ();
}


