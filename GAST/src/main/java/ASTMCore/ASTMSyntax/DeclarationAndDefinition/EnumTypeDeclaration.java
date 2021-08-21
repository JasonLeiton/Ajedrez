package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ENUM_TYPE_DECLARATION;

public class EnumTypeDeclaration extends TypeDeclaration {

	public EnumTypeDeclaration() {
		setTag(ENUM_TYPE_DECLARATION);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
