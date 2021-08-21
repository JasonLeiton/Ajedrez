package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.LITERAL;

public class Literal extends Expression {

   public Literal() {
      setTag(LITERAL);
   }

   private String value;
   
   public void setValue(String value) {
      this.value = value;
   }
   
   public String getValue() {
      return this.value;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
