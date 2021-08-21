package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.AGGREGATE_TYPE_DECLARATION;

/**
 * Esta clase modela la declaraci�n de un elemento agregado por ejemplo una clase.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class AggregateTypeDeclaration extends TypeDeclaration {

	public AggregateTypeDeclaration() {
		setTag(AGGREGATE_TYPE_DECLARATION);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
