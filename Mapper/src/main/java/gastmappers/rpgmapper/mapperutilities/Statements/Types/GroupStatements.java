package gastmappers.rpgmapper.mapperutilities.Statements.Types;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;


public class GroupStatements extends Statement {

	private List<Statement> statements;

	public GroupStatements() {
		statements = new ArrayList<Statement>();
		this.setType("Group Statements");
	}
	/**
	 * Método que agrega statements a la lista de statements que representan las instrucciones obtenidas del árbol
	 * Obtiene los indicadores de los CTypeStatements y los agrega a la lista principal
	 * 
	 * @param statement Statement que representa una instrucción del árbol de RPG
	 */
	public void addStatement(Statement statement) {
		if(statement != null){
			this.statements.add(statement);
			if (statement.getClass().getSuperclass().getSimpleName().equals("CTypeStatement")) {
				CTypeStatement statementAux = (CTypeStatement) statement;
				List<Statement> listaIndicadores =  statementAux.getIndicatorsStatements();
				if (listaIndicadores != null) {
					for (Statement indicador : listaIndicadores) {
						this.statements.add(indicador);
					}
				}

			}
		}
	}
	
	public List<Statement> getStatements() {
		return this.statements;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
	}
}
