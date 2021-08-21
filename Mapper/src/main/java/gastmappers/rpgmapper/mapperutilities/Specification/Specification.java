package gastmappers.rpgmapper.mapperutilities.Specification;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

/**
 * Clase abstracta para los tipos de hoja.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public abstract class Specification {

	private SpecificationEnum specificationType;

	/**
	 * Constructor del tipo de hoja
	 * 
	 * @param specificationType
	 */
	public Specification(SpecificationEnum specificationType) {
		this.specificationType = specificationType;
	}

	/**
	 * Método para obtener el tipo de hoja
	 * 
	 * @return
	 */
	public SpecificationEnum getSpecificationType() {
		return this.specificationType;
	}

	/**
	 * Construir los Statement según el tipo de hoja
	 * 
	 * @param parseTree subárbol con la información
	 * @return Statement con la declaración
	 */
	public abstract Statement constructStatement(ParseTree parseTree);

	public abstract Statement constructSubroutine(ParseTree parseTree);

	/**
	 * Se obtiene el caracter de la hoja.
	 * 
	 * @param parseTree árbol donde está la estructura a analizar
	 * @return un único caracter con el tipo de hoja
	 */
	public static String getSpecificationLetter(ParseTree parseTree) {
		// Las operaciones de FREE se consideran de tipo C
		String className = parseTree.getClass().getSimpleName();
		
		if (className.contentEquals("FreeContext")
				|| ((className.contains("Begin") || className.contains("OnError")) && className.contains("Context"))
				|| (parseTree.getChildCount()>0 && parseTree.getChild(0).getClass().getSimpleName().contentEquals("FreeContext"))) {
			return "C";
		} else {
			return (parseTree.getChildCount()>0)?parseTree.getChild(0).getText().substring(0, 1):parseTree.getText().substring(0,1);
		}
	}
}
