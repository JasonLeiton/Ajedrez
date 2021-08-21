package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.MULTIPLY;

public class Multiply extends BinaryOperator {

    public Multiply() {
        setTag(MULTIPLY);
    }

    private String simbol="*";

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

	public void accept(GASTVisitor v) {
		v.visitMultiply(this);
	}
	
}
