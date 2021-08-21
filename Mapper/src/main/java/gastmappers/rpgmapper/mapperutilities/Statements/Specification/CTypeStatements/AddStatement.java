package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

/**
 * Modela la declaración de un Add.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class AddStatement extends CTypeStatement {

	private Factor leftOperand, rightOperand, result;
	private Operand operand;
	private List<Statement> indicatorsStatements;
	private boolean roundUp;

	public AddStatement(Opcode opcode) {
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

	public Factor getResult() {
		return result;
	}

	public void setResult(Factor result) {
		this.result = result;
	}

	public Operand getOperand() {
		return operand;
	}

	public void setOperand(Operand operand) {
		this.operand = operand;
	}

	public List<Statement> getIndicatorsStatements() {
		return indicatorsStatements;
	}

	public void setIndicatorsStatements(List<Statement> indicatorsStatements) {
		this.indicatorsStatements = indicatorsStatements;
		
	}

	public boolean isRoundUp() {
		return roundUp;
	}

	public void setRoundUp(boolean roundUp) {
		this.roundUp = roundUp;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.leftOperand = this.getLeftFactor(parseTree);
		this.rightOperand = this.getFactor(parseTree, "rightFactor");
		this.result = this.getFactor(parseTree, "resultFactor");
		if (leftOperand == null) {
			this.leftOperand = this.result;
		}
		this.operand = new Operand("+");
		
		this.indicatorsStatements = getIndicatorsStatements(parseTree, this.result, new LiteralFactor("0"),"AssignStatement");
		this.roundUp = this.hasExtender(parseTree, "H");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}
	
}
