package gastvalidator;

import java.util.ArrayList;

/**
 * Modelo del objeto del JSON con el diccionario. En el JSON busca los vectores
 * AST y los asocia a la variable AST el String ASTM.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class ElementDictionary {
	private ArrayList<String> AST;
	private String ASTM;

	/**
	 * El diccionario presenta un vector bajo el nombre <i>AST</i>. Por lo que crea
	 * una lista con los nombres de los métodos.
	 * 
	 * @return Lista con métodos que se encuentran en el nombre <i>AST</i>.
	 */
	public ArrayList<String> getASTName() {
		return this.AST;
	}

	/**
	 * El diccionario presenta una etiqueta bajo el nombre <i>ASTM</i>.
	 * 
	 * @return Nombre de un método asociado a la palabra <i>ASTM</i>.
	 */
	public String getASTMName() {
		return this.ASTM;
	}
}