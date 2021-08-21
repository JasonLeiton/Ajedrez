package ASTMCore.ASTMSyntax.Types;

import java.util.List;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.TYPE_PARAMETER;

public class TypeParameter extends Type{

	public TypeParameter() {
		setTag(TYPE_PARAMETER);
	}

	private List<Modifiers> modifiers;
	private Name identifier;
	private List<Type> property;

	public List<Modifiers> getModifiers() {
		return modifiers;
	}
	
	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}
	
	public Name getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(Name identifier) {
		this.identifier = identifier;
	}
	
	public List<Type> getProperty() {
		return property;
	}
	
	public void setProperty(List<Type> property) {
		this.property = property;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitTypeParameter(this);
	}

}
