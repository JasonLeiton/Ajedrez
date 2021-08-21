package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

public class CheckedExpression extends Expression {

    private Expression internalExpression;

    public Expression getInternalExpression() {
        return internalExpression;
    }

    public void setInternalExpression(Expression internalExpression) {
        this.internalExpression = internalExpression;
    }

    public void accept(GASTVisitor v) {
        v.visitCheckedExpression(this);
    }

}
