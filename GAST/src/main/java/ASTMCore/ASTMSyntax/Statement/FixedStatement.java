package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DefintionObject;
import ASTMCore.visitor.GASTVisitor;

import java.util.List;

import static ASTMCore.metadata.Tags.FIXED_STATEMENT;

/**
 * Statement that prevents the garbage collector from relocating a movable variable.
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class FixedStatement extends Statement {

    public FixedStatement() {
        setTag(FIXED_STATEMENT);
    }

    /**
     * Pointer of the variable not to be relocated
     */
    DefintionObject pointerDeclaration;

    /**
     * Body of the statement that uses the pointer and depends on it not being relocated
     */
    Statement body;

    /**
     * Getter for the pointer declaration
     *
     * @return DefintionObject with the pointer declaration
     */
    public DefintionObject getPointerDeclaration() {
        return pointerDeclaration;
    }

    /**
     * Setter for the pointer declaration
     *
     * @param pointerDeclaration DefintionObject with the pointer declaration
     */
    public void setPointerDeclaration(DefintionObject pointerDeclaration) {
        this.pointerDeclaration = pointerDeclaration;
    }

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
