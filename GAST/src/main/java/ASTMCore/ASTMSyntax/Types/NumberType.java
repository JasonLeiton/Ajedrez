package ASTMCore.ASTMSyntax.Types;


public abstract class NumberType extends PrimitiveType {
   private boolean isSigned;
   
   public void setIsSigned(boolean value) {
      this.isSigned = value;
   }
   
   public boolean getIsSigned() {
      return this.isSigned;
   }
   
   }
