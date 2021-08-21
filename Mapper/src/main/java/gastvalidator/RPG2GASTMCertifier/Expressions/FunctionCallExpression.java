package gastvalidator.RPG2GASTMCertifier.Expressions;

import java.util.ArrayList;
import java.util.List;

public class FunctionCallExpression extends Expression{
	
	private String functionName;
	private List<Expression> args;

	public FunctionCallExpression(ExpressionType ptype, String name) {
		super(ptype);
		// TODO Auto-generated constructor stub
		setFunctionName(name);
		setArgs(new ArrayList<Expression>());
	}
	
	public FunctionCallExpression(ExpressionType ptype, String name, List<Expression> arguments) {
		super(ptype);
		// TODO Auto-generated constructor stub
		setFunctionName(name);
		setArgs(arguments);
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public List<Expression> getArgs() {
		return args;
	}

	public void setArgs(List<Expression> args) {
		this.args = args;
	}

}
