package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DefintionObject;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DECLARATION_OR_FUNCTION_STATEMENT;

public class DeclarationOrDefinitionStatement extends Statement {

	public DeclarationOrDefinitionStatement() {
		setTag(DECLARATION_OR_FUNCTION_STATEMENT);
	}

	/**
	 * <pre>
	 *           1..1          1..1
	 * DeclarationOrDefinitionStatement ------------------------> DefintionObject
	 *                   &lt;       declOrDefn
	 * </pre>
	 */
	private DefintionObject declOrDefn;

	public void setDeclOrDefn(DefintionObject value) {
		this.declOrDefn = value;
	}

	public DefintionObject getDeclOrDefn() {
		return this.declOrDefn;
	}


	public void accept(GASTVisitor v) {
		v.visitDeclarationOrDefinitionStatement(this);
	}
	
}
