package gastvalidator.RPG2GASTMCertifier.Statements;

import gastvalidator.RPG2GASTMCertifier.Expressions.Expression;

public class When extends Statement{
	private Expression condition;
	
	private String extender;
	
	private BlockStatement body;
	
	public When() {
		body = new BlockStatement();
	}

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
