package ASTMCore.ASTMSyntax.Types;

/**
 * Esta clase modela el tipo de un parámetro.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class FormalParameterType extends DataType {
 /**
  * Indica el tipo del parámetro.
  */
   private TypeReference type;
   /**
    * Establece el tipo del parámetro.
    * @param value El tipo.
    */
   public void setType(TypeReference value) {
      this.type = value;
   }
   /**
    * Obtiene el tipo del parámetro.
    * @return El parámetro.
    */
   public TypeReference getType() {
      return this.type;
   }
   
   }
