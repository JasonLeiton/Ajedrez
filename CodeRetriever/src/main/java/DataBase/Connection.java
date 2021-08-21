package DataBase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connection {

	private static  String server="127.0.0.1";
	private static int port=27017;
	private static String dataBaseName="DataBaseCode";
	private MongoClient client;
	private MongoDatabase dataBase;
	public Connection() {
		try {
		this.client =new MongoClient(server,port);
		dataBase= client.getDatabase(dataBaseName);
	}
		catch (Exception e) {
			System.out.println("Error al conectar con el servidor: "+server+" en el puestro: " +port);
			// TODO: handle exception
		}
		
	}
	public void InsertCode(ArrayList<String> code) {
		try {
		
		MongoCollection colection=dataBase.getCollection("codeTraslated");
		//BasicDBObject document= BasicDBObject.parse(code);
		//System.out.println("entra"+code.length());
		//colection.insertOne(new Document().parse(code));
		Document document=new Document("files", code);
		System.out.println("Insertando...");
		colection.insertOne(document);
		JOptionPane.showMessageDialog(null,"Datos almacenados en la base: "+ dataBaseName+ "y colección: codeTraslated");
		}
		catch (Exception e) {
			System.out.println("error al guardar los datos");
		}
	}
	
	public void GetAllNameClasses() {
		System.out.println("consultando la base de datos");
	}
	
}
