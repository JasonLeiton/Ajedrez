package scm.elem;

import java.util.ArrayList;
import java.util.Collection;

public class ISCMDirectory implements ISCMFileSystemElement{

	
	private Collection<ISCMFileSystemElement> childs;
	private String path,name;
	
	private ISCMDirectory(String path, String name) {
		// TODO Auto-generated constructor stub
		childs = new ArrayList<>();
		this.path = path;
		this.name = name;
	}
	
	public static ISCMDirectory build(String path,String name){
		return new ISCMDirectory(path,name);
	}
	
	
	
	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	@Override
	public boolean isFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDirectory() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<ISCMFileSystemElement> childs() throws ISCMFileHasNotChilds {
		// TODO Auto-generated method stub
		return childs;
	}
	
	public void addChild(ISCMFileSystemElement fse) throws NullPointerException{
		if (fse == null) throw new NullPointerException("Inserted child is a null value");
		childs.add(fse);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
	
}
