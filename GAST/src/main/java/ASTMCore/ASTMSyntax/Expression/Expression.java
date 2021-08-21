package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.GASTMSyntaxObject;
import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

public abstract class Expression extends GASTMSyntaxObject {

	private TypeReference expressionType;

	public void setExpressionType(TypeReference value) {
		this.expressionType = value;
	}

	public TypeReference getExpressionType() {
		return this.expressionType;
	}

	public abstract void accept(GASTVisitor v);

}
