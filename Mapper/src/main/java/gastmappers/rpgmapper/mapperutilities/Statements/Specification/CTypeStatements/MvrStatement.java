package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.AssignStatement;

public class MvrStatement {

	public MvrStatement() {
	}

	public AssignStatement buildRemainderStatement(ParseTree parseTree, Factor leftOperand, Factor rightOperand) {
		String resultName = getRemainderField(parseTree);
		AssignStatement assignStatement = new AssignStatement();
		assignStatement.setOperands(leftOperand, rightOperand, resultName, 3);
		assignStatement.setType("RemainderStatement");
		return assignStatement;
	}

	private String getRemainderField(ParseTree parseTree) {
		int lastChild = parseTree.getChildCount() - 1;
		Cspec_fixed_standard_partsContext remainder = (Cspec_fixed_standard_partsContext) parseTree.getChild(lastChild);
		return remainder.result.getText();
	}

}
