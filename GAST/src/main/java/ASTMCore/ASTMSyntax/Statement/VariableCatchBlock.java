package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DataDefinition;

import static ASTMCore.metadata.Tags.VARIABLE_CATCH_BLOCK;

public class VariableCatchBlock extends CatchBlock {

   public VariableCatchBlock() {
      setTag(VARIABLE_CATCH_BLOCK);
   }

   /**
    * <pre>
    *           1..1          1..1
    * VariableCatchBlock ------------------------> DataDefinition
    *                   &lt;       exceptionVariable
    * </pre>
    */
   private DataDefinition exceptionVariable;
   
   public void setExceptionVariable(DataDefinition value) {
      this.exceptionVariable = value;
   }
   
   public DataDefinition getExceptionVariable() {
      return this.exceptionVariable;
   }
   
   }
