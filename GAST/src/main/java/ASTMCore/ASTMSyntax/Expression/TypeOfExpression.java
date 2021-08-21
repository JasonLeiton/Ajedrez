package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

public class TypeOfExpression extends Expression{
    TypeReference typeReference;

    public void setTypeReference(TypeReference typeReference) {
        this.typeReference = typeReference;
    }

    public TypeReference getTypeReference() {
        return typeReference;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
