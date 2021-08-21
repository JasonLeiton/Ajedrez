package ASTMCore.ASTMSyntax.Types;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.TypeDefinition;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.NAMED_TYPE_REFERENCE;

public class NamedTypeReference extends TypeReference {

	public NamedTypeReference() {
		setTag(NAMED_TYPE_REFERENCE);
	}

	private Name typeName;

	public NamedTypeReference(Name typeName) {
		this.typeName = typeName;
	}

	public void setTypeName(Name value) {
		this.typeName = value;
	}

	public Name getTypeName() {
		return this.typeName;
	}


	private TypeDefinition type;

	public void setType(TypeDefinition value) {
		this.type = value;
	}

	public TypeDefinition getType() {
		return this.type;
	}
	private DataType dataType;

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitNamedTypeReference(this);
	}

}
