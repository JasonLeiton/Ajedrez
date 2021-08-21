package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Types.Type;

import java.util.ArrayList;

import static ASTMCore.metadata.Tags.TYPES_CATCH_BLOCK;

public class TypesCatchBlock extends CatchBlock {

   public TypesCatchBlock() {
      setTag(TYPES_CATCH_BLOCK);
   }

   /**
    * <pre>
    *           1..1          1..*
    * TypesCatchBlock ------------------------> Type
    *                   &lt;       exceptions
    * </pre>
    */
   private ArrayList<Type> exceptions;
   
   public ArrayList<Type> getExceptions() {
      if (this.exceptions == null) {
         this.exceptions = new ArrayList<Type>();
      }
      return this.exceptions;
   }
   
   }
