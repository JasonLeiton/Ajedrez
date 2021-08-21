package ASTMCore.ASTMSyntax.Types;

import java.util.ArrayList;
import java.util.List;

import ASTMCore.ASTMSemantics.AggregateScope;
import ASTMCore.visitor.GASTVisitor;
/**
 * Esta clase modela un tipo agregado, por ejemplo los enumerados y clases.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class AggregateType extends DataType {
	/**
	 * Lista de atributos del tipo
	 */
	private List<MemberObject> members;
	/**
	 * Obtiene la lista de atributos.
	 * @return Lista de atributos.
	 */
	public List<MemberObject> getMembers() {
		if (this.members == null) {
			this.members = new ArrayList<MemberObject>();
		}
		return this.members;
	}

	/**
	 * Establece los atributos. 
	 * @param members Lista de atributos del tipo.
	 */
	public void setMembers(List<MemberObject> members) {
		this.members = members;
	}


	/**
	 * Alcance del tipo agregado
	 */
	private AggregateScope opensScope;
	/**
	 * Establece el alcance de tipo agregado.
	 * @param value Alcance del tipo agregado.
	 */
	public void setOpensScope(AggregateScope value) {
		this.opensScope = value;
	}
	/**
	 * Obtiene el alcance del tipo agregado.
	 * @return Alcance del tipo agregado.
	 */
	public AggregateScope getOpensScope() {
		if (this.opensScope==null)
			this.opensScope=new AggregateScope();
		return this.opensScope;
	}
	
	abstract public void accept(GASTVisitor v);

}
