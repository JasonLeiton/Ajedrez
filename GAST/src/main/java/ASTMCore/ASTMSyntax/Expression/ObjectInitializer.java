package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class ObjectInitializer extends Expression{
    public ArrayList<Expression> getMember_initializers() {
        return member_initializers;
    }

    public void setMember_initializers(ArrayList<Expression> member_initializers) {
        this.member_initializers = member_initializers;
    }

    ArrayList<Expression> member_initializers;

    @Override
    public void accept(GASTVisitor v) {

    }
}
