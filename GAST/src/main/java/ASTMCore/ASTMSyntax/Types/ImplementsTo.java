package ASTMCore.ASTMSyntax.Types;

import java.util.ArrayList;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.AccessKind;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.VirtualSpecification;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.IMPLEMENTS_TO;

public class ImplementsTo extends MinorSyntaxObject {

	public ImplementsTo() {
		setTag(IMPLEMENTS_TO);
	}

	private TypeReference implementsTo;
	private Name directImplements;
		
	public Name getDirectImplements() {
			return directImplements;
		}

		public void setDirectImplements(Name directImplements) {
			this.directImplements = directImplements;
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
	   
	
	   private ArrayList<NamedTypeReference> className;
	   
	   public ArrayList<NamedTypeReference> getClassName() {
	      if (this.className == null) {
	         this.className = new ArrayList<NamedTypeReference>();
	      }
	      return this.className;
	   }

	public TypeReference getImplementsTo() {
		return implementsTo;
	}

	public void setImplementsTo(TypeReference implementsTo) {
		this.implementsTo = implementsTo;
	}

	public void setClassName(ArrayList<NamedTypeReference> className) {
		this.className = className;
	}

		private String interfaceName;
	    private String packageName;
	    private String signature;

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void accept(GASTVisitor v) {
		v.visitImplementsTo(this);
	}
	   
}
