package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import java.util.List;

import ASTMCore.ASTMSyntax.Types.EnumType;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ENUM_TYPE_DEFINITION;

/**
 * Esta clase modela la definici�n de un enumerado.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class EnumTypeDefinition extends TypeDefinition {

	public EnumTypeDefinition() {
		setTag(ENUM_TYPE_DEFINITION);
	}

	/**
	 * Enumerado
	 */
	private EnumType definitionType;
	
	private List<Modifiers> modifiers;
	
	/**
	 * Establece el enumerado.
	 * @param value El enumerado.
	 */
	public void setDefinitionType(EnumType value) {
		this.definitionType = value;
	}
	
	/**
	 * Obtiene el enumerado.
	 * @return El enumerado.
	 */
	public EnumType getDefinitionType() {
		return this.definitionType;
	}

	
	
	@Override
	public void accept(GASTVisitor v) {
		v.visitEnumTypeDefinition(this);
	}

	public List<Modifiers> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

}
