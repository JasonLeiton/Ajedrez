package ASTMCore.ASTMSyntax.Types;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.AccessKind;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.VirtualSpecification;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.List;

import static ASTMCore.metadata.Tags.DERIVES_FORM;

public class DerivesFrom extends MinorSyntaxObject {

	public DerivesFrom() {
		setTag(DERIVES_FORM);
	}

	private Type superclass;

	public Type getSuperclass() {
		return superclass;
	}

	public void setSuperclass(Type superclass) {
		this.superclass = superclass;
	}

	private String packageName;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	private String signature;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	private AccessKind accessKind;

	public void setAccessKind(AccessKind value) {
		this.accessKind = value;
	}

	public AccessKind getAccessKind() {
		return this.accessKind;
	}


	private VirtualSpecification virtualSpecifier;

	public void setVirtualSpecifier(VirtualSpecification value) {
		this.virtualSpecifier = value;
	}

	public VirtualSpecification getVirtualSpecifier() {
		return this.virtualSpecifier;
	}
	
	public void accept(GASTVisitor v) {
		v.visitDerivesFrom(this);
	}


}

/* This is the old code for derivesFrom, is necessary to change the mapping code in JavaCompilatioUniti to use this implementation.
public class DerivesFrom extends MinorSyntaxObject {
	private Name directClassDerived;

	public Name getDirectClassDerived() {
		return directClassDerived;
	}

	public void setDirectClassDerived(Name directClassDerived) {
		this.directClassDerived = directClassDerived;
	}


	private AccessKind accessKind;

	public void setAccessKind(AccessKind value) {
		this.accessKind = value;
	}

	public AccessKind getAccessKind() {
		return this.accessKind;
	}


	private VirtualSpecification virtualSpecifier;

	public void setVirtualSpecifier(VirtualSpecification value) {
		this.virtualSpecifier = value;
	}

	public VirtualSpecification getVirtualSpecifier() {
		return this.virtualSpecifier;
	}


	private List<TypeReference> className;

	public List<TypeReference> getClassName() {
		if (this.className == null) {
			this.className = new ArrayList<TypeReference>();
		}
		return this.className;
	}


	public void setClassName(List<TypeReference> className) {
		this.className = className;
	}
	
	public void accept(IEncoder v) {
		v.visitDerivesFrom(this);
	}


}
*/