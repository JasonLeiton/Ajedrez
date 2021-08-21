package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class TimeStatement extends CTypeStatement {

	private Factor result;

	public TimeStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getResult() {
		return result;
	}

	public void setResult(Factor result) {
		this.result = result;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.result = this.getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}
}
