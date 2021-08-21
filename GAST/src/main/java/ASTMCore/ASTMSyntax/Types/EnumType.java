package ASTMCore.ASTMSyntax.Types;

import ASTMCore.ASTMSemantics.AggregateScope;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.EnumLiteralDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.visitor.GASTVisitor;

import java.util.List;

import static ASTMCore.metadata.Tags.ENUM_TYPE;

/**
 * Esta clase modela el tipo de dato enumerado.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class EnumType extends DataType {

	public EnumType() {
		setTag(ENUM_TYPE);
	}

	/**
	 * Indica los elementos del enumerado.
	 */
	private List<EnumLiteralDefinition> enumLiterals;

	private List<ImplementsTo> implementesTo;

	private List<Modifiers> modifiers;

	private List<MemberObject> members;

	private AggregateScope opensScope;

	private String identifier;

	public List<EnumLiteralDefinition> getEnumLiterals() {
		return enumLiterals;
	}

	public void setEnumLiterals(List<EnumLiteralDefinition> enumLiterals) {
		this.enumLiterals = enumLiterals;
	}

	public List<ImplementsTo> getImplementesTo() {
		return implementesTo;
	}

	public void setImplementesTo(List<ImplementsTo> implementesTo) {
		this.implementesTo = implementesTo;
	}

	public List<Modifiers> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

	public AggregateScope getOpensScope() {
		if (opensScope==null){
			opensScope=new AggregateScope();
		}
		return opensScope;
	}

	public void setOpensScope(AggregateScope opensScope) {
		this.opensScope = opensScope;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<MemberObject> getMembers() {
		return members;
	}

	public void setMembers(List<MemberObject> members) {
		this.members = members;
	}

	public void accept(GASTVisitor v) {
		v.visitEnumType(this);
	}

}
