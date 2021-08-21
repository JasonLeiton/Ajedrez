package ASTMCore.ASTMSemantics;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.AGGREGATE_SCOPE;

/**
 * Esta clase modela el alcance de un tipo agregado.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class AggregateScope extends Scope {

	public AggregateScope() {
		setTag(AGGREGATE_SCOPE);
	}

	public void accept(GASTVisitor v) {
		v.visitAggregateScope(this);
	}

}
