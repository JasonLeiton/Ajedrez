package ASTMCore.ASTMSource;

/**
 * Esta clase modela una referencia, la cual es un archivo externo, por ejemplo un .jar.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class SourceFileReference extends SourceFile {
  /**
   * Indica la localizaci�n del archivo. 
   */
   private SourceLocation locationInfo;
   /**
    * Establece la localizaci�n.
    * @param value La ubicaci�n.
    */
   public void setLocationInfo(SourceLocation value) {
      this.locationInfo = value;
   }
   /**
    * Obtiene la localizaci�n del archivo.
    * @return Localizaci�n de archivos.
    */
   public SourceLocation getLocationInfo() {
      return this.locationInfo;
   }
   

   private SourceFile ofSourceFile;
   
   public void setOfSourceFile(SourceFile value) {
      this.ofSourceFile = value;
   }
   
   public SourceFile getOfSourceFile() {
      return this.ofSourceFile;
   }
   
   }
