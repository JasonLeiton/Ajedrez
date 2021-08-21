/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

import static ASTMCore.metadata.Tags.MEMBER_ACCESS;

/**
 *
 * @author jason
 */
public class MemberAccess extends Expression{

	public MemberAccess() {
		setTag(MEMBER_ACCESS);
	}

	private String type="Memberaccess";

	ArrayList<TypeReference> identifiers;
	private Expression identifierName;
	private Expression qualifiedName;

	public void setIdentifiers(ArrayList<TypeReference> identifiers) {
		this.identifiers = identifiers;
	}

	public ArrayList<TypeReference> getIdentifiers() {
		return identifiers;
	}

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
		v.visitMemberAccess(this);
	}

}
