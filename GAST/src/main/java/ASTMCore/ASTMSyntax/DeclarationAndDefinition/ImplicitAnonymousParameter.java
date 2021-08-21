package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Expression.IdentifierReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class ImplicitAnonymousParameter extends Declaration {
    @Override
    public void accept(GASTVisitor v) {

    }
    ArrayList<IdentifierReference> identifierReferences;

    public void setIdentifierReferences(ArrayList<IdentifierReference> identifierReferences) {
        this.identifierReferences = identifierReferences;
    }

    public ArrayList<IdentifierReference> getIdentifierReferences() {
        return identifierReferences;
    }
}
