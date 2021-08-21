package gastvalidator.RPG2GASTMCertifier.Statements;

import gastvalidator.RPG2GASTMCertifier.Expressions.Expression;

public class ElseIf extends Statement{
	private Expression condition;
	
	private BlockStatement body;
	
	private String extender;

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public BlockStatement getBody() {
		return body;
	}

	public void setBody(BlockStatement body) {
		this.body = body;
	}

	public String getExtender() {
		return extender;
	}

	public void setExtender(String extender) {
		this.extender = extender;
	}
	
	
}
