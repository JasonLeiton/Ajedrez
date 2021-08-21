package gastvalidator.RPG2GASTMCertifier.Statements;

import gastvalidator.RPG2GASTMCertifier.Expressions.Expression;

public class For extends Statement{
	private AssigmentStatement increment;
	private Expression stopCondition;
	private AssigmentStatement initialCondition;
	private BlockStatement body;
	
	public For() {
		body = new BlockStatement();
	}
	
	public AssigmentStatement getIncrement() {
		return increment;
	}
	public void setIncrement(AssigmentStatement increment) {
		this.increment = increment;
	}
	public Expression getStopCondition() {
		return stopCondition;
	}
	public void setStopCondition(Expression stopCondition) {
		this.stopCondition = stopCondition;
	}
	public AssigmentStatement getInitialCondition() {
		return initialCondition;
	}
	public void setInitialCondition(AssigmentStatement initialCondition) {
		this.initialCondition = initialCondition;
	}
	public BlockStatement getBody() {
		return body;
	}
	public void setBody(BlockStatement body) {
		this.body = body;
	}

}
