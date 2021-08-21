package gastvalidator.RPG2GASTMCertifier.Expressions;

public class ExpressionNode extends gastvalidator.RPG2GASTMCertifier.Expressions.Expression {
	
	private String value;

	public ExpressionNode(ExpressionType ptype) {
		super(ptype);
		// TODO Auto-generated constructor stub
	}
	
	public ExpressionNode(gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionType ptype, String value) {
		super(ptype);
		// TODO Auto-generated constructor stub
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
