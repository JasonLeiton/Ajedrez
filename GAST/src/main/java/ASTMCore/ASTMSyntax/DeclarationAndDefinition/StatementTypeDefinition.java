package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Statement.Statement;
import ASTMCore.visitor.GASTVisitor;

public class StatementTypeDefinition extends TypeDefinition{
	private Statement statement;

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	
}
