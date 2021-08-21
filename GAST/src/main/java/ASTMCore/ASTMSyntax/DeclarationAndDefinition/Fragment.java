package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FRAGMENT;

public class Fragment  extends DataDefinition {

	public Fragment() {
		setTag(FRAGMENT);
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitFragment(this);

	}



}
