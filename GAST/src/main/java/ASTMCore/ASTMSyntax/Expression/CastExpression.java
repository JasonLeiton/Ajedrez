package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.CAST_EXPRESSION;

public class CastExpression extends Expression {

	public CastExpression() {
		setTag(CAST_EXPRESSION);
	}

	private TypeReference castType;

	public void setCastType(TypeReference value) {
		this.castType = value;
	}

	public TypeReference getCastType() {
		return this.castType;
	}

	private Expression expression;

	public void setExpression(Expression value) {
		this.expression = value;
	}

	public Expression getExpression() {
		return this.expression;
	}
	
	public void accept(GASTVisitor v) {
		v.visitCastExpression(this);
	}

}
