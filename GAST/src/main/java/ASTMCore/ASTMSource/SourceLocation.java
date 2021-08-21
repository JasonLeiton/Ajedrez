package ASTMCore.ASTMSource;

import ASTMCore.visitor.GASTVisitor;

/**
 * Esta clase es para localizar elementos dentro del código fuente.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class SourceLocation extends GASTMSourceObject {
   private Integer startLine;
   
   public void setStartLine(Integer value) {
      this.startLine = value;
   }
   
   public Integer getStartLine() {
      return this.startLine;
   }
   
   private Integer startPosition;
   
   public void setStartPosition(Integer value) {
      this.startPosition = value;
   }
   
   public Integer getStartPosition() {
      return this.startPosition;
   }
   
   private Integer endLine;
   
   public void setEndLine(Integer value) {
      this.endLine = value;
   }
   
   public Integer getEndLine() {
      return this.endLine;
   }
   
   private Integer endPosition;
   
   public void setEndPosition(Integer value) {
      this.endPosition = value;
   }
   
   public Integer getEndPosition() {
      return this.endPosition;
   }
   
   private SourceFile inSourceFile;
   
   public void setInSourceFile(SourceFile value) {
      this.inSourceFile = value;
   }
   
   public SourceFile getInSourceFile() {
      return this.inSourceFile;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
