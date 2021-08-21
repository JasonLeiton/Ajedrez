package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.UNSAFE_STATEMENT;

/**
 * Statement that executes code in a unsafe context which is required for operations involving pointers (in C#).
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class UnsafeStatement extends Statement {

    public UnsafeStatement() {
        setTag(UNSAFE_STATEMENT);
    }

    /**
     * Body of the statement with code to execute in a unsafe context
     */
    private Statement body;

    /**
     * Getter for the body of the statement
     *
     * @return Statement with the body
     */
    public Statement getBody() {
        return body;
    }

    /**
     * Setter for the body of the statement
     *
     * @param body Statement with the body
     */
    public void setBody(Statement body) {
        this.body = body;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
