package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FORMAL_PARAMETER_DECLARATION;

public class FormalParameterDeclaration extends Declaration {

	public FormalParameterDeclaration() {
		setTag(FORMAL_PARAMETER_DECLARATION);
	}
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

	
}
