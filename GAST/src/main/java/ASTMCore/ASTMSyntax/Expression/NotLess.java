package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NOT_LESS;

public class NotLess extends BinaryOperator {

	public NotLess() {
		setTag(NOT_LESS);
	}

	private String simbol=">=";

	public String getSimbol() {
		return simbol;
	}

	public void setSimbol(String simbol) {
		this.simbol = simbol;
	}
    
	public void accept(GASTVisitor v) {
		v.visitNotLess(this);
	}
	
}
