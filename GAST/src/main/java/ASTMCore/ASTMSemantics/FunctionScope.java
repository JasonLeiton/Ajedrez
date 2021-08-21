package ASTMCore.ASTMSemantics;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.FunctionDefintion;

import static ASTMCore.metadata.Tags.FUNCTION_SCOPE;

/**
 * Esta clase indica el alcance de una funci�n o m�todo.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class FunctionScope extends Scope {

   public FunctionScope() {
      setTag(FUNCTION_SCOPE);
   }

   /**
    * A quien pertenece el alcance de la funci�n.
    */
   private FunctionDefintion scopeOpenedBy;
   /**
    * Establece  a quien le pertenece el scope.
    * @param value Funci�n que 
    */
   public void setScopeOpenedBy(FunctionDefintion value) {
      this.scopeOpenedBy = value;
   }
   /**
    * 
    * @return Obtiene el objeto scopeOpenedBy
    */
   public FunctionDefintion getScopeOpenedBy() {
      return this.scopeOpenedBy;
   }
   
   }
