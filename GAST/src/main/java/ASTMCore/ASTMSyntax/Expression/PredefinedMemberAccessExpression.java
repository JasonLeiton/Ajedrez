package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.NamedTypeReference;
import ASTMCore.visitor.GASTVisitor;

public class PredefinedMemberAccessExpression extends  Expression {
    NamedTypeReference namedTypeReference;

    public void setNamedTypeReference(NamedTypeReference namedTypeReference) {
        this.namedTypeReference = namedTypeReference;
    }

    public NamedTypeReference getNamedTypeReference() {
        return namedTypeReference;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
