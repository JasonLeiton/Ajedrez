package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

public class Generator extends Expression {

	private InlineForExpression generator;
	
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

	public InlineForExpression getGenerator() {
		return generator;
	}



	public void setGenerator(InlineForExpression generator) {
		this.generator = generator;
	}
	

}
