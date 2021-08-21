package ASTMCore.ASTMSyntax.Types;

/**
 * Esta clase modela el tipo de un par�metro.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class FormalParameterType extends DataType {
 /**
  * Indica el tipo del par�metro.
  */
   private TypeReference type;
   /**
    * Establece el tipo del par�metro.
    * @param value El tipo.
    */
   public void setType(TypeReference value) {
      this.type = value;
   }
   /**
    * Obtiene el tipo del par�metro.
    * @return El par�metro.
    */
   public TypeReference getType() {
      return this.type;
   }
   
   }
