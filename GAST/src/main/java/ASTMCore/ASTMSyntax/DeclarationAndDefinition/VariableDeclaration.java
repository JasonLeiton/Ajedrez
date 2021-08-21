package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.VARIABLE_DECLARATION;

public class VariableDeclaration extends Declaration {

	public VariableDeclaration() {
		setTag(VARIABLE_DECLARATION);
	}

	private Boolean isMutable;

	public void setIsMutable(Boolean value) {
		this.isMutable = value;
	}

	public Boolean getIsMutable() {
		return this.isMutable;
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
