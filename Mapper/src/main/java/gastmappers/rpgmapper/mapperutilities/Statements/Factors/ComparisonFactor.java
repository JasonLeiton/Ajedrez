package gastmappers.rpgmapper.mapperutilities.Statements.Factors;

public class ComparisonFactor implements Factor {

	private Factor leftOperand, rightOperand;
	private Operand operand;

	public ComparisonFactor() {

	}

	public ComparisonFactor(ComparisonFactor comparisonFactor) {
		this.leftOperand = comparisonFactor.getLeftOperand();
		this.rightOperand = comparisonFactor.getRightOperand();
		this.operand = comparisonFactor.getOperand();
	}

	public ComparisonFactor(Factor leftOperand, Factor rightOperand, Operand operand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operand = operand;
	}

	public void setOperands(Factor leftOperand, Factor rightOperand, Operand operator) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operand = operator;
	}

	@Override
	public String getValue() {

		return null;
	}

	@Override
	public String getType() {

		return null;
	}

	@Override
	public void setValue(String value) {

	}

	@Override
	public void setType(String type) {

	}

	public Factor getLeftOperand() {
		return leftOperand;
	}

	public Factor getRightOperand() {
		return rightOperand;
	}

	public Operand getOperand() {
		return operand;
	}

}
