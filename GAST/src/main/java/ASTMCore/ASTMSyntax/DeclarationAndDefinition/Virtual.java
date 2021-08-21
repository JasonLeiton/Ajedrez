package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.VIRTUAL;

public class Virtual extends VirtualSpecification {

	public Virtual() {
		setTag(VIRTUAL);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
