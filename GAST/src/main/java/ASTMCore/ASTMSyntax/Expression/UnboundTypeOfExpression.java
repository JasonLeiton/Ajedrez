package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import java.util.List;

public class UnboundTypeOfExpression extends Expression {
    List<IdentifierReference> identifierReferenceList;

    public void setIdentifierReferenceList(List<IdentifierReference> identifierReferenceList) {
        this.identifierReferenceList = identifierReferenceList;
    }

    public List<IdentifierReference> getIdentifierReferenceList() {
        return identifierReferenceList;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
