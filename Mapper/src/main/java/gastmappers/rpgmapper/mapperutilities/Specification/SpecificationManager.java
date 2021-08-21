package gastmappers.rpgmapper.mapperutilities.Specification;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;
import gastmappers.rpgmapper.mapperutilities.Structure.Statements.Context.StatementContextBuilder;

/**
 * Administra las declaraciones que están presente en la hoja.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class SpecificationManager {

	public SpecificationManager() {

	}

	/**
	 * Crea las declaraciones.
	 * 
	 * @param parseTree Subárbol con datos.
	 * @return Declaración con las instrucción.
	 */
	public Statement createStatement(ParseTree parseTree) {
		if (parseTree == null){
			return null;
		}
		String className = unifyClassNames(parseTree.getClass().getSimpleName());
		// Lleva un proceso dependiendo de la clase que tiene el nodo.
		switch (className) {
		// Caso especial donde es un bloque de código. El código se debe analizar
		case "BlockContext":
			// Se baja un nivel en caso de que solo haya un hijo
			if (parseTree.getChildCount() == 1) {
				return analyzeBlockStatement(parseTree.getChild(0));
			} else {
				return analyzeBlockStatement(parseTree);
			}
			// Caso general para analizar la sentencia
		case "SubroutineContext":
			return Statement.generateSubroutineStatement(parseTree);
		case "specification":
		case "ProcedureContext":
		case "FreeContext":
		case "DspecContext":
			return Statement.generateStatement(parseTree, parseTree);
		case "SubprocedurestatementContext":
			ParseTree subProcedureStatement = parseTree.getChild(0).getChild(0);
			if(subProcedureStatement.getClass().getSimpleName().contentEquals("BegsrContext")) {
				subProcedureStatement = subProcedureStatement.getParent();
			}else if(subProcedureStatement.getClass().getSimpleName().contentEquals("BlockContext")) {
				return createStatement(subProcedureStatement);
			}
			return Statement.generateStatement(subProcedureStatement, subProcedureStatement);
			
		default:
			break;
		}
		return null;
	}

	private String unifyClassNames(String className) {
		if (className.contains("_") && className.contains("Context") && !className.contains("Op_")) {
			return "specification";
		} else {
			return className;
		}
	}

	/**
	 * Método para analizar casos especiales cuando es un bloque de declaraciones.
	 * 
	 * @param parseTree subárbol con los diferentes elementos.
	 * @return
	 */
	private Statement analyzeBlockStatement(ParseTree parseTree) {
		return new StatementContextBuilder().createStatement(parseTree);
	}

}
