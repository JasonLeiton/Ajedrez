package gastvalidator.RPG2GASTMCertifier.Expressions;

public class BinaryExpression extends ExpressionWithOperator{
	
	private Expression leftOperand,rightOperand;
	

	public BinaryExpression(ExpressionType ptype) {
		super(ptype);
		// TODO Auto-generated constructor stub
	}
	
	public BinaryExpression(ExpressionType ptype, Expression left, String operand, Expression right) {
		super(ptype);
		// TODO Auto-generated constructor stub
		setLeftOperand(left);
		setRightOperand(right);
		setOperator(ExpressionWithOperator.getBinaryType(operand));
		
	}
	
	public BinaryExpression(ExpressionType ptype, String left, String operand, String right) {
		super(ptype);
		// TODO Auto-generated constructor stub
		setLeftOperand(new ExpressionNode(ExpressionType.NODE_EXPRESSION, left));
		setRightOperand(new ExpressionNode(ExpressionType.NODE_EXPRESSION, right));
		setOperator(ExpressionWithOperator.getBinaryType(operand));
	}
	
	public BinaryExpression(ExpressionType ptype, String left, String operand, Expression right) {
		super(ptype);
		// TODO Auto-generated constructor stub
		setLeftOperand(new ExpressionNode(ExpressionType.NODE_EXPRESSION, left));
		setRightOperand(right);
		setOperator(ExpressionWithOperator.getBinaryType(operand));
	}
	
	public BinaryExpression(ExpressionType ptype, Expression left, String operand, String right) {
		super(ptype);
		// TODO Auto-generated constructor stub
		setLeftOperand(left);
		setRightOperand(new ExpressionNode(ExpressionType.NODE_EXPRESSION, right));
		setOperator(ExpressionWithOperator.getBinaryType(operand));
	}


	public Expression getLeftOperand() {
		return leftOperand;
	}


	public void setLeftOperand(Expression leftOperand) {
		this.leftOperand = leftOperand;
	}


	public Expression getRightOperand() {
		return rightOperand;
	}


	public void setRightOperand(Expression rightOperand) {
		this.rightOperand = rightOperand;
	}


}
