package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

public class SwitchArmExpression extends Expression{
private Expression  expression;
private Expression  throwableExpression;
private Expression  caseArrow;
    @Override
    public void accept(GASTVisitor v) {

    }

    public Expression getExpression() {
        return expression;
    }

    public Expression getThrowableExpression() {
        return throwableExpression;
    }

    public Expression getCaseArrow() {
        return caseArrow;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setThrowableExpression(Expression throwableExpression) {
        this.throwableExpression = throwableExpression;
    }

    public void setCaseArrow(Expression caseArrow) {
        this.caseArrow = caseArrow;
    }
}
