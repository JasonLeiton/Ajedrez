package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.visitor.GASTVisitor;

public abstract class UnaryOperator extends MinorSyntaxObject {
	private String simbolo;

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public abstract void accept(GASTVisitor v);

}
