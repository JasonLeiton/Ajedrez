package gastvalidator.RPG2GASTMCertifier.Statements;

import java.util.ArrayList;

public class BlockStatement extends Statement{
	public BlockStatement() {
		// TODO Auto-generated constructor stub
		_statements = new ArrayList<Statement>();
	}

	private ArrayList<Statement> _statements;

	public ArrayList<Statement> getStatements() {
		return _statements;
	}

	public void setStatements(ArrayList<Statement> statements) {
		_statements = statements;
	}
	
	public void addStatements(Statement statement) {
		_statements.add(statement);
	}
	
	public boolean hasStatements() {
		return _statements != null && _statements.size()>0;
	}
	
	
}
