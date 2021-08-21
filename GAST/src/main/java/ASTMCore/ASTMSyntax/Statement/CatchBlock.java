package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DataDefinition;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.CATCH_BLOCK;

/**
 * Block that catches exceptions during execution
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.0
 */
public class CatchBlock extends MinorSyntaxObject {

    public CatchBlock() {
        setTag(CATCH_BLOCK);
    }

    /**
     * Body of the catch block
     */
    private Statement body;

    /**
     * Setter for the body of the block
     *
     * @param value Statement with the body of the catch block
     */
    public void setBody(Statement value) {
        this.body = value;
    }

    /**
     * Getter for the body of the block
     *
     * @return Statement with the body of the catch block
     */
    public Statement getBody() {
        return this.body;
    }

    /**
     * Type of exception to catch
     */
    private DataDefinition exception;

    /**
     * Getter for the type of exception
     *
     * @return DataDefinition with the type of exception
     */
    public DataDefinition getException() {
        return exception;
    }

    /**
     * Setter for the type of exception
     *
     * @param exception DataDefinition with the type of exception
     */
    public void setException(DataDefinition exception) {
        this.exception = exception;
    }

    /**
     * Expression that filters which exceptions to catch
     */
    private Expression expressionFilter;

    /**
     * Getter for the expression filter
     *
     * @return Expression with a condition to filter the exception type
     */
    public Expression getExpressionFilter() {
        return expressionFilter;
    }

    /**
     * Setter for the expression filter
     *
     * @param expressionFilter Expression with a condition to filter the exception type
     */
    public void setExpressionFilter(Expression expressionFilter) {
        this.expressionFilter = expressionFilter;
    }

    public void accept(GASTVisitor v) {
        v.visitCatchBlock(this);
    }


}
