package ASTMCore.ASTMSyntax.Types;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.UNNAMED_TYPE_REFERENCE;

/**
 * Esta clase modela una referencia sin nombrar.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class UnnamedTypeReference extends TypeReference {

	public UnnamedTypeReference() {
		setTag(UNNAMED_TYPE_REFERENCE);
	}

	/**
	 * Indica el tipo.
	 */
	private Type type;
	/**
	 * Establece el tipo.
	 * @param value El tipo.
	 */
	public void setType(Type value) {
		this.type = value;
	}
	/**
	 * Obtiene el tipo.
	 * @return El tipo.
	 */
	public Type getType() {
		return this.type;
	}
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub

	}

}
