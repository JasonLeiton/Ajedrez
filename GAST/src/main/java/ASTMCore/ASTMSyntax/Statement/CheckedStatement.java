package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.CHECKED_STATEMENT;

/**
 * Statement that executes code in a checked context where arithmetic overflow raises an exception.
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class CheckedStatement extends Statement {

    public CheckedStatement() {
        setTag(CHECKED_STATEMENT);
    }

    /**
     * Body of the statement with code to execute in a checked context
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
        v.visitCheckedStatement(this);
    }
}
