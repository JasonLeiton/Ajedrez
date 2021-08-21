package ASTMCore;

import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

/**
 * Esta clase es la raiz del GAST y de la cual heredan todos los demás
 * elementos.
 * 
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class GASTMObject {
	/**
	 * Este atributo es para asignarle un identificador a cada elemento del código
	 * analizado.
	 */
	private static long currentid = 0L;
	private long id;
	private String tag;
	private ArrayList<String> subTags = new ArrayList<>();

	public void addSubTag(String subTag) {
		subTags.add(subTag);
	}

	/**
	 * Obtiene el identificador del elemento
	 * 
	 * @return El id del elemento.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Constructor que aumenta en uno el identificador, con el fin de que no se
	 * repitan.
	 */
	public GASTMObject() {
		id = currentid++;
		// tag="tag";
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public abstract void accept(GASTVisitor v);

}
