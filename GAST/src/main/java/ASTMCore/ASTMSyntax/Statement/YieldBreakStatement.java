package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.YIELD_BREAK_STATEMENT;

/**
 * Statement that ends the iteration of the yield statement
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class YieldBreakStatement extends Statement{

    public YieldBreakStatement() {
        setTag(YIELD_BREAK_STATEMENT);
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
