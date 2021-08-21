package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.ExplicitAnonymousParameter;
import ASTMCore.ASTMSyntax.Statement.BlockStatement;
import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class AnonymousMethodExpression extends  Expression {
ArrayList<ExplicitAnonymousParameter> explicitAnonymousParameterArrayList;
    public BlockStatement blockStatement;

    public void setExplicitAnonymousParameterArrayList(ArrayList<ExplicitAnonymousParameter> explicitAnonymousParameterArrayList) {
        this.explicitAnonymousParameterArrayList = explicitAnonymousParameterArrayList;
    }

    public ArrayList<ExplicitAnonymousParameter> getExplicitAnonymousParameterArrayList() {
        return explicitAnonymousParameterArrayList;
    }

    public void setBlockStatement(BlockStatement blockStatement) {
        this.blockStatement = blockStatement;
    }

    public BlockStatement getBlockStatement() {
        return blockStatement;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
