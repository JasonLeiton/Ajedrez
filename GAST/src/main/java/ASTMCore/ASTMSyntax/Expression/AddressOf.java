package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ADDRESS_OF;

public class AddressOf extends UnaryOperator {

	public AddressOf() {
		setTag(ADDRESS_OF);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
