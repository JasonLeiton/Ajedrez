package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.IMPORT_DECLARATION;

/**
 * Esta clase modela la importaci�n de un archivo.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class ImportDeclaration extends Declaration {

	public ImportDeclaration() {
		setTag(IMPORT_DECLARATION);
	}

	/**
	 * Indica si es est�tico.
	 */
	private boolean isStatic;
	
	/**
	 * 
	 * @return Valor booleano que indica si es est�tica.
	 */
	public boolean isStatic() {
		return isStatic;
	}
	
	/**
	 * 
	 * @param isStatic  Valor booleano que indica si es est�tica.
	 */
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitImportDeclaration(this);
	}

}
