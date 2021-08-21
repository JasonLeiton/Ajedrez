package gastvalidator.RPG2GASTMCertifier.Expressions;

public class UnaryExpression extends ExpressionWithOperator{

	private Expression target;
	
	public UnaryExpression(ExpressionType ptype) {
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
