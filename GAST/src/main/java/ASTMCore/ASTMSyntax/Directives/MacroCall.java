package ASTMCore.ASTMSyntax.Directives;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.MACRO_CALL;

/**
 * Esta clase modela una macro llamada.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class MacroCall extends PreprocessorElement {

    public MacroCall() {
        setTag(MACRO_CALL);
    }

    /**
   * La referencia que llama
   */
   private MacroDefinition refersTo;
   /**
    * 
    * @param value Referencia a llamar.
    */
   public void setRefersTo(MacroDefinition value) {
      this.refersTo = value;
   }
   /**
    * 
    * @return Referencia a la cual se llama.
    */
   public MacroDefinition getRefersTo() {
      return this.refersTo;
   }
@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
