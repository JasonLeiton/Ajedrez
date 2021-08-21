package ASTMCore.ASTMSyntax.Statement;

import java.util.List;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.SUPER_INVOCATION;

public class SuperInvocation extends Statement {

	public SuperInvocation() {
		setTag(SUPER_INVOCATION);
	}

	private List<Expression> arguments;
	private Expression expression;
	private List<Type> argumentsTypes;


	public List<Expression> getArguments() {
		return arguments;
	}
	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}
	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	public List<Type> getArgumentsTypes() {
		return argumentsTypes;
	}
	public void setArgumentsTypes(List<Type> argumentsTypes) {
		this.argumentsTypes = argumentsTypes;
	}

	public void accept(GASTVisitor v) {
		v.visitSuperInvocation(this);
	}

}
