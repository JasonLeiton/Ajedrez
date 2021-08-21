package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.PER_CLASS_MEMBER;

public class PerClassMember extends StorageSpecification {

	public PerClassMember() {
		setTag(PER_CLASS_MEMBER);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
