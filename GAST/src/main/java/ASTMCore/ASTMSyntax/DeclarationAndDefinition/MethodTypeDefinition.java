package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.visitor.GASTVisitor;

public class MethodTypeDefinition extends TypeDefinition{
	private FunctionDefintion method;
	   
	public FunctionDefintion getMethod() {
		return method;
	}
	public void setMethod(FunctionDefintion method) {
		this.method = method;
	}
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
			
	}
}
