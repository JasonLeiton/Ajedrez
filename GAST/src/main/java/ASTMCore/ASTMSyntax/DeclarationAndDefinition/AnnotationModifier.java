package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ANNOTATION_MODIFIER;

public class AnnotationModifier extends Modifiers {

	public AnnotationModifier() {
		setTag(ANNOTATION_MODIFIER);
	}

	private String modifier;

	public AnnotationModifier(String modifier) {
		this.modifier=modifier;
	}
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitAnnotationModifier(this);
	}
}
