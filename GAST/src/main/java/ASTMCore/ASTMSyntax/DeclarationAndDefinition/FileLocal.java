package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FILE_LOCAL;

public class FileLocal extends StorageSpecification {

	public FileLocal() {
		setTag(FILE_LOCAL);
	}

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
}
