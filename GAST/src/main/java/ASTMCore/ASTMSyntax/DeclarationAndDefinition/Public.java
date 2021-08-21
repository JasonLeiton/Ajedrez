package ASTMCore.ASTMSyntax.DeclarationAndDefinition;


import static ASTMCore.metadata.Tags.PUBLIC;

public class Public extends AccessKind {

    public Public() {
        setTag(PUBLIC);
    }

    private String modifier="public";

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    
}
