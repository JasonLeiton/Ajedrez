package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

public class DictValue extends Expression{
	private Expression Key;
	private Expression Value;
	
	public Expression getKey() {
		return Key;
	}
	public void setKey(Expression key) {
		Key = key;
	}
	public Expression getValue() {
		return Value;
	}
	public void setValue(Expression value) {
		Value = value;
	}
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}
	
	
}
