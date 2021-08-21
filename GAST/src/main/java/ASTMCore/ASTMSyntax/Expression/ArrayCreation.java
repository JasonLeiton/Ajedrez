package ASTMCore.ASTMSyntax.Expression;

import java.util.List;

import ASTMCore.ASTMSyntax.Types.ArrayType;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ARRAY_CREATION;

public class ArrayCreation extends Expression{

	public ArrayCreation() {
		setTag(ARRAY_CREATION);
	}

	private ArrayType type;
	private List<Expression> dimension;


	public ArrayType getType() {
		return type;
	}
	
	public void setType(ArrayType type) {
		this.type = type;
	}
	
	public List<Expression> getDimension() {
		return dimension;
	}
	
	public void setDimension(List<Expression> dimension) {
		this.dimension = dimension;
	}
	
	public void accept(GASTVisitor v) {
		v.visitArrayCreation(this);
	}

}
