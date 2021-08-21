package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class QueryExpressionClause extends Expression{
    String clauseName;
    ArrayList<IdentifierReference> identifierReferences;
    Type typeCombined;
    ArrayList<String> orderingDirs;
    ArrayList<Expression> expressions;

    public String getClauseName() {
        return clauseName;
    }

    public ArrayList<IdentifierReference> getIdentifierReferences() {
        return identifierReferences;
    }

    public Type getTypeCombined() {
        return typeCombined;
    }

    public ArrayList<String> getOrderingDirs() {
        return orderingDirs;
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    public void setClauseName(String clauseName) {
        this.clauseName = clauseName;
    }

    public void setIdentifierReferences(ArrayList<IdentifierReference> identifierReferences) {
        this.identifierReferences = identifierReferences;
    }

    public void setTypeCombined(Type typeCombined) {
        this.typeCombined = typeCombined;
    }

    public void setOrderingDirs(ArrayList<String> orderingDirs) {
        this.orderingDirs = orderingDirs;
    }

    public void setExpressions(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
