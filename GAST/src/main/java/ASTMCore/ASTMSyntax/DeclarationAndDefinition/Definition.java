package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

import ASTMCore.ASTMSyntax.Types.TypeReference;
/**
 * Esta clase modela la definición  de un elemento.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class Definition extends DeclarationOrDefinition {
 /**
  * Identificador del elemento.
  */
   private Name identifierName;
   /**
    * Establece el identificador de la definición.
    * @param value Indentificador.
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
 * Tipo de referencia que es la definición
 */
   private TypeReference definitionType;
   /**
    * Establece el tipo 
    * @param value Tipo
    */
   public void setDefinitionType(TypeReference value) {
      this.definitionType = value;
   }
   /**
    * Obtiene el tipo.
    * @return Tipo.
    */
   public TypeReference getDefinitionType() {
      return this.definitionType;
   }
   

   private Declaration ofDeclaration;
   
   public void setOfDeclaration(Declaration value) {
      this.ofDeclaration = value;
   }
   
   public Declaration getOfDeclaration() {
      return this.ofDeclaration;
   }
   
   }
