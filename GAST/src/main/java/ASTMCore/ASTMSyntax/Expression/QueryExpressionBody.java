package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class QueryExpressionBody extends Expression {
    Expression selectGroupClauseFirst;
    Expression selectGroupClauseSecond;
    String selectGroupClause;
    QueryExpressionBody nextBody;
    IdentifierReference continuationIdentifier;
    ArrayList<QueryExpressionClause> clauses;

    public void setContinuationIdentifier(IdentifierReference continuationIdentifier) {
        this.continuationIdentifier = continuationIdentifier;
    }

    public void setClauses(ArrayList<QueryExpressionClause> clauses) {
        this.clauses = clauses;
    }

    public ArrayList<QueryExpressionClause> getClauses() {
        return clauses;
    }

    public IdentifierReference getContinuationIdentifier() {
        return continuationIdentifier;
    }

    public void setSelectGroupClauseFirst(Expression selectGroupClauseFirst) {
        this.selectGroupClauseFirst = selectGroupClauseFirst;
    }

    public void setSelectGroupClauseSecond(Expression selectGroupClauseSecond) {
        this.selectGroupClauseSecond = selectGroupClauseSecond;
    }

    public void setSelectGroupClause(String selectGroupClause) {
        this.selectGroupClause = selectGroupClause;
    }

    public void setNextBody(QueryExpressionBody nextBody) {
        this.nextBody = nextBody;
    }

    public void setNextIdentifier(IdentifierReference nextIdentifier) {
        this.continuationIdentifier = nextIdentifier;
    }

    public Expression getSelectGroupClauseFirst() {
        return selectGroupClauseFirst;
    }

    public Expression getSelectGroupClauseSecond() {
        return selectGroupClauseSecond;
    }

    public String getSelectGroupClause() {
        return selectGroupClause;
    }

    public QueryExpressionBody getNextBody() {
        return nextBody;
    }

    public IdentifierReference getNextIdentifier() {
        return continuationIdentifier;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
