package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

public class YieldExpression extends Expression{

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
	
	Expression expression;

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
	

}
