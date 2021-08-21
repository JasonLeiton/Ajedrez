package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.EQUAL;

public class Equal extends BinaryOperator {

    public Equal() {
        setTag(EQUAL);
    }

    private String simbolo="equal";

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    
	public void accept(GASTVisitor v) {
		v.visitEqual(this);
	}
	
}
