package ASTMCore.ASTMSyntax.Types;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.STRUCTURE_TYPE;

public class StructureType extends AggregateType {

	public StructureType() {
		setTag(STRUCTURE_TYPE);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
