package scm.elem;

public class ISCMFileHasNotChilds extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "File Has not Childs, use isLeaf method to avoid this error!";
	}
	
	
	
	
}
