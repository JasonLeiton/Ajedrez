package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Types.NamedType;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NAMED_TYPE_DEFINITION;

public class NamedTypeDefinition extends TypeDefinition {

	public NamedTypeDefinition() {
		setTag(NAMED_TYPE_DEFINITION);
	}

	private NamedType definitionType;

	public void setDefinitionType(NamedType value) {
		this.definitionType = value;
	}

	public NamedType getDefinitionType() {
		return this.definitionType;
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
