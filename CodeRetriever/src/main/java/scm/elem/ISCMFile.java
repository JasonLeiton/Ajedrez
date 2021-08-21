package scm.elem;

import java.util.Collection;

public class ISCMFile implements ISCMFileSystemElement{
	
	private String path,name;
	
	private ISCMFile(String path,String name){
		this.path = path;
		this.name = name;
	}
	
	public static ISCMFile build(String path, String name){
		return new ISCMFile(path,name);
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	@Override
	public boolean isFile() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isDirectory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<ISCMFileSystemElement> childs() throws ISCMFileHasNotChilds {
		// TODO Auto-generated method stub
		throw new ISCMFileHasNotChilds();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
