package gastvalidator.RPG2GASTMCertifier.Statements;

import java.util.ArrayList;

import gastvalidator.RPG2GASTMCertifier.Expressions.Expression;

public class If extends Statement{
	
	private Expression condition;
	
	private BlockStatement body;
	
	private ArrayList<ElseIf> elseIf;
	
	private BlockStatement elseBody;
	
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

	public ArrayList<ElseIf> getElseIf() {
		return elseIf;
	}

	public void setElseIf(ArrayList<ElseIf> elseIf) {
		this.elseIf = elseIf;
	}

	public BlockStatement getElseBody() {
		return elseBody;
	}

	public void setElseBody(BlockStatement elseBody) {
		this.elseBody = elseBody;
	}

	public String getExtender() {
		return extender;
	}

	public void setExtender(String extender) {
		this.extender = extender;
	}

}
