package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.UNCHECKED_STATEMENT;

/**
 * Statement that executes code in a unchecked context where arithmetic overflow is ignored
 * and the result is truncated.
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class UncheckedStatement extends Statement {

    public UncheckedStatement() {
        setTag(UNCHECKED_STATEMENT);
    }

    /**
     * Body of the statement with code to execute in a unchecked context
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

    public void accept(GASTVisitor v) {
        v.visitUncheckedStatement(this);
    }
}
