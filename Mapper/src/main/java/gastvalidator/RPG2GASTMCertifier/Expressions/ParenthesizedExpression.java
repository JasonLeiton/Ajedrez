package gastvalidator.RPG2GASTMCertifier.Expressions;

public class ParenthesizedExpression extends Expression{
	
	private Expression target;

	public ParenthesizedExpression(ExpressionType ptype) {
		super(ptype);
		// TODO Auto-generated constructor stub
	}

	public Expression getTarget() {
		return target;
	}

	public void setTarget(Expression target) {
		this.target = target;
	}

}
