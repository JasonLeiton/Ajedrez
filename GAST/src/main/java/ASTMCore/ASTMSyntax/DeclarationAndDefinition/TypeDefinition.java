package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

/**
 * Esta clase modela el tipo de definición de un elemento.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class TypeDefinition extends DefintionObject {   
   
 /**
  * Nombre del tipo de definición del elemento.
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
    * Obtiene el nombre del tipo de definición.
    * @return Nombre de la definición.
    */
   public Name getTypeName() {
	   if (this.typeName==null) {
		   this.typeName=new Name();
	   }
      return this.typeName;
   }
   }
