package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Expression.Expression;
/**
 * Esta clase modela la definición de un dato.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class DataDefinition extends Definition {
   /**
    * Indica si es mutable.
    */
	private Boolean isMutable;
   /**
    * Establece el valor de si es mutable.
    * @param value Mutable
    */
   public void setIsMutable(Boolean value) {
      this.isMutable = value;
   }
   /**
    * Obtiene el valor si es mutuble.
    * @return valor de si es mutable.
    */
   public Boolean getIsMutable() {
      return this.isMutable;
   }
   /**
    * Indica el valor inicial, en caso de que exista.
    */
   private Expression initialValue;
   /**
    * Establece el valor inicial.
    * @param value Valor inicial.
    */
   public void setInitialValue(Expression value) {
      this.initialValue = value;
   }
   /**
    * Obtiene el valor inicial.
    * @return El valor inicial.
    */
   public Expression getInitialValue() {
      return this.initialValue;
   }
   }
