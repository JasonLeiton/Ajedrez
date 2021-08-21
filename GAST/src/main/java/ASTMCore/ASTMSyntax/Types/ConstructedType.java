package ASTMCore.ASTMSyntax.Types;

/**
 * Esta clase modela el tipo de dato construido por ejemplo un arreglo.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class ConstructedType extends DataType {
/**
 * Indica el tipo de dato que es el elemento.
 */
   private TypeReference baseType;
   /**
    * Establece el tipo de dato.
    * @param value Tipo de dato.
    */
   public void setBaseType(TypeReference value) {
      this.baseType = value;
   }
   /**
    * Obtiene el tipo de dato del elemento.
    * @return El tipo de dato.
    */
   public TypeReference getBaseType() {
      return this.baseType;
   }
   
   }
