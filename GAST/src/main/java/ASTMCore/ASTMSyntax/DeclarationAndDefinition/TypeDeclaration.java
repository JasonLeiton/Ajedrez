package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Types.TypeReference;

public abstract class TypeDeclaration extends DefintionObject {
  
   private TypeReference typeReference;
   
   public void setTypeReference(TypeReference value) {
      this.typeReference = value;
   }
   
   public TypeReference getTypeReference() {
      return this.typeReference;
   }
   
   }
