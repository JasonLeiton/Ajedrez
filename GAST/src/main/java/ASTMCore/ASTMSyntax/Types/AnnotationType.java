package ASTMCore.ASTMSyntax.Types;

import java.util.List;

import ASTMCore.ASTMSemantics.AggregateScope;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.TypeDefinition;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ANNOTATION_TYPE;

public class AnnotationType extends TypeDefinition {

	public AnnotationType() {
		setTag(ANNOTATION_TYPE);
	}

	private List<Modifiers> modifiers;
	private Name name;
	private AggregateScope opensScope;
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
	public AggregateScope getOpensScope() {
		return opensScope;
	}
	public void setOpensScope(AggregateScope opensScope) {
		this.opensScope = opensScope;
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}


}
