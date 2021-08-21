package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DIVIDE;

public class Divide extends BinaryOperator {

    public Divide() {
        setTag(DIVIDE);
    }

    private String simbol="/";

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

	public void accept(GASTVisitor v) {
		v.visitDivide(this);
	}
}
