package gastvalidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Crea el hashmap agregando los datos del archivo JSON.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class HashMapDictionary {
	Map<String, ArrayList<String>> hashMapDictionary;

	/**
	 * Toma los valores del diccionario y crea un hashmap.
	 * 
	 * @param dictionary Lista con la llave y los valores.
	 */
	public HashMapDictionary(List<ElementDictionary> dictionary) {
		this.hashMapDictionary = new HashMap<String, ArrayList<String>>();
		for (ElementDictionary e : dictionary) {
			this.hashMapDictionary.put(e.getASTMName(), e.getASTName());
		}
	}

	/**
	 * Obtener el hashmap generado mediante el diccionario.
	 * 
	 * @return Un hashmap que recibe una llave y devuelve un vector.
	 */
	public Map<String, ArrayList<String>> getHashMapDictionary() {
		return this.hashMapDictionary;
	}
}
