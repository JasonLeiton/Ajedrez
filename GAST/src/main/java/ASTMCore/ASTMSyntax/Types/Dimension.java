package ASTMCore.ASTMSyntax.Types;

import ASTMCore.ASTMSyntax.MinorSyntaxObject;
import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DIMENSION;

public class Dimension extends MinorSyntaxObject {

   public Dimension() {
      setTag(DIMENSION);
   }

   private Expression dimension;
   
   public void setLowBound(Expression value) {
      this.dimension = value;
   }
   
   public Expression getLowBound() {
      return this.dimension;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
