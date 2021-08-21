package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class DefineStatement extends CTypeStatement {

	private DefineStatement statement;

	public DefineStatement(Opcode opcode) {
		super(opcode);

	}

	public DefineStatement getStatement() {
		return statement;
	}

	public void setStatement(DefineStatement statement) {
		this.statement = statement;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		String flagText = this.getLeftFactor(parseTree).getValue();

		Factor rightOperand = this.getFactor(parseTree, "rightFactor");
		Factor resultFactor = this.getFactor(parseTree, "resultFactor");
		if (flagText.contentEquals("*LIKE")) {
			DefineLikeStatement defineLike = new DefineLikeStatement(getOpcode());
			defineLike.setVariables(rightOperand, resultFactor);
			this.statement = defineLike;
		} else if (flagText.contentEquals("*DTAARA")) {
			DefineDtaaraStatement defineDtaara = new DefineDtaaraStatement(getOpcode());
			defineDtaara.setVariables(rightOperand, resultFactor);
			this.statement = defineDtaara;
		}

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
