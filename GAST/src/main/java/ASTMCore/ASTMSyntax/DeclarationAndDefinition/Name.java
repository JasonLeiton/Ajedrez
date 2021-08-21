package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NAME;

/**
 * Esta clase es para modelar el identificador de un elemento.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class Name extends MinorSyntaxObject {



	/**
	 * Nombre  del elemento. 
	 */
	private String nameString;

	public Name() {
		setTag(NAME);
	}

	public Name(String nameString) {
		this.nameString = nameString;
	}
	/**
	 * Establece el nombre.
	 * @param value Nombre que se desea asignar.
	 */
	public void setNameString(String value) {
		this.nameString = value;
	}
	/**
	 * 
	 * @return Nombre del objeto.
	 */
	public String getNameString() {
		return this.nameString;
	}


	private Name ofTypeReference;

	public void setOfTypeReference(Name value) {
		this.ofTypeReference = value;
	}

	public Name getOfTypeReference() {
		return this.ofTypeReference;
	}
	
	public void accept(GASTVisitor v) {
		v.visitName(this);

	}

}
