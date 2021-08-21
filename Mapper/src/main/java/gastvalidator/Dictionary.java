package gastvalidator;

import java.util.List;

/**
 * Crea el objeto diccionario y permite acceder a sus valores.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class Dictionary {
	private String language;
	private List<ElementDictionary> dictionary;

	/**
	 * Obtiene el lenguaje de programación del diccionario.
	 * 
	 * @return nombre del lenguaje de programación.
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Obtiene una lista de cada uno de los elementos en el JSON que corresponden al
	 * diccionario.
	 * 
	 * @return Lista de pares de la llave del ASTM con los correspondientes en el
	 *         AST.
	 */
	public List<ElementDictionary> getDictionary() {
		return this.dictionary;
	}

	/**
	 * Imprimir todos los valores de la lista del diccionario.
	 */
	public void printDictionary() {
		System.out.println("LANGUAGE: " + this.language);
		for (ElementDictionary element : dictionary) {
			System.out.println("AST " + element.getASTName() + " ASTM " + element.getASTMName());
		}
	}
}
