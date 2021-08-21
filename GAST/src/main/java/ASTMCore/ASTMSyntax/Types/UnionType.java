package ASTMCore.ASTMSyntax.Types;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.UNION_TYPE;

public class UnionType extends AggregateType {

	public UnionType() {
		setTag(UNION_TYPE);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
