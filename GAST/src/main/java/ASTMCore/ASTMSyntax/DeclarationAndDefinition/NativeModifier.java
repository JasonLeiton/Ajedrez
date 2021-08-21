package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NATIVE_MODIFIER;

public class NativeModifier extends Modifiers {

	public NativeModifier() {
		setTag(NATIVE_MODIFIER);
	}

	private String modifier="native";

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitNativeModifier(this);
	}
	
}
