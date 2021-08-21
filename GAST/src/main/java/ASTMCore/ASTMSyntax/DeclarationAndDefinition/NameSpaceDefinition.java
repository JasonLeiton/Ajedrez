package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.ArrayList;
import java.util.Set;

import ASTMCore.ASTMSyntax.Types.NameSpaceType;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NAME_SPACE_DEFINITION;

public class NameSpaceDefinition extends DefintionObject {

	public NameSpaceDefinition() {
		setTag(NAME_SPACE_DEFINITION);
	}

	private Name nameSpace;

	public void setNameSpace(Name value) {
		this.nameSpace = value;
	}

	public Name getNameSpace() {
		return this.nameSpace;
	}


	private ArrayList<DefintionObject> body;

	public ArrayList<DefintionObject> getBody() {
		if (this.body == null) {
			this.body = new ArrayList<DefintionObject>();
		}
		return this.body;
	}


	private NameSpaceType nameSpaceType;

	public void setNameSpaceType(NameSpaceType value) {
		this.nameSpaceType = value;
	}

	public NameSpaceType getNameSpaceType() {
		return this.nameSpaceType;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitNameSpaceDefinition(this);
	}
	
	

}
