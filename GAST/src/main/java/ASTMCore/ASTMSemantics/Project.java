package ASTMCore.ASTMSemantics;

import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import static ASTMCore.metadata.Tags.PROJECT_SCOPE;

/**
 * Se encarga de modelar un projecto y sus características.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class Project extends GASTMSemanticObject {

   private GlobalScope outerScope;

    public Project() {
        setTag(PROJECT_SCOPE);
    }

    public void setOuterScope(GlobalScope value) {
      this.outerScope = value;
   }
   
   public GlobalScope getOuterScope() {
      return this.outerScope;
   }
   
 /**
  * Conjunto de archivos fuente que conforman un proyecto informático.
  */
   private ArrayList<CompilationUnit> files;
   /**
    * Obtiene el conjunto de archivos que le pertenecen a un proyecto.
    * @return Lista de las clases pertenecientes al proyecto.
    */
   public ArrayList<CompilationUnit> getFiles() {
      if (this.files == null) {
         this.files = new ArrayList<CompilationUnit>();
      }
      return this.files;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
