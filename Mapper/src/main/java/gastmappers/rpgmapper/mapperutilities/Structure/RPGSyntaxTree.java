package gastmappers.rpgmapper.mapperutilities.Structure;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationManager;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

/**
 * Encargado de analizar todo el código fuente. Su función es crear las
 * declaraciones para generar un bloque con las declaraciones.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class RPGSyntaxTree {

	private GroupStatements statementContext;

	public RPGSyntaxTree() {

	}

	/**
	 * Método para generar el árbol a partir de la raíz.
	 * 
	 * @param parseTree árbol con la raíz.
	 */
	public void generateTree(ParseTree parseTree) {
		GroupStatements stateContext = new GroupStatements();
		SpecificationManager specificationManager = new SpecificationManager();
		int amountChildren = parseTree.getChildCount();
		for (int i = 0; i < amountChildren; i++) {
			ParseTree child = parseTree.getChild(i);
			String childClass = checkDTypeContext(child.getClass().getSimpleName());
			Statement statement = null;
			switch (childClass) {
			case "StatementContext":
				statement = specificationManager.createStatement(child.getChild(0));
				break;
			case "SubroutineContext":
			case "D_Context":
			case "ProcedureContext":
				statement = specificationManager.createStatement(child);
				break;
			default:
				break;
			}
			
			if (statement != null) {
				if (statement.getClass().getSimpleName().contentEquals("GroupStatements")) {
					GroupStatements groupStatements = (GroupStatements) statement;
					List<Statement> listStatements = groupStatements.getStatements();
					for (int j = 0; j < listStatements.size(); j++) {
						
						stateContext.addStatement(listStatements.get(j));
					}
				} else {
					
					stateContext.addStatement(statement);
				}
			}
		}
		this.statementContext = stateContext;
	}

	private String checkDTypeContext(String className) {
		if (className.charAt(0) == 'D' && className.contains("Context")) {
			return "D_Context";
		} else {
			return className;
		}
	}

	public GroupStatements getStatementContext() {
		return statementContext;
	}

}
