package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DEFAULT_BLOCK;

public class DefaultBlock extends SwitchCase {

	public DefaultBlock() {
		setTag(DEFAULT_BLOCK);
	}

	public void accept(GASTVisitor v) {
		v.visitDefaultBlock(this);
	}
	
}
