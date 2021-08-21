package ASTMCore.ASTMSyntax.Expression;

import java.util.List;

import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.SUPER_METHOD_INVOCATION;

public class SuperMethodInvocation extends Expression {

	public SuperMethodInvocation() {
		setTag(SUPER_METHOD_INVOCATION);
	}

	private List<Expression>arguments;
	private Expression name;
	private Expression qualifiedName;
	private List<Type> argumentType;

	public List<Expression> getArguments() {
		return arguments;
	}

	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}

	public Expression getName() {
		return name;
	}

	public void setName(Expression name) {
		this.name = name;
	}

	public Expression getQualifiedName() {
		return qualifiedName;
	}

	public void setQualifiedName(Expression qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	public List<Type> getArgumentType() {
		return argumentType;
	}

	public void setArgumentType(List<Type> argumentType) {
		this.argumentType = argumentType;
	}

	public void accept(GASTVisitor v) {
		v.visitSuperMethodInvocation(this);
	}

}
