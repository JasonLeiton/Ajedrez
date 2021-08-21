package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FUNCTION_PERSISTENT;

public class FunctionPersistent extends StorageSpecification {

	public FunctionPersistent() {
		setTag(FUNCTION_PERSISTENT);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
