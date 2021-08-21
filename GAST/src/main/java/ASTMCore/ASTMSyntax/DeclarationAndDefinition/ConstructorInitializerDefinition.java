package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Expression.ActualParameter;
import ASTMCore.ASTMSyntax.Statement.Statement;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class ConstructorInitializerDefinition extends Statement {
    String type;
    ArrayList<ActualParameter> argumentList;

    public void setType(String type) {
        this.type = type;
    }

    public void setArgumentList(ArrayList<ActualParameter> argumentList) {
        this.argumentList = argumentList;
    }

    public String getType() {
        return type;
    }

    public ArrayList<ActualParameter> getArgumentList() {
        return argumentList;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
