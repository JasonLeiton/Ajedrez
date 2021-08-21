package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.visitor.GASTVisitor;

public class MemberInitializer extends Expression {
    private Name identifier;
    private Expression index;
    private Expression value;

    public Name getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Name identifier) {
        this.identifier = identifier;
    }

    public Expression getIndex() {
        return index;
    }

    public void setIndex(Expression index) {
        this.index = index;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
