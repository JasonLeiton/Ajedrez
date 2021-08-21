package ASTMCore.ASTMSyntax.Types;

import static ASTMCore.metadata.Tags.NAMED_TYPE;

/**
 * Esta clase modela un tipo nombrado.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class NamedType extends DataType {

    public NamedType() {
        setTag(NAMED_TYPE);
    }

    /**
   * Indica el tipo.
   */
   private Type body;
   /**
    * Establece el tipo.
    * @param value Tipo
    */
   public void setBody(Type value) {
      this.body = value;
   }
   /**
    * Obtiene el tipo.
    * @return El tipo.
    */
   public Type getBody() {
      return this.body;
   }
   
   }
