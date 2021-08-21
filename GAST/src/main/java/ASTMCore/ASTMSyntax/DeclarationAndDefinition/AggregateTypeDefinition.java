package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Types.AggregateType;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.AGGREGATE_TYPE_DEFINITION;

/**
 * Esta clase modela la definici�n de un elemento agregado por ejemplo una clase.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class AggregateTypeDefinition extends TypeDefinition {

	public AggregateTypeDefinition() {
		setTag(AGGREGATE_TYPE_DEFINITION);
	}

	/**
	 * Tipo agregado.
	 */
	private AggregateType aggregateType;

	public void setAggregateType(AggregateType value) {
		this.aggregateType = value;
	}

	public AggregateType getAggregateType() {
		return this.aggregateType;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitAggregateTypeDefinition(this);
	}

}
