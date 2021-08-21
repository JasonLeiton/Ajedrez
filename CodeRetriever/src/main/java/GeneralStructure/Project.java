package GeneralStructure;

import java.awt.HeadlessException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.eclipse.jdt.core.dom.ThisExpression;

import com.google.gson.JsonArray;

public class Project {
	private String name;
	private Date creationDate;
	private String owner;
	private String version;
	private String listFiles;

	public String GetData(String list) throws HeadlessException, ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		this.listFiles = list;
		this.name = JOptionPane.showInputDialog("Ingrese el nombre del proyecto");
		this.creationDate = format
				.parse(JOptionPane.showInputDialog("Ingrese la fecha de creación. Ejemplo 19/8/2001"));
		this.owner = JOptionPane.showInputDialog("Ingrese el dueño del proyecto");
		this.version = JOptionPane.showInputDialog("Ingrese la versión del proyecto");
		
		String json = "{\"name\":" + name + "," + "\"creationDate\":" + creationDate.toString() + "," + "\"owner\":"
				+ owner + "," + "\"version\":"+version+","+"\"listFiles\":"+list + "}";
		System.out.println("Está llegando");
		return json;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getListFiles() {
		return listFiles;
	}

	public void setListFiles(String listFiles) {
		this.listFiles = listFiles;
	}

}
