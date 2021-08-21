package ASTMCore.ASTMSyntax.Expression;

import java.util.ArrayList;
import java.util.List;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ARRAY_ACCESS;

public class ArrayAccess extends Expression {
	
	private ArraySlice slice;
	
	public ArraySlice getSlice() {
		return slice;
	}

	public void setIndex(ArraySlice slice) {
		this.slice = slice;
	}

	public ArrayAccess() {
		setTag(ARRAY_ACCESS);
	}

	private Expression arrayName;

	public void setArrayName(Expression value) {
		this.arrayName = value;
	}

	public Expression getArrayName() {
		return this.arrayName;
	}

	private List<Expression> subScripts;

	public List<Expression> getSubScripts() {
		if (this.subScripts == null) {
			this.subScripts = new ArrayList<Expression>();
		}
		return this.subScripts;
	}

	public void setSubScripts(List<Expression> subScripts) {
		this.subScripts = subScripts;
	}

	private Expression index;

	public Expression getIndex() {
		return index;
	}

	public void setIndex(Expression index) {
		this.index = index;
	}
	
	public void accept(GASTVisitor v) {
		v.visitArrayAccess(this);
	}
	
}
