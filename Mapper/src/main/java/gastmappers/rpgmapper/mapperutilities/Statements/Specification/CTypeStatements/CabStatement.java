package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.DefaultValues.BooleanOperation;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

/**
 * Modela la operación Cab.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class CabStatement extends CTypeStatement {

	private Factor leftOperand, rightOperand;
	private Operand operand;
	private String labelResultField;
	private List<Statement> indicatorsStatements;

	public CabStatement(Opcode opcode) {
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

	public Operand getOperand() {
		return operand;
	}

	public void setOperand(Operand operand) {
		this.operand = operand;
	}

	public String getLabelResultField() {
		return labelResultField;
	}

	public void setLabelResultField(String labelResultField) {
		this.labelResultField = labelResultField;
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
		this.labelResultField = this.getFactor(parseTree, "resultFactor").getValue();

		String operand = BooleanOperation.getBooleanOperationSymbol(super.getOpcode().booleanOperation);
		this.operand = new Operand(operand);

		this.indicatorsStatements = getIndicatorsStatements(parseTree, this.leftOperand, this.rightOperand,
				"AssignStatement");

		this.setControlIndicators(parseTree);
		this.setType("ComparisonBranch");
	}

}
