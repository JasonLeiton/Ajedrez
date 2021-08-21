package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.AND;

public class And extends BinaryOperator {

	public And() {
		setTag(AND);
	}

	private String simbol="&&";

	public String getSimbol() {
		return simbol;
	}

	public void setSimbol(String simbol) {
		this.simbol = simbol;

	}

	public void accept(GASTVisitor v) {
		v.visitAnd(this);
	}
	
}
