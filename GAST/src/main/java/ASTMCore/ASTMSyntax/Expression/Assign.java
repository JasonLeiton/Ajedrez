package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ASSIGN;

public class Assign extends BinaryOperator {

    public Assign() {
        setTag(ASSIGN);
    }

    private String simbolo="=";

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

	public void accept(GASTVisitor v) {
		v.visitAssign(this);
	}
    
}
