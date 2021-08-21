package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.AssignStatement;

public class SqrtStatement extends CTypeStatement {

	private boolean halfAdjust;
	private AssignStatement operation;

	public SqrtStatement(Opcode opcode) {
		super(opcode);

	}

	public boolean isHalfAdjust() {
		return halfAdjust;
	}

	public void setHalfAdjust(boolean halfAdjust) {
		this.halfAdjust = halfAdjust;
	}

	public AssignStatement getOperation() {
		return operation;
	}

	public void setOperation(AssignStatement operation) {
		this.operation = operation;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		Factor number = getFactor(parseTree, "rightFactor");
		Factor result = getFactor(parseTree, "resultFactor");

		this.operation = new AssignStatement(number, new LiteralFactor("0.5"), result, new Operand("**"));

		this.halfAdjust = hasExtender(parseTree, "H");

		this.setControlIndicators(parseTree);
		this.setType("Statement");

	}

}
