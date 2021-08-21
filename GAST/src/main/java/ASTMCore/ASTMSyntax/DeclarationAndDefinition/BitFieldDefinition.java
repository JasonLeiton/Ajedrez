package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.BIT_FIELD_DEFINITION;

public class BitFieldDefinition extends DataDefinition {

	public BitFieldDefinition() {
		setTag(BIT_FIELD_DEFINITION);
	}

	private Expression bitFieldSize;

	public void setBitFieldSize(Expression value) {
		this.bitFieldSize = value;
	}

	public Expression getBitFieldSize() {
		return this.bitFieldSize;
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
