/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;
//import encoders.IFrame;

/**
 *
 * @author jason
 */
public class PointerAccess extends Expression{
    private String type="Pointeraccess";

    private Expression identifierName;
    private Expression qualifiedName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Expression getIdentifierName() {
        return identifierName;
    }

    public void setIdentifierName(Expression identifierName) {
        this.identifierName = identifierName;
    }

    public Expression getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(Expression expression) {
        this.qualifiedName = expression;
    }

    public void accept(GASTVisitor v) {
        v.visitPointerAccess(this);
    }

}
