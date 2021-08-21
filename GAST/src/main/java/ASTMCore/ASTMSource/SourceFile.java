package ASTMCore.ASTMSource;

import ASTMCore.visitor.GASTVisitor;

/**
 * Esta clase modela un archivo de c�digo fuente.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class SourceFile extends GASTMSourceObject {
   /**
    * Indica la ubicaci�n del archivo.
    */
	private String path;
   /**
    * Se establece el valor de la ruta de localizaci�n.
    * @param value Ruta.
    */
   public void setPath(String value) {
      this.path = value;
   }
   /**
    * Se obtiene la localizaci�n del archivo.
    * @return La ruta.
    */
   public String getPath() {
      return this.path;
   }
@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
