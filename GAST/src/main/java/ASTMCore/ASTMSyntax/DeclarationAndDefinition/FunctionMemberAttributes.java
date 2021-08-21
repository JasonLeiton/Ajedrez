package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.FUNCTION_MEMBER_ATTRIBUTE;

public class FunctionMemberAttributes extends MinorSyntaxObject {

   public FunctionMemberAttributes() {
      setTag(FUNCTION_MEMBER_ATTRIBUTE);
   }

   private Boolean isFriend;
   
   public void setIsFriend(Boolean value) {
      this.isFriend = value;
   }
   
   public Boolean getIsFriend() {
      return this.isFriend;
   }
   
   private Boolean isInline;
   
   public void setIsInline(Boolean value) {
      this.isInline = value;
   }
   
   public Boolean getIsInline() {
      return this.isInline;
   }
   
   private Boolean isThisConst;
   
   public void setIsThisConst(Boolean value) {
      this.isThisConst = value;
   }
   
   public Boolean getIsThisConst() {
      return this.isThisConst;
   }
   
 
   private VirtualSpecification virtualSpecifier;
   
   public void setVirtualSpecifier(VirtualSpecification value) {
      this.virtualSpecifier = value;
   }
   
   public VirtualSpecification getVirtualSpecifier() {
      return this.virtualSpecifier;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
