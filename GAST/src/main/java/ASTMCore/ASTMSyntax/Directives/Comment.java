package ASTMCore.ASTMSyntax.Directives;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.COMMENT;

/**
 * Esta clase modela un comentario en el código fuente.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class Comment extends PreprocessorElement {

   public Comment() {
      setTag(COMMENT);
   }

   /**
    * Cuerpo del comentario.
    */
	private String body;
   /**
    * Establece el comentario.
    * @param value Comentario.
    */
   public void setBody(String value) {
      this.body = value;
   }
   /**
    * Obtiene el valor del comentario.
    * @return El comentario.
    */
   public String getBody() {
      return this.body;
   }
@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
