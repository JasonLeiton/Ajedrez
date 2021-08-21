package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.LESS;

public class Less extends BinaryOperator {

    public Less() {
        setTag(LESS);
    }

    private String simbol="<";

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }
    
	public void accept(GASTVisitor v) {
		v.visitLess(this);
	}
	
}
