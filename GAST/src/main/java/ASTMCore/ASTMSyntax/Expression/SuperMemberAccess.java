package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.SUPER_MEMBER_ACCESS;

public class SuperMemberAccess extends Expression {

	public SuperMemberAccess() {
		setTag(SUPER_MEMBER_ACCESS);
	}

	Expression fieldName;
	
	Expression qualifiedName;

	public Expression getFieldName() {
		return fieldName;
	}

	public void setFieldName(Expression fieldName) {
		this.fieldName = fieldName;
	}

	public Expression getQualifiedName() {
		return qualifiedName;
	}

	public void setQualifiedName(Expression qualifiedName) {
		this.qualifiedName = qualifiedName;
	}
	
	public void accept(GASTVisitor v) {
		v.visitSuperMemberAccess(this);
	}
	
}
