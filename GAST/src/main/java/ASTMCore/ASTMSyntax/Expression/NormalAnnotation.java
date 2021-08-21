package ASTMCore.ASTMSyntax.Expression;

import java.util.List;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NORMAL_ANNOTATION;

public class NormalAnnotation extends Expression {

	public NormalAnnotation() {
		setTag(NORMAL_ANNOTATION);
	}

	private Expression name;
	private List<MemberValuePair> value;
	public Expression getName() {
		return name;
	}
	public void setName(Expression name) {
		this.name = name;
	}
	public List<MemberValuePair> getValue() {
		return value;
	}
	public void setValue(List<MemberValuePair> value) {
		this.value = value;
	}
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

	
}
