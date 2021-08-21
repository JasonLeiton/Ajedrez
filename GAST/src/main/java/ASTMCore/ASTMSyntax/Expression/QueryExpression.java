package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

public class QueryExpression extends Expression {
    public void setReferenceFor(TypeReference referenceFor) {
        this.referenceFor = referenceFor;
    }

    public void setIdentifierReferenceFrom(IdentifierReference identifierReferenceFrom) {
        this.identifierReferenceFrom = identifierReferenceFrom;
    }

    public void setExpressionFrom(Expression expressionFrom) {
        this.expressionFrom = expressionFrom;
    }

    public void setBody(QueryExpressionBody body) {
        this.body = body;
    }

    public TypeReference getReferenceFor() {
        return referenceFor;
    }

    public IdentifierReference getIdentifierReferenceFrom() {
        return identifierReferenceFrom;
    }

    public Expression getExpressionFrom() {
        return expressionFrom;
    }

    public QueryExpressionBody getBody() {
        return body;
    }

    TypeReference referenceFor;
    IdentifierReference identifierReferenceFrom;
    Expression expressionFrom;
    QueryExpressionBody body;

    @Override
    public void accept(GASTVisitor v) {

    }
}
