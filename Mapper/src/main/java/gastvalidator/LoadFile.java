package gastvalidator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * Encargado de leer y generar el diccionario a partir del archivo.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class LoadFile {

	static final String DIRECTORY_LOCATION = ".\\src\\main\\java\\gastvalidator\\dictionaries\\";
	private List<Dictionary> dictionaries = new ArrayList<Dictionary>();

	/**
	 * Lee los archivos y crea un diccionario por archivo que encuentre en la
	 * carpeta correspondiente.
	 * 
	 * @return Lista de diccionarios con archivos encontrados en el directorio.
	 */
	public List<Dictionary> readFile() {
		File directory = new File(DIRECTORY_LOCATION);
		File[] listFiles = directory.listFiles();
		for (File file : listFiles) {
			if (file.isFile()) {
				// Creacion de diccionario a partir de la direccion del archivo
				Dictionary dictionary = createDictionary(file.getAbsolutePath());
				this.dictionaries.add(dictionary);
			}
		}
		return this.dictionaries;
	}

	/**
	 * Crea un diccionario de un archivo dentro del directorio.
	 * 
	 * @param url ruta absoluta del archivo.
	 * @return Objeto de tipo <i>Dictionary</i> con la información del JSON.
	 */
	private Dictionary createDictionary(String url) {
		Gson gson = new Gson();
		Dictionary dictionary = new Dictionary();
		try (Reader reader = new FileReader(url)) {
			// Se asocia un json con un objeto diccionario para retornar un objeto
			dictionary = gson.fromJson(reader, Dictionary.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dictionary;
	}
}
