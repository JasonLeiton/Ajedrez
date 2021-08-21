package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.List;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ANNOTATION_MEMBER_DECLARATION;

public class AnnotationMemberDeclaration extends DeclarationOrDefinition {

	public AnnotationMemberDeclaration() {
		setTag(ANNOTATION_MEMBER_DECLARATION);
	}

	private Expression defuaultValue;
	private List<Modifiers> modifiers;
	private Name name;
	private TypeReference type;
	public Expression getDefuaultValue() {
		return defuaultValue;
	}
	public void setDefuaultValue(Expression defuaultValue) {
		this.defuaultValue = defuaultValue;
	}
	public List<Modifiers> getModifiers() {
		return modifiers;
	}
	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public TypeReference getType() {
		return type;
	}
	public void setType(TypeReference type) {
		this.type = type;
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}


}
