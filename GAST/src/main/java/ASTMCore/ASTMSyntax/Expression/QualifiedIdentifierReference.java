package ASTMCore.ASTMSyntax.Expression;


public abstract class QualifiedIdentifierReference extends NameReference {

	private Expression qualifiers;

	public void setQualifiers(Expression value) {
		this.qualifiers = value;
	}

	public Expression getQualifiers() {
		return this.qualifiers;
	}


	private IdentifierReference member;

	public void setMember(IdentifierReference value) {
		this.member = value;
	}

	public IdentifierReference getMember() {
		return this.member;
	}

}
