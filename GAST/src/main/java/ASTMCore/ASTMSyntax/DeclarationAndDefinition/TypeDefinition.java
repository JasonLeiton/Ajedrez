package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

/**
 * Esta clase modela el tipo de definici�n de un elemento.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class TypeDefinition extends DefintionObject {   
   
 /**
  * Nombre del tipo de definici�n del elemento.
  */
   private Name typeName;
   
   /**
    * Establece el nombre.
    * @param value Nombre del tipo.
    */
   public void setTypeName(Name value) {
      this.typeName = value;
   }
   /**
    * Obtiene el nombre del tipo de definici�n.
    * @return Nombre de la definici�n.
    */
   public Name getTypeName() {
	   if (this.typeName==null) {
		   this.typeName=new Name();
	   }
      return this.typeName;
   }
   }
