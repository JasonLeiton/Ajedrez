package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.MODULUS;

public class Modulus extends BinaryOperator {

    public Modulus() {
        setTag(MODULUS);
    }

    private String simbol="%";

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }
	
	public void accept(GASTVisitor v) {
		v.visitModulus(this);
	}
	
}
