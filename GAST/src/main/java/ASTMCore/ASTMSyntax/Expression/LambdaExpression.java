package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.ExplicitAnonymousParameter;
import ASTMCore.ASTMSyntax.Statement.BlockStatement;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class LambdaExpression extends Expression {
    public ArrayList<ExplicitAnonymousParameter> explicitAnonymousParameter;
    public ArrayList<IdentifierReference> identifierReferenceArrayList;
    public IdentifierReference identifier;
    public Expression throwExpression;
    public BlockStatement blockStatement;

    public void setExplicitAnonymousParameter(ArrayList<ExplicitAnonymousParameter> explicitAnonymousParameter) {
        this.explicitAnonymousParameter = explicitAnonymousParameter;
    }

    public void setIdentifierReferenceArrayList(ArrayList<IdentifierReference> identifierReferenceArrayList) {
        this.identifierReferenceArrayList = identifierReferenceArrayList;
    }

    public void setIdentifier(IdentifierReference identifier) {
        this.identifier = identifier;
    }

    public void setThrowExpression(Expression throwExpression) {
        this.throwExpression = throwExpression;
    }

    public void setBlockStatement(BlockStatement blockStatement) {
        this.blockStatement = blockStatement;
    }

    public ArrayList<ExplicitAnonymousParameter> getExplicitAnonymousParameter() {
        return explicitAnonymousParameter;
    }

    public ArrayList<IdentifierReference> getIdentifierReferenceArrayList() {
        return identifierReferenceArrayList;
    }

    public IdentifierReference getIdentifier() {
        return identifier;
    }

    public Expression getThrowExpression() {
        return throwExpression;
    }

    public BlockStatement getBlockStatement() {
        return blockStatement;
    }

    @Override
    public void accept(GASTVisitor v) {

    }

}
