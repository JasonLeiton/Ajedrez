package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import ASTMCore.ASTMSyntax.Statement.Statement;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ENTRY_DEFINITION;

public class EntryDefinition extends Definition {

	public EntryDefinition() {
		setTag(ENTRY_DEFINITION);
	}

	private ArrayList<FormalParameterDefinition> formalParameters;

	public ArrayList<FormalParameterDefinition> getFormalParameters() {
		if (this.formalParameters == null) {
			this.formalParameters = new ArrayList<FormalParameterDefinition>();
		}
		return this.formalParameters;
	}


	private ArrayList<Statement> body;

	public ArrayList<Statement> getBody() {
		if (this.body == null) {
			this.body = new ArrayList<Statement>();
		}
		return this.body;
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
