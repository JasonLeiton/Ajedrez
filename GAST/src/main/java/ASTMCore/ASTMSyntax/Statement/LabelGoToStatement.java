package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.LABEL_GO_TO_STATEMENT;

/**
 * Goto statement that jumps to a label statement
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class LabelGoToStatement extends Statement {

    public LabelGoToStatement() {
        setTag(LABEL_GO_TO_STATEMENT);
    }

    /**
     * Identifier with the destination of the jump
     */
    Name Identifier;

    /**
     * Setter for the identifier
     *
     * @param identifier Name of label
     */
    public void setIdentifier(Name identifier) {
        Identifier = identifier;
    }

    /**
     * Getter for the identifier
     *
     * @return Name of label
     */
    public Name getIdentifier() {
        return Identifier;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
