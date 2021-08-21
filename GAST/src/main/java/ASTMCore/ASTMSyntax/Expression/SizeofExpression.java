package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

public class SizeofExpression  extends Expression {
    TypeReference typeReference;
    @Override
    public void accept(GASTVisitor v) {

    }

    public TypeReference getTypeReference() {
        return typeReference;
    }

    public void setTypeReference(TypeReference typeReference) {
        this.typeReference = typeReference;
    }

}
