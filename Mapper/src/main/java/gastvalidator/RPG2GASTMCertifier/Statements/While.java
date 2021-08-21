package gastvalidator.RPG2GASTMCertifier.Statements;

import gastvalidator.RPG2GASTMCertifier.Expressions.Expression;

public class While extends Statement{
	
	private BlockStatement body;
	private Expression condition;
	
	public While() {
		body = new BlockStatement();
	}

	public BlockStatement getBody() {
		return body;
	}

	public void setBody(BlockStatement body) {
		this.body = body;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
}
