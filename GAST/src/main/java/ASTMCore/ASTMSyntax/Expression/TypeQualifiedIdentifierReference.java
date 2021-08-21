package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

import static ASTMCore.metadata.Tags.TYPE_QUALIFIED_IDENTIFIER_REFERENCE;

public class TypeQualifiedIdentifierReference extends NameReference {

   public TypeQualifiedIdentifierReference() {
      setTag(TYPE_QUALIFIED_IDENTIFIER_REFERENCE);
   }

   private ArrayList<TypeReference> aggregateType;
   
   public ArrayList<TypeReference> getAggregateType() {
      if (this.aggregateType == null) {
         this.aggregateType = new ArrayList<TypeReference>();
      }
      return this.aggregateType;
   }
   
 
   private IdentifierReference member;
   
   public void setMember(IdentifierReference value) {
      this.member = value;
   }
   
   public IdentifierReference getMember() {
      return this.member;
   }

      @Override
      public void accept(GASTVisitor v) {
          // TODO Auto-generated method stub

      }
   
}
