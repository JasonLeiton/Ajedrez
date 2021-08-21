package ASTMCore.ASTMSyntax.Types;

import ASTMCore.ASTMSyntax.GASTMSyntaxObject;
import ASTMCore.visitor.GASTVisitor;
/**
 * Esta clase modela un tipo y es la clase padre del paquete Types.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class Type extends GASTMSyntaxObject {
	/**
	 * Indica si es constante
	 */
	private Boolean isConst;
	/**
	 * Establece el valor de si es constante.
	 * @param value Valor de si es constante.
	 */
	public void setIsConst(Boolean value) {
		this.isConst = value;
	}
	/**
	 * Obtiene el valor de si es constante.
	 * @return Valor de si es constante.
	 */
	public Boolean getIsConst() {
		return this.isConst;
	}
	
	public void accept(GASTVisitor v) {
		// TODO: This method must be an abstract method, and every specialization of this class must implement the method itself.
		System.err.println("Accept method in class " + getClass().getName() + " not implemetend yet" );
	}

}
