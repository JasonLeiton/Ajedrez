package ASTMCore.ASTMSyntax.Types;


public abstract class IntegralType extends NumberType {
   private Integer size;
   
   public void setSize(Integer value) {
      this.size = value;
   }
   
   public Integer getSize() {
      return this.size;
   }
   
   }
