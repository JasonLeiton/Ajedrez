package ASTMCore.ASTMSyntax.Types;

import java.util.List;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.PARAMETERIZED_TYPE;

public class ParameterizedType extends DataType {

	public ParameterizedType() {
		setTag(PARAMETERIZED_TYPE);
	}

	private TypeReference type;
	private List<TypeReference> Arguments;

	public TypeReference getType() {
		return type;
	}
	public void setType(TypeReference type) {
		this.type = type;
	}
	public List<TypeReference> getArguments() {
		return Arguments;
	}
	public void setArguments(List<TypeReference> arguments) {
		Arguments = arguments;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitParameterizedType(this);
	}


}
