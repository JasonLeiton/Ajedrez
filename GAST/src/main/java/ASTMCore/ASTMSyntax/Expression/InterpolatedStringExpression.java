package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class InterpolatedStringExpression extends Expression {
    @Override
    public void accept(GASTVisitor v) {

    }
    ArrayList<Expression> expressionArrayList;

    public ArrayList<Expression> getExpressionArrayList() {
        return expressionArrayList;
    }

    public void setExpressionArrayList(ArrayList<Expression> expressionArrayList) {
        this.expressionArrayList = expressionArrayList;
    }
}
