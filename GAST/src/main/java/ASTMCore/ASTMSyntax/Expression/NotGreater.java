package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NOT_GREATER;

public class NotGreater extends BinaryOperator {

	public NotGreater() {
		setTag(NOT_GREATER);
	}

	private String simbol="<=";

	public String getSimbol() {
		return simbol;
	}

	public void setSimbol(String simbol) {
		this.simbol = simbol;
	}
    
	public void accept(GASTVisitor v) {
		v.visitNotGreater(this);
	}
	
}
