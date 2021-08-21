package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.SUBTRACT;

public class Subtract extends BinaryOperator {

    public Subtract() {
        setTag(SUBTRACT);
    }

    private String simbol="-";

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

	public void accept(GASTVisitor v) {
		v.visitSubtract(this);
	}
	
}
