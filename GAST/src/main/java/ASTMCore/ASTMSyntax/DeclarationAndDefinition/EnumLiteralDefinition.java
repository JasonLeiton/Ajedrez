package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.List;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ENUM_LITERAL_DEFINITION;

public class EnumLiteralDefinition extends Definition {

	public EnumLiteralDefinition() {
		setTag(ENUM_LITERAL_DEFINITION);
	}

	private List<Name> identifiers;
	private List<Expression> arguments;
	private Expression initialValue;
	private List<Expression> initialValues;
	private List<Modifiers> modifiers;
	private Name identifier;

	public List<Expression> getArguments() {
		return arguments;
	}
	
	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}
	
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
	
	

	@Override
	public void accept(GASTVisitor v) {
		v.visitEnumLiteralDefinition(this);
	}

	public Expression getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(Expression initialValue) {
		this.initialValue = initialValue;
	}

	public List<Name> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<Name> identifiers) {
		this.identifiers = identifiers;
	}

	public List<Expression> getInitialValues() {
		return initialValues;
	}

	public void setInitialValues(List<Expression> initialValues) {
		this.initialValues = initialValues;
	}
}
