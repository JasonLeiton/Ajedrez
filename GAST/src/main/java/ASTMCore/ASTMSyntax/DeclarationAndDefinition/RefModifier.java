package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.REF_MODIFIER;

/**
 * Modifier used for passing arguments to methods as a reference type.
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class RefModifier extends Modifiers {

    public RefModifier() {
        setTag(REF_MODIFIER);
    }

    /**
     * Attribute with the name of the modifier
     */
    private String modifier="ref";

    /**
     * Getter for modifier
     * @return String with modifier name
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * Setter for modifier
     * @param modifier String with modifier name
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public void accept(GASTVisitor v) {
        v.visitRefModifier(this);
    }
}
