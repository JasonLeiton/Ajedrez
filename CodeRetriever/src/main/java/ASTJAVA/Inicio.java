package ASTJAVA;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ASTMCore.ASTMSource.CompilationUnit;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;


import GeneralStructure.Project;

import gastmappers.Mapper;

/**
 * Esta clase es la encargada de iniciar el proceso de traducción, ya que es
 * aquí donde se establece la ruta de los archivos que se desean procesar,
 * además es quien se encarga de obtener los datos de los archivos, por lo que
 * utiliza JDT. También se llama a la clase para mapear el AST de Java.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */

public class Inicio {

	/**
	 * Este atributo es para almacenar la ruta donde se localiza los archivos que se
	 * desean analizar
	 */
	private String inputPath, translationFilePath;
	private int cantidad;
	private static ArrayList<String> parsedFileList;
	private static ArrayList<ASTMCore.ASTMSource.CompilationUnit> compilationUnitList;

	private final Mapper mapper;

	// private String differencesList;

	/**
	 * Establece el directorio de los archivos
	 * 
	 * @param inputPath raiz donde se encuentran los archivos de código .java
	 * @throws IOException
	 */
	public Inicio(String inputPath, String outputPath, Mapper mapper) throws IOException {
		this.inputPath = inputPath;
		this.translationFilePath = outputPath + "\\result.json";
		parsedFileList = new ArrayList<String>();
		compilationUnitList = new ArrayList<ASTMCore.ASTMSource.CompilationUnit>();

		this.mapper = mapper;
	}

	/**
	 * 
	 * @return retorna el valor de la ruta establecida
	 */
	public String getPath() {
		return inputPath;
	}

	/**
	 * establece un valor al directorio de los archivos
	 * 
	 * @param path Directorio de los archivos
	 */
	public void setPath(String path) {
		this.inputPath = path;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList getParsedFileList() {
		return parsedFileList;
	}

	public void setParsedFileList(ArrayList parsedFileList) {
		this.parsedFileList = parsedFileList;
	}

	/**
	 * Se hace la obtención de todos los archivos contenidos en el directorio.
	 * 
	 * @param path ruta absoluta del directorio.
	 * @throws IOException               Puede ocurrir una excepción en la lectura
	 *                                   de los archivos.
	 * @throws IllegalAccessException    si se accede a un método que no se tienen
	 *                                   los permisos.
	 * @throws IllegalArgumentException  si se invoca un método y los parámetros
	 *                                   asociados no corresponden a los que el
	 *                                   método recibe.
	 * @throws InvocationTargetException si se invoca un método y al parámetro se
	 *                                   encuentra incompleto.
	 * @throws NoSuchMethodException     si se invoca un método que no existe en el
	 *                                   objeto.
	 * @throws SecurityException         si se trata de acceder a un valor
	 *                                   protegido.
	 */
	public void ParseFilesInDir(String path) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		File dirs = new File(path);
		String dirPath = dirs.getCanonicalPath() + File.separator;

		File root = new File(dirPath);
		File[] files = root.listFiles();
		String filePath = null;
		for (File f : files) {
			filePath = f.getAbsolutePath();
			if (f.isFile() && filePath.contains(".java")) {
				System.out.println(filePath + " " + parsedFileList.size());

				parse(filePath);

			} else if (f.isDirectory()) {
				// System.out.println(filePath);
				ParseFilesInDir(filePath);
			}

		}

		// System.out.println("Parseo finalizado");

	}

