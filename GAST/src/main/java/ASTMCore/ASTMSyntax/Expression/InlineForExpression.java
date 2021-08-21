package ASTMCore.ASTMSyntax.Expression;

import java.util.List;

import ASTMCore.visitor.GASTVisitor;

public class InlineForExpression extends Expression {

	private ConditionalExpression condition;
	
	private Expression iterationBody;
	
	private List<Expression> iterators;
	
	private Expression iterator;
	
	private Expression returnedValue;
	
	private InlineForExpression nestedLoop;
	
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

	public Expression getIterationBody() {
		return iterationBody;
	}

	public void setIterationBody(Expression iterationBody) {
		this.iterationBody = iterationBody;
	}

	public ConditionalExpression getCondition() {
		return condition;
	}

	public void setCondition(ConditionalExpression condition) {
		this.condition = condition;
	}

	public Expression getIterator() {
		return iterator;
	}

	public void setIterator(Expression iterator) {
		this.iterator = iterator;
	}

	public Expression getReturnedValue() {
		return returnedValue;
	}

	public void setReturnedValue(Expression returnedValue) {
		this.returnedValue = returnedValue;
	}

	public InlineForExpression getNestedLoop() {
		return nestedLoop;
	}

	public void setNestedLoop(InlineForExpression nestedLoop) {
		this.nestedLoop = nestedLoop;
	}

	public List<Expression> getIterators() {
		return iterators;
	}

	public void setIterators(List<Expression> iterators) {
		this.iterators = iterators;
	}


}
