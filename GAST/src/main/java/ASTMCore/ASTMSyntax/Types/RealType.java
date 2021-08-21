package ASTMCore.ASTMSyntax.Types;


public abstract class RealType extends NumberType {
   private Integer precision;
   
   public void setPrecision(Integer value) {
      this.precision = value;
   }
   
   public Integer getPrecision() {
      return this.precision;
   }
   
   }
