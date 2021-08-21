package ASTMCore.ASTMSyntax.DeclarationAndDefinition;

/**
 * Esta clase modela la definición O declaración de un elemento en código.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public abstract class DeclarationOrDefinition extends DefintionObject {
   private String linkageSpecifier;
   
   public void setLinkageSpecifier(String value) {
      this.linkageSpecifier = value;
   }
   
   public String getLinkageSpecifier() {
      return this.linkageSpecifier;
   }
   
/**
 * Tipo de acceso al elemento
 */
   private AccessKind accessKind;
   /**
    * Establece el tipo de acceso a la declaración o definición.
    * @param value Tipo de acceso.
    */
   public void setAccessKind(AccessKind value) {
      this.accessKind = value;
   }
   /**
    * Obtiene el tipo de acceso.
    * @return el tipo de acceso.
    */
   public AccessKind getAccessKind() {
      return this.accessKind;
   }
   
 
   private StorageSpecification storageSpecifier;
   
   public void setStorageSpecifier(StorageSpecification value) {
      this.storageSpecifier = value;
   }
   
   public StorageSpecification getStorageSpecifier() {
      return this.storageSpecifier;
   }
   
   }
