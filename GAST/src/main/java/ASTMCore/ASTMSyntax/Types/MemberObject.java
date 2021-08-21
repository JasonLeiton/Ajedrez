package ASTMCore.ASTMSyntax.Types;

import java.util.List;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DefintionObject;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.MEMBER_OBJECT;

public class MemberObject extends MinorSyntaxObject {

	public MemberObject() {
		setTag(MEMBER_OBJECT);
	}

	private Integer offset;

	public void setOffset(Integer value) {
		this.offset = value;
	}

	public Integer getOffset() {
		return this.offset;
	}


	private DefintionObject member;

	public void setMember(DefintionObject value) {
		this.member = value;
	}

	public DefintionObject getMember() {
		return this.member;
	}
	private List<Modifiers> modifiers;

	public List<Modifiers> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

	public void accept(GASTVisitor v) {
		v.visitMemberObject(this);
	}
	
}
