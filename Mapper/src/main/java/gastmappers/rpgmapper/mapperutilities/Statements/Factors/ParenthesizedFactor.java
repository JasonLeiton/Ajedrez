package gastmappers.rpgmapper.mapperutilities.Statements.Factors;

/**
 * Modela factores más complejos, como los que tiene asociado a su vez otros
 * factores.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class ParenthesizedFactor implements Factor {

	private String value, type;
	private Factor leftOperand, rightOperand;
	private Operand operand;

	public ParenthesizedFactor(Factor leftOperand, Factor rightOperand, Operand operand) {

		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operand = operand;
	}

	@Override
	public String getValue() {

		return this.value;
	}

	@Override
	public String getType() {

		return this.type;
	}

	@Override
	public void setValue(String value) {

		this.value = value;
	}

	@Override
	public void setType(String type) {

		this.type = type;
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

}
