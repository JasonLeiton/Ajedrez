package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

public class ThrowExpression extends Expression {
    @Override
    public void accept(GASTVisitor v) {

    }
    private String throwable;
    private Expression expression;

    public String getThrowable() {
        return throwable;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setThrowable(String throwable) {
        this.throwable = throwable;
    }
}
