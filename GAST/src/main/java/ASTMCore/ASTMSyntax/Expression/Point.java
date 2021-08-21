/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.POINT;

/**
 *
 * @author jason
 */
public class Point extends BinaryOperator{

    public Point() {
        setTag(POINT);
    }

    private String simbol=".";

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
    
    
}
