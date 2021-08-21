package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.OR;

public class Or extends BinaryOperator {

	public Or() {
		setTag(OR);
	}

	private String simbol="||";

	public String getSimbol() {
		return simbol;
	}

	public void setSimbol(String simbol) {
		this.simbol = simbol;

	}

	public void accept(GASTVisitor v) {
		v.visitOr(this);
	}
	
}