	/**
	 * Obtiene el texto de un archivo .java en un string.
	 * 
	 * @param filePath Ruta del archivo
	 * @return el contenido del archivo en un string
	 * @throws IOException en la lectura del contenido de los archivos
	 */
	// este metodo toma la lineas del archivo de la ruta y lo pasa a string
	public String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			// System.out.println(numRead);
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);// va agregando lo que va leyendo
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

	/**
	 * Aquí se parsea el código y se obtine el AST de Java, para posteriormente
	 * llamar a la clase de mapeo entre el SAST y el GAST y por último lo convierte
	 * a JSON por medio Gson para mostrarlo en formato JSON.
	 *
	 * @param filePath Ruta absoluta del archivo con código fuente.
	 * @throws IllegalAccessException    si se accede a un método que no se tienen
	 *                                   los permisos.
	 * @throws IllegalArgumentException  si se invoca un método y los parámetros
	 *                                   asociados no corresponden a los que el
	 *                                   método recibe.
	 * @throws InvocationTargetException si se invoca un método y al parámetro se
	 *                                   encuentra incompleto.
	 * @throws NoSuchMethodException     si se invoca un método que no existe en el
	 *                                   objeto.
	 * @throws SecurityException         si se trata de acceder a un valor
	 *                                   protegido.
	 * @throws IOException               si ocurre un problema en la lectura o
	 *                                   escritura de un archivo.
	 */
	// use ASTParse to parse string
	@SuppressWarnings("unchecked")
	public void parse(String filePath) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		Gson json = new Gson(); // biblioteca para usar objetos Json

		// Parse the file.
		ArrayList<ASTMCore.ASTMSource.CompilationUnit> gastCompilationUnits;
		gastCompilationUnits = mapper.getGastCompilationUnit(filePath);

		for (CompilationUnit compilationUnit : gastCompilationUnits) {

			// Lo que hacian antes el proyecto de Jason
			compilationUnitList.add(compilationUnit);
			String resultado = json.toJson(compilationUnit).replaceAll("null,", "");// ASTM, quitando los nulos
			parsedFileList.add(resultado);

//			// Crea el validador con las dos estructuras generadas.
//			 ASTMStructureValidator validator = new ASTMStructureValidator(cu, comp, filePath);
//			 try {
//			 	ArrayList<DifferenceLog> differencesListResult = validator.compareCompilationUnits();
//			 	if (differencesListResult.size() > 0) {
//			 		Inicio.differencesList = differencesListResult;
//			 	}
//			 } catch (Exception e) {
//			 	System.out.println("Error en " + filePath + " " + e);
//			 }

		}

		// System.out.println(resultado);
	}


	public void WriteTranslation() throws IOException, HeadlessException, ParseException {
		Gson json = new Gson();
		// JsonParser parser= new JsonParser();
		Project project = new Project(); // estructura básica de un proyecto, con los datos generales
		String translation = parsedFileList.toString();// obtiene los datos generales del proyecto
		// parsedFileList=null;
		/// translation="{"+"\"files\":"+translation+"}";
		writeInFile(translation, this.translationFilePath, false);
		// SaveCodeTranslated();// envía los datos a la base de datos
	}



	/**
	 * Función genérica para escribir en un archivo.
	 * 
	 * @param text        mensaje que se desea escribir en el documento.
	 * @param urlFile     dirección exacta del documento.
	 * @param flagAddText true: se escribe al final del documento, false: se
	 *                    sobreescribe en el documento.
	 * @throws IOException si ocurre un problema en la lectura o escritura de un
	 *                     archivo.
	 */
	private void writeInFile(String text, String urlFile, boolean flagAddText) throws IOException {
		FileWriter file = new FileWriter(urlFile, flagAddText);
		file.write(text);
		file.flush();
		file.close();
	}

	public void SaveCodeTranslated() throws IOException {
		/////////////// conexión//////////////////////
		File file = new File(this.translationFilePath);
		String server = "127.0.0.1";
		int port = 27017;
		String dataBaseName = "DataBaseCode";
		MongoClient client = new MongoClient(server, port);
		DB dataBase = client.getDB(dataBaseName);

		GridFS codeTraslated = new GridFS(dataBase, "codeTraslated");
		GridFSInputFile readyFile = codeTraslated.createFile(file);
		readyFile.setFilename(JOptionPane.showInputDialog("Ingrese el nombre de proyecto"));
		System.out.println("Insertando...");
		readyFile.save();
		JOptionPane.showMessageDialog(null,
				"Datos almacenados en la base: " + dataBaseName + "y colección: codeTraslated");
		client.close();
	}

}
