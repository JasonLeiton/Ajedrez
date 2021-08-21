package ASTMCore.ASTMSyntax.Types;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.List;

import static ASTMCore.metadata.Tags.CLASS_TYPE;

/**
 * Esta clase modela el tipo "Clase" y sus caracter�sticas.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class ClassType extends AggregateType {

	public ClassType() {
		setTag(CLASS_TYPE);
	}

	/**
	 * Indica de cuales clases hereda.
	 */
	private List<DerivesFrom> derivesFrom;

	/**
	 * Obtiene las clases de las cuales hereda.
	 * @return Listas de las clases que hereda.
	 */
	public List<DerivesFrom> getDerivesFrom() {
		if (this.derivesFrom == null) {
			this.derivesFrom = new ArrayList<DerivesFrom>();
		}
		return this.derivesFrom;
	}
	
	/**
	 * Establece las clases de las cuales hereda.
	 * @param derivesFrom Lista de clases que hereda.
	 */
	public void setDerivesFrom(List<DerivesFrom> derivesFrom) {
		this.derivesFrom = derivesFrom;
	}
	
	/**
	 * Indica los modificadores que posee la clase.
	 */
	private List<Modifiers> modifiers;
	
	/**
	 * Obtiene la lista con los modificadores de la clase.
	 * @return Lista con los modificadores.
	 */
	public List<Modifiers> getModifiers() {
		return modifiers;
	}
	
	/**
	 * Establece los modificadores de la clase.
	 * @param modifiers Lista de los modificadores de la clase.
	 */
	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}
	
	/**
	 * Indica si la clase es interface.
	 */
	private boolean isInterface;
	
	/**
	 * 
	 * @return Si es interface o  no.
	 */
	public boolean isInterface() {
		return isInterface;
	}
	
	/**
	 * 
	 * @param isInterface El valor de si es interface o no.
	 */
	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}
	
	/**
	 * Indica las clases que implementa 
	 */
	private List<ImplementsTo> implementesTo;

	/**
	 * Obtiene las clases que implementa.
	 * @return Lista de las clases que implementa
	 */
	public List<ImplementsTo> getImplementesTo() {
		return implementesTo;
	}
	
	/**
	 * Establece las clases que implementa.
	 * @param implementesTo Lista con las clases que implementa.
	 */
	public void setImplementesTo(List<ImplementsTo> implementesTo) {
		this.implementesTo = implementesTo;
	}
	
	/**
	 * Indica el nombre de la clase.
	 */
	private Name nameString;

	/**
	 * Obtiene el nombre de la clase.
	 * @return Nombre de la clase.
	 */
	public Name getNameString() {
		return nameString;
	}
	
	/**
	 * Establece el nombre de la clase.
	 * @param nameString Nombre de la clase.
	 */
	public void setNameString(Name nameString) {
		this.nameString = nameString;
	}

	private List<TypeParameter> parameters;

	public List<TypeParameter> getParameters() {
		return parameters;
	}
	
	public void setParameters(List<TypeParameter> parameters) {
		this.parameters = parameters;
	}

	private String packageName;

	public String getPackageName() { return packageName; }

	public void setPackageName(String packageName) { this.packageName = packageName; }

	@Override
	public void accept(GASTVisitor v) {
		v.visitClassType(this);
	}


}
