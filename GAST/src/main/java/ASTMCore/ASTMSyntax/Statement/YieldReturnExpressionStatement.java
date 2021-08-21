package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;
import static ASTMCore.metadata.Tags.YIELD_RETURN_EXPRESSION_STATEMENT;

/**
 * Statement that returns the next element of an iterable object
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class YieldReturnExpressionStatement extends Statement {

    public YieldReturnExpressionStatement() {
        setTag(YIELD_RETURN_EXPRESSION_STATEMENT);
    }

    /**
     * Expression of an iterable to return one by one
     */
    Expression expression;

    /**
     * Setter for the expression to return
     *
     * @param expression Expression of an iterable
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Getter for the expression
     *
     * @return Expression of an iterable
     */
    public Expression getExpression() {

        return expression;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
