package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.List;

import ASTMCore.ASTMSyntax.Expression.ActualParameter;
import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.SINGLE_MEMBER_ANNOTATION;


public class SingleMemberAnnotation extends Modifiers{

	public SingleMemberAnnotation() {
		setTag(SINGLE_MEMBER_ANNOTATION);
	}

	private Expression value;
	private Expression name;
	private List<ActualParameter> formalParameters;
	
	
	public Expression getValue() {
		return value;
	}
	public void setValue(Expression value) {
		this.value = value;
	}
	public Expression getName() {
		return name;
	}
	public void setName(Expression name) {
		this.name = name;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitSingleMemberAnnotation(this);
	}
	public List<ActualParameter> getFormalParameters() {
		return formalParameters;
	}
	public void setFormalParameters(List<ActualParameter> formalParameters) {
		this.formalParameters = formalParameters;
	}

	
}
