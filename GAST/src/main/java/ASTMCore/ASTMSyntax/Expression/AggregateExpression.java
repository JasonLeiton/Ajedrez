package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.AGGREGATE_EXPRESSION;

public class AggregateExpression extends Expression {

	public AggregateExpression() {
		setTag(AGGREGATE_EXPRESSION);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
