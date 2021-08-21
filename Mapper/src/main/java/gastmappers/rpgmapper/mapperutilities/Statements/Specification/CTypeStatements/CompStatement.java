package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class CompStatement extends CTypeStatement {

	private Factor leftOperand, rightOperand;
	private List<Statement> indicatorsStatements;

	public CompStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(Factor leftOperand) {
		this.leftOperand = leftOperand;
	}

	public Factor getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(Factor rightOperand) {
		this.rightOperand = rightOperand;
	}

	public List<Statement> getIndicatorsStatements() {
		return indicatorsStatements;
	}

	public void setIndicatorsStatements(List<Statement> indicatorsStatements) {
		this.indicatorsStatements = indicatorsStatements;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.leftOperand = this.getLeftFactor(parseTree);
		this.rightOperand = this.getFactor(parseTree, "rightFactor");

		this.indicatorsStatements = getIndicatorsStatements(parseTree, this.leftOperand, this.rightOperand,
				"AssignStatement");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
