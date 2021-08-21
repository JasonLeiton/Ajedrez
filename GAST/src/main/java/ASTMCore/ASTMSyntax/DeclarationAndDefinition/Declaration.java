package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.ASTMSyntax.Types.TypeReference;
/**
 * Esta clase modela la declaración  de un elemento.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class Declaration extends DeclarationOrDefinition {
	
	 
	/**
	 * Identificador del elemento.
	 */
   private Name identifierName;
   /**
    * Establdece el nombre del identificador.
    * @param value Nombre.
    */
   public void setIdentifierName(Name value) {
      this.identifierName = value;
   }
   /**
    * Obtiene el nombre del identificador.
    * @return Identificador.
    */
   public Name getIdentifierName() {
      return this.identifierName;
   }
   
   /**
    * Tipo de dato del elemento.
    */
   private TypeReference declarationType;
   /**
    * Establece el tipo de declaración.
    * @param value Tipo de declaración.
    */
   public void setDeclarationType(TypeReference value) {
      this.declarationType = value;
   }
   /**
    * Obtiene el tipo de declaración.
    * @return Tipo de dato.
    */
   public TypeReference getDeclarationType() {
      return this.declarationType;
   }
   

   private Definition defRef;
   
   public void setDefRef(Definition value) {
      this.defRef = value;
   }
   
   public Definition getDefRef() {
      return this.defRef;
   }
   
   }
