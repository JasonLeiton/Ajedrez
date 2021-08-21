package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.GASTMSyntaxObject;
import ASTMCore.visitor.GASTVisitor;
/**
 * Esta clase modela un subtipo de elemento sint�ctico.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class DefintionObject extends GASTMSyntaxObject {
	
	abstract public void accept(GASTVisitor v);
}
