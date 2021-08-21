package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.ArrayList;
import java.util.Set;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FUNCTION_DECLARATION;

/**
 * Esta clase modela la declaración  de una función.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class FunctionDeclaration extends Declaration {

	public FunctionDeclaration() {
		setTag(FUNCTION_DECLARATION);
	}

	/**
	 * Lista de parámetros de la función
	 */
	private ArrayList<FormalParameterDeclaration> formalParameters;
	/**
	 * Obtiene la lista de los parámetros.
	 * @return Lista de los parámetros.
	 */
	public ArrayList<FormalParameterDeclaration> getFormalParameters() {
		if (this.formalParameters == null) {
			this.formalParameters = new ArrayList<FormalParameterDeclaration>();
		}
		return this.formalParameters;
	}


	private FunctionMemberAttributes functionMemberAttributes;

	public void setFunctionMemberAttributes(FunctionMemberAttributes value) {
		this.functionMemberAttributes = value;
	}

	public FunctionMemberAttributes getFunctionMemberAttributes() {
		return this.functionMemberAttributes;
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
