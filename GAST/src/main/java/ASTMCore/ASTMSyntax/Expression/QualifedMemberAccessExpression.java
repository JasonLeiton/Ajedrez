package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class QualifedMemberAccessExpression extends Expression {
    IdentifierReference leftIdentifier;
    IdentifierReference rightIdentifier;
    ArrayList<TypeReference> typeReferences;

    public void setLeftIdentifier(IdentifierReference leftIdentifier) {
        this.leftIdentifier = leftIdentifier;
    }

    public void setRightIdentifier(IdentifierReference rightIdentifier) {
        this.rightIdentifier = rightIdentifier;
    }

    public void setTypeReferences(ArrayList<TypeReference> typeReferences) {
        this.typeReferences = typeReferences;
    }

    public IdentifierReference getLeftIdentifier() {
        return leftIdentifier;
    }

    public IdentifierReference getRightIdentifier() {
        return rightIdentifier;
    }

    public ArrayList<TypeReference> getTypeReferences() {
        return typeReferences;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
