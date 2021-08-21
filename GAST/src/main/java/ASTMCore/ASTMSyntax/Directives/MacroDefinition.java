package ASTMCore.ASTMSyntax.Directives;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.MACRO_DEFINITION;

public class MacroDefinition extends PreprocessorElement {

   public MacroDefinition() {
      setTag(MACRO_DEFINITION);
   }

   private String macroName;
   
   public void setMacroName(String value) {
      this.macroName = value;
   }
   
   public String getMacroName() {
      return this.macroName;
   }
   
   private String body;
   
   public void setBody(String value) {
      this.body = value;
   }
   
   public String getBody() {
      return this.body;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
