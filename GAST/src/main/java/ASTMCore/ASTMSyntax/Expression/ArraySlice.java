package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;
/*
 * Esta clase modela la expresión para acceder una lista por rangos
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class ArraySlice extends Expression {
	
	private Expression initialIndex;
	private Expression endIndex;
	private Expression step;
	private Expression jumpsIndex;
	
	
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

	public Expression getInitialIndex() {
		return initialIndex;
	}


	public void setInitialIndex(Expression initialIndex) {
		this.initialIndex = initialIndex;
	}


	public Expression getEndIndex() {
		return endIndex;
	}


	public void setEndIndex(Expression endIndex) {
		this.endIndex = endIndex;
	}


	public Expression getStep() {
		return step;
	}


	public void setStep(Expression step) {
		this.step = step;
	}


	public Expression getJumpsIndex() {
		return jumpsIndex;
	}


	public void setJumpsIndex(Expression jumpsIndex) {
		this.jumpsIndex = jumpsIndex;
	}
}


