package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ACCESS_KIND;

/**
 * Esta clase modela el tipo de acceso para los elementos sintácticos del código.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class AccessKind extends MinorSyntaxObject {

	public AccessKind() {
		setTag(ACCESS_KIND);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
