package gastmappers.rpgmapper.mapperutilities.Statements.Types;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;

public class AssignStatement extends Statement {

	private Factor leftOperand, rightOperand, result;
	private Operand operand;

	public AssignStatement() {
	}

	public AssignStatement(Factor leftOperand, Factor rightOperand, Factor result, Operand operand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.result = result;
		this.operand = operand;
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

	public void setLeftOperand(Factor leftOperand) {
		this.leftOperand = leftOperand;
	}

	public void setOperand(Operand operand) {
		this.operand = operand;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

	}

	public Operand getOperand() {
		return this.operand;
	}

	public Factor getLeftOperand() {
		return this.leftOperand;
	}

	public void setOperands(Factor leftOperand, Factor rightOperand, String indicator, int indicatorCount) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;

		Factor resultFactor;
		if (indicatorCount < 3) {
			String indicatorNumber = "*IN" + indicator;
			resultFactor = new LiteralFactor(indicatorNumber, "boolean");
		} else {
			resultFactor = new LiteralFactor(indicator, "Variable");
		}

		this.result = resultFactor;
		this.operand = new Operand(getIndicatorSymbol(indicatorCount));

		this.type = "IndicatorStatement";
	}

	public void setOperators(Factor leftOperand, Factor rightOperand, Operand operator) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operand = operator;
	}

	private String getIndicatorSymbol(int indicatorNumber) {
		String[] symbols = { ">", "<", "==", "%", "+", "-" }; // Corresponden a los valores Hi, Lo y Eq.
		return symbols[indicatorNumber];
	}

}
