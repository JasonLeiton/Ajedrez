package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.CASE_GO_TO_STATEMENT;

/**
 * Goto statement that jumps to a Case statement inside a switch
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class CaseGoToStatement extends Statement {

    public CaseGoToStatement() {
        setTag(CASE_GO_TO_STATEMENT);
    }

    /**
     * Expression with the destination of the jump
     */
    private Expression expression;

    /**
     * Setter for the expression
     *
     * @param expression Expression with the destination of the jump
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Getter for the expression
     *
     * @return Expression with the destination of the jump
     */
    public Expression getExpression() {
        return expression;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
