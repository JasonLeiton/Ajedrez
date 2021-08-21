package gastvalidator.RPG2GASTMCertifier.Expressions;

public class ExpressionWithOperator extends Expression{
	
	private ExpressionOperator operator;

	public ExpressionWithOperator(ExpressionType ptype) {
		super(ptype);
		// TODO Auto-generated constructor stub
	}

	public ExpressionOperator getOperator() {
		return operator;
	}

	public void setOperator(ExpressionOperator operator) {
		this.operator = operator;
	}
	
	public static ExpressionOperator getBinaryType(String inputType) {
		ExpressionOperator type = null;
		switch(inputType.toLowerCase()) {
			case "+":
				type = ExpressionOperator.ADD;
				break;
			case "-":
				type = ExpressionOperator.SUB;
				break;
			case "*":
				type = ExpressionOperator.MUL;
				break;
			case "/":
				type = ExpressionOperator.DIV;
				break;
			case "%":
				type = ExpressionOperator.MOD;
				break;
			case "**":
				type = ExpressionOperator.POW;
				break;

			case "sqrt":
				type = ExpressionOperator.SQRT;
				break;
			case "or":
				type = ExpressionOperator.OR;
				break;
			case "and":
				type = ExpressionOperator.AND;
				break;
			case "!":
				type = ExpressionOperator.NOT;
				break;
			case ">":
				type = ExpressionOperator.GT;
				break;
			case ">=":
				type = ExpressionOperator.GE;
				break;
			case "<":
				type = ExpressionOperator.LT;
				break;
			case "<=":
				type = ExpressionOperator.LE;
				break;
			case "!=":
				type = ExpressionOperator.NEQ;
				break;
			case "=":
				type = ExpressionOperator.EQ;
				break;
			case "<<":
				type = ExpressionOperator.SHIFT_LEFT;
				break;
			case ">>":
				type = ExpressionOperator.SHIFT_RIGHT;
				break;
		}
		return type;
	}
	
	public static ExpressionOperator getUnaryType(String inputType) {
		ExpressionOperator type = null;
		switch(inputType) {
			case "-":
				type = ExpressionOperator.NEG;
				break;
			case "!":
				type = ExpressionOperator.NOT;
				break;
		}
		return type;
	}
	
	
	public static boolean operatorIsArithmetical(String inputType) {
		ExpressionOperator type = null;
		switch(inputType) {
			case "+":
				type = ExpressionOperator.ADD;
				break;
			case "-":
				type = ExpressionOperator.SUB;
				break;
			case "*":
				type = ExpressionOperator.MUL;
				break;
			case "/":
				type = ExpressionOperator.DIV;
				break;
			case "%":
				type = ExpressionOperator.MOD;
				break;
			case "**":
				type = ExpressionOperator.POW;
				break;

			case "sqrt":
				type = ExpressionOperator.SQRT;
				break;
		}
		return type != null;
	}
	
	public static boolean operatorIsLogical(String inputType) {
		ExpressionOperator type = null;
		switch(inputType) {
			case "|":
				type = ExpressionOperator.OR;
				break;
			case "&":
				type = ExpressionOperator.AND;
				break;
			case "!":
				type = ExpressionOperator.NOT;
				break;
			case ">":
				type = ExpressionOperator.GT;
				break;
			case ">=":
				type = ExpressionOperator.GE;
				break;
			case "<":
				type = ExpressionOperator.LT;
				break;
			case "<=":
				type = ExpressionOperator.LE;
				break;
			case "!=":
				type = ExpressionOperator.NEQ;
				break;
			case "=":
				type = ExpressionOperator.EQ;
				break;
			case "<<":
				type = ExpressionOperator.SHIFT_LEFT;
				break;
			case ">>":
				type = ExpressionOperator.SHIFT_RIGHT;
				break;
		}
		return type != null;
	}
	

}
