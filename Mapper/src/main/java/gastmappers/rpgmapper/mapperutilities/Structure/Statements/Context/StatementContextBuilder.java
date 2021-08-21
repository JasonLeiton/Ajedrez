package gastmappers.rpgmapper.mapperutilities.Structure.Statements.Context;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

/**
 * Clase para extraer y formar las instrucciones dentro de un for, if, etc.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class StatementContextBuilder {

	/**
	 * Crea la declaración dependiendo del opcode. Se hacen dos casos para enviar
	 * también el cuerpo de la instrucción (IF, FOR). Si no son de este tipo, se
	 * ignora esa condición.
	 * 
	 * @param parseTree
	 * @return
	 */
	public Statement createStatement(ParseTree parseTree) {
		String className = parseTree.getClass().getSimpleName();
		ParseTree statementParseTree = parseTree;
		statementParseTree = parseTree.getChild(0);

		if (className.contentEquals("StatementContext")) {
			parseTree = parseTree.getChild(0);
		}

		return Statement.generateStatement(statementParseTree, parseTree);
	}

}
