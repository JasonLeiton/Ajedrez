package scm.elem;

import java.util.Collection;

public interface ISCMFileSystemElement {
	
	public String getName();
	public String getPath();
	public boolean isFile();
	public boolean isDirectory();
	public Collection<ISCMFileSystemElement> childs() throws ISCMFileHasNotChilds;
	
	
	public static void main(String[] args) {
		System.out.println("hello");
	}
	
}
