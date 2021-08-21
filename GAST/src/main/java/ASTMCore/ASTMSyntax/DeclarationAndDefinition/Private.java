package ASTMCore.ASTMSyntax.DeclarationAndDefinition;


import static ASTMCore.metadata.Tags.PRIVATE;

public class Private extends AccessKind {

    public Private() {
        setTag(PRIVATE);
    }

    private String modifier="private";

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    
}
