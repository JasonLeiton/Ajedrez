package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.GREATER;

public class Greater extends BinaryOperator {

    public Greater() {
        setTag(GREATER);
    }

    private String simbol=">";

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

	public void accept(GASTVisitor v) {
		v.visitGreater(this);
	}
	
}
