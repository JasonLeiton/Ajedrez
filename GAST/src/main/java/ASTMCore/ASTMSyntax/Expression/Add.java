package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ADD;

public class Add extends BinaryOperator {

    public Add() {
        setTag(ADD);
    }

    private String simbol="+";

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }
    
	public void accept(GASTVisitor v) {
		v.visitAdd(this);
	}
	
}
