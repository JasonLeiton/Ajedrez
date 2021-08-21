package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NOT_EQUAL;

public class NotEqual extends BinaryOperator {

    public NotEqual() {
        setTag(NOT_EQUAL);
    }

    private String simbol="!=";

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

	public void accept(GASTVisitor v) {
		v.visitNotEqual(this);
	}
	
}
