package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

public class NullCoalescingExpression extends  Expression {
    Expression expression;
    Expression conditionalOrExpression;
    Expression throwExpression;

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setConditionalOrExpression(Expression conditionalOrExpression) {
        this.conditionalOrExpression = conditionalOrExpression;
    }

    public void setThrowExpression(Expression throwExpression) {
        this.throwExpression = throwExpression;
    }

    public Expression getExpression() {
        return expression;
    }

    public Expression getConditionalOrExpression() {
        return conditionalOrExpression;
    }

    public Expression getThrowExpression() {
        return throwExpression;
    }

    @Override
    public void accept(GASTVisitor v) {

    }


}
