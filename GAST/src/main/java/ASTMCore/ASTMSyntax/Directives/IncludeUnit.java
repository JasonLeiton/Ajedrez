package ASTMCore.ASTMSyntax.Directives;

import ASTMCore.ASTMSource.SourceFileReference;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.INCLUDE_UNIT;

/**
 * Esta clase modela unidad para incluir archivos externos.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class IncludeUnit extends PreprocessorElement {

    public IncludeUnit() {
        setTag(INCLUDE_UNIT);
    }

    /**
  * Indica el archivo a incluir.
  */
   private SourceFileReference file;
   /**
    * Referencia del archivo.
    */
   private String reference;
   /**
    * Establece el archivo.
    * @param value Archivo.
    */
   public void setFile(SourceFileReference value) {
      this.file = value;
   }
   /**
    * Obtiene el archivo.
    * @return El archivo.
    */
   public SourceFileReference getFile() {
      return this.file;
   }
/**
 * Obtiene la referencia.
 * @return La referencia del archivo.
 */
    public String getReference() {
        return reference;
    }
/**
 * Establece la referencia del archivo.
 * @param reference Referencia del archivo.
 */
    public void setReference(String reference) {
        this.reference = reference;
    }
@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
