package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.List;

public class NameOfExpression extends Expression {
    List<IdentifierReference> identifierReferenceList;
    @Override
    public void accept(GASTVisitor v) {

    }

    public void setIdentifierReferenceList(List<IdentifierReference> identifierReferenceList) {
        this.identifierReferenceList = identifierReferenceList;
    }

    public List<IdentifierReference> getIdentifierReferenceList() {
        return identifierReferenceList;
    }
}
