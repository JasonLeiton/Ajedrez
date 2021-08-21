package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.List;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FORMAL_PARAMETER_DEFINITION;

public class FormalParameterDefinition extends DataDefinition {

	/**
	 * Modificadores del paramentro
	 */
	private List<Modifiers> modifiers;
	
	/**
	 * Obtiene la lista de modificadores
	 * @return Modificadores.
	 */
	public List<Modifiers> getModifiers() {
		return modifiers;
	}
	
	/**
	 * Establece los modificadores de la funci�n.
	 * @param modifiers Modificadores.
	 */
	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

	public FormalParameterDefinition() {
		setTag(FORMAL_PARAMETER_DEFINITION);
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitFormalParameterDefinition(this);
	}


}
