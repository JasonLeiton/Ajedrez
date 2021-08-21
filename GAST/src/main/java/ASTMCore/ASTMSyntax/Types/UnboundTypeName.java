package ASTMCore.ASTMSyntax.Types;

import ASTMCore.ASTMSyntax.Expression.IdentifierReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.List;

public class UnboundTypeName extends TypeReference{
    @Override
    public void accept(GASTVisitor v) {

    }
    List<IdentifierReference> identifierReferenceList;
    List<String> dimensionSpecifierList;

    public List<IdentifierReference> getIdentifierReferenceList() {
        return identifierReferenceList;
    }

    public List<String> getDimensionSpecifierList() {
        return dimensionSpecifierList;
    }

    public void setIdentifierReferenceList(List<IdentifierReference> identifierReferenceList) {
        this.identifierReferenceList = identifierReferenceList;
    }

    public void setDimensionSpecifierList(List<String> typeOfSpecifier) {
        this.dimensionSpecifierList = typeOfSpecifier;
    }
}
