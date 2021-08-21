package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.ArrayList;
import java.util.Set;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FUNCTION_DECLARATION;

/**
 * Esta clase modela la declaraci�n  de una funci�n.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class FunctionDeclaration extends Declaration {

	public FunctionDeclaration() {
		setTag(FUNCTION_DECLARATION);
	}

	/**
	 * Lista de par�metros de la funci�n
	 */
	private ArrayList<FormalParameterDeclaration> formalParameters;
	/**
	 * Obtiene la lista de los par�metros.
	 * @return Lista de los par�metros.
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
