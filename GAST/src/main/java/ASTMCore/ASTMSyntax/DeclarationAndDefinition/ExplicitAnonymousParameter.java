package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Expression.IdentifierReference;
import ASTMCore.ASTMSyntax.Statement.BlockStatement;
import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.visitor.GASTVisitor;

public class ExplicitAnonymousParameter extends Declaration {

    public Type type;
    public String refout;
    IdentifierReference identifierReference;

    public void setType(Type type) {
        this.type = type;
    }

    public void setRefout(String refout) {
        this.refout = refout;
    }


    public void setIdentifierReference(IdentifierReference identifierReference) {
        this.identifierReference = identifierReference;
    }

    public Type getType() {
        return type;
    }

    public String getRefout() {
        return refout;
    }


    public IdentifierReference getIdentifierReference() {
        return identifierReference;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
